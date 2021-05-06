package com.hjy.wuai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjy.wuai.pojo.ActiveCode;
import com.hjy.wuai.pojo.Comment;
import com.hjy.wuai.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hjy
 * @date 2021/4/27 0027,下午 19:26
 * @email 541605007@qq.com
 */
@Repository
public interface ActiveCodeMapper extends BaseMapper<ActiveCode> {


    /**
     * 查询已使用的激活码
     *
     * @return 返回 ActiveCode 的集合
     */
    @Select("select * from active_code where is_delete = 1")
    List<ActiveCode> findIsDelete();


    /**
     * 分页查询已使用的激活码
     *
     * @param index
     * @param size
     * @param code
     * @return
     */
    @Select("select * from active_code where is_delete = 1 AND act_code like #{code} limit #{index} , #{size}")
    List<ActiveCode> pagingQueryIsDelete(String code, Integer index, Integer size);


    /**
     * 查询已使用的激活码
     *
     * @param code 激活码
     * @return 返回 ActiveCode 的集合
     */
    @Select("select * from active_code where is_delete = 1 and act_code like #{code}")
    List<ActiveCode> findActiveCodesByCodeIsDelete(String code);

}
