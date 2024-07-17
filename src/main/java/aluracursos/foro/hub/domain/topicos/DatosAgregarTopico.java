package aluracursos.foro.hub.domain.topicos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosAgregarTopico(

        Long id,
        @NotBlank
        String usuario,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Curso curso
        ) {
}
