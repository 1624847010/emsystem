package com.em.service;

import com.em.vo.Goods;

import java.util.List;

public interface GoodsService {
    int addGoods(Goods goods);

//    int delGoods(Goods goods);

    int updateGoods(Goods goods);

    List<Goods> selectGoods(int pageSize, int pageNum, String goodsName,Integer shopId,Integer type);

    //根据分类查询goodsList
    List<Goods> selectGoodsByType(int shopId,int typeId);

    //根据json查询商品与数量
    List<Goods> selectGoodsById(String goods);
    //查询销售数量
    Integer selectSaleCount(Long id);

    int selectCount(Integer id);
    //根据商品id查询商品信息
    List<Goods> selectGoodsInfo(Integer id);
}
