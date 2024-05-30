package com.caohao.controller;

import com.caohao.common.Result;
import com.caohao.common.SuperController;
import com.caohao.pojo.entity.AreaSelection;
import com.caohao.service.AreaSelectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * (AreaSelection)表控制层
 *
 * @author caohao
 * @since 2022-04-04 17:01:41
 */
@Api(tags = "AreaSelection")
@RestController
@RequestMapping("/areaSelection")
@CrossOrigin
public class AreaSelectionController extends SuperController {
    /**
     * 服务对象
     */
    @Resource
    private AreaSelectionService areaSelectionService;


    @GetMapping("/area")
    public Result queryArea(){
        Result result = areaSelectionService.queryArea();
        return result;
    }

    /**
     * 分页查询
     *
     * @param areaSelection 筛选条件
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/{pageNum}/{pageSize}")
    public Result queryByPage(@RequestBody AreaSelection areaSelection,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {
        return success(this.areaSelectionService.queryByPage(areaSelection, pageNum, pageSize));
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
        return success(this.areaSelectionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param areaSelection 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping
    public Result add(@RequestBody AreaSelection areaSelection) {
        return success(this.areaSelectionService.insert(areaSelection));
    }

    /**
     * 编辑数据
     *
     * @param areaSelection 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑数据")
    @PutMapping
    public Result edit(@RequestBody AreaSelection areaSelection) {
        return success(this.areaSelectionService.update(areaSelection));
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
        return success(this.areaSelectionService.deleteById(id));
    }

}

