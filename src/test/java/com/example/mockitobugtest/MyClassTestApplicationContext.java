package com.example.mockitobugtest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

public class MyClassTestApplicationContext {

	@Test
	public void dontStuck() {
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(StuckConfig.myOtherClass.getClass());
		beanDefinition.setSource(StuckConfig.myOtherClass);
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBeanDefinition("myOtherClass", beanDefinition);
		context.refresh();
	}

	@Test
	public void dontStuckAlso() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(DontStuckConfig.class);
		context.refresh();
		context.getBean(MyOtherClass.class);
	}

	@Test
	public void stuck() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(StuckConfig.class);
		context.refresh();
	}

	@Configuration
	public static class StuckConfig {

		private static final MyOtherClass myOtherClass = Mockito.mock(MyOtherClass.class);

		@Bean
		public MyOtherClass myOtherClass() {
			return myOtherClass;
		}
	}

	@Configuration
	public static class DontStuckConfig {

		private final MyOtherClass myOtherClass = Mockito.mock(MyOtherClass.class);

		@Bean
		public MyOtherClass myOtherClass() {
			return myOtherClass;
		}
	}

}
