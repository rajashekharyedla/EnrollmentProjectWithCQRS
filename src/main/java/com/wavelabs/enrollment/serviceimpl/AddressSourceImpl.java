package com.wavelabs.enrollment.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavelabs.enrollment.domain.Address;
import com.wavelabs.enrollment.domain.User;
import com.wavelabs.enrollment.repository.AddressRepository;
import com.wavelabs.enrollment.service.AddressSourceService;

@Service
public class AddressSourceImpl implements AddressSourceService {

//	static Logger logger = Logger.getLogger(AddressSource.class);

	@Autowired
	GoogleGeoCodeServiceImpl googleGeoCode;
	@Autowired
	AddressRepository addressrepo;

	public Address persistAddress(User user) {
		Address address = user.getAddress();
		String zipcode = address.getZipCode();
		String[] result = googleGeoCode.getLatitudeLongitude(zipcode);
		address.setLatitude(result[0]);
		address.setLongitude(result[1]);
		addressrepo.save(address);
		return address;
	}

}
