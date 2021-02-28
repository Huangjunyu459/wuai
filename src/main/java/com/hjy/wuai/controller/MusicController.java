package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Music;
import com.hjy.wuai.service.impl.MusicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicServiceImpl musicService;


    @PostMapping("/save")
    public String save(Music entity) {
        return musicService.save(entity) == true ? "success" : "fail";
    }

}

