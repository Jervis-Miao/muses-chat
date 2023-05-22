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
public class MessageDTO extends BaseDTO {
    private static final long serialVersionUID = 160914436631988843L;

    private ROLE role;

    private String content;

    public MessageDTO() {}

    public MessageDTO(String content) {
        this(ROLE.USER, content);
    }

    public MessageDTO(ROLE role, String content) {
        this.role = role;
        this.content = content;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("role", role)
            .append("content", content)
            .toString();
    }

    /** 角色 **/
    public enum ROLE {
        // 系统
        SYSTEM,

        // 用户
        USER,

        // AI助理答复
        ASSISTANT
    }
}
