package tek;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A simple Dictionary Tree algorithm implementation,
 * using bible.txt as its input words.
 * 
 * @author Niu Xingman
 *
 */
public class DictionaryTree {

	private Node head;
	private int count,distinctCount;
	
	public DictionaryTree(){
		head=new Node();
	}
	
	public void insertWord(String word){
		Node cur = head;
		int index = 0;
		for(char c : word.toCharArray()){
			if(c>'z'||c<'a')continue;
			index = c-'a';
			if(cur.nodes[index]==null){
				cur.nodes[index] = new Node();
			}
			cur = cur.nodes[index];
		}
		if(cur.count==0){
			this.distinctCount++;
		}
		cur.count++;
		this.count++;
	}
	
	public void printTopN(int n){
		head.word = "";
		
		SortedSet<Node> set = new TreeSet<Node>();
		set.add(head);
		
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.offer(head);
		
		Node cur = null;
		
		while(!queue.isEmpty()){
			cur = queue.poll();
			
			for(int i=0;i<26;i++){
				if(cur.nodes[i]!=null){
					cur.nodes[i].word = cur.word+(char)(i+'a');
					queue.add(cur.nodes[i]);
				}
			}
			
			if(cur.compareTo(set.first())>0){
				set.add(cur);
				if(set.size()>10){
					set.remove(set.first());
				}
			}
			else{
				//delete cur.word
				cur.word = null;
			}
		}
		
		System.out.printf("Here is Top%d words out of %d words(%d distinct):\n",n,this.count,this.distinctCount);
		while(!set.isEmpty()){
			System.out.printf("%s\t%d\t%2.1f%%\n",set.last().word,set.last().count,(float)(set.last().count*100)/count);
			set.remove(set.last());
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DictionaryTree trie = new DictionaryTree();
		try{
			BufferedReader reader = new BufferedReader(new FileReader("bible.txt"));
			String line = "";
			while((line = reader.readLine())!=null){
				for(String word : line.split(" ")){
					trie.insertWord(word.toLowerCase());
				}
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		trie.printTopN(10);
	}

	class Node implements Comparable<Node>{
		public Node(){
			this.nodes = new Node[26];
			this.count = 0;
			this.word = null;
		}
		public Node[] nodes;
		public int count;
		public String word;
		
		public boolean equals(Object o){
			return this.count==((Node)o).count;
		}
		@Override
		public int compareTo(Node o) {
			return this.count-o.count;
		}
	}
}
