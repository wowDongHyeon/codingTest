import java.util.*;
import java.util.Collections;

public class MainTest {

	public static void main(String[] args) {
		int[] scoville= {1, 2, 3, 9, 10, 12};
//		int[] priorities= {1, 1, 9, 1, 1, 1};4
		int k=7;
		System.out.println(Solution.solution(scoville, k)); 
	}

}

class Solution {
    public static int solution(int[] scoville, int K) {
        int answer = 0;
        ArrayList<Integer> scovilleList=getArrayListFromArray(scoville);
        while(!checkOverK(scovilleList,K)) {
        	if(scovilleList.size()>=2) {
        		addScoville(scovilleList);
        		answer++;
        	}else {
        		answer=-1;
        		break;
        	}
        }
        	
        return answer;
    }
    private static ArrayList<Integer> getArrayListFromArray(int[] scoville) {
		ArrayList<Integer> result=new ArrayList<Integer>();
		for(int i: scoville) {
			result.add(i);
		}
		return result; 
	}
    private static boolean checkOverK(ArrayList<Integer> scovilleList, int K) {
		for(int i : scovilleList) {
			if(i<K) {
				return false;
			}
		}
		return true;
	}
    private static void addScoville(ArrayList<Integer> scovilleList) {
    	Collections.sort(scovilleList);
    	int sum=scovilleList.get(0)+scovilleList.get(1)*2;
    	scovilleList.add(sum);
    	scovilleList.remove(0);
    	scovilleList.remove(0);
	}
	
}

