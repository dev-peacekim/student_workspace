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
		System.out.println("AddressDaoImpl addresschkcnt address : " + address);
		int result = session.selectOne("lhsAddresschkcnt", address);
		System.out.println("AddressDaoImpl addresschkcnt result : " + result);
		return result;
	}
	
	@Override
	public int addressAdd(Address address) {
		System.out.println("AddressDaoImpl addressAdd address : " + address);
		int result = session.insert("lhsAddressAdd", address);
		return result;
	}

}
