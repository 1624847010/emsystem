package com.em.service.impl;

import com.em.mapper.GoodsMapper;
import com.em.service.FileService;
import com.em.service.GoodsService;
import com.em.vo.File;
import com.em.vo.Goods;
import com.em.vo.GoodsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/18
 * @Time 17:37
 **/
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private FileService fileService;
    //商品业务id
    private Integer goodsIcon = 3;

    //插入商品
    @Override
    public int addGoods(Goods goods) {
        int insert = goodsMapper.insertSelective(goods);
        insertIcon(goods);
        return insert;
    }
    //插入商品图片
    private void insertIcon(Goods goods) {
        if (goods.getGoodsImg() != null) {
            //插入商品图片
            File file = new File();
            file.setFileUrl(goods.getGoodsImg().getFileUrl());
            file.setFileId(goods.getGoodsImg().getFileId());
            file.setBusinessType(goodsIcon);
            file.setBusinessId(Math.toIntExact(goods.getId()));
            fileService.saveFile(file);
        }
    }

    //删除商品
    @Override
    public int delGoods(Goods goods) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(goods.getId());
        int i = goodsMapper.deleteByExample(goodsExample);
        File file = new File();
        file.setBusinessId(Math.toIntExact(goods.getId()));
        file.setBusinessType(goodsIcon);
        fileService.delFile(file);
        return i;
    }
    //编辑商品
    @Override
    public int updateGoods(Goods goods) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(goods.getId());
        int i = goodsMapper.updateByExample(goods,goodsExample);
        insertIcon(goods);
        return i;
    }

    //分页查询商品
    @Override
    public List<Goods> selectGoods(int pageSize, int pageNum, String goodsName) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setNum((pageNum - 1)* pageSize);
        goodsExample.setSize(pageSize);
        goodsExample.createCriteria().andGoodsNameLike('%'+goodsName+'%');
        List<Goods> list = goodsMapper.selectByExample(goodsExample);
        //遍历图片
        selectFile(list);
        return list;
    }

    //根据分类查询商品
    @Override
    public List<Goods> selectGoodsByType(int shopId, int typeId) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andShopIdEqualTo(shopId).andTypeIdEqualTo(typeId);
        List<Goods> list = goodsMapper.selectByExample(goodsExample);
        //遍历图片
        selectFile(list);
        return list;
    }

    //将图片找出来放到列表中
    private void selectFile(List<Goods> list) {
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                File file = new File();
                file.setBusinessType(goodsIcon);
                file.setBusinessId(Math.toIntExact(list.get(i).getId()));
                List<File> goods = fileService.selectFile(file);
                list.get(i).setGoodsImg(goods.get(0));
                list.get(i).setCount(0);
            }
        }
    }
}
