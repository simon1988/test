package fragments;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

public class TestCollection {
	
	public void test1(){	
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(2);
		stack.pop();
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(3);
		System.out.println(queue.poll());
	}
	public void test2(){
		SortedSet<Integer> set = new TreeSet<Integer>();
		Collections.addAll(set, 4,1,5,3);
		System.out.println(set.size()+":"+set);
	}
	public <someg> void test3(someg...g){
		System.out.println(g[0]);
	}
	public static void main(String[] args) {
		TestCollection tc = new TestCollection();
		tc.test1();
		tc.test2();
		tc.test3(9,5,6);
	}

}