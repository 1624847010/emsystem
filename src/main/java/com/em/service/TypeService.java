package com.em.service;

import com.em.vo.Type;

import java.util.List;

public interface TypeService {
    int addType(Type type);

    int delType(Type type);

    int updateType(Type type);

    List<Type> selectShopType();

    List<Type> selectGoodsType(int shopId);

    int selectCount(Long id);

    String selectTypeName(Integer id);
}
