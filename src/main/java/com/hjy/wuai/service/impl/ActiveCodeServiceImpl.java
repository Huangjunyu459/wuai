package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.UpdateById;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjy.wuai.mapper.ActiveCodeMapper;
import com.hjy.wuai.mapper.UserMapper;
import com.hjy.wuai.pojo.ActiveCode;
import com.hjy.wuai.pojo.Comment;
import com.hjy.wuai.pojo.User;
import com.hjy.wuai.service.ActiveCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
     * 保存激活码对象
     *
     * @param entity 用户实体类
     * @return 返回的结果
     */
    @Override
    public boolean save(ActiveCode entity) {
        ActiveCode activeCode = new ActiveCode();
        activeCode.setActCode(entity.getActCode());
        activeCode.setUserId(entity.getUserId());
        return activeCodeMapper.insert(activeCode) == 1;
    }

    /**
     * 删除激活码对象
     *
     * @param id 激活码对象id
     * @return 返回的结果
     */
    @Override
    public boolean removeById(Serializable id) {
        return activeCodeMapper.deleteById(id) == 1;
    }

    /**
     * 更新激活码信息
     *
     * @param entity 用户实体类
     * @return 返回的结果
     */
    @Override
    public boolean updateById(ActiveCode entity) {
        ActiveCode activeCode = getById(entity.getId());
        activeCode.setActCode(entity.getActCode());
        activeCode.setUserId(entity.getUserId());
        return activeCodeMapper.updateById(activeCode) == 1;
    }

    @Override
    public ActiveCode getById(Serializable id) {
        return activeCodeMapper.selectById(id);
    }

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

    /**
     * 查询未使用的激活码
     *
     * @return 返回的结果
     */
    @Override
    public List<ActiveCode> findAllActiveCode() {
        QueryWrapper<ActiveCode> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0);
        return activeCodeMapper.selectList(wrapper);
    }

    /**
     * 查询已使用的激活码
     *
     * @return 返回的结果
     */
    @Override
    public List<ActiveCode> findIsDelete() {
        return activeCodeMapper.findIsDelete();
    }

    /**
     * 根据激活码模糊查找
     *
     * @param code 激活码
     * @return 返回的结果
     */
    @Override
    public List<ActiveCode> findActiveCodesByCode(String code) {
        QueryWrapper<ActiveCode> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0).like("act_code", code);
        return activeCodeMapper.selectList(wrapper);
    }

    /**
     * 根据激活码模糊查找已使用
     *
     * @param code 激活码
     * @return 返回的结果
     */
    @Override
    public List<ActiveCode> findActiveCodesByCodeIsDelete(String code) {
        code = "%" + code + "%";
        System.out.println(code);
        return activeCodeMapper.findActiveCodesByCodeIsDelete(code);
    }

    /**
     * 根据激活码精确查找
     *
     * @param code 激活码
     * @return 返回的结果
     */
    @Override
    public ActiveCode findActiveCodeByCode(String code) {
        QueryWrapper<ActiveCode> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0).eq("act_code", code);
        return activeCodeMapper.selectOne(wrapper);
    }

    /**
     * 随机生成16位激活码
     *
     * @return 返回的结果
     */
    public String GenActiveCode() {
        String[] beforeShuffle = new String[]{
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "a", "b", "d", "c", "e", "f", "g", "h", "i", "j",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(1, 17);
        return result;
    }

    /**
     * 分页查询
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<ActiveCode> pagingQuery(String code, Integer index, Integer size) {
        IPage<ActiveCode> page = new Page<>(index, size);
        QueryWrapper<ActiveCode> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time").like("act_code", code);
        return activeCodeMapper.selectPage(page, wrapper);
    }

    /**
     * 分页查询（已使用的激活码）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public List<ActiveCode> pagingQueryIsDelete(String code, Integer index, Integer size) {
        code = "%" + code + "%";
        List<ActiveCode> activeCodeList = activeCodeMapper.pagingQueryIsDelete(code, index, size);
        return activeCodeList;
    }
}
