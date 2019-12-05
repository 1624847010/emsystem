package com.em.service.impl;

import com.em.mapper.FileMapper;
import com.em.service.FileService;
import com.em.vo.File;
import com.em.vo.FileExample;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/13
 * @Time 14:36
 **/
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    private FileMapper fileMapper;
    //图片服务器地址
    @Value("${fdfs.hostPort}")
    private String hostPort;

    @Override
    public File uploadFile(MultipartFile file) {
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            String fileId = storePath.getFullPath();
            String fileUrl = hostPort + fileId;
            File f1 = new File();
            f1.setFileId(fileId);
            f1.setFileUrl(fileUrl);
            return f1;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delFile(File file) {
        try {
            //该图片是否有id（是否已上传并保存）
            //有id就在数据库中删除
            if (null != file.getBusinessId() && null != file.getBusinessType()) {
                FileExample fileExample = new FileExample();
                fileExample.createCriteria().andBusinessIdEqualTo(file.getBusinessId()).andBusinessTypeEqualTo(file.getBusinessType());
            fileMapper.deleteByExample(fileExample);
            }
            //删除图片(有没有id都要删)
            if (null != file.getFileId()) {
                fastFileStorageClient.deleteFile(file.getFileId());
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    //根据businessId和businessType查询
    @Override
    public List<File> selectFile(File file) {
        FileExample fileExample = new FileExample();
        fileExample.createCriteria().andBusinessIdEqualTo(file.getBusinessId()).andBusinessTypeEqualTo(file.getBusinessType());
        List<File> files = fileMapper.selectByExample(fileExample);
        return files;
    }
    //保存图片
    @Override
    public int saveFile(File file) {
        int insert = fileMapper.insert(file);
        return insert;
    }
}
