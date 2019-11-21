package com.em.service.impl;

import com.em.mapper.TypeMapper;
import com.em.service.GoodsService;
import com.em.service.TypeService;
import com.em.vo.Goods;
import com.em.vo.Type;
import com.em.vo.TypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/18
 * @Time 15:14
 **/
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private GoodsService goodsService;

    @Override
    public int addType(Type type) {
        int i = typeMapper.insertSelective(type);
        return i;
    }

    @Override
    public int delType(Type type) {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andIdEqualTo(type.getId());
        int i = typeMapper.deleteByExample(typeExample);
        return i;
    }

    @Override
    public int updateType(Type type) {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andIdEqualTo(type.getId());
        int i = typeMapper.updateByExampleSelective(type, typeExample);
        return i;
    }

    @Override
    public List<Type> selectShopType() {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andTypeEqualTo(0);
        List<Type> list = typeMapper.selectByExample(typeExample);
        return list;
    }

    @Override
    public List<Type> selectGoodsType(int shopId) {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andTypeEqualTo(1).andShopIdEqualTo(shopId);
        List<Type> list = typeMapper.selectByExample(typeExample);
        for (int i = 0; i < list.size(); i++) {
            List<Goods> goodsList = goodsService.selectGoodsByType(shopId, list.get(i).getId());
            list.get(i).setGoodsList(goodsList);
        }
        return list;
    }
}
