package aluracursos.foro.hub.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String usuario,
        String titulo,
        String mensaje,
        Curso curso
) {
}
