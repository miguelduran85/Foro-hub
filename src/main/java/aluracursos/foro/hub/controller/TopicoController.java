package aluracursos.foro.hub.controller;

import aluracursos.foro.hub.domain.topicos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    //metodo post que agregar los tipicos a la base de datos

    @PostMapping
    @Operation(summary = "Registra un nuevo medico en la base de datos")
    public ResponseEntity<DatosRespuestaTopico> registraTopico(@RequestBody @Valid
                                                                   DatosAgregarTopico datosAgregarTopico, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRepository.save(new Topico(datosAgregarTopico));
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getUsuario(), topico.getTitulo(),
                topico.getMensaje(), topico.getCurso(), topico.getFecha(), topico.getActivo());

        return ResponseEntity.created(url).body(datosRespuestaTopico);

    }

    //  mostrar lista de topicos
    @GetMapping
    @Operation(summary = "Obtiene el listado de los topicos")

    public ResponseEntity<Page<DatosListaTopicos>> listadoTopicos(@PageableDefault(size= 10,sort = "fecha") Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(DatosListaTopicos::new));
    }

    // mostrar topicos por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene los topicos con ID")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosMedico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datostopico = new DatosRespuestaTopico(topico.getId(), topico.getUsuario(),topico.getTitulo(),topico.getMensaje(),topico.getCurso(),
                                                        topico.getFecha(), topico.getActivo());
        return ResponseEntity.ok(datostopico);
    }

    // actualizar topicos
    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza los topicos")
    public ResponseEntity<DatosRespuestaTopico> actualizarPaciente(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getUsuario(), topico.getTitulo(), topico.getMensaje(), topico.getCurso(),
                topico.getFecha(), topico.getActivo()));
    }

    //eliminar los topicos
    @DeleteMapping
    @Transactional
    @Operation(summary = "eliminar topicos del foro")
    public ResponseEntity eliminartopico(@PathVariable Long id){
        Optional topico = topicoRepository.deleteAllById(id);
        return ResponseEntity.noContent().build();
    }


}


