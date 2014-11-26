package fragments;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

public class TestCollection {
	
	public void test1(){
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		heap.addAll(Arrays.asList(4,3,5,2,1));
		System.out.println(heap.size()+":"+heap);
		while(!heap.isEmpty()){
			System.out.print(heap.poll());
		}
		System.out.println();
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(2);
		stack.pop();
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(3);
		queue.poll();
	}
	public void test2(){
		SortedSet<Integer> set = new TreeSet<Integer>();
		Collections.addAll(set, 4,1,5,3);
		System.out.println(set.size()+":"+set);
	}
	
	public static void main(String[] args) {
		TestCollection tc = new TestCollection();
		tc.test1();
		tc.test2();
	}

}