package com.caohao.controller;

import com.caohao.common.Result;
import com.caohao.common.SuperController;
import com.caohao.pojo.entity.ReplyInfo;
import com.caohao.service.ReplyInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * (ReplyInfo)表控制层
 *
 * @author caohao
 * @since 2022-04-03 19:01:33
 */
@Api(tags = "ReplyInfo")
@RestController
@RequestMapping("/replyInfo")
@CrossOrigin
public class ReplyInfoController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private ReplyInfoService replyInfoService;

    /**
     * 分页查询
     *
     * @param replyInfo 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody ReplyInfo replyInfo,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.replyInfoService.queryByPage(replyInfo, pageNum, pageSize));
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
        return success(this.replyInfoService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param replyInfo 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping
    public Result add(@RequestBody ReplyInfo replyInfo) {
        return success(this.replyInfoService.insert(replyInfo));
    }

    /**
     * 编辑数据
     *
     * @param replyInfo 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑数据")
    @PutMapping
    public Result edit(@RequestBody ReplyInfo replyInfo) {
        return success(this.replyInfoService.update(replyInfo));
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
        return success(this.replyInfoService.deleteById(id));
    }

}

