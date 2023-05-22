/*
 * Copyright (c) 2023. Muses Co., Ltd. All rights reserved.
 */

package cn.muses.web.service.openai.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.muses.web.service.BaseDTO;

/**
 * @author jervis
 * @date 2023/5/17.
 */
public class OpenAiReqDTO extends BaseDTO {
    private static final long serialVersionUID = -4903993150194787240L;

    /**
     * ID of the model to use. See the model endpoint compatibility table for details on which <br/>
     * models work with the Chat API.
     */
    private String model;

    public OpenAiReqDTO() {}

    public OpenAiReqDTO(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("model", model)
            .toString();
    }
}
