package com.em.service.impl;

import com.em.mapper.TypeMapper;
import com.em.service.FileService;
import com.em.service.GoodsService;
import com.em.service.MerchantService;
import com.em.service.TypeService;
import com.em.vo.File;
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
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private FileService fileService;
    //分类图片
    private static final Integer CLASSIFICATIONTYPE = 7;

    @Override
    public int addType(Type type) {
        int i = typeMapper.insertSelective(type);
        //插入商家分类则带图片
        if (type.getType() == 0 && type.getShopId() == 0) {
            insertImg(type);
        }
        return i;
    }
    //插入图片
    private void insertImg(Type type) {
        if (type.getFile()!=null) {
            File file = new File();
            file.setBusinessId(type.getId());
            file.setBusinessType(CLASSIFICATIONTYPE);
            file.setFileUrl(type.getFile().getFileUrl());
            file.setFileId(type.getFile().getFileId());
            fileService.saveFile(file);
        }
    }

    @Override
    public int delType(Type type) {
        //分类下是否有商家，有则无法删除
        if (type.getShopId() == 0) {
            int count = merchantService.selectCount(type.getId());
            if (count >= 1) {
                return 2;
            }
        }else {
            //分类下是否有商品，有则无法删除
            int count = goodsService.selectCount(type.getId());
            if (count > 0) {
                return 3;
            }
        }
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
        insertImg(type);
        return i;
    }

    //商家分类
    @Override
    public List<Type> selectShopType() {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andTypeEqualTo(0);
        List<Type> list = typeMapper.selectByExample(typeExample);
        for (int i = 0; i < list.size(); i++) {
            File file = new File();
            file.setBusinessType(CLASSIFICATIONTYPE);
            file.setBusinessId(list.get(i).getId());
            List<File> files = fileService.selectFile(file);
            if (files.size()!=0) {
                list.get(i).setFile(files.get(0));
            }
        }
        return list;
    }

    //商品分类
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

    @Override
    public int selectCount(Long id) {
        TypeExample example = new TypeExample();
        example.createCriteria().andShopIdEqualTo(Math.toIntExact(id));
        long count = typeMapper.countByExample(example);
        return Math.toIntExact(count);
    }

    //根据id查询分类名称
    @Override
    public String selectTypeName(Integer id) {
        TypeExample example = new TypeExample();
        example.createCriteria().andIdEqualTo(id);
        List<Type> list = typeMapper.selectByExample(example);
        String typeName = list.get(0).getTypeName();
        return typeName;
    }
}
