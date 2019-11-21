package com.em.service;

import com.em.vo.Merchant;

import java.util.List;

public interface MerchantService {
    int addMerchant(Merchant merchant);

    //当前身份为管理员查询所有商家列表
    List<Merchant> selectMerchantList(int pageSize, int pageNum, String shopName);
    //当前身份为商家仅查询当前商家列表
    List<Merchant> selectMerchantListByType(int pageSize, int pageNum, String shopName, Integer type);

    int updateMerchant(Merchant merchant);

    int delMerchant(Merchant merchant);
}
