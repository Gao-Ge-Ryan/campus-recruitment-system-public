package com.caohao.controller;

import com.caohao.common.Result;
import com.caohao.common.SuperController;
import com.caohao.pojo.entity.Comments;
import com.caohao.service.CommentsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * (Comments)表控制层
 *
 * @author caohao
 * @since 2022-04-03 18:59:20
 */
@Api(tags = "Comments")
@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentsController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private CommentsService commentsService;

    /**
     * 分页查询
     *
     * @param comments 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody Comments comments,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.commentsService.queryByPage(comments, pageNum, pageSize));
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
        return success(this.commentsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param comments 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping
    public Result add(@RequestBody Comments comments) {
        return success(this.commentsService.insert(comments));
    }

    /**
     * 编辑数据
     *
     * @param comments 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑数据")
    @PutMapping
    public Result edit(@RequestBody Comments comments) {
        return success(this.commentsService.update(comments));
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
        return success(this.commentsService.deleteById(id));
    }

}

