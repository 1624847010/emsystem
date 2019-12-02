package com.em.service.impl;

import com.em.mapper.GoodsMapper;
import com.em.service.FileService;
import com.em.service.GoodsService;
import com.em.service.OrderfromService;
import com.em.vo.File;
import com.em.vo.Goods;
import com.em.vo.GoodsExample;
import com.em.vo.Orderfrom;
import com.github.pagehelper.PageHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
//    @Transactional
//    @Override
//    public int delGoods(Goods goods) {
//        GoodsExample goodsExample = new GoodsExample();
//        goodsExample.createCriteria().andIdEqualTo(goods.getId());
//        int i = goodsMapper.deleteByExample(goodsExample);
//        File file = new File();
//        file.setBusinessId(Math.toIntExact(goods.getId()));
//        file.setBusinessType(goodsIcon);
//        fileService.delFile(file);
//        return i;
//    }

    //编辑商品
    @Override
    public int updateGoods(Goods goods) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(goods.getId());
        int i = goodsMapper.updateByExampleSelective(goods,goodsExample);
        insertIcon(goods);
        return i;
    }

    //分页查询商品
    @Override
    public List<Goods> selectGoods(int pageSize, int pageNum, String goodsName,Integer shopId) {
        GoodsExample goodsExample = new GoodsExample();
//        goodsExample.setNum((pageNum - 1)* pageSize);
//        goodsExample.setSize(pageSize);
        PageHelper.startPage(pageNum,pageSize);
        goodsExample.createCriteria().andGoodsNameLike('%'+goodsName+'%').andShopIdEqualTo(shopId);
        List<Goods> list = goodsMapper.selectByExample(goodsExample);
        //遍历图片
        selectFile(list);
        return list;
    }

    //根据分类查询商品
    @Override
    public List<Goods> selectGoodsByType(int shopId, int typeId) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andShopIdEqualTo(shopId).andTypeIdEqualTo(typeId).andGoodsStatusEqualTo(0);
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
                if (goods.size()!=0) {
                    list.get(i).setGoodsImg(goods.get(0));
                    //？？？？
                    list.get(i).setCount(0);
                }
            }
        }
    }
    //根据json查询商品与数量
    @Override
    public List<Goods> selectGoodsById(String goods) {
        List<Map<String,Long>> list = new ArrayList<>();
        Map<String,Long> map;
       try {
           JSONObject json = new JSONObject(goods);
           JSONArray goodsJson = json.getJSONArray("goods");
           for (int i = 0; i < goodsJson.length(); i++) {
               JSONObject jsonObject = goodsJson.getJSONObject(i);
               Long count = Long.valueOf(jsonObject.getInt("count"));
               Long goodsId = Long.valueOf(jsonObject.getInt("id"));
               map = new HashMap<>();
               map.put("count",count);
               map.put("id",goodsId);
               list.add(map);
           }
           List<Long> idList =new ArrayList<>();
           List<Long> countList =new ArrayList<>();
           List<Goods> goodsList;
           for (Map<String, Long> stringObjectMap : list) {
               idList.add(stringObjectMap.get("id"));
               countList.add(stringObjectMap.get("count"));
           }
           GoodsExample example = new GoodsExample();
            example.createCriteria().andIdIn(idList);
           goodsList = goodsMapper.selectByExample(example);
           for (int i = 0; i < goodsList.size(); i++) {
               goodsList.get(i).setOrderCount(countList.get(i));
           }
           selectFile(goodsList);
           return goodsList;
       }catch (Exception e){
           System.out.println("转换错误");
           return null;
       }
    }
    //查询商品销售数量
    @Override
    public Integer selectSaleCount(Long id) {
        return goodsMapper.selectSaleCountByShopId(id);
    }
    //根据id查询商品数量
    @Override
    public int selectCount(Integer id) {
        GoodsExample example =new GoodsExample();
        example.createCriteria().andTypeIdEqualTo(id);
        long count = goodsMapper.countByExample(example);
        return Math.toIntExact(count);
    }

    //根据商品id查询商品信息
    @Override
    public List<Goods> selectGoodsInfo(Integer id) {
        GoodsExample example = new GoodsExample();
        example.createCriteria().andIdEqualTo(Long.valueOf(id));
        List<Goods> goodsList = goodsMapper.selectByExample(example);
        selectFile(goodsList);
        return goodsList;
    }
}
