package com.example.demo.domain.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String,Object> findUser(String id){

		StringBuilder query = new StringBuilder();
		query.append("SELECT ")
			.append("* ")
			.append("FROM user_table ")
			.append("WHERE user_id=?");
		Map<String,Object> user = jdbcTemplate.queryForMap(query.toString(),id);

		return user;

	}

	public void insertToken(String id, String accessToken, String scope, String expiredAt) {

		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ")
			.append("token_table (user_id, access_token, scope, expired_at) ")
			.append("VALUES(?, ?, ?, ?)");

		jdbcTemplate.update(query.toString(),id, accessToken, scope, expiredAt);

	}

}
