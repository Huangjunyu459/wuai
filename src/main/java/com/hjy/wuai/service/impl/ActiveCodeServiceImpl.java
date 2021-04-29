package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjy.wuai.mapper.ActiveCodeMapper;
import com.hjy.wuai.mapper.UserMapper;
import com.hjy.wuai.pojo.ActiveCode;
import com.hjy.wuai.pojo.User;
import com.hjy.wuai.service.ActiveCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hjy
 * @since 2021-04-27
 */
@Service
public class ActiveCodeServiceImpl extends ServiceImpl<ActiveCodeMapper, ActiveCode> implements ActiveCodeService {


    /**
     * 注入 activeCodeMapper
     */
    @Autowired
    private ActiveCodeMapper activeCodeMapper;


    /**
     * 注入 userMapper
     */
    @Autowired
    private UserServiceImpl userService;


    /**
     * 充值功能
     *
     * @param activeCode 激活码对象
     * @return
     */
    @Override
    public boolean recharge(ActiveCode activeCode) {
        String code = activeCode.getActCode();
        if (code.length() == 16) {
            QueryWrapper<ActiveCode> wrapper = new QueryWrapper<>();
            wrapper.eq("act_code", code);
            if (activeCodeMapper.selectOne(wrapper) != null && userService.recharge(activeCode.getUserId())) {
                return activeCodeMapper.delete(wrapper) == 1;
            }
        }
        return false;
    }
}
