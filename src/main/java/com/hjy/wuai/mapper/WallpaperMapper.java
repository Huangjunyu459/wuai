package com.hjy.wuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjy.wuai.pojo.Wallpaper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2021-02-18
 */
@Repository
public interface WallpaperMapper extends BaseMapper<Wallpaper> {

    /**
     * 查询已删除的壁纸
     *
     * @return
     */
    @Select("select * from wallpaper where is_delete = 1")
    public List<Wallpaper> findIsDelete();

}
