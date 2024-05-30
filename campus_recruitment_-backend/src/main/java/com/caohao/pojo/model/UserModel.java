package com.caohao.pojo.model;

import com.caohao.common.enums.EnumEntity;
import com.caohao.common.enums.impl.UserRoleEnum;
import com.caohao.pojo.entity.Feedback;
import com.caohao.pojo.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author caohao
 * @since 2021-11-14 18:48:16
 */
@Data
public class UserModel extends User {
    private static final long serialVersionUID = -80774066608981041L;

    /**
     * 角色：user普通用户，enterprise企业，admin管理员
     */
    private EnumEntity role;




}

