package tek;

import java.util.LinkedList;

public class Amazon4 {
	static int calculateWorkingRobots(int rows, int columns, int[] steps) {
        int result = 0;
        boolean[] visited = new boolean[steps.length];
        for(int i=0;i<steps.length;i++){
            boolean bad=false;
            if(steps[i]!=0&&!visited[i]){
                LinkedList<Integer> stack = new LinkedList<Integer>();
                stack.push(i);
                while(!stack.isEmpty()){
                    int cur = stack.pop();
                    //right,down,left,up
                    if(cur%columns+1!=columns&&steps[cur+1]!=0&&!visited[cur+1])stack.push(cur+1);
                    if(cur/columns+1!=rows&&steps[cur+columns]!=0&&!visited[cur+columns])stack.push(cur+columns);
                    if(cur%columns!=0&&steps[cur-1]!=0&&!visited[cur-1])stack.push(cur-1);
                    if(cur/columns!=0&&steps[cur-columns]!=0&&!visited[cur-columns])stack.push(cur-columns);
                    if(steps[cur]==2)bad=true;
                    visited[cur]=true;
                }
                if(!bad)result++;
            }
        }
        return result;
    }
	
	public static void main(String args[]){
		int a[] = {0,0,0,0,0,1,1,0,1,1,0,0,0,0,1,0,0,0,1,0};
		System.out.println(calculateWorkingRobots(5,4,a));
	}
}
