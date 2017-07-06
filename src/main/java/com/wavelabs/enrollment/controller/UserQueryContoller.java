package com.wavelabs.enrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavelabs.enrollment.domain.User;
import com.wavelabs.enrollment.repository.UserRepository;
import com.wavelabs.enrollment.service.UserFlowService;

@RestController
@Component
public class UserQueryContoller {
	@Autowired
	UserRepository userrepo;
	@Autowired
	UserFlowService userFlow;
	/*@Autowired
	QueryService queryService;
*/
	/**
	 * This is getting all the employees available in the database.
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<User> getAll() {
		return (List<User>) userrepo.findAll();
	}

	@RequestMapping(value = "/userlogin", method = RequestMethod.GET)

	public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password) {
		User member = userrepo.findByEmailAndPassword(email, password);
		if (member != null) {
			return ResponseEntity.status(HttpStatus.OK).body(member);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Invalid details Please check your Email and Password");
		}
	}

}
