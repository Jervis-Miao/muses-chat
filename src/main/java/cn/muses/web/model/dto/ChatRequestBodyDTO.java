/**
 * Copyright 2022 XYZ Co., Ltd. All Rights Reserved
 */
package cn.muses.web.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author jervis
 * @date 2023/3/21.
 */
public class ChatRequestBodyDTO extends BaseDTO {
    private static final long serialVersionUID = -3239829317699694073L;

    /**
     * ID of the model to use. See the model endpoint compatibility table for details on which <br/>
     * models work with the Chat API.
     */
    private String model;

    /**
     * The messages to generate chat completions for, in the chat format.
     */
    private List<Messages> messages;

    /**
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output <br/>
     * more random, while lower values like 0.2 will make it more focused and deterministic. <br/>
     * We generally recommend altering this or top_p but not both.
     */
    private BigDecimal temperature;

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model <br/>
     * considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens <br/>
     * comprising the top 10% probability mass are considered. <br/>
     *
     * We generally recommend altering this or temperature but not both.
     */
    private BigDecimal top_p;

    /**
     * How many chat completion choices to generate for each input message.
     */
    private Integer n;

    /**
     * If set, partial message deltas will be sent, like in ChatGPT. Tokens will be sent as data-only <br/>
     * server-sent events as they become available, with the stream terminated by a data: <br/>
     * [DONE] message. See the OpenAI Cookbook for example code.
     */
    private Boolean stream;

    /**
     * string or array <br/>
     * Up to 4 sequences where the API will stop generating further tokens.
     */
    private Object stop;

    /**
     * The maximum number of tokens to generate in the chat completion. <br/>
     * The total length of input tokens and generated tokens is limited by the model's context length.
     */
    private Integer max_tokens;

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing <br/>
     * frequency in the text so far, decreasing the model's likelihood to repeat the same line verbatim.
     */
    private BigDecimal presence_penalty;

    /**
     * Modify the likelihood of specified tokens appearing in the completion.
     *
     * Accepts a json object that maps tokens (specified by their token ID in the tokenizer) to an <br/>
     * associated bias value from -100 to 100. Mathematically, the bias is added to the logits <br/>
     * generated by the model prior to sampling. The exact effect will vary per model, but values <br/>
     * between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 <br/>
     * should result in a ban or exclusive selection of the relevant token.
     */
    private Object logit_bias;

    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     */
    private String user;

    public ChatRequestBodyDTO() {
        super();
    }

    public ChatRequestBodyDTO(String model, List<Messages> messages) {
        super();
        this.model = model;
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getTop_p() {
        return top_p;
    }

    public void setTop_p(BigDecimal top_p) {
        this.top_p = top_p;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Boolean getStream() {
        return stream;
    }

    public void setStream(Boolean stream) {
        this.stream = stream;
    }

    public Object getStop() {
        return stop;
    }

    public void setStop(Object stop) {
        this.stop = stop;
    }

    public Integer getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(Integer max_tokens) {
        this.max_tokens = max_tokens;
    }

    public BigDecimal getPresence_penalty() {
        return presence_penalty;
    }

    public void setPresence_penalty(BigDecimal presence_penalty) {
        this.presence_penalty = presence_penalty;
    }

    public Object getLogit_bias() {
        return logit_bias;
    }

    public void setLogit_bias(Object logit_bias) {
        this.logit_bias = logit_bias;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("model", model)
            .append("messages", messages)
            .append("temperature", temperature)
            .append("top_p", top_p)
            .append("n", n)
            .append("stream", stream)
            .append("stop", stop)
            .append("max_tokens", max_tokens)
            .append("presence_penalty", presence_penalty)
            .append("logit_bias", logit_bias)
            .append("user", user)
            .toString();
    }

    public static class Messages implements Serializable {
        private static final long serialVersionUID = -3468469927878643188L;

        private ROLE role;

        private String content;

        public Messages() {}

        public Messages(String content) {
            this(ROLE.user, content);
        }

        public Messages(ROLE role, String content) {
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
    }

    /** 角色 **/
    public enum ROLE {

        // 用户
        user
    }
}
