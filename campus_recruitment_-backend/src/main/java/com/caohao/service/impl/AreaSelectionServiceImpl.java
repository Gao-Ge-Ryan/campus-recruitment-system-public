package com.caohao.service.impl;

import com.caohao.common.Result;
import com.caohao.pojo.entity.AreaSelection;
import com.caohao.dao.AreaSelectionDao;
import com.caohao.service.AreaSelectionService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.List;

/**
 * (AreaSelection)表服务实现类
 *
 * @author caohao
 * @since 2022-04-04 17:01:41
 */
@Service("areaSelectionService")
public class AreaSelectionServiceImpl implements AreaSelectionService {
    @Resource
    private AreaSelectionDao areaSelectionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AreaSelection queryById(String id) {
        return this.areaSelectionDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param areaSelection 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @Override
    public PageInfo<AreaSelection> queryByPage(AreaSelection areaSelection,  Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AreaSelection> areaSelections =this.areaSelectionDao.queryAllByLimit(areaSelection);
        return new PageInfo<>(areaSelections);
    }

    /**
     * 新增数据
     *
     * @param areaSelection 实例对象
     * @return 实例对象
     */
    @Override
    public AreaSelection insert(AreaSelection areaSelection) {
        this.areaSelectionDao.insert(areaSelection);
        return areaSelection;
    }

    /**
     * 修改数据
     *
     * @param areaSelection 实例对象
     * @return 实例对象
     */
    @Override
    public AreaSelection update(AreaSelection areaSelection) {
        this.areaSelectionDao.update(areaSelection);
        return this.queryById(areaSelection.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.areaSelectionDao.deleteById(id) > 0;
    }

    @Override
    public Result queryArea() {
//        List<AreaSelection> areaSelections= areaSelectionDao.selectByParentCode();
        return null;
    }
}
