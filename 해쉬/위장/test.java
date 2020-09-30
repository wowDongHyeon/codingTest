import java.util.HashMap;
import java.util.Iterator;

public class JavaTest {

	public static void main(String[] args) {

		
		

//		String [][] param= {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"},{"green_turban", "headgear"}};
		String [][] param= {{"crow_mask", "face"}, {"blue_sunglasses", "face"},{"smoky_makeup", "face"}};
		
		
		System.out.println(Solution.solution(param));
	}

}
class Solution {
	  public static int solution(String[][] clothes) {
		  int answer = 0;
	      HashMap <String,Integer> map=getClothCntMap(clothes);
	      answer=calculCombination(map);
		  return answer;
	  }

	private static HashMap<String, Integer> getClothCntMap(String[][] clothes) {
		HashMap <String,Integer> result=new HashMap <String,Integer> ();
		for(int i=0; i<clothes.length; i++) {

			if(result.containsKey(clothes[i][1])) {
				result.put(clothes[i][1], result.get(clothes[i][1])+1);
			}else {
				result.put(clothes[i][1],1);
			}
		}
		return result;
	}
	
	private static int calculCombination(HashMap<String, Integer> map) {
		// TODO Auto-generated method stub
		Iterator<String> keys=map.keySet().iterator();
		int result=1;
		while(keys.hasNext()) {
			String key=keys.next();
			result*=map.get(key)+1;
		}
		return result-1;
	}
	
}
