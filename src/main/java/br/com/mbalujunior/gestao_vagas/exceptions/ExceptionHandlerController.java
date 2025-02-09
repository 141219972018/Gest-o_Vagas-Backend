package br.com.mbalujunior.gestao_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    
    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource message){
        this.messageSource=message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        //lista para a minha class ErrorMessage pegando em todos os erros 
        List<ErrorMessageDTO> dto= new ArrayList<>();
        //pegando(percorendo todos os erros) todos os erros para que sejam tratado
            e.getBindingResult().getFieldErrors().forEach(err -> {
                String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
        //Aqui vai pegar a mesagem que interessa para add no array
                ErrorMessageDTO error= new ErrorMessageDTO(message, err.getField());
                dto.add(error);
                
    });
    //Retorn 400 que corresponde a message de erro
    return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
}
}
