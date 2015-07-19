package spring;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationContext {
	private SpringApplicationContext() {
	}

	private static AnnotationConfigApplicationContext ctx;

	public static void initialize() {
		try {
			ctx = new AnnotationConfigApplicationContext();
			ctx.scan("spring.configurations", "redis", "controllers", "services");
			ctx.refresh();
		} catch (Exception e) {
			String msg = "Application Context could not be initialized properly";
			System.out.println(msg + ": " + ExceptionUtils.getStackTrace(e));
			throw new IllegalStateException(msg, e);
		}
	}

	public static <T> T getBean(Class<T> beanClass) {
		return ctx.getBean(beanClass);
	}

	public static <T> T getBeanNamed(String beanName, Class<T> beanClass) {
		return ctx.getBean(beanName, beanClass);
	}

	public static void close() {
		if (ctx != null) {
			ctx.close();
			ctx = null;
		}
	}
}
