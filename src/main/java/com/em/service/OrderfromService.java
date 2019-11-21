package com.em.service;

import com.em.vo.Orderfrom;

import java.util.List;

public interface OrderfromService {
    List<Orderfrom> getListByShop(Integer page, Integer limit, Integer isSend, Integer isComment, Integer isPay, Integer isClose, Integer shopId);

    List<Orderfrom> listByUser(Integer userId);

    int addOrder(Orderfrom orderfrom);

    int updateOrder(Orderfrom orderfrom);
}
