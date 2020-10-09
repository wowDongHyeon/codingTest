import java.util.*;

public class MainTest {

	public static void main(String[] args) {

		String operations[]= {"I 4","I 3","I 2","I 1", "D 1","D 1","D -1","D -1","D -1","D -1","D -1","I 5","I 6"};
		

		int [] result=Solution.solution(operations);
		for(int i=0; i<result.length; i++) {
			System.out.println(result[i]);
		}
	}

}

class Solution {
    public static int[] solution(String[] operations) {
    	try {
    		 int[] answer = {};
    	        ArrayList<Integer> queue=new ArrayList<Integer>();
    	        for(int i=0; i<operations.length; i++) {
    	        	caculation(operations[i],queue);
    	        }
    	        answer=getMinMaxIntArray(queue);
    	        return answer;
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    		return null; 
    	}
		
       
    }

	private static void caculation(String operations, ArrayList<Integer> queue) {
		if("D 1".equals(operations)) {
			if(queue.size()!=0) {
				deleteMaxAtQueue(queue);
			}
		}
		else if("D -1".equals(operations)) {
			if(queue.size()!=0) {
				deleteMinAtQueue(queue);
			}
		}
		else{
			int num=Integer.parseInt(operations.replace("I ", ""));
			queue.add(num);
		}
	}

	private static void deleteMinAtQueue(ArrayList<Integer> queue) {
		queue.remove(findMinIndexAtQueue(queue));
		
	}

	private static int findMinIndexAtQueue(ArrayList<Integer> queue) {
		int min=9999999;
		int index=-1;
		for(int i=0; i<queue.size(); i++) {
			if(min>=queue.get(i)) {
				min=queue.get(i);
				index=i;
			}
		}
		return index;
	}

	private static void deleteMaxAtQueue(ArrayList<Integer> queue) {
		queue.remove(findMaxIndexAtQueue(queue));
	}

	private static int findMaxIndexAtQueue(ArrayList<Integer> queue) {
		int max=-999999999;
		int index=-1;
		for(int i=0; i<queue.size(); i++) {
			if(max<=queue.get(i)) {
				max=queue.get(i);
				index=i;
			}
		}
		return index;
	}
	private static int[] getMinMaxIntArray(ArrayList<Integer> queue) {
		
		if(queue.size()==0) {
			int [] result= {0,0};
			return result;
		}else {
			int [] result= {queue.get(findMaxIndexAtQueue(queue)),queue.get(findMinIndexAtQueue(queue))};
			return result;
		}
		
	}
}
