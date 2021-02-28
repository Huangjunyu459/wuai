package com.hjy.wuai.mapper;

import com.hjy.wuai.pojo.Music;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2021-02-28
 */
@Repository
public interface MusicMapper extends BaseMapper<Music> {


    /**
     * 查询已删除的音乐
     *
     * @return
     */
    @Select("select * from music where is_delete = 1")
    public List<Music> findIsDelete();

}
