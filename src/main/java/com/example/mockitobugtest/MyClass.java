package com.example.mockitobugtest;

import org.springframework.stereotype.Component;

public class MyClass {

	private final MyOtherClass myOtherClass;

	public MyClass(MyOtherClass myOtherClass) {
		this.myOtherClass = myOtherClass;
	}

	public String doIt() {
		return this.myOtherClass.hello();
	}
}
