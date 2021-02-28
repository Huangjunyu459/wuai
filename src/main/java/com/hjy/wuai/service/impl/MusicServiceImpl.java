package com.hjy.wuai.service.impl;

import com.hjy.wuai.pojo.Music;
import com.hjy.wuai.mapper.MusicMapper;
import com.hjy.wuai.service.MusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {

    @Autowired
    private MusicMapper musicMapper;


    @Override
    public boolean save(Music entity) {
        return musicMapper.insert(entity) == 1 ? true : false;
    }
}
