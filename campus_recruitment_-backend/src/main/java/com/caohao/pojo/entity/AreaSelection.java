package com.caohao.pojo.entity;

import java.io.Serializable;

/**
 * (AreaSelection)实体类
 *
 * @author caohao
 * @since 2022-04-04 17:01:41
 */
public class AreaSelection implements Serializable {
    private static final long serialVersionUID = 611366085084252529L;
    /**
     * id
     */
    private String id;
    /**
     * 行政区划代码
     */
    private String code;
    /**
     * 行政区划名称
     */
    private String name;
    /**
     * 上级代码
     */
    private String parentCode;
    /**
     * 级别
     */
    private String level;
    /**
     * 热门城市
     */
    private Integer hot;
    /**
     * 备注
     */
    private String remark;
    /**
     * 序列

     */
    private String sequence;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

}

