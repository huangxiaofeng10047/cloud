package online.shenjian.api.config;

import java.util.Locale;
import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class LocaleConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        Locale.setDefault(Locale.CHINA);
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        //设置国际化文件存储路径和名称    i18n目录，messages文件名
        source.setBasenames("i18n/messages", "i18n/error", "i18n/message-system");
        //设置根据key如果没有获取到对应的文本信息,则返回key作为信息
        source.setUseCodeAsDefaultMessage(true);
        //设置字符编码
        source.setDefaultEncoding("UTF-8");
        return source;
    }
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.CHINA);
        localeResolver.setDefaultTimeZone(TimeZone.getTimeZone("UTC"));
        return localeResolver;
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        //通过localeData来设置语言
        //http://localhost:8080/login?localeData=zh 中文
        //http://localhost:8080/login?localeData=en 英文
        localeChangeInterceptor.setParamName("localeData");
        return localeChangeInterceptor;
    }

}

