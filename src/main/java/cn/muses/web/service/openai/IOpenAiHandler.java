/*
 * Copyright (c) 2023. Muses Co., Ltd. All rights reserved.
 */

package cn.muses.web.service.openai;

import cn.muses.exceptions.MusesException;
import cn.muses.web.service.openai.dto.OpenAiReqDTO;
import cn.muses.web.service.openai.dto.OpenAiResDTO;

/**
 * @author jervis
 * @date 2023/5/17.
 */
public interface IOpenAiHandler<T extends OpenAiReqDTO, R extends OpenAiResDTO> {

    /**
     * 执行特定的任务并返回结果。
     * 
     * @param request
     * @return
     * @throws MusesException
     */
    R execute(T request) throws MusesException;

    /** OPENAI API **/
    enum API {
        /** Learn how to turn audio into text. **/
        audio,

        /** Given a list of messages describing a conversation, the model will return a response. **/
        chat,

        /** Given a prompt and an instruction, the model will return an edited version of the prompt. **/
        edit,

        /**
         * Get a vector representation of a given input that can be easily consumed by machine learning models and
         * algorithms.
         */
        embedding,

        /** Files are used to upload documents that can be used with features like Fine-tuning. **/
        file,

        /** Manage fine-tuning jobs to tailor a model to your specific training data **/
        fine_tune,

        /** Given a prompt and/or an input image, the model will generate a new image. **/
        image,

        /** Given a input text, outputs if the model classifies it as violating OpenAI's content policy. **/
        moderation;
    }
}
