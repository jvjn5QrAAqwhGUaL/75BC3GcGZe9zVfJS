package moe.dic1911.esun_library;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@SpringBootApplication
@RestController
@EnableScheduling
public class EsunLibraryApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EsunLibraryApplication.class).bannerMode(Banner.Mode.OFF).run();
    }

    @GetMapping("/ping")
    public String test() {
//        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/index")).build();
        return "Pong!";
    }

}
