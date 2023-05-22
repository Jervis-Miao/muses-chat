/**
 * Copyright 2022 All rights reserved.
 */

package cn.muses.web.controller.openai;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.muses.exceptions.MusesException;
import cn.muses.web.controller.BaseController;
import cn.muses.web.model.BaseResponseVO;
import cn.muses.web.service.openai.AbstractOpenAiService;
import cn.muses.web.service.openai.IOpenAiHandler;
import cn.muses.web.service.openai.dto.OpenAiResDTO;

/**
 * @author jervis
 * @date 2022/11/15.
 */
@RestController
@RequestMapping("v1")
public class OpenAiController extends BaseController {

    @Autowired
    private Map<String, AbstractOpenAiService> openAiServices;

    @PostMapping("{api}/completions")
    public BaseResponseVO<OpenAiResDTO> requestChatGpt(@PathVariable IOpenAiHandler.API api, @RequestBody Object body)
        throws MusesException {
        AbstractOpenAiService openAiService;
        if (null == (openAiService = openAiServices.get(api.name()))) {
            throw new MusesException("The API you are visiting does not exist or is not supported!");
        }
        return this.returnWithSuccess(openAiService.executeOpenai(body));
    }
}
