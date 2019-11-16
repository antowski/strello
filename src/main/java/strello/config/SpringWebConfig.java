package strello.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan({"strello"})
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ViewResolver viewResolver() {

        VelocityLayoutViewResolver resolver = new VelocityLayoutViewResolver();

        resolver.setPrefix("/WEB-INF/velocity/view/");
        resolver.setLayoutUrl("/WEB-INF/velocity/layout.vm");
        resolver.setSuffix(".vm");
        resolver.setContentType("text/html; charset=UTF-8");

        return resolver;

    }

    @Bean
    public VelocityConfigurer velocityConfig() {

        Properties config = new Properties();
        config.setProperty("input.encoding", "UTF-8");
        config.setProperty("output.encoding", "UTF-8");
        config.setProperty("default.contentType", "text/html;charset=UTF-8");

        VelocityConfigurer vc = new VelocityConfigurer();
        vc.setResourceLoaderPath("/");
        vc.setVelocityProperties(config);

        return vc;

    }

}
