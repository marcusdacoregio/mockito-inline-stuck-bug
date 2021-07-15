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
	public void doIt() {
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(Stub.myOtherClass.getClass());
		beanDefinition.setSource(Stub.myOtherClass);
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBeanDefinition("myOtherClass", beanDefinition);
		context.refresh();
		MyOtherClass myOtherClass = context.getBean(MyOtherClass.class);
		MyClass myClass = new MyClass(myOtherClass);
	}

	@Test
	public void doItStuck() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Stub.class);
		context.refresh();
		MyOtherClass myOtherClass = context.getBean(MyOtherClass.class);
		MyClass myClass = new MyClass(myOtherClass);
	}

	@Configuration
	public static class Stub {

		private static final MyOtherClass myOtherClass = Mockito.mock(MyOtherClass.class);
		// and it works if you use the line below instead of the line above
//		 private MyOtherClass myOtherClass = Mockito.mock(MyOtherClass.class);

		@Bean(name = "myOtherClass")
		public MyOtherClass myOtherClass() {
			return myOtherClass;
		}
	}

}
