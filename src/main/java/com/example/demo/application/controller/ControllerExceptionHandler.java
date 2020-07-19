package com.example.demo.application.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.application.resource.ErrorResponse;
import com.example.demo.domain.exception.ExpiredAccessTokenException;
import com.example.demo.domain.exception.InvalidAccessTokenException;

import lombok.extern.slf4j.Slf4j;

/*
 * https://qiita.com/YutaKase6/items/434d36901e3a3360e9b0
 * 実装参考
 */

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


	// 全てのハンドリングの共通処理をするためオーバーライド
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request){
		if (!(body instanceof ErrorResponse)) {
			body = new ErrorResponse(false,status.getReasonPhrase(),"");
		}
		return new ResponseEntity<>(body,headers,status);

	}

	// springbootで用意されている例外に関しては、例外ごとに専用のメソッドが用意されているため、
	// それをoverrideする
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){

		//バリデーション失敗のフィールドリスト
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

		StringBuilder errorDetail = new StringBuilder();
		fieldErrors.forEach(fieldError ->
				errorDetail.append(fieldError.getField())
				.append(": ")
				.append(fieldError.getDefaultMessage())
				.append("; ")
				);

		log.error(errorDetail.toString());

		ErrorResponse body = new ErrorResponse(false, "Bad Request","9000");
		return this.handleExceptionInternal(ex, body, headers, status, request);

	}

	@ExceptionHandler({InvalidAccessTokenException.class})
	public ResponseEntity<Object> handleTokenExpired(InvalidAccessTokenException ex, WebRequest request){
		HttpHeaders headers = new HttpHeaders();
		ErrorResponse body = new ErrorResponse(false, ex.getMessage(), "9004");
		HttpStatus status = HttpStatus.FORBIDDEN;

		return this.handleExceptionInternal(ex, body, headers, status, request);

	}



	@ExceptionHandler({ExpiredAccessTokenException.class})
	public ResponseEntity<Object> handleTokenExpired(ExpiredAccessTokenException ex, WebRequest request){
		HttpHeaders headers = new HttpHeaders();
		ErrorResponse body = new ErrorResponse(false, ex.getMessage(), "9003");
		HttpStatus status = HttpStatus.FORBIDDEN;

		return this.handleExceptionInternal(ex, body, headers, status, request);

	}


	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request){
		HttpHeaders headers = new HttpHeaders();
		ErrorResponse body = new ErrorResponse(false, "Internal Server Error", "9999");
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		return this.handleExceptionInternal(ex, body, headers, status, request);


	}


}
