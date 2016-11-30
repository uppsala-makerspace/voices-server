package se.uppsalamakerspace.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by fredl2 on 2016-10-27.
 */
@SpringBootApplication
@EnableCaching
public class VoicesServer {

    public static void main(String[] args) {
        SpringApplication.run(VoicesServer.class, args);
    }
}
