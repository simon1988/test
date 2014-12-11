package leet.easy;
/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 * 
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * 
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * 
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 *
 *      1
 *     / \
 *    /   \
 *   0 --- 2
 *        / \
 *        \_/
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class CloneGraph {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null)return null;
        UndirectedGraphNode res = new UndirectedGraphNode(node.label);
        LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.offer(node);
        HashMap<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
        map.put(node,res);
        while(!queue.isEmpty()){
            UndirectedGraphNode cur = queue.poll();
            for(UndirectedGraphNode n:cur.neighbors){
                if(!map.containsKey(n)){
                    queue.offer(n);
                    UndirectedGraphNode copy=new UndirectedGraphNode(n.label);
                    map.put(n,copy);
                    map.get(cur).neighbors.add(copy);
                }else{
                    map.get(cur).neighbors.add(map.get(n));
                }
            }
        }
        return res;
    }
	class UndirectedGraphNode {
		public int label;
		public ArrayList<UndirectedGraphNode> neighbors;

		public UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}
}
