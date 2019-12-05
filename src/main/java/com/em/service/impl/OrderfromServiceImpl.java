package com.em.service.impl;

import com.em.mapper.AddressMapper;
import com.em.mapper.OrderfromMapper;
import com.em.service.CommentService;
import com.em.service.GoodsService;
import com.em.service.MerchantService;
import com.em.service.OrderfromService;
import com.em.vo.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MerchantService merchantService;

    @Override
    public List<Orderfrom> getListByShop(Integer page, Integer limit, Integer isSend, Integer isComment,Date startTime,Date endTime, Integer shopId) {
        OrderfromExample example = new OrderfromExample();
        //设置分页
        PageHelper.startPage(page,limit);
        //排序
        example.setOrderByClause("start_time desc");
        OrderfromExample.Criteria criteria = example.createCriteria();
        //判断是否送到
        if (isSend != -1) {
            criteria.andIsSendEqualTo(isSend);
        }
        //判断是否评论
        if (isComment != -1) {
            criteria.andIsCommentEqualTo(isComment);
        }
        criteria.andShopIdEqualTo(shopId);
        criteria.andStartTimeBetween(startTime,endTime);
        List<Orderfrom> orderfroms = mapper.selectByExample(example);
        setAddress(orderfroms);
        return orderfroms;
    }
    //设置商品,地址,评论
    private void setAddress(List<Orderfrom> orderfroms) {
        for (int i = 0; i < orderfroms.size(); i++) {
            //查询地址
            AddressExample address = new AddressExample();
            address.createCriteria().andIdEqualTo(Long.valueOf(orderfroms.get(i).getUserAddress()));
            List<Address> addresses = addressMapper.selectByExample(address);
            if (addresses.size()!=0) {
                orderfroms.get(i).setAddress(addresses.get(0));
            }
            //查询评论
            Comment comment = commentService.selectById(orderfroms.get(i).getIsClose());
            orderfroms.get(i).setComment(comment);
            //插入商品
            List<Goods> goodsList = goodsService.selectGoodsById(orderfroms.get(i).getGoods());
            orderfroms.get(i).setGoodsList(goodsList);
            //查询商家
            Merchant merchant = merchantService.selectById(orderfroms.get(i).getShopId());
            orderfroms.get(i).setMerchant(merchant);
        }
    }

    @Override
    public List<Orderfrom> listByUser(Integer userId,Integer page,Integer limit) {
        OrderfromExample example = new OrderfromExample();
        //排序
        example.setOrderByClause("start_time desc");
        PageHelper.startPage(page,limit);
        example.createCriteria().andUserIdEqualTo(userId);
        List<Orderfrom> orderfroms = mapper.selectByExample(example);
        setAddress(orderfroms);
        return orderfroms;
    }

    @Override
    public int addOrder(Orderfrom orderfrom) {
        int i = mapper.insert(orderfrom);
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
