package com.em.service.impl;

import com.em.mapper.CommentMapper;
import com.em.mapper.GoodsMapper;
import com.em.mapper.MerchantMapper;
import com.em.service.*;
import com.em.vo.File;
import com.em.vo.Merchant;
import com.em.vo.MerchantExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/18
 * @Time 10:10
 **/
@Service
@Transactional
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private FileService fileService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private TypeService typeService;
    //商家介绍业务属性为1
    private static final Integer MERCHANTTYPE = 1;
    //商家头像业务属性为2
    private static final Integer MERCHANTICON = 2;

    //新增商家
    @Override
    public int addMerchant(Merchant merchant) {
        //插入数据
        int insert = merchantMapper.insert(merchant);
        insertImg(merchant);
        if (insert > 0) {
            //插入成功
            return 1;
        }
        //插入失败
        return 0;
    }

    private void insertImg(Merchant merchant) {
        //插入商家详情图片
        if (merchant.getFiles()!=null) {
            for (int i = 0; i < merchant.getFiles().size(); i++) {
                File file = merchant.getFiles().get(i);
                file.setFileId(merchant.getFiles().get(i).getFileId());
                file.setFileUrl(merchant.getFiles().get(i).getFileUrl());
                file.setBusinessId(Math.toIntExact(merchant.getId()));
                file.setBusinessType(MERCHANTTYPE);
                fileService.saveFile(file);
            }
        }
        //插入商家头像
        if (merchant.getFile() != null) {
            File file = new File();
            file.setFileUrl(merchant.getFile().getFileUrl());
            file.setFileId(merchant.getFile().getFileId());
            file.setBusinessId(Math.toIntExact(merchant.getId()));
            file.setBusinessType(MERCHANTICON);
            fileService.saveFile(file);
        }
    }

    //当前身份为管理员查询所有商家列表
    @Override
    public List<Merchant> selectMerchantList(int pageSize, int pageNum, String shopName,Integer shopType,Integer type) {
        MerchantExample merchantExample = new MerchantExample();
        MerchantExample.Criteria criteria = merchantExample.createCriteria();
        //根据name模糊查询
        criteria.andShopNameLike('%'+shopName+'%');
        //根据分类查询商品
        if (shopType!=0) {
            criteria.andShopTypeEqualTo(shopType);
        }
        //身份为商家进行分页查询
        if (type!=0) {
            criteria.andUserIdEqualTo(type);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Merchant> merchants = merchantMapper.selectByExample(merchantExample);
        //设置图片
        setFiles(merchants);
        //商家平均分
        for (int i = 0; i < merchants.size(); i++) {
            float  sumGrade = commentService.selectGradeAvg(merchants.get(i).getId());
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String format = decimalFormat.format(sumGrade);
            merchants.get(i).setSumGrade(Float.parseFloat(format));
        }
        //商家销量
        for (int i = 0; i < merchants.size(); i++) {
            Integer saleCount = goodsService.selectSaleCount(merchants.get(i).getId());
            merchants.get(i).setSaleCount(saleCount);
        }
        return merchants;
    }

    //循环遍历商店图片
    private void setFiles(List<Merchant> merchants) {
        for (int i = 0; i < merchants.size(); i++) {
            //插入商家详情图片
            File file = new File();
            file.setBusinessId(Math.toIntExact(merchants.get(i).getId()));
            file.setBusinessType(MERCHANTTYPE);
            List<File> list = fileService.selectFile(file);
            if (list.size()!=0) {
                merchants.get(i).setFiles(list);
            }
            //插入商家头像
            file.setBusinessId(Math.toIntExact(merchants.get(i).getId()));
            file.setBusinessType(MERCHANTICON);
            list = fileService.selectFile(file);
            if (list.size()!=0) {
                merchants.get(i).setFile(list.get(0));
            }
        }
    }

    //编辑商家信息
    @Override
    public int updateMerchant(Merchant merchant) {
        MerchantExample merchantExample = new MerchantExample();
        merchantExample.createCriteria().andIdEqualTo(merchant.getId());
        int i = merchantMapper.updateByExampleSelective(merchant, merchantExample);
        insertImg(merchant);
        return i;
    }
    //删除商家信息
    @Transactional
    @Override
    public int delMerchant(Merchant merchant) {
        int count = typeService.selectCount(merchant.getId());
        if (count >=1) {
            return -1;
        }
        MerchantExample merchantExample = new MerchantExample();
        merchantExample.createCriteria().andIdEqualTo(merchant.getId());
        //删除商家
        int i = merchantMapper.deleteByExample(merchantExample);
        //删除商家详情图片
        File file = new File();
        file.setBusinessId(Math.toIntExact(merchant.getId()));
        file.setBusinessType(MERCHANTICON);
        fileService.delFile(file);
        //删除商家头像
        file.setBusinessId(Math.toIntExact(merchant.getId()));
        file.setBusinessType(MERCHANTTYPE);
        fileService.delFile(file);
        return i;
    }

    //根据商家id返回商家信息
    @Override
    public Merchant selectById(Integer shopId) {
        MerchantExample example = new MerchantExample();
        example.createCriteria().andIdEqualTo(Long.valueOf(shopId));
        List<Merchant> merchants = merchantMapper.selectByExample(example);
        setFiles(merchants);
        return merchants.get(0);
    }

    //查询商家数量
    @Override
    public int selectCount(Integer id) {
        MerchantExample example = new MerchantExample();
        example.createCriteria().andShopTypeEqualTo(id);
        long count = merchantMapper.countByExample(example);
        return Math.toIntExact(count);
    }
}
