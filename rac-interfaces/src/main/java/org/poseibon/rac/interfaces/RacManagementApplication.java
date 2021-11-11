package org.poseibon.rac.interfaces;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用入口类
 *
 * @author qingchuan
 * @date 2020/12/06
 */
@EnableDubbo
@MapperScan("org.poseibon.rac.infrastructure.mapper")
@SpringBootApplication(scanBasePackages = "org.poseibon.rac")
public class RacManagementApplication {

    private static final Logger log = LoggerFactory.getLogger(RacManagementApplication.class);

    /**
     * 主函数
     *
     * @param args 应用启动入参
     */
    public static void main(String[] args) {
        SpringApplication.run(RacManagementApplication.class, args);
        log.info("rac management application started!");
    }

}

