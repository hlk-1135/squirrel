package com.ldu.service.impl;

import com.ldu.dao.ImageMapper;
import com.ldu.pojo.Image;
import com.ldu.service.ImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2017/5/12.
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService {
    @Resource
    private ImageMapper imageMapper;
    public int insert(Image record) {
        return imageMapper.insert(record);
    }
}
