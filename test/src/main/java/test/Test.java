package test;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	private static String trim(String src){
		char c[]=new char[src.length()];
		int j=0;
		for(int i=0;i<src.length();i++){
			char cc=src.charAt(i);
			if(cc!='\''&&cc!=' '&&cc!='\t')
				c[j++]=cc;
		}
		return String.valueOf(c, 0, j);
	}
	
	private static void printSameMonth(int fromYear,int days){
		if(days<0 || fromYear<1971)
			throw new IllegalArgumentException("days must be greater than 0!");
		Calendar calendar=Calendar.getInstance();
		calendar.set(fromYear, 0, 1);
		int count=0;
		for(int i=0;i<days;i++){
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			if(calendar.get(Calendar.DAY_OF_MONTH)==1&&calendar.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY){
				count++;
				System.out.println(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));
			}
		}
		double rate=100*count/(12.0*days/365.0);
		System.out.printf("%d\t%.2f\t%.2f",count,(12.0*days/365.0), rate);
	}
	
	private static void testReflection(){
		System.out.println(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()));
		Field[] fields = CompareResult.class.getDeclaredFields();
		for(Field field : fields){
			Class<?> fieldType = field.getType();
			fieldType.isPrimitive();
			System.out.println(fieldType.getName());
			if(fieldType.isAssignableFrom(java.util.List.class)){
				Type fc = field.getGenericType();
				System.out.println(fc);
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(trim("abc\tdef  \t'''ddd' f\tgg"));
		//printSameMonth(2000,13333);
	}

}
