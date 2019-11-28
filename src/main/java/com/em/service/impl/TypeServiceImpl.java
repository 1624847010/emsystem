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
import org.springframework.transaction.annotation.Transactional;

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
    private static final Integer CLASSIFICATIONTYPE = 7;

    @Override
    public int addType(Type type) {
        int i = typeMapper.insertSelective(type);
        if (type.getType() == 0 && type.getShopId() == 0) {
            File file = new File();
            file.setBusinessId(type.getId());
            file.setBusinessType(CLASSIFICATIONTYPE);
            file.setFileUrl(type.getFile().getFileUrl());
            file.setFileId(type.getFile().getFileId());
            fileService.saveFile(file);
        }
        return i;
    }

    @Transactional
    @Override
    public int delType(Type type) {
        if (type.getShopId() == 0) {
            int count = merchantService.selectCount(type.getId());
            if (count >= 1) {
                return 2;
            }
        }else {
            int count = goodsService.selectCount(type.getId());
            if (count >= 1) {
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
        return i;
    }

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
}
