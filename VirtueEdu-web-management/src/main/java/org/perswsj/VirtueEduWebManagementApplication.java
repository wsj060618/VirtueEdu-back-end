package org.perswsj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class VirtueEduWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtueEduWebManagementApplication.class, args);
    }

}
