package com.caohao.pojo.param;

import com.caohao.common.enums.impl.EmploymentEducationEnum;
import com.caohao.pojo.entity.Employment;
import lombok.Data;

import java.io.Serializable;

/**
 * (Employment)实体类
 *
 * @author caohao
 * @since 2021-11-14 17:17:17
 */
@Data
public class EmploymentParam extends Employment {
    private static final long serialVersionUID = 104752132349503805L;


    /**
     * 学历
     */
    private EmploymentEducationEnum education;





}

