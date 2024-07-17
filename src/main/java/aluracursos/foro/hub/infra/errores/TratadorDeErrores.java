package aluracursos.foro.hub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//@RestControllerAdvicepara el manejo de excepciones
@RestControllerAdvice
public class TratadorDeErrores {
    //@ExceptionHandler metodo de tratado de exepciones especificas en este caso tratamos "EntityNotFoundException"
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
            //notFound()es el que regresa el codigo 404
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity tratarError400(MethodArgumentNotValidException e){

        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        // badRequest() corespode al codigo 400
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionDeIntegridad.class)

    public ResponseEntity errorHandlerValidacionersDeIntegridad(Exception e){

            return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)

    public ResponseEntity errorHandlerValidacionersDeNegocio(Exception e){

        return ResponseEntity.badRequest().body(e.getMessage());
    }
    // DTO con los campos que retornaremos al cliente sobro los campos que faltaron
    private record  DatosErrorValidacion(
            String campo,
            String error
    ){   //constructor del DTO
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());

        }
    }
}
