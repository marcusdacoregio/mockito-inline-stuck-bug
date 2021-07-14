package com.example.mockitobugtest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootTest(classes = MyClassTests.Stub.class)
public class MyClassTests {

	@Autowired
	private MyOtherClass myOtherClass;

//	private static final MyOtherClass asd = Mockito.mock(MyOtherClass.class); // it works if you uncomment this line

	@Test
	public void doIt() {
		MyClass myClass = new MyClass(myOtherClass);
		String value = myClass.doIt();
	}

	@EnableWebSecurity
	public static class Stub extends WebSecurityConfigurerAdapter {
		private static final MyOtherClass myOtherClass = Mockito.mock(MyOtherClass.class);
		// and it works if you use the line below instead of the line above
//		 private MyOtherClass myOtherClass = Mockito.mock(MyOtherClass.class);

		@Bean
		MyOtherClass get() {
			return myOtherClass;
		}
	}
}