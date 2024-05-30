package com.caohao.pojo.param;

import com.caohao.common.enums.impl.EmploymentReplyStatus;
import com.caohao.common.enums.impl.EmploymentUserStatus;
import com.caohao.pojo.entity.EmploymentUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * (EmploymentUser)实体类
 *
 * @author caohao
 * @since 2021-11-14 17:48:02
 */
@Data
public class EmploymentUserParam extends EmploymentUser {
    private static final long serialVersionUID = -65741003971629401L;


    /**
     * 状态：0待回复，1拒绝入职，2同意入职
     */
    private EmploymentReplyStatus replyStatus;
    /**
     * 状态：0待回复，1接收offer,2拒绝offer
     */
    private EmploymentUserStatus userStatus;

    private String companyId;

    private List<String> ids;



}

