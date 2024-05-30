package com.caohao.controller;

import com.caohao.common.Result;
import com.caohao.common.SuperController;
import com.caohao.pojo.entity.CommentReplyRelation;
import com.caohao.service.CommentReplyRelationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * (CommentReplyRelation)表控制层
 *
 * @author caohao
 * @since 2022-04-03 19:02:15
 */
@Api(tags = "CommentReplyRelation")
@RestController
@RequestMapping("/commentReplyRelation")
@CrossOrigin
public class CommentReplyRelationController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private CommentReplyRelationService commentReplyRelationService;

    /**
     * 分页查询
     *
     * @param commentReplyRelation 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody CommentReplyRelation commentReplyRelation,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.commentReplyRelationService.queryByPage(commentReplyRelation, pageNum, pageSize));
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
        return success(this.commentReplyRelationService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param commentReplyRelation 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping
    public Result add(@RequestBody CommentReplyRelation commentReplyRelation) {
        return success(this.commentReplyRelationService.insert(commentReplyRelation));
    }

    /**
     * 编辑数据
     *
     * @param commentReplyRelation 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑数据")
    @PutMapping
    public Result edit(@RequestBody CommentReplyRelation commentReplyRelation) {
        return success(this.commentReplyRelationService.update(commentReplyRelation));
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
        return success(this.commentReplyRelationService.deleteById(id));
    }

}

