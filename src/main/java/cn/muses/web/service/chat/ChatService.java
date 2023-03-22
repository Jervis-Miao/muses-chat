/**
 * Copyright 2022 All rights reserved.
 */

package cn.muses.web.service.chat;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.muses.utils.JsonMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.muses.exceptions.MusesException;
import cn.muses.web.model.dto.ChatRequestBodyDTO;
import cn.muses.web.service.BaseService;

/**
 * @author jervis
 * @date 2022/11/15.
 */
@Service
public class ChatService extends BaseService {

    @Value("${chat.url}")
    private URI chatUrl;
    @Value("${chat.secretKey}")
    private String secretKey;
    @Value("${chat.model}")
    private String model;
    @Value("${chat.http.connectTimeout}")
    private int restTemplateConnectTimeout;
    @Value("${chat.http.readTimeout}")
    private int restTemplateReadTimeout;

    @Autowired
    private JsonMapper jsonMapper;

    private RestTemplate restTemplate;

    @Autowired
    public ChatService() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(this.restTemplateConnectTimeout);
        requestFactory.setReadTimeout(this.restTemplateReadTimeout);
        this.restTemplate = new RestTemplate(requestFactory);
    }

    public Map requestChat(List<String> contents) throws MusesException {
        if (CollectionUtils.isEmpty(contents)) {
            return null;
        }

        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(this.secretKey);

        // 构建request
        ChatRequestBodyDTO body = new ChatRequestBodyDTO(this.model, contents.stream().filter(StringUtils::isNotBlank)
            .map(ChatRequestBodyDTO.Messages::new).collect(Collectors.toList()));
        HttpEntity request = new HttpEntity(jsonMapper.fromJson(jsonMapper.toJson(body), Map.class), headers);

        return this.doPost(request, Map.class);
    }

    private <T> T doPost(HttpEntity request, Class<T> clazz) throws MusesException {
        try {
            ResponseEntity<T> response = this.restTemplate.postForEntity(this.chatUrl, request, clazz);
            HttpStatus statusCode;
            String errMsg;
            if (!HttpStatus.OK.equals(statusCode = response.getStatusCode())) {
                logger.info(errMsg = String.format("ChatGPT服务请求失败, 接口返回状态码为: %s", statusCode));
                throw new MusesException(errMsg);
            } else if (null == response.getBody()) {
                logger.info(errMsg = String.format("ChatGPT服务请求失败, 接口响应为空, 接口返回状态码为: %s", statusCode));
                throw new MusesException(errMsg);
            } else {
                logger.info("ChatGPT服务请求成功, 接口响应信息为: {}", response.getBody().toString());
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("request chatGPT exception: {}", e.getMessage(), e);
            throw new MusesException("ChatGPT服务异常");
        }
    }
}
