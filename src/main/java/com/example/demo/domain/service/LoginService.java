package com.example.demo.domain.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.object.User;
import com.example.demo.domain.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	public User findUser(String id) {

		Map<String,Object> map = null;
		try {
			map = loginRepository.findUser(id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		String userId = (String)map.get("user_id");
		String password = (String)map.get("password");
		String authority = (String)map.get("authority");

		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		user.setAuthority(authority);

		return user;
	}

	public String createToken(String id, String authority) {

		String token = RandomStringUtils.randomAlphanumeric(40);
		//TODO　引数で受け取ったユーザ権限に応じてscopeを決定
		String scope = "test";

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("JST"));
		calendar.add(Calendar.HOUR_OF_DAY, 24);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String expiredAt = sdf.format(calendar.getTime());

		loginRepository.insertToken(id, token, scope, expiredAt);

		return token;
	}


}
