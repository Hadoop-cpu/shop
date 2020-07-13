package com.zcib.test;

public class TestCZ {
	
	public static void main(String[] args) {
		A a = new A();
		a.test();
		B b = new B();
		b.test();
		
		A aa = new B();
		aa.test();
		
	}
	
	

}

class A{
	public void test(){
		System.out.println("testA");
	}
}

class B extends A{
	public void test() {
		System.out.println("testB");
	}
}
