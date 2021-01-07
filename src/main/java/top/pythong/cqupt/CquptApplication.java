package top.pythong.cqupt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.pythong.cqupt.config.SwaggerConfig;

@SpringBootApplication
//扫描 mybatis mapper 包路径
@MapperScan(basePackages = "top.pythong.cqupt.mapper")
@EnableTransactionManagement
@EnableConfigurationProperties(SwaggerConfig.class)
public class CquptApplication {

    public static void main(String[] args) {
        SpringApplication.run(CquptApplication.class, args);
    }

}
