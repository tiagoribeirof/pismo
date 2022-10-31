package com.pismo.pismotest.errorhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pismo.pismotest.dto.ResponseDTO;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { org.postgresql.util.PSQLException.class })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "409", description = "Tryed to insert duplicated values", content = {
					@Content(mediaType = "application/json") }) })
	protected ResponseEntity<Object> handleDataBaseConflict(RuntimeException ex, WebRequest request) {
		return handleExceptionInternal(ex,
				ResponseDTO.builder().status(HttpStatus.CONFLICT.value()).value(null).build(), new HttpHeaders(),
				HttpStatus.OK, request);
	}

	@ExceptionHandler(value = { org.springframework.dao.DataIntegrityViolationException.class })
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Illegal argument", content = {
			@Content(mediaType = "application/json") }) })
	protected ResponseEntity<Object> handleNullValueBadRequest(RuntimeException ex, WebRequest request) {
		return handleExceptionInternal(ex,
				ResponseDTO.builder().status(HttpStatus.BAD_REQUEST.value()).value(null).build(), new HttpHeaders(),
				HttpStatus.OK, request);
	}

}
