package com.caohao.controller;

import com.caohao.common.Result;
import com.caohao.common.SuperController;
import com.caohao.common.enums.impl.ArticleTypeEnum;
import com.caohao.pojo.entity.Article;
import com.caohao.pojo.model.ArticleModel;
import com.caohao.pojo.param.ArticleParam;
import com.caohao.security.util.GetTokenInfoUtil;
import com.caohao.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * (Article)表控制层
 *
 * @author caohao
 * @since 2021-12-04 14:54:12
 */
@Api(tags = "Article")
@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

    @GetMapping("/user/{pageNum}/{pageSize}")
    public Result queryByUser(@PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize,
                              ArticleTypeEnum type){
        PageHelper.startPage(pageNum,pageSize);
        List<ArticleModel> articleModels=articleService.queryByUser(GetTokenInfoUtil.getUsername(),type);
        return Result.ok(new PageInfo<>(articleModels));
    }

    /**
     * 分页查询
     *
     * @param article 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody ArticleParam article,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.articleService.queryByPage(article, pageNum, pageSize));
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
        return success(this.articleService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param article 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping
    public Result add(@RequestBody ArticleParam article) {
        return success(this.articleService.insert(article));
    }

    /**
     * 编辑数据
     *
     * @param article 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑数据")
    @PutMapping
    public Result edit(@RequestBody ArticleParam article) {
        return success(this.articleService.update(article));
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
        return success(this.articleService.deleteById(id));
    }

}

