package tek;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * 
Before the round:
player 1: A K 8 8 6
player 2: A 3 Q 5 9 K J
player 3: A 5 Q 4 7 2 3

After the round:
player 1: 8 6
player 2: K J A K 8 A 3 Q 5 9 A 5 Q 4 7
player 3: 2 3

 * @author simon
 *
 */
public class War {

	private static final String POKERS[] = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"}; 

	public static void main(String[] args) {
		War instance = new War();
		LinkedList<Integer> results = new LinkedList<Integer>();
		Scanner scanner = new Scanner(System.in);
		int nset = scanner.nextInt();
		scanner.nextLine();
		while((--nset)>=0){
			LinkedList<String> playerPokers = new LinkedList<String>();
			int number = scanner.nextInt();
			scanner.nextLine();
			for(int i=0;i<number;i++){
				String text = scanner.nextLine();
				playerPokers.add(text.substring(text.indexOf(" ")+1));
			}
			results.add(instance.war(playerPokers));
		}
		scanner.close();
		for(int result: results){
			System.out.println(result);
		}
	}
	private int findIndex(String card){
		for(int i=0;i<POKERS.length;i++){
			if(POKERS[i].equals(card)){
				return i;
			}
		}
		return -1;
	}
	public int war(LinkedList<String> playerPokers){
		LinkedList<LinkedList<String>> cards = new LinkedList<LinkedList<String>>();
		LinkedList<LinkedList<String>> piles = new LinkedList<LinkedList<String>>();
		LinkedList<Integer> order = new LinkedList<Integer>();
		int o = 0;
		for(String s : playerPokers){
			cards.add(new LinkedList<String>(Arrays.asList(s.split(" "))));
			piles.add(new LinkedList<String>());
			order.add(++o);
		}
		while(cards.size()>1){
		LinkedList<Integer> equalCards = new LinkedList<Integer>();
		int big = -1;
		for(int i=0;i<cards.size();i++){
			int cur = findIndex(cards.get(i).getFirst());
			if(cur>big){
				equalCards.clear();
				big = cur;
				equalCards.add(i);
			}else if(cur==big){
				equalCards.add(i);
			}
			piles.get(i).add(cards.get(i).removeFirst());
		}
		while(equalCards.size()>1){
			LinkedList<Integer> equalCards2 = new LinkedList<Integer>();
			big =-1;
			for(Iterator<Integer> iterator = equalCards.iterator();iterator.hasNext();){
				int curI = iterator.next();
				if(cards.get(curI).size()<2){
					continue;
				}
				int cur = findIndex(cards.get(curI).get(1));
				piles.get(curI).add(cards.get(curI).removeFirst());
				piles.get(curI).add(cards.get(curI).removeFirst());
				if(cur>big){
					equalCards2.clear();
					big = cur;
					equalCards2.add(curI);
				}else if(cur==big){
					equalCards2.add(curI);
				}
			}
			if(equalCards2.isEmpty()){
				System.out.println("No Winner");
				System.exit(0);
			}
			equalCards=equalCards2;
		}
		for(LinkedList<String> l:piles){
			while(!l.isEmpty()){
				cards.get(equalCards.getFirst()).add(l.removeFirst());
			}
		}
		equalCards.clear();
		for(int i=0;i<cards.size();i++){
			if(cards.get(i).isEmpty()){
				equalCards.push(i);
			}
		}
		for(int i:equalCards){
			cards.remove(i);
			piles.remove(i);
			order.remove(i);
		}
		}
		return order.getFirst();
	}

}
