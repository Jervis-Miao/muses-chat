/*
 * Copyright (c) 2023. Muses Co., Ltd. All rights reserved.
 */

package cn.muses.web.service.openai.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cn.muses.exceptions.MusesException;
import cn.muses.web.service.openai.dto.OpenAiReqDTO;
import cn.muses.web.service.openai.dto.OpenAiResDTO;

/**
 * @author jervis
 * @date 2023/5/17.
 */
@Component
public class FileHandler extends AbstractOpenAiHandler<OpenAiReqDTO, OpenAiResDTO> {

    @Override
    protected String getEndpoint() {
        return null;
    }

    @Override
    protected Object getRequestBody(OpenAiReqDTO request) {
        return null;
    }

    @Override
    protected OpenAiResDTO handleResponse(ResponseEntity response) throws MusesException {
        return null;
    }
}
