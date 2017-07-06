package com.wavelabs.enrollment.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.wavelabs.enrollment.builder.DataBuilder;
import com.wavelabs.enrollment.controller.UserCommandContoller;
import com.wavelabs.enrollment.domain.LoginType;
import com.wavelabs.enrollment.domain.Status;
import com.wavelabs.enrollment.domain.User;
import com.wavelabs.enrollment.repository.UserRepository;
import com.wavelabs.enrollment.service.SocialUserService;
import com.wavelabs.enrollment.service.UserFlowService;

@RunWith(MockitoJUnitRunner.class)
public class UserCommandControllerTest {

	@InjectMocks
	private UserCommandContoller facebookUserResource;

	@Mock
	private SocialUserService socialUserService;
	@InjectMocks
	private UserCommandContoller googleUserResource;

	/*
	 * @Mock private SocialUserService socialUserService;
	 */

	@InjectMocks
	UserCommandContoller userLoginResource;
	@Mock
	UserCommandContoller userRepo;
	@Mock
	User user;
	@InjectMocks
	UserCommandContoller userResource;
	// @Mock
	// UserRepository userRepo;
	// @Mock
	// User user;
	@Mock
	UserFlowService userFlowService;
	@Mock
	private List<User> mockArrayList;

	@Test
	public void testFbUser() {

		when(socialUserService.persistSocialUser(any(User.class), anyString())).thenReturn("852566565255");
		facebookUserResource.addfbUser(DataBuilder.getUser(), "852566565255");
		verify(socialUserService, times(1)).persistSocialUser(any(User.class), anyString());

	}

	@Test
	public void testGoogleUser() {

		when(socialUserService.persistSocialUser(any(User.class), anyString())).thenReturn("852566565255");
		googleUserResource.addGmailUser(DataBuilder.getUser(), "a");
		verify(socialUserService, times(1)).persistSocialUser(any(User.class), anyString());

	}

	/*@Test
	public void testLoginValidation() {
		when(userRepo.findByEmailAndPassword(anyString(), anyString())).thenReturn(DataBuilder.getUser());
		ResponseEntity en = userLoginResource.credentials("fds", "sddddd");
		Assert.assertEquals(200, en.getStatusCodeValue());

	}

	@Test
	public void testLoginValidationFail() {
		when(userRepo.findByEmailAndPassword(anyString(), anyString())).thenReturn(null);
		ResponseEntity en = userLoginResource.credentials("fds", "sddddd");
		Assert.assertEquals(401, en.getStatusCodeValue());

	}

	@Test
	public void testfindByEmail() {
		when(userRepo.findByEmail(anyString())).thenReturn(DataBuilder.getUser());
		ResponseEntity en = userResource.findByEmail("ddd@gmail.com");
		Assert.assertEquals(200, en.getStatusCodeValue());

	}

	@Test
	public void testfindByEmailFail() {
		when(userRepo.findByEmailAndPassword(anyString(), anyString())).thenReturn(null);
		ResponseEntity en = userResource.findByEmail("fds");
		Assert.assertEquals(401, en.getStatusCodeValue());

	}

	@Test
	public void testNormalUser() {
		User user22 = DataBuilder.getUser();
		user22.setStatus(Status.ONE);
		user22.setLoginType(LoginType.NORMALUSER);
		when(userRepo.save(any(User.class))).thenReturn(mock(User.class));
		userResource.normaluser(user22);
		verify(userRepo, times(0)).save(any(User.class));
	}

	@Test
	public void testGetAllUsers() {
		mockArrayList.add(user);
		// Mockito.verify(mockArrayList).add("one");
		assertEquals(0, mockArrayList.size());

	}*/
}
