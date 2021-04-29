package com.hjy.wuai.service;

import com.hjy.wuai.pojo.ActiveCode;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
