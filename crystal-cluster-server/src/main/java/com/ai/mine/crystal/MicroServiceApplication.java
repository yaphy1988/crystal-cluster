package com.ai.mine.crystal;

import com.ai.mine.security.annotation.EnableMineRestSecurity;
import com.ai.mine.security.annotation.EnableMineWebSecurity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ai.paas", "com.ai.mine", "com.ai.mine.crystal"})
@MapperScan({"com.ai.mine.**.dao.mapper","com.ai.mine.crystal.dao.mapper"})
@EnableMineWebSecurity
@EnableMineRestSecurity
public class MicroServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroServiceApplication.class, args);
    }
}
