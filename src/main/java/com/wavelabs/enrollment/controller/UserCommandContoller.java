package com.wavelabs.enrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavelabs.enrollment.domain.LoginType;
import com.wavelabs.enrollment.domain.Status;
import com.wavelabs.enrollment.domain.User;
import com.wavelabs.enrollment.repository.UserRepository;
import com.wavelabs.enrollment.service.AddressSourceService;
import com.wavelabs.enrollment.service.SocialUserService;
import com.wavelabs.enrollment.service.UserFlowService;

@RestController
@Component
/**
 * CommandController class is for separating commands and Queries. The Commands
 * are for Post Operations. User CommandController is for all the Post
 * operations in this.
 * 
 * @author rajashekhary
 *
 */
public class UserCommandContoller {
	@Autowired
	AddressSourceService addressSourceService;
	@Autowired
	SocialUserService socialUserService;
	@Autowired
	UserFlowService userFlowService;
	
	/*@Autowired
	CommandService commandService;*/
	@Autowired
	UserRepository userRepo;
	

	@RequestMapping(value = "/usercreate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String normaluser(@RequestBody User user) {
		user.setLoginType(LoginType.NORMALUSER);
		return userFlowService.normaluser(user);
	}

	@RequestMapping(value = "/byemail", method = RequestMethod.POST)
	public ResponseEntity<?> findByEmail(@RequestParam("email") String email) {
	/*	User member = commandService.findByEmail(email);*/
		
		User member = userRepo.findByEmail(email);
		if (member != null) {
			return ResponseEntity.status(HttpStatus.OK).body(member);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User doesnot exists");
	}

	@RequestMapping(value = "/gmail/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)

	public void addGmailUser(@RequestBody User user, @PathVariable("id") String tokenid) {
		user.setLoginType(LoginType.GOOGLE);
		user.setStatus(Status.ONE);
		socialUserService.persistSocialUser(user, tokenid);
	}

	@RequestMapping(value = "/fb/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addfbUser(@RequestBody User user, @PathVariable("id") String tokenid) {
		user.setLoginType(LoginType.FACEBOOK);
		user.setStatus(Status.ONE);
		socialUserService.persistSocialUser(user, tokenid);
	}
}
