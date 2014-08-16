package fragments;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegrex {

	public void test1(){
		String line = "This order was placed for QT3000! OK?";
		Pattern pattern = Pattern.compile("(.*?)(\\d+)(.*)");
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
		    System.out.println("group 0: " + matcher.group());
		    System.out.println("group 1: " + matcher.group(1));
		    System.out.println("group 2: " + matcher.group(2));
		    System.out.println("group 3: " + matcher.group(3));
		}
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

	public static void main(String[] args) {
		TestRegrex ts=new TestRegrex();
		
		ts.test1();
		
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
