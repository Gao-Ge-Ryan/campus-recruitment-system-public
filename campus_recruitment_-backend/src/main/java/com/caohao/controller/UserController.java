package com.caohao.controller;

import com.caohao.common.Result;
import com.caohao.common.SuperController;
import com.caohao.pojo.entity.User;
import com.caohao.pojo.model.UserModel;
import com.caohao.pojo.param.UserParam;
import com.caohao.security.util.GetTokenInfoUtil;
import com.caohao.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * (User)表控制层
 *
 * @author caohao
 * @since 2021-11-14 18:48:12
 */
@Api(tags = "User")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @GetMapping("/username")
    public Result queryByUsername(){
      UserModel userModel= userService.queryByUsername(GetTokenInfoUtil.getUsername());
      return Result.ok(userModel);
    }
    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody UserParam user,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.userService.queryByPage(user, pageNum, pageSize));
    }

    @PatchMapping("/patch")
    public Result patchInfo(@RequestBody UserParam user){
        userService.patchInfo(user);
        return Result.ok("编辑成功");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") String id) {
        return success(this.userService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping
    public Result add(@RequestBody UserParam user) {
        return success(this.userService.insert(user));
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑数据")
    @PutMapping
    public Result edit(@RequestBody UserParam user) {
        return success(this.userService.update(user));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除数据")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        return success(this.userService.deleteById(id));
    }

    /**
     * 注册用户
     *
     * @param user 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping("/registeredUser")
    public Result registeredUser(@RequestBody UserParam user) {
        return this.userService.registeredUser(user);
    }

    @PostMapping("/password")
    public Result updatePassword(@RequestBody UserParam user){
        return this.userService.updatePassword(user);
    }

}

