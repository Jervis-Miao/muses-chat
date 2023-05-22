/*
 * Copyright (c) 2023. Muses Co., Ltd. All rights reserved.
 */

package cn.muses.web.service.openai.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cn.muses.utils.JsonMapper;
import cn.muses.web.service.openai.dto.ChatGptReqDTO;
import cn.muses.web.service.openai.dto.ChatGptResDTO;

/**
 * @author jervis
 * @date 2023/5/17.
 */
@Component
public class ChatHandler extends AbstractOpenAiHandler<ChatGptReqDTO, ChatGptResDTO> {

    @Autowired
    private JsonMapper jsonMapper;

    @Override
    protected String getEndpoint() {
        return "chat/completions";
    }

    @Override
    protected Map getRequestBody(ChatGptReqDTO request) {
        // 构建request请求体
        return jsonMapper.fromJson(jsonMapper.toJson(request), Map.class);
    }

    @Override
    public ChatGptResDTO handleResponse(ResponseEntity response) {
        return null;
    }
}
