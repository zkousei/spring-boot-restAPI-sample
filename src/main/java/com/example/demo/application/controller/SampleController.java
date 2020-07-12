package com.example.demo.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.resource.SampleBody;
import com.example.demo.application.resource.SampleResponse;
import com.example.demo.domain.service.AuthorizationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SampleController {

	@Autowired
	private AuthorizationService authorizationService;

	/*
	 * Beanを戻り値に設定して、jsonレスポンスに変換するサンプル
	 */
	@PostMapping("/apisample/v1")
	@ResponseBody
	public SampleResponse restApiSample  (@RequestBody  @Validated SampleBody request)   {

		log.info("REST API のサンプル");

		log.info(request.getParam());

		return new SampleResponse(true,"hello");
	}

	/*
	 * ResponseEntityを戻り値に設定するサンプル
	 * httpステータス、ヘッダなどを設定できる。
	 * body部のデータ構造をラッパーして吸収できる。
	 */
	@PostMapping("/apisample/v2")
	@ResponseBody
	public ResponseEntity<Object> restApiSample2  (@RequestBody  @Validated SampleBody request)   {

		log.info("REST API のサンプル");

		log.info(request.getParam());

		return new ResponseEntity<>(new SampleResponse(true,"hello"),new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}


	/*
	 * トークン検証のサンプルエンドポイント
	 */
	@GetMapping("/authsample")
	@ResponseBody
	public SampleResponse authorizationSample (@RequestHeader("Authorization") String value) throws Exception {
		authorizationService.verifyToken(value, "test");

		return new SampleResponse(true,"authorized");
	}

}
