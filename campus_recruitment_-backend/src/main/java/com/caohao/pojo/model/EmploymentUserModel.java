package com.caohao.pojo.model;

import com.caohao.common.enums.EnumEntity;
import com.caohao.common.enums.impl.EmploymentReplyStatus;
import com.caohao.common.enums.impl.EmploymentUserStatus;
import com.caohao.pojo.entity.Employment;
import com.caohao.pojo.entity.EmploymentUser;
import com.caohao.pojo.entity.UserResume;
import lombok.Data;

import java.io.Serializable;

/**
 * (EmploymentUser)实体类
 *
 * @author caohao
 * @since 2021-11-14 17:48:02
 */
@Data
public class EmploymentUserModel extends EmploymentUser {
    private static final long serialVersionUID = -65741003971629401L;


    /**
     * 状态：0待回复，1拒绝入职，2同意入职
     */
    private EnumEntity replyStatus;
    /**
     * 状态：0待回复，1接收offer,2拒绝offer
     */
    private EnumEntity userStatus;

    private EmploymentModel employmentModel;

    private UserResume userResume;



}

