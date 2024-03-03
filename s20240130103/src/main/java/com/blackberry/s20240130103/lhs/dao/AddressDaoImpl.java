package com.blackberry.s20240130103.lhs.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.lhs.model.Address;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AddressDaoImpl implements AddressDao {
	
	private final SqlSession session;

	@Override
	public int addresschkcnt(Address address) {
		return 0;
	}

}
