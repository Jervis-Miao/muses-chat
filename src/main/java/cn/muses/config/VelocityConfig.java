package cn.muses.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.ToolManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.velocity.VelocityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import com.alibaba.boot.velocity.VelocityConstants;

import cn.muses.utils.VelocityUtils;

/**
 * Velocity bean 配置
 *
 * @author Jervis.
 * @date 2021/02/02 16:05
 * @see
 * @since
 */
public class VelocityConfig {

}
