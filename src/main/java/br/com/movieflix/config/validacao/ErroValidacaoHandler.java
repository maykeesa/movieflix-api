package br.com.movieflix.config.validacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroValidacaoHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
		LocalDateTime timeStamp = LocalDateTime.now();
		
		//Lista que será retornada 
		List<ErroDeFormularioDto> errorDto = new ArrayList<>();
		
		//Lista dos erros, de acordo com a Exception passada no parâmetro
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		for(FieldError fe: fieldErrors) {
			String mensagem = this.messageSource.getMessage(fe, LocaleContextHolder.getLocale());
			ErroDeFormularioDto erro = new ErroDeFormularioDto((long) 400 ,fe.getField(), mensagem, timeStamp);
			errorDto.add(erro);
		}
		return errorDto;
	}
}
