package ua.shield;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by sa on 03.12.17.
 */
@SpringBootApplication
public class SpringBootHospitalApplication extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(SpringBootHospitalApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHospitalApplication.class, args);
    }
}
