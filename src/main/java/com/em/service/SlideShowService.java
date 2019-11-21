package com.em.service;

import com.em.vo.Slideshow;

import java.util.List;

public interface SlideShowService {
    List<Slideshow> getSlideshowList(Integer page, Integer limit, Integer status);

    int addSlideshows(Slideshow slideshow);

    int deleteSlideshows(Integer id);

    int updateSlideshows(Slideshow slideshow);
}
