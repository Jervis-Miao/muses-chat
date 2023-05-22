/*
 * Copyright (c) 2023. Muses Co., Ltd. All rights reserved.
 */

package cn.muses.web.service.openai.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import cn.muses.exceptions.MusesException;
import cn.muses.web.service.openai.IOpenAiHandler;
import cn.muses.web.service.openai.dto.OpenAiReqDTO;
import cn.muses.web.service.openai.dto.OpenAiResDTO;

/**
 * @author jervis
 * @date 2023/5/17.
 */
public abstract class AbstractOpenAiHandler<T extends OpenAiReqDTO, R extends OpenAiResDTO>
    implements IOpenAiHandler<T, R> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${openai.baseUrl}")
    private String baseUrl;
    @Value("${openai.head.secretKey}")
    private String secretKey;
    @Value("${openai.head.organization}")
    private String organization;
    @Value("${openai.model}")
    private String model;
    @Value("${openai.http.connectTimeout}")
    private int restTemplateConnectTimeout;
    @Value("${openai.http.readTimeout}")
    private int restTemplateReadTimeout;

    private RestTemplate restTemplate;

    public AbstractOpenAiHandler() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(this.restTemplateConnectTimeout);
        requestFactory.setReadTimeout(this.restTemplateReadTimeout);
        this.restTemplate = new RestTemplate(requestFactory);
    }

    @Override
    public R execute(T request) throws MusesException {
        ResponseEntity<String> response;
        try {
            response = this.restTemplate.postForEntity(this.getUrl(), this.prepareRequest(request), String.class);
        } catch (Exception e) {
            logger.error("request chatGPT exception: {}", e.getMessage(), e);
            throw new MusesException("ChatGPT服务异常");
        }
        HttpStatus statusCode;
        String errMsg, resBody;
        if (!HttpStatus.OK.equals(statusCode = response.getStatusCode())) {
            logger.info(errMsg = String.format("ChatGPT服务请求失败, 接口返回状态码为: %s", statusCode));
            throw new MusesException(errMsg);
        } else if (null == (resBody = response.getBody())) {
            logger.info(errMsg = String.format("ChatGPT服务请求失败, 接口响应为空, 接口返回状态码为: %s", statusCode));
            throw new MusesException(errMsg);
        } else {
            logger.info("ChatGPT服务请求成功, 接口响应信息为: {}", resBody);
            return this.handleResponse(response);
        }
    }

    /**
     * 获取接口地址
     *
     * @return
     */
    private String getUrl() {
        return new StringBuilder(this.baseUrl).append(this.getEndpoint()).toString();
    }

    /**
     * 准备需要发送给OpenAI API的请求。包括设置请求头、请求体等。
     *
     * @param t
     * @return
     */
    private HttpEntity prepareRequest(T t) {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(this.secretKey);
        headers.set("OpenAI-Organization", this.organization);

        // 请求体
        t.setModel(this.model);
        return new HttpEntity(this.getRequestBody(t), headers);
    }

    /**
     * 获取接口地址
     *
     * @return
     */
    protected abstract String getEndpoint();

    /**
     * 获取请求体。
     * 
     * @param t
     * @return
     */
    protected abstract Object getRequestBody(T t);

    /**
     * 处理从OpenAI API接收到的响应。包括解析响应体、处理错误等。
     * 
     * @param response
     * @return
     * @throws MusesException
     */
    protected abstract R handleResponse(ResponseEntity response) throws MusesException;
}
