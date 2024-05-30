package com.caohao.pojo.entity;

import com.caohao.common.enums.impl.CompanyCategoryEnum;
import com.caohao.common.enums.impl.CompanyNatureEnum;
import com.caohao.common.enums.impl.CompanyStatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * (Company)实体类
 *
 * @author caohao
 * @since 2021-11-14 16:28:22
 */
@Data
public class Company implements Serializable {
    private static final long serialVersionUID = 150393452170955368L;

    private String id;
    /**
     * 经营许可证编号
     */
    private String companyNumber;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 公司描述
     */
    private String description;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 修改时间
     */
    private Long updateTime;

    /**
     * 审核人
     */
    private String processor;
    /**
     * 回复时间
     */
    private Long replyTime;
    /**
     * 回复内容
     */
    private Long replyContent;
    /**
     * 公司人数
     */
    private Integer number;

    private String companyLogo;



}

