package com.caohao;

import com.caohao.controller.websocket.server.WebsocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.caohao.dao")
@EnableSwagger2
public class CampusApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CampusApplication.class, args);
        // 解决Websocket空注入问题
        WebsocketServer.setApplicationContext(applicationContext);
    }
}
