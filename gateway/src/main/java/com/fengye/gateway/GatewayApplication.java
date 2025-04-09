package com.fengye.gateway;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class GatewayApplication
{
    private static final Logger log = LoggerFactory.getLogger(GatewayApplication.class);
    public static void main( String[] args )
    {
        SpringApplication app = new SpringApplication(GatewayApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("启动成功！！");
        log.info("测试地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }
}
