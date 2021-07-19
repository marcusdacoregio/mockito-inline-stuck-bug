package com.example.mockitobugtest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MyClassCglibTests {

//	private static final MyOtherClass myOtherClass = Mockito.mock(MyOtherClass.class); // works if uncomment this line

	@Test
	public void stuck() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(StuckConfig.class);
		enhancer.setCallback((FixedValue) () -> null);
		StuckConfig stuckConfig = (StuckConfig) enhancer.create();
	}

	public static class StuckConfig {
		private static final MyOtherClass myOtherClass = Mockito.mock(MyOtherClass.class);
	}

}
