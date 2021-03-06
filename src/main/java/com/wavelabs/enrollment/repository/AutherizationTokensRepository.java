package com.wavelabs.enrollment.repository;

import org.springframework.data.repository.CrudRepository;

import com.wavelabs.enrollment.domain.AutherizationTokens;

public interface AutherizationTokensRepository extends CrudRepository<AutherizationTokens, Integer> {

	AutherizationTokens findByTokenId(String tokenid);

}
