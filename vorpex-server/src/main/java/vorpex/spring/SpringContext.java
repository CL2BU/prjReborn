package vorpex.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {

	private static ApplicationContext context;
    private static SpringContext instance;
    private final static String CONFIG_FILE = "spring-config.xml";

    public static SpringContext getInstance() {
        if (null == instance) {
            instance = new SpringContext();
            context = new ClassPathXmlApplicationContext(
                    CONFIG_FILE);
        }
        return instance;
    }

    public Object getBean(String bean) {
        return context.getBean(bean);
    }
}
