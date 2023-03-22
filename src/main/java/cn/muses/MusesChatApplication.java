package cn.muses;

/**
 * Copyright 2022 All rights reserved.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jervis
 * @date 2022/11/14.
 */
@SpringBootApplication
public class MusesChatApplication {

    /**
     * 启动
     *
     * @param args
     */
    public static void main(String[] args) {
        loadConfiguration();
        SpringApplication.run(MusesChatApplication.class, args);
    }

    /**
     * 加载配置
     */
    private static final void loadConfiguration() {
        System.setProperty("test", "test");
    }
}
