/*
 * Copyright (c) 2023. Muses Co., Ltd. All rights reserved.
 */

package cn.muses.web.service.openai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.muses.exceptions.MusesException;
import cn.muses.web.service.openai.dto.OpenAiReqDTO;
import cn.muses.web.service.openai.dto.OpenAiResDTO;
import cn.muses.web.service.openai.handler.AbstractOpenAiHandler;

/**
 * @author jervis
 * @date 2023/5/16.
 */
public abstract class AbstractOpenAiService<T extends OpenAiReqDTO, R extends OpenAiResDTO> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** OPENAI MODEL 接口处理器 **/
    private AbstractOpenAiHandler<T, R> handler;

    public AbstractOpenAiService(AbstractOpenAiHandler handler) {
        this.handler = handler;
    }

    /**
     * 请求 OPENAI MODEL 接口
     * 
     * @param body
     * @return
     * @throws MusesException
     */
    public final R executeOpenai(Object body) throws MusesException {
        return this.handler.execute(this.convertRequest(body));
    }

    /**
     * 转换请求体
     * 
     * @param body
     * @return
     */
    protected abstract T convertRequest(Object body);
}
