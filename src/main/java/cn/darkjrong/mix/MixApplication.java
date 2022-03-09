package cn.darkjrong.mix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * MIX 服务
 *
 * @author Rong.Jia
 * @date 2022/03/08
 */
@MapperScan("cn.darkjrong.mix.mapper")
@EnableScheduling
@EnableCaching
@EnableAsync
@SpringBootApplication
public class MixApplication {

    public static void main(String[] args) {
        SpringApplication.run(MixApplication.class, args);
    }

}
