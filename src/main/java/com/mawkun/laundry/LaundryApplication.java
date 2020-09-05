package com.mawkun.laundry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ComponentScan("com.mawkun")
@MapperScan(basePackages = {"com.mawkun.laundry.base.dao", "com.mawkun.laundry.dao"})
public class LaundryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaundryApplication.class, args);
    }

}
