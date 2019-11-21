package com.em.service.impl;

import com.em.mapper.MerchantMapper;
import com.em.service.FileService;
import com.em.service.MerchantService;
import com.em.vo.File;
import com.em.vo.Merchant;
import com.em.vo.MerchantExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/18
 * @Time 10:10
 **/
@Service
@Transactional
public class MerchantServiceImpl implements MerchantService {
    //商家介绍业务属性为1
    private Integer merchantType = 1;
    //商家头像业务属性为2
    private Integer merchantIcon = 2;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private FileService fileService;

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
        if (merchant.getFiles().size() > 0) {
            for (int i = 0; i < merchant.getFiles().size(); i++) {
                File file = merchant.getFiles().get(i);
                file.setFileId(merchant.getFiles().get(i).getFileId());
                file.setFileUrl(merchant.getFiles().get(i).getFileUrl());
                file.setBusinessId(Math.toIntExact(merchant.getId()));
                file.setBusinessType(merchantType);
                fileService.saveFile(file);
            }
        }
        //插入商家头像
        if (merchant.getFile() != null) {
            File file = new File();
            file.setFileUrl(merchant.getFile().getFileUrl());
            file.setFileId(merchant.getFile().getFileId());
            file.setBusinessId(Math.toIntExact(merchant.getId()));
            file.setBusinessType(merchantIcon);
            fileService.saveFile(file);
        }
    }

    //当前身份为管理员查询所有商家列表
    @Override
    public List<Merchant> selectMerchantList(int pageSize, int pageNum, String shopName) {
        MerchantExample merchantExample = new MerchantExample();
        //根据name模糊查询
        merchantExample.createCriteria().andShopNameLike('%'+shopName+'%');
        merchantExample.setNum((pageNum-1)*pageSize);
        List<Merchant> merchants = merchantMapper.selectByExample(merchantExample);
        //设置图片
        setFiles(merchants);
        return merchants;
    }
    //循环设置商店图片
    private void setFiles(List<Merchant> merchants) {
        for (int i = 0; i < merchants.size(); i++) {
            //插入商家详情图片
            File file = new File();
            file.setBusinessId(Math.toIntExact(merchants.get(i).getId()));
            file.setBusinessType(merchantType);
            List<File> list = fileService.selectFile(file);
            merchants.get(i).setFiles(list);
            //插入商家头像
            file.setBusinessId(Math.toIntExact(merchants.get(i).getId()));
            file.setBusinessType(merchantIcon);
            list = fileService.selectFile(file);
            merchants.get(i).setFile(list.get(0));
        }
    }
    //身份为商家进行分页查询
    @Override
    public List<Merchant> selectMerchantListByType(int pageSize, int pageNum, String shopName, Integer type) {
        MerchantExample merchantExample = new MerchantExample();
        merchantExample.createCriteria().andShopNameLike('%'+shopName+'%').andUserIdEqualTo(type);
        merchantExample.setNum((pageNum-1)*pageSize);
        List<Merchant> merchants = merchantMapper.selectByExample(merchantExample);
        //循环查询图片
        setFiles(merchants);
        return merchants;
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
    @Override
    public int delMerchant(Merchant merchant) {
        MerchantExample merchantExample = new MerchantExample();
        merchantExample.createCriteria().andIdEqualTo(merchant.getId());
        //删除商家
        int i = merchantMapper.deleteByExample(merchantExample);
        //删除商家详情图片
        File file = new File();
        file.setBusinessId(Math.toIntExact(merchant.getId()));
        file.setBusinessType(merchantIcon);
        fileService.delFile(file);
        //删除商家头像
        file.setBusinessId(Math.toIntExact(merchant.getId()));
        file.setBusinessType(merchantType);
        fileService.delFile(file);
        return i;
    }
}
