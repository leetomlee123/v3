package com.lx.backstagemanagement;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(FdfsClientConfig.class)
@SpringBootApplication


public class BackstageManagementApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BackstageManagementApplication.class, args);
    }
}