package com.hjy.wuai.controller;

import com.hjy.wuai.pojo.ActiveCode;
import com.hjy.wuai.pojo.Comment;
import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.service.impl.ActiveCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author hjy
 * @date 2021/5/5 0005,下午 20:35
 * @email 541605007@qq.com
 */
@RestController
@RequestMapping("/actCode")
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*", methods = {})
public class ActiveCodeController {

    /**
     * 引入 activeCodeService
     */
    @Autowired
    private ActiveCodeServiceImpl activeCodeService;


    /**
     * 新增评论
     *
     * @param activeCode 评论实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("addActCode")
    public Result1 addActCode(@RequestBody ActiveCode activeCode) {
        if (activeCodeService.save(activeCode)) {
            return Result1.success().setMessage("上传成功");
        } else {
            return Result1.fail().setMessage("上传失败");
        }
    }

    /**
     * 新增评论
     *
     * @param activeCode 评论实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("updateActCodeById")
    public Result1 updateActCodeById(@RequestBody ActiveCode activeCode) {
        if (activeCodeService.updateById(activeCode)) {
            return Result1.success().setMessage("更新成功");
        } else {
            return Result1.fail().setMessage("更新失败");
        }
    }

    /**
     * 查找所有的激活码
     *
     * @return 返回上传的结果 msg
     */
    @GetMapping("findAllActiveCode")
    public Result1 findAllActiveCode() {
        List<ActiveCode> activeCodeList = activeCodeService.findAllActiveCode();
        if (activeCodeList.size() != 0) {
            return Result1.success().data("activeCodeList", activeCodeList);
        } else {
            return Result1.fail().setMessage("查找失败");
        }
    }

    /**
     * 根据id查找激活码
     *
     * @return 返回上传的结果 msg
     */
    @GetMapping("getById")
    public Result1 getById(String id) {
        ActiveCode activeCode = activeCodeService.getById(id);
        if (activeCode != null) {
            return Result1.success().data("activeCode", activeCode);
        } else {
            return Result1.fail().setMessage("查找失败");
        }
    }


    /**
     * 删除激活码
     *
     * @return 返回上传的结果 msg
     */
    @DeleteMapping("removeById")
    public Result1 removeById(String id) {
        boolean statue = activeCodeService.removeById(id);
        if (statue) {
            return Result1.success().setMessage("删除成功");
        } else {
            return Result1.fail().setMessage("删除失败");
        }
    }

    /**
     * 查找所有的激活码
     *
     * @return 返回上传的结果 msg
     */
    @GetMapping("findIsDelete")
    public Result1 findIsDelete() {
        List<ActiveCode> activeCodeList = activeCodeService.findIsDelete();
        if (activeCodeList.size() != 0) {
            return Result1.success().data("activeCodeList", activeCodeList);
        } else {
            return Result1.fail().setMessage("查找失败");
        }
    }

    /**
     * 根据激活码模糊查找
     *
     * @param code 激活码
     * @return 返回的结果
     */
    @GetMapping("findActiveCodesByCode")
    public Result1 findActiveCodesByCode(String code) {
        List<ActiveCode> activeCodeList = activeCodeService.findActiveCodesByCode(code);
        if (activeCodeList.size() != 0) {
            return Result1.success().data("activeCodeList", activeCodeList);
        } else {
            return Result1.fail().setMessage("查找失败");
        }
    }

    /**
     * 根据激活码模糊查找已使用
     *
     * @param code 激活码
     * @return 返回的结果
     */
    @GetMapping("findActiveCodesByCodeIsDelete")
    public Result1 findActiveCodesByCodeIsDelete(String code) {
        List<ActiveCode> activeCodeList = activeCodeService.findActiveCodesByCodeIsDelete(code);
        if (activeCodeList.size() != 0) {
            return Result1.success().data("activeCodeList", activeCodeList);
        } else {
            return Result1.fail().setMessage("查找失败");
        }
    }

    /**
     * 根据激活码精确查找
     *
     * @param code 激活码
     * @return 返回的结果
     */
    @GetMapping("findActiveCodeByCode")
    public Result1 findActiveCodeByCode(String code) {
        ActiveCode activeCode = activeCodeService.findActiveCodeByCode(code);
        if (activeCode != null) {
            return Result1.success().data("activeCode", activeCode);
        } else {
            return Result1.fail().setMessage("查找失败");
        }
    }

    /**
     * 生成激活码
     *
     * @return 返回的结果
     */
    @GetMapping("GenActiveCode")
    public Result1 GenActiveCode() {
        String genActiveCode = activeCodeService.GenActiveCode();
        if (genActiveCode != null) {
            return Result1.success().data("genActiveCode", genActiveCode);
        } else {
            return Result1.fail().setMessage("激活码生成失败");
        }
    }


    /**
     * 分页查询
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQuery")
    public Result1 pagingQuery(String code, Integer index, Integer size) {
        Serializable activeCodeIPage = activeCodeService.pagingQuery(code, index, size);
        if (activeCodeIPage != null) {
            return Result1.success().data("activeCodeIPage", activeCodeIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

    /**
     * 分页查询（已使用的激活码）
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryIsDelete")
    public Result1 pagingQueryIsDelete(String code, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        List<ActiveCode> activeCodeList = activeCodeService.pagingQueryIsDelete(code, index, size);
        if (activeCodeList.size() != 0) {
            return Result1.success().data("activeCodeList", activeCodeList);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }


}
