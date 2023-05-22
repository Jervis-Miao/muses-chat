/*
 * Copyright (c) 2023. Muses Co., Ltd. All rights reserved.
 */

package cn.muses.web.model;

import java.util.StringJoiner;

/**
 * @author jervis
 * @date 2020/12/3.
 */
public class ApiBaseResponseVO<T> extends BaseResponseVO<T> {
    private static final long serialVersionUID = 9018073932146610258L;

    private String signature;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApiBaseResponseVO.class.getSimpleName() + "[", "]")
            .add("signature='" + signature + "'")
            .toString();
    }
}
