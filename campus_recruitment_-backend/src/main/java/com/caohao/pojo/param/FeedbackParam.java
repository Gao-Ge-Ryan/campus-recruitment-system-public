package com.caohao.pojo.param;

import com.caohao.common.enums.impl.FeedBackStatusEnum;
import com.caohao.common.enums.impl.FeedBackTypeEnum;
import com.caohao.pojo.entity.Feedback;
import lombok.Data;

import java.io.Serializable;

/**
 * 问题反馈(Feedback)实体类
 *
 * @author caohao
 * @since 2021-11-14 18:36:17
 */
@Data
public class FeedbackParam extends Feedback {
    private static final long serialVersionUID = -73116585888000840L;


    /**
     * 类型：1bug反馈，2建议，3投诉
     */
    private FeedBackTypeEnum type;

    /**
     * 状态：0未回复，1已回复
     */
    private FeedBackStatusEnum status;



}

