package com.caohao.pojo.param;

import com.caohao.common.enums.impl.CompanyCategoryEnum;
import com.caohao.common.enums.impl.CompanyNatureEnum;
import com.caohao.common.enums.impl.CompanyStatusEnum;
import com.caohao.pojo.entity.Company;
import lombok.Data;

import java.io.Serializable;

/**
 * (Company)实体类
 *
 * @author caohao
 * @since 2021-11-14 16:28:22
 */
@Data
public class CompanyParam extends Company {
    private static final long serialVersionUID = 150393452170955368L;


    /**
     * 状态：0待审核，1通过，2审核不通过
     */
    private CompanyStatusEnum status;

    /**
     * 性质：1私企，2国企，3上市公司
     */
    private CompanyNatureEnum nature;

    /**
     * 公司类别
     */
    private CompanyCategoryEnum category;


}

