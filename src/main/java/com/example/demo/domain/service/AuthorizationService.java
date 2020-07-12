package com.example.demo.domain.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exception.ExpiredAccessTokenException;
import com.example.demo.domain.exception.InvalidAccessTokenException;
import com.example.demo.domain.repository.AuthorizationRepository;

@Service
public class AuthorizationService {

	@Autowired
	private AuthorizationRepository authorizationRepository;

	public void verifyToken(String accessToken, String scope) throws Exception {

		Map<String, Object> map = null;
		try {
			map = authorizationRepository.findToken(accessToken);
		} catch (EmptyResultDataAccessException e) {
			throw new InvalidAccessTokenException("不正なアクセストークンです");
		}

		Timestamp timestamp = (Timestamp) map.get("expired_at");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Calendar expiredAt = Calendar.getInstance();
		expiredAt.setTime(sdf.parse(timestamp.toString()));

		Calendar now = Calendar.getInstance();

		if(expiredAt.before(now)) {
			throw new ExpiredAccessTokenException("期限切れのアクセストークンです");
		}



	}


}
