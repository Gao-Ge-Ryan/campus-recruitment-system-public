package com.caohao.controller;

import com.caohao.common.Result;
import com.caohao.common.SuperController;
import com.caohao.dao.EmploymentDao;
import com.caohao.pojo.entity.EmploymentUser;
import com.caohao.pojo.model.EmploymentUserModel;
import com.caohao.pojo.param.EmploymentUserParam;
import com.caohao.security.util.GetTokenInfoUtil;
import com.caohao.service.EmploymentUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * (EmploymentUser)表控制层
 *
 * @author caohao
 * @since 2021-11-14 17:48:02
 */
@Api(tags = "EmploymentUser")
@RestController
@RequestMapping("/employmentUser")
@CrossOrigin
public class EmploymentUserController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private EmploymentUserService employmentUserService;

    @Resource
    private EmploymentDao employmentDao;

    @PostMapping("/company/{pageNum}/{pageSize}")
    public Result queryByPageCompany(@RequestBody EmploymentUserParam employmentUser,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.employmentUserService.queryByPageCompany(employmentUser, pageNum, pageSize));
    }

    @GetMapping("/user/{pageNum}/{pageSize}")
    public Result getByUser(@PathVariable("pageNum") Integer pageNum,
                            @PathVariable("pageSize") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);

        List<EmploymentUserModel> employmentUserModels =employmentUserService.getByUser(GetTokenInfoUtil.getUsername());
        return Result.ok(new PageInfo<>(employmentUserModels));
    }

    /**
     * 分页查询
     *
     * @param employmentUser 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody EmploymentUserParam employmentUser,
                                     @PathVariable("pageNum") Integer pageNum,
                                     @PathVariable("pageSize") Integer pageSize) {
        return success(this.employmentUserService.queryByPage(employmentUser, pageNum, pageSize));
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
        return success(this.employmentUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param employmentUser 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping
    public Result add(@RequestBody EmploymentUserParam employmentUser) {
        return success(this.employmentUserService.insert(employmentUser));
    }

    /**
     * 编辑数据
     *
     * @param employmentUser 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑数据")
    @PutMapping
    public Result edit(@RequestBody EmploymentUserParam employmentUser) {
        return success(this.employmentUserService.update(employmentUser));
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
        return success(this.employmentUserService.deleteById(id));
    }

}

