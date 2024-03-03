package com.blackberry.s20240130103.lhs.service;

import org.springframework.stereotype.Service;

import com.blackberry.s20240130103.lhs.dao.AddressDao;
import com.blackberry.s20240130103.lhs.model.Address;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

	private final AddressDao addressDao;
	
	@Override
	public int addresschkcnt(Address address) {
		int result = addressDao.addresschkcnt(address);
		return result;
	}
	
	@Override
	public int addressAdd(Address address) {
		int result = addressDao.addressAdd(address);
		return result;
	}

}
