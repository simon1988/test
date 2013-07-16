package fragments;
import java.util.*;

public class TestCollection {

	/**
	 * @param args
	 */
	public TestCollection(){
		
	}
	public void test1(){
		List<Integer> fixedList = Arrays.<Integer>asList(1,2);
		List<Integer> list = new ArrayList<Integer>(fixedList);
		Collections.addAll(list, 0, 1, 0, 3, 4, 5);
		//list.add(9, 1);
		list = list.subList(1, list.size());
		list.remove(new Integer(0));
		list.set(2, 2);
		list.retainAll(fixedList);
		for(Integer i : list){
			System.out.println(i);
		}
	}
	public void test2(){
		LinkedList<Integer> list = (LinkedList<Integer>)Collections.synchronizedList(new LinkedList<Integer>());
		System.out.println(list.peek());
		Collections.addAll(list, 1,2,3,4,5);
		System.out.println(list.peek());
		System.out.println(list);
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(2);
		stack.pop();
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(2);
		queue.offer(3);
		System.out.println(queue.poll());
	}
	public <someg> void test3(someg...g){
		for(Map.Entry<String, String> entry : System.getenv().entrySet()){
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
	}
	public void test4(){
		SortedSet<Integer> set = new TreeSet<Integer>();
		Collections.addAll(set, 4,1,5,3);
		System.out.println(set.size()+":"+set);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestCollection tc = new TestCollection();
		//tc.test1();
		//tc.test2();
		//tc.test3(9,5,6);
		tc.test4();

	}

}

class MyCollection<T> extends AbstractCollection<T>{

	private T[] data;
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<T>(){

			private int i=0;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return i<=data.length;
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				return data[i++];
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException();
			}
			
		};
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}