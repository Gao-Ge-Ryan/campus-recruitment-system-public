package com.caohao.pojo.entity;

import com.caohao.common.enums.impl.EmploymentEducationEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * (Employment)实体类
 *
 * @author caohao
 * @since 2021-11-14 17:17:17
 */
@Data
public class Employment implements Serializable {
    private static final long serialVersionUID = 104752132349503805L;

    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;

    /**
     * 位置
     */
    private String position;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 修改时间
     */
    private Long updateTime;
    /**
     * 状态：0未发布，1已发布
     */
    private Integer status;
    /**
     * 工资
     */
    private String minSalary;
    /**
     * 公司id
     */
    private String companyId;
    /**
     * 待遇
     */
    private String treatment;

    private String maxSalary;




}

