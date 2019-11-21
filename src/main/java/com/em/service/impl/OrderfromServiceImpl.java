package com.em.service.impl;

import com.em.mapper.OrderfromMapper;
import com.em.service.OrderfromService;
import com.em.vo.Orderfrom;
import com.em.vo.OrderfromExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/21
 * @Time 14:36
 **/
@Service
public class OrderfromServiceImpl implements OrderfromService {
    @Autowired
    private OrderfromMapper mapper;

    @Override
    public List<Orderfrom> getListByShop(Integer page, Integer limit, Integer isSend, Integer isComment, Integer isPay, Integer isClose, Integer shopId) {
        OrderfromExample example = new OrderfromExample();
        example.setPage(page);
        example.setLimit(limit);
        example.createCriteria().andIsSendEqualTo(isSend).andIsCommentEqualTo(isComment).andIsPayEqualTo(isPay).andIsCloseEqualTo(isClose).andShopIdEqualTo(shopId);
        List<Orderfrom> orderfroms = mapper.selectByExample(example);
        return orderfroms;
    }

    @Override
    public List<Orderfrom> listByUser(Integer userId) {
        OrderfromExample example = new OrderfromExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<Orderfrom> orderfroms = mapper.selectByExample(example);
        return orderfroms;
    }

    @Override
    public int addOrder(Orderfrom orderfrom) {
        int i = mapper.insertSelective(orderfrom);
        return i;
    }

    @Override
    public int updateOrder(Orderfrom orderfrom) {
        OrderfromExample example = new OrderfromExample();
        example.createCriteria().andIdEqualTo(orderfrom.getId());
        int i = mapper.updateByExampleSelective(orderfrom,example);
        return i;
    }
}
