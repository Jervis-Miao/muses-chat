/*
 * Copyright (c) 2023. Muses Co., Ltd. All rights reserved.
 */

package cn.muses.web.service.openai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.muses.web.service.openai.dto.ChatGptReqDTO;
import cn.muses.web.service.openai.dto.ChatGptResDTO;
import cn.muses.web.service.openai.handler.ChatHandler;

/**
 * @author jervis
 * @date 2023/5/19.
 */
@Component("chat")
public class ChatService extends AbstractOpenAiService<ChatGptReqDTO, ChatGptResDTO> {

    @Autowired
    public ChatService(ChatHandler handler) {
        super(handler);
    }

    @Override
    protected ChatGptReqDTO convertRequest(Object body) {
        return null;
    }
}
