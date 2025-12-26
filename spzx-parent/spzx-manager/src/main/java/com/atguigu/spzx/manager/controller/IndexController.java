package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.manager.service.ValidateCodeService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import com.atguigu.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 作者:hfj
 * 功能:首页的基本功能
 * 日期: 2025/12/24 10:17
 */

@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private SysMenuService sysMenuService;

    //生成图片验证码
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo,ResultCodeEnum.SUCCESS);
    }

    //用户登录
    @Operation(summary = "登录的方法")
    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo,ResultCodeEnum.SUCCESS);
    }

    //获取当前登录的用户信息
    @GetMapping(value = "/getUserInfo")
    public Result<SysUser> getUserInfo() {
        //返回用户信息
        return Result.build(AuthContextUtil.get(),ResultCodeEnum.SUCCESS);
    }

    //用户退出功能
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(value = "token") String token) {
        sysUserService.logout(token);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }

    //查询用户可以操作的菜单
    @GetMapping("/menus")
    public Result menus() {
        List<SysMenuVo> sysMenuVoList =  sysMenuService.findMenusByUserId();
        return Result.build(sysMenuVoList, ResultCodeEnum.SUCCESS);
    }
}
