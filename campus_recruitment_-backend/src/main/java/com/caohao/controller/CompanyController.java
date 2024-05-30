package com.caohao.controller;

import com.caohao.common.Result;
import com.caohao.common.SuperController;
import com.caohao.pojo.entity.Company;
import com.caohao.pojo.model.CompanyModel;
import com.caohao.pojo.param.CompanyParam;
import com.caohao.security.util.GetTokenInfoUtil;
import com.caohao.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * (Company)表控制层
 *
 * @author caohao
 * @since 2021-11-14 16:28:19
 */
@Api(tags = "Company")
@RestController
/** 返回json格式的数据 */
@RequestMapping("/company")
@CrossOrigin
public class CompanyController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    /** 通过名称查找 */
    private CompanyService companyService;

    @GetMapping("/user/name")
    public Result queryByUsername(){
        CompanyModel companyModel=companyService.queryByUsername(GetTokenInfoUtil.getUsername());
        return Result.ok(companyModel);
    }

    /**
     * 分页查询
     *
     * @param company 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody CompanyParam company,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.companyService.queryByPage(company, pageNum, pageSize));
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
        return success(this.companyService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param company 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping
    public Result add(@RequestBody CompanyParam company) {
        return success(this.companyService.insert(company));
    }

    /**
     * 编辑数据
     *
     * @param company 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑数据")
    @PutMapping
    public Result edit(@RequestBody CompanyParam company) {
        return success(this.companyService.update(company));
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
        return success(this.companyService.deleteById(id));
    }

}

