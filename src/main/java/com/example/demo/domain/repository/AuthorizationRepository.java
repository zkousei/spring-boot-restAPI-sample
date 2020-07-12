package com.example.demo.domain.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorizationRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, Object> findToken(String accessToken){
		StringBuilder query = new StringBuilder();
		query.append("SELECT ").
			append("* ").
			append("FROM token_table ").
			append("WHERE access_token=?");

		Map<String, Object> token = jdbcTemplate.queryForMap(query.toString(),accessToken);

		return token;
	}

}
