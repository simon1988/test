package fragments;

public class TestOverride {

	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void callAB(A a){
		System.out.println("Call A");
	}
	public static void callAB(B b){
		System.out.println("Call B");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a=new B();
		a.f();
		callAB(a);

	}

}
class A{
	public void f(){
		System.out.println("F in A");
	}
}
class B extends A{
	public void f(){
		System.out.println("F in B"+InnerB.i);
	}
	
	void f(int i){
		System.out.println("F(i) in B");
	}
	static class InnerB{
		private static int i=0;
	}
}
