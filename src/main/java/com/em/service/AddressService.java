package com.em.service;

import com.em.vo.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAddressList(Integer id);

    int addAddress(Address address);

    int updateAddress(Address address);

    int deleteAddress(Integer id);
}
