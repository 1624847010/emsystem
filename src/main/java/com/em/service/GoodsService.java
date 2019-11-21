package com.em.service;

import com.em.vo.Goods;

import java.util.List;

public interface GoodsService {
    int addGoods(Goods goods);

    int delGoods(Goods goods);

    int updateGoods(Goods goods);

    List<Goods> selectGoods(int pageSize, int pageNum, String goodsName);

    //根据分类查询goodsList
    List<Goods> selectGoodsByType(int shopId,int typeId);
}
