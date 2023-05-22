/**
 * Copyright 2022 All rights reserved.
 */

package cn.muses.web.controller;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;

import cn.muses.web.model.BaseResponseVO;

/**
 * @author jervis
 * @date 2022/6/17.
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BaseController() {}

    protected <T> BaseResponseVO<T> returnWithSuccess(T t) {
        return new BaseResponseVO(t);
    }

    protected BaseResponseVO returnWithFail(Integer errorCode, String errorMsg) {
        BaseResponseVO dto = new BaseResponseVO();
        dto.setRet(errorCode);
        dto.getMsg().add(errorMsg);
        return dto;
    }

    protected BaseResponseVO returnWithFail(Integer errorCode, List<String> errorMsg) {
        BaseResponseVO dto = new BaseResponseVO();
        dto.setRet(errorCode);
        dto.getMsg().addAll(errorMsg);
        return dto;
    }

    protected BaseResponseVO returnWithCheckFail(List<? extends ObjectError> errorList) {
        BaseResponseVO dto = new BaseResponseVO();
        if (CollectionUtils.isNotEmpty(errorList)) {
            dto.setRet(BaseResponseVO.DEFAULT_RESPONSE_RESULT.PARAM_CHECK_FAIL.value());
            Iterator var3 = errorList.iterator();

            while (var3.hasNext()) {
                ObjectError error = (ObjectError)var3.next();
                dto.getMsg().add(error.getDefaultMessage());
            }
        }

        return dto;
    }

    protected BaseResponseVO returnWithCheckFail(ObjectError error) {
        BaseResponseVO dto = new BaseResponseVO();
        dto.setRet(BaseResponseVO.DEFAULT_RESPONSE_RESULT.PARAM_CHECK_FAIL.value());
        dto.getMsg().add(error.getDefaultMessage());
        return dto;
    }

    protected BaseResponseVO returnWithCheckFail(String error) {
        BaseResponseVO dto = new BaseResponseVO();
        dto.setRet(BaseResponseVO.DEFAULT_RESPONSE_RESULT.PARAM_CHECK_FAIL.value());
        dto.getMsg().add(error);
        return dto;
    }
}
