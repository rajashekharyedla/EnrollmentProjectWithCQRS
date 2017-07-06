package com.wavelabs.enrollment.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavelabs.enrollment.domain.AutherizationTokens;
import com.wavelabs.enrollment.domain.User;
import com.wavelabs.enrollment.repository.AutherizationTokensRepository;
import com.wavelabs.enrollment.repository.UserRepository;
import com.wavelabs.enrollment.service.SocialUserService;
@Service
public class SocialUserServiceImpl implements SocialUserService {
	@Autowired
	UserRepository userrepo;

	@Autowired
	AutherizationTokensRepository authtokenrepo;
	/*@Autowired
	QueryService queryService;
	*/
	

	public String persistSocialUser(User user, String tokenid) {
		AutherizationTokens token = new AutherizationTokens();
		authtokenrepo.findByTokenId(tokenid);
		if (authtokenrepo.findByTokenId(tokenid) != null) {
			return "WELCOME";

		} else {
			token.setTokenId(tokenid);
			authtokenrepo.save(token);
			user.setTokens(token);
			userrepo.save(user);
		}
		return "registered";

	}

}
