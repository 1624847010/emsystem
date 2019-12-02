package com.em.service.impl;

import com.em.mapper.SlideshowMapper;
import com.em.service.FileService;
import com.em.service.SlideShowService;
import com.em.vo.File;
import com.em.vo.Slideshow;
import com.em.vo.SlideshowExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/21
 * @Time 13:42
 **/
@Service
public class SlideShowServiceImpl implements SlideShowService {
    @Autowired
    private SlideshowMapper mapper;
    @Autowired
    private FileService fileService;
    private Integer SLIDETYPE = 5;

    @Override
    public List<Slideshow> getSlideshowList(Integer page, Integer limit, Integer status) {
        SlideshowExample example = new SlideshowExample();
//        example.setPage(page);
//        example.setLimit(limit);
        PageHelper.startPage(page,limit);
        example.createCriteria().andStatusEqualTo(status);
        List<Slideshow> slideshows = mapper.selectByExample(example);
        for (int i = 0; i < slideshows.size(); i++) {
            File file = new File();
            file.setBusinessType(SLIDETYPE);
            file.setBusinessId(Math.toIntExact(slideshows.get(i).getId()));
            List<File> list = fileService.selectFile(file);
            slideshows.get(i).setImg(list.get(0));
        }
        return slideshows;
    }

    @Override
    public int addSlideshows(Slideshow slideshow) {
        if (slideshow.getImg() != null) {
            int i = mapper.insertSelective(slideshow);
            File file = new File();
            file.setBusinessType(SLIDETYPE);
            file.setBusinessId(Math.toIntExact(slideshow.getId()));
            file.setFileId(slideshow.getImg().getFileId());
            file.setFileUrl(slideshow.getImg().getFileUrl());
            fileService.saveFile(file);
            return i;
        }else {
            return 0;
        }
    }

    @Override
    public int deleteSlideshows(Integer id) {
        SlideshowExample example = new SlideshowExample();
        example.createCriteria().andIdEqualTo(Long.valueOf(id));
        int i = mapper.deleteByExample(example);
        File file = new File();
        file.setBusinessId(id);
        file.setBusinessType(SLIDETYPE);
        fileService.delFile(file);
        return i;
    }


    @Override
    public int updateSlideshows(Slideshow slideshow) {
        SlideshowExample example = new SlideshowExample();
        example.createCriteria().andIdEqualTo(slideshow.getId());
        int i = mapper.updateByExampleSelective(slideshow, example);
        return i;
    }
}
