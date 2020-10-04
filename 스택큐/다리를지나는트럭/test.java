package first.test;

import java.util.*;

public class JavaMainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
//		int[] list= {7,4,5,6};
//		System.out.println(Solution.solution(2,10,list));
//		int[] list= {10};
//		System.out.println(Solution.solution(100,100,list));
//		int[] list= {10,10,10,10,10,10,10,10,10,10};
//		System.out.println(Solution.solution(100,100,list));
		
		
		int[] list= {20,30,50,10,24,100,34,1,1,2,3};
		System.out.println(Solution.solution(30,150,list));
	}

}
class Solution {
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int time=0;
        ArrayList<Integer> stillList=makeQueue(truck_weights);
        ArrayList<TruckVo> bridgeList= new ArrayList<TruckVo>();
//        ArrayList<Integer> successList= new ArrayList<Integer>();
        do {
        	++time;
        	checkSuccess(bridgeList, bridge_length);
        	if(stillList.size()>0) {
		    	if(getBridgeWeight(bridgeList)+stillList.get(0)<=weight) {
		    		
		    		TruckVo truckVo=new TruckVo();
		    		truckVo.setValue(stillList.get(0));
		    		truckVo.setTime(1);
		    		stillList.remove(0);
		    		bridgeList.add(truckVo);
				}
        	}
//        	System.out.println(time);
        }while(bridgeList.size()>0);
        answer=time;
        return answer;
    }



	private static int getBridgeWeight(ArrayList<TruckVo> bridgeList) {
		int sum=0;
		for(int i=0; i<bridgeList.size(); i++) {
			sum+=bridgeList.get(0).getValue();
		}
		return sum;
	}



	private static void checkSuccess(ArrayList<TruckVo> bridgeList,int bridge_length) {
		for(int i=0; i<bridgeList.size(); i++) {
			bridgeList.get(i).setTime(bridgeList.get(i).getTime()+1);
			if(bridgeList.get(i).getTime()>bridge_length) {
				bridgeList.remove(i);
				i--;
			}
		}
	}

	private static ArrayList<Integer> makeQueue(int[] truck_weights) {
		ArrayList<Integer> result= new ArrayList<Integer>();
		for(int i=0; i<truck_weights.length; i++) {
			result.add(truck_weights[i]);
		}
		return result;
	}
}

class TruckVo {
	private int value;
	private int time;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "TruckVo [value=" + value + ", time=" + time + "]";
	}
	
}
