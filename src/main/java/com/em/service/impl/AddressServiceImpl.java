package com.em.service.impl;

import com.em.mapper.AddressMapper;
import com.em.service.AddressService;
import com.em.vo.Address;
import com.em.vo.AddressExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/21
 * @Time 9:02
 **/
@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressMapper addressMapper;
    /**
    　　* @description: 查询
    　　* @author ll
    　　* @date 2019/12/4 10:10
    　　*/
    @Override
    public List<Address> getAddressList(Integer id) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(id);
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        return addresses;
    }
    /**
    　　* @description: 增
    　　* @author ll
    　　* @date 2019/12/4 10:10
    　　*/
    @Override
    public int addAddress(Address address) {
        int count = addressMapper.insertSelective(address);
        return count;
    }
    /**
    　　* @description: 改
    　　* @author ll
    　　* @date 2019/12/4 10:10
    　　*/
    @Override
    public int updateAddress(Address address) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andIdEqualTo(address.getId());
        int count = addressMapper.updateByExampleSelective(address,addressExample);
        return count;
    }
    /**
    　　* @description: 删
    　　* @author ll
    　　* @date 2019/12/4 10:10
    　　*/
    @Override
    public int deleteAddress(Integer id) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andIdEqualTo(Long.valueOf(id));
        int i = addressMapper.deleteByExample(addressExample);
        return i;
    }
}
