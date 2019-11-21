package com.em;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author ll
 * @Date 2019/11/12
 * @Time 8:31
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.em.mapper")
@EnableSwagger2
@Import(FdfsClientConfig.class)
@Configuration
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        System.out.println("该服务的名称为：{" + context.getEnvironment().getProperty("spring.application.name") + "}");
        System.out.println("该服务器启动的端口：{" + context.getEnvironment().getProperty("server.port") + "}");
    }
}
