package aluracursos.foro.hub.domain.topicos;

import java.time.LocalDateTime;

public record DatosListaTopicos(

        Long id,
        String usuario,
        String titulo,
        String mensaje,
        Curso curso,
        LocalDateTime fecha,
        Boolean activo
) {
    public DatosListaTopicos(Topico topico){
        this(topico.getId(), topico.getUsuario(), topico.getTitulo(), topico.getMensaje(),topico.getCurso(), topico.getFecha(), topico.getActivo());
    }

}
