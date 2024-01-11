package online.shenjian.api.component;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Utils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 获取国际化信息
     *
     * @param key
     *            String ：传入的国际化key
     * @param obj
     *            Object ：传入的国际化参数
     * @return String 返回国际化信息
     */
    public static String getI18n(String key, Object[] obj) {
        // 目前我在Utils中写死中文，具体使用时请可配置化
        Locale locale = LocaleContextHolder.getLocale();
        String menuName = applicationContext.getMessage(key, obj, "", locale);
        return menuName;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Utils.applicationContext == null) {
            Utils.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
