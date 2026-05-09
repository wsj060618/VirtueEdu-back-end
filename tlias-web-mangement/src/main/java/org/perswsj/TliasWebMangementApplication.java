package org.perswsj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class TliasWebMangementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebMangementApplication.class, args);
    }

}
