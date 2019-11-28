package com.em.service;

import com.em.vo.Orderfrom;

import java.util.Date;
import java.util.List;

public interface OrderfromService {
    List<Orderfrom> getListByShop(Integer page, Integer limit, Integer isComment, Integer isPay,Date startTime,Date endTime, Integer shopId);

    List<Orderfrom> listByUser(Integer userId,Integer page,Integer limit);

    int addOrder(Orderfrom orderfrom);

    int updateOrder(Orderfrom orderfrom);
}
