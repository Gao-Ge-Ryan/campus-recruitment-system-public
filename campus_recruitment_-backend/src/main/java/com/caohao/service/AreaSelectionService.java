package com.caohao.service;

import com.caohao.common.Result;
import com.caohao.pojo.entity.AreaSelection;
import com.github.pagehelper.PageInfo;

/**
 * (AreaSelection)表服务接口
 *
 * @author caohao
 * @since 2022-04-04 17:01:41
 */
public interface AreaSelectionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AreaSelection queryById(String id);

    /**
     * 分页查询
     *
     * @param areaSelection 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<AreaSelection> queryByPage(AreaSelection areaSelection, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param areaSelection 实例对象
     * @return 实例对象
     */
    AreaSelection insert(AreaSelection areaSelection);

    /**
     * 修改数据
     *
     * @param areaSelection 实例对象
     * @return 实例对象
     */
    AreaSelection update(AreaSelection areaSelection);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    Result queryArea();
}
