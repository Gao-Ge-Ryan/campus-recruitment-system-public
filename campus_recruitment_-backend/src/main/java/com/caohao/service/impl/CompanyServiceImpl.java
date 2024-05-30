package com.caohao.service.impl;

import com.caohao.common.utils.DateUtil;
import com.caohao.common.utils.IDGenerator;
import com.caohao.dao.EmploymentDao;
import com.caohao.dao.EmploymentUserDao;
import com.caohao.pojo.entity.Company;
import com.caohao.dao.CompanyDao;
import com.caohao.pojo.entity.Employment;
import com.caohao.pojo.model.CompanyModel;
import com.caohao.pojo.model.EmploymentModel;
import com.caohao.pojo.param.CompanyParam;
import com.caohao.pojo.param.EmploymentParam;
import com.caohao.service.CompanyService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Company)表服务实现类
 *
 * @author caohao
 * @since 2021-11-14 16:28:23
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
    @Resource
    private CompanyDao companyDao;

    @Resource
    private EmploymentDao employmentDao;

    @Resource
    private EmploymentUserDao employmentUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CompanyModel queryById(String id) {
        CompanyModel companyModel = this.companyDao.queryById(id);
        List<EmploymentModel> employmentModels = employmentDao.selectByCompanyId(id);
        companyModel.setEmploymentModels(employmentModels);
        return companyModel;
    }

    /**
     * 分页查询
     *
     * @param company 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @Override
    public PageInfo<CompanyModel> queryByPage(CompanyParam company, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CompanyModel> companys =this.companyDao.queryAllByLimit(company);
        for (CompanyModel companyModel : companys) {
            List<EmploymentModel> employmentModels=employmentDao.selectByCompanyId(companyModel.getId());
            companyModel.setPublishedSize(employmentModels.size());
        }
        List<CompanyModel> collect = companys.stream().filter(n -> n.getCompanyNumber() != null).collect(Collectors.toList());

        return new PageInfo<>(collect);
    }

    /**
     * 新增数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    @Override
    public CompanyParam insert(CompanyParam company) {
        company.setId(IDGenerator.StringID());
        company.setCreateTime(DateUtil.getCurrentTimeMillis());
        company.setUpdateTime(DateUtil.getCurrentTimeMillis());
        this.companyDao.insert(company);
        return company;
    }

    /**
     * 修改数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    @Override
    public CompanyModel update(CompanyParam company) {
        company.setUpdateTime(DateUtil.getCurrentTimeMillis());
        this.companyDao.update(company);
        return this.queryById(company.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        EmploymentParam employmentParam = new EmploymentParam();
        employmentParam.setCompanyId(id);
        List<EmploymentModel> employmentModels = employmentDao.queryAllByLimit(employmentParam);
        for (EmploymentModel employmentModel : employmentModels) {
            employmentUserDao.deleteByEmployId(employmentModel.getId());
        }

        employmentDao.deleteByCompanyId(id);
        return this.companyDao.deleteById(id) > 0;
    }

    @Override
    public CompanyModel queryByUsername(String username) {
        return companyDao.queryByUsername(username);
    }
}
