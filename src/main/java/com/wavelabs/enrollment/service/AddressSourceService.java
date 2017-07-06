package com.wavelabs.enrollment.service;

import com.wavelabs.enrollment.domain.Address;
import com.wavelabs.enrollment.domain.User;

public interface AddressSourceService {

	public Address persistAddress(User user);
}
