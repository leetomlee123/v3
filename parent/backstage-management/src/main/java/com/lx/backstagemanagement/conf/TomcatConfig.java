//package com.lx.backstagemanagement.conf;
//
//import org.junit.Test;
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.File;
//
//@Configuration
//public class TomcatConfig {
//    @Bean
//    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
//        ConfigurableEmbeddedServletContainer factory = new TomcatEmbeddedServletContainerFactory();
//        factory.setDocumentRoot(new File("src//main//webapp//"));
//        return (EmbeddedServletContainerFactory) factory;
//    }
//    @Test
//    public void s(){
//        System.out.println(getClass().getResource("../../../.."));
//    }
//}