package com.em.service;

import com.em.vo.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    File uploadFile(MultipartFile file);

    boolean delFile(File file);

    List<File> selectFile(File file);

    int saveFile(File file);
}
