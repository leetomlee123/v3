package com.lx.backstagemanagement.conf;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.nio.charset.Charset;
import java.util.List;


@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);


    // 使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                // 保留空的字段
                SerializerFeature.WriteMapNullValue,
                // String null -> ""
                SerializerFeature.WriteNullStringAsEmpty,
                // Number null -> 0
                SerializerFeature.WriteNullNumberAsZero);
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

//	// 统一异常处理
//	@Override
//	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//		exceptionResolvers.add(new HandlerExceptionResolver() {
//			@Override
//			public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
//					Object handler, Exception e) {
//				ResultCode result = null;
//				if (handler instanceof HandlerMethod) {
//					HandlerMethod handlerMethod = (HandlerMethod) handler;
//
//					if (e instanceof ServiceException) {
//						// 业务失败的异常，如“账号或密码错误”
//						result = ResultCode.getFailure(ResultCode.CODE_UNKNOWN_ERROR, e.getMessage());
//						logger.info(e.getMessage());
//					} else {
//						result = ResultCode.getFailure(ResultCode.INTERNAL_SERVER_ERROR,
//								"接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
//						String message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(),
//								handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod().getName(),
//								e.getMessage());
//						logger.error(message, e);
//					}
//				} else {
//					if (e instanceof NoHandlerFoundException) {
//						result = ResultCode.getFailure(ResultCode.NOT_FOUND,
//								"接口 [" + request.getRequestURI() + "] 不存在");
//					} else {
//						result = ResultCode.getFailure(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
//						logger.error(e.getMessage(), e);
//					}
//				}
//				responseResult(response, result);
//				return new ModelAndView();
//			}
//
//		});
//	}


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/image/**").addResourceLocations("/image/");
        registry.addResourceHandler("/WEB-INF/**").addResourceLocations("/WEB-INF/");
//		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }


//    @Bean
//    public MultipartResolver multipartResolver() {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setMaxUploadSize(1000000);
//        return resolver;
//    }
}






