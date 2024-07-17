package aluracursos.foro.hub.domain.topicos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String usuario,
        String titulo,
        String mensaje,
        Curso curso,
        LocalDateTime fecha,
        Boolean activo
) {
}
