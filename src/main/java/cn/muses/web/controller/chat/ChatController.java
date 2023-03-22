/**
 * Copyright 2022 All rights reserved.
 */

package cn.muses.web.controller.chat;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.muses.exceptions.MusesException;
import cn.muses.web.controller.BaseController;
import cn.muses.web.model.dto.BaseResponseDTO;
import cn.muses.web.service.chat.ChatService;

/**
 * @author jervis
 * @date 2022/11/15.
 */
@RestController
@RequestMapping("v1/chat")
public class ChatController extends BaseController {

    @Autowired
    private ChatService chatService;

    @PostMapping("completions")
    public BaseResponseDTO<Map> requestChat(@RequestBody List<String> contents) throws MusesException {
        return this.returnWithSuccess(chatService.requestChat(contents));
    }
}
