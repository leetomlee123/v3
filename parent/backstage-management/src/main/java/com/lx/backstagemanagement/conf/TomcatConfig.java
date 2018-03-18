package com.lx.backstagemanagement.conf;

import org.junit.Test;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class TomcatConfig {
    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        ConfigurableEmbeddedServletContainer factory = new TomcatEmbeddedServletContainerFactory();
        factory.setDocumentRoot(new File("D:\\【批量下载】WEB32_网上商城等\\WEB33_网上商城\\WEB33_网上商城\\代码\\parent\\backstage-management\\src\\main\\webapp\\"));
        return (EmbeddedServletContainerFactory) factory;
    }

}