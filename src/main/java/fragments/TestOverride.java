package fragments;

public class TestOverride {

	public static void callAB(A a) {
		if(a instanceof B){
			callAB((B)a);
		}
		System.out.println("Call A");
	}

	public static void callAB(B b) {
		System.out.println("Call B");
	}

	public static void main(String[] args) {
		A a = new B();
		a.f();
		callAB(a);
	}

}

class A {
	public void f() {
		System.out.println("F in A");
	}
}

class B extends A {
	public void f() {
		System.out.println("F in B");
	}
}
