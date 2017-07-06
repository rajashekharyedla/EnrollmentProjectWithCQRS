package com.wavelabs.enrollment.service;

import com.wavelabs.enrollment.domain.User;

public interface SocialUserService {
	
	public String persistSocialUser(User user, String tokenid);

}
