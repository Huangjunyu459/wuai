package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.ActiveCode;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjy.wuai.pojo.Comment;
import com.hjy.wuai.pojo.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hjy
 * @since 2021-04-27
 */
public interface ActiveCodeService extends IService<ActiveCode> {

    /**
     * 会员充值功能
     *
     * @param activeCode 激活码对象
     * @return 返回的结果
     */
    boolean recharge(ActiveCode activeCode);


    /**
     * 查询未使用的激活码
     *
     * @return 返回的结果
     */
    List<ActiveCode> findAllActiveCode();


    /**
     * 根据激活码模糊查找
     *
     * @param code 激活码
     * @return 返回的结果
     */
    List<ActiveCode> findActiveCodesByCode(String code);

    /**
     * 根据激活码模糊查找已使用
     *
     * @param code 激活码
     * @return 返回的结果
     */
     List<ActiveCode> findActiveCodesByCodeIsDelete(String code);

    /**
     * 根据激活码精确查找
     *
     * @param code 激活码
     * @return 返回的结果
     */
    ActiveCode findActiveCodeByCode(String code);

    /**
     * 查找所有已使用的激活码
     *
     * @return 返回的结果
     */
    List<ActiveCode> findIsDelete();

    /**
     * 分页查询
     *
     * @param index    索引号
     * @param size     页数大小
     * @param code
     * @return 返回的结果
     */
    IPage<ActiveCode> pagingQuery(String code, Integer index, Integer size);

    /**
     * 分页查询（已使用的激活码）
     *
     * @param index    索引号
     * @param size     页数大小
     * @param code
     * @return 返回的结果
     */
    List<ActiveCode> pagingQueryIsDelete(String code, Integer index, Integer size);
}
