package fragments;
import java.util.regex.*;

public class TestRegrex {

	public void test1(){
		String s=String.format("%9s!", "abc");
		System.out.println(s);
	}
	
	public void test2(String pattern, String str){
		Pattern pat = Pattern.compile(pattern);
		Matcher matcher = pat.matcher(str);
		System.out.println(str);
		while(matcher.find()){
			System.out.print(matcher.start()+"-"+(matcher.end()-1)+" : ");
			System.out.println(matcher.group());
		}
		for(String s : pat.split(str)){
			System.out.println(s);
		}
	}
	
	public void test3(String pattern, String str){
		Matcher matcher=Pattern.compile(pattern).matcher(str);
		System.out.println(matcher.replaceAll("$2$1"));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestRegrex ts=new TestRegrex();
		
		//ts.test1();
		
		String s="a // b6     c33";
		//find
		ts.test2("\\W+",s);
		//split
		for(String ss:s.split("\\W+")){
			System.out.print(ss+" ");
		}
		System.out.println();
		//replace
		ts.test3("(\\w)(\\d+)",s);
	}

}
