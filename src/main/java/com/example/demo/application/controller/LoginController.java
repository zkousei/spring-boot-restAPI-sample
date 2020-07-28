package com.example.demo.application.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.resource.ErrorResponse;
import com.example.demo.application.resource.LoginBody;
import com.example.demo.application.resource.LoginResponse;
import com.example.demo.domain.object.User;
import com.example.demo.domain.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {

	@Autowired
	private LoginService loginService;

	/**
	 * ログイン用のコントローラ
	 * 処理結果と、トークンを返却
	*/

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<Object> getLogin(@RequestBody @Validated LoginBody request) throws Exception {
		log.info("ログイン処理開始");
		// log.info(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));

		//リクエストのIDからユーザ情報を取得
		User user = loginService.findUser(request.getId());
		//ユーザがDB上に存在しない場合は、エラーレスポンスを返却する
		if (user == null) {
			return new ResponseEntity<> (new ErrorResponse(false,"ユーザが存在しません","9001"),
					new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}

		//パスワードを検証して、トークンを発行
		//パスワードが不正の場合は、エラーレスポンスを返却
		if(BCrypt.checkpw(request.getPassword(), user.getPassword())) {
			String token = loginService.createToken(user.getUserNo(), user.getAuthority());
			return new ResponseEntity<>(new LoginResponse(true, token),
					new HttpHeaders(),HttpStatus.OK);
		} else {
			return new ResponseEntity<> (new ErrorResponse(false,"パスワードが不正です","9002"),
					new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}

	}




}
