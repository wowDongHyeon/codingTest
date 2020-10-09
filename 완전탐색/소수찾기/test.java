
import java.util.*;

public class MainTest {

	public static void main(String[] args) {
		String numbers="17";
//		String numbers="011";
		System.out.println(Solution.solution(numbers));
		
	}

	
}

class Solution {
    public static int solution(String numbers) {
        int answer = 0;
        int [] numbersArray=getArrayFromString(numbers);
        
        ArrayList<int[]> numArrayList;
        ArrayList<Integer> numList;
        for(int i=1; i<=numbersArray.length; i++) {
        	numArrayList = getPermutation(numbersArray, i);
        	numList=getArraytoInt(numArrayList, i);
        	answer+=getPrimeNumCntFromNumList(numList);
        }
        return answer;
    }
    
    private static int[] getArrayFromString(String numbers) {
  		int [] result=new int[numbers.length()];
  		for(int i=0; i<numbers.length(); i++) {
  			result[i]=Integer.parseInt(String.valueOf(numbers.charAt(i)));
  		}
  		return result;
  	}
//외부코드
	/**
     * 순열 구하기
     */
    public static ArrayList<int[]> getPermutation(int[] inputArray, int selectCount) {
        ArrayList<int[]> resultList = new ArrayList<int[]>();
        
        ArrayList<int[]> combinationList = getCombination(inputArray, selectCount);
        int count = combinationList.size();
        for (int i=0; i<count; i++) {
            if (combinationList.get(i) == null) {
                continue;
            }
            
            int[] singleArray = combinationList.get(i);
//            singleArray = removeDuplication(singleArray);
            if (singleArray == null || singleArray.length == 0) {
                continue;
            }
            
            addSinglePermutation(resultList, singleArray, 0);
        }
        
        if (resultList != null) {
            resultList = removeDuplication(resultList);
        }
        
        return resultList;
    }
    
    
    /**
     * 순열 구하기
     */
    public static void addSinglePermutation(ArrayList<int[]> resultList, int[] inputArray, int position) {
        
        int count = inputArray.length;
        for (int i=position; i<count; i++) {
            int[] inputArray2 = cloneArray(inputArray);
            swapArray(inputArray2, position, i);
            resultList.add(inputArray);
            
            addSinglePermutation(resultList, inputArray2, position + 1);
        }
    }
    
    
    /**
     * 조합 구하기
     */
    public static ArrayList<int[]> getCombination(int[] inputArray, int selectCount) {
        // 미리 중복 제거하고 실행
//        inputArray = removeDuplication(inputArray);
                
        ArrayList<int[]> resultList = new ArrayList<int[]>();
        int[] hintArray = new int[inputArray.length];
        
        addSingleCombination(resultList, selectCount, inputArray, 0, hintArray, 0);
        return resultList;
    }
    
    
    /**
     * 조합 구하기 재귀메서드
     */
    public static void addSingleCombination(ArrayList<int[]> resultList, int selectCount, int[] inputArray, int position, int[] hintArray, int hintIndex) {
        
        if (hintIndex == selectCount) {
            int[] singleArray = resizeIntArray(hintArray, hintIndex);
            if (singleArray != null && singleArray.length > 0) {
                resultList.add(singleArray);
            }
            return;
        }
        
        int lastIndex = inputArray.length - 1;
        if (position > lastIndex) {
            return;
        }
        
        int[] hintArray2 = cloneArray(hintArray);
        hintArray[hintIndex] = 0;
        hintArray2[hintIndex] = inputArray[position];
        
        position++;
        
        addSingleCombination(resultList, selectCount, inputArray, position, hintArray, hintIndex);
        addSingleCombination(resultList, selectCount, inputArray, position, hintArray2, hintIndex + 1);
    }
    
    
    /**
     * int 배열을 복제한다.
     */
    public static int[] cloneArray(int[] inputArray) {
        if (inputArray == null) {
            return null;
        }
        
        int count = inputArray.length;
        int[] resultArray = new int[count];
        
        for (int i=0; i<count; i++) {
            resultArray[i] = inputArray[i];
        }
        
        return resultArray;
    }
    
    
    /**
     * int 배열의 두 요소의 값을 맞바꾼다.
     */
    public static boolean swapArray(int[] array, int i, int k) {
        if (array == null) {
            return false;
        }
        
        int count = array.length;
        int lastIndex = count - 1;
        
        if (i < 0 || k < 0 || i > lastIndex || k > lastIndex) {
            return false;
        }
        
        int temp = array[i];
        array[i] = array[k];
        array[k] = temp;
        
        return true;
    }
    
    
    /**
     * int 배열의 길이를 변경한다. (조정한 배열을 새로 얻는다)
     */
    public static int[] resizeIntArray(int[] inputArray, int newSize) {
        int lastIndex = -1;
        if (inputArray != null) {
            lastIndex = inputArray.length - 1;
        }
        
        int[] newArray = new int[newSize];
        
        for (int i=0; i<newSize; i++) {
            if (i > lastIndex) {
                newArray[i] = 0;
            } else {
                newArray[i] = inputArray[i];
            }
        }
        
        return newArray;
    }
    
    
    /**
     * 인트 배열의 중복을 제거한다.
     */
    public static int[] removeDuplication(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return null;
        }
        
        int count = inputArray.length;
        int[] resultArray = new int[count];
        
        int currentIndex = 0;

        int singleItem = 0;
        
        oLoop : for (int i=0; i<count; i++) {
            singleItem = inputArray[i];
            
            for (int k=0; k<resultArray.length; k++) {
                if (resultArray[k] == singleItem) {
                    continue oLoop;
                }
            }
            
            resultArray[currentIndex] = singleItem;
            currentIndex++;
        }
        
        if (currentIndex != count) {
            resultArray = resizeIntArray(resultArray, currentIndex);
        }
        
        return resultArray;
    }
    
    
    /**
     * Generic int 배열인 ArrayList 의 중복을 제거한다.
     */
    public static ArrayList<int[]> removeDuplication(ArrayList<int[]> inputList) {
        if (inputList == null || inputList.size() == 0) {
            return null;
        }
        
        int count = inputList.size();
        ArrayList<int[]> resultList = new ArrayList<int[]>();
        
        int[] singleItem = null;
        
        oLoop : for (int i=0; i<count; i++) {
            singleItem = inputList.get(i);
            
            for (int k=0; k<resultList.size(); k++) {
                if (equals(singleItem, resultList.get(k))) {
                    continue oLoop;
                }
            }
            
            resultList.add(singleItem);
        }
        
        return resultList;
    }
    
    
    /**
     * int 배열 2개가 서로 같은지 비교한다.
     */
    public static boolean equals(int[] array1, int[] array2) {
        if (array1 == null && array2 == null) {
            return true;
        }
        
        if (array1 == null || array2 == null) {
            return false;
        }
        
        if (array1.length != array2.length) {
            return false;
        }
        
        int count = array1.length;
        for (int i=0; i<count; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    
    /**
     * 제네릭이 int 배열인 ArrayList를 출력한다.
     */
    public static void print(ArrayList<int[]> inputList) {
        System.out.println(convertToString(inputList));
    }
    
    
    /**
     * int 배열을 출력한다.
     */
    public static void print(int[] inputArray) {
        System.out.println(convertToString(inputArray, ","));
    }
    
    
    /**
     * 제네릭이 int 배열인 ArrayList의 내용을 문자열 형태로 만든다.
     */
    public static String convertToString(ArrayList<int[]> inputList) {
        if (inputList == null || inputList.size() == 0) {
            return "";
        }
        
        StringBuffer buff = new StringBuffer();
        
        int count = inputList.size();
        for (int i=0; i<count; i++) {
            if (buff.length() > 0) {
                buff.append("\n");
            }
            
            buff.append(convertToString(inputList.get(i), ","));
        }
        
        return buff.toString();
    }

    
    /**
     * int 배열의 내용을 문자열 형태로 만든다.
     */
    public static String convertToString(int[] inputArray, String delimiter) {
        if (inputArray == null || inputArray.length == 0) {
            return "";
        }
        
        StringBuffer buff = new StringBuffer();
        
        int count = inputArray.length;
        for (int i=0; i<count; i++) {
            if (buff.length() > 0) {
                buff.append(delimiter);
            }
            
            buff.append(inputArray[i]);
        }
        
        return buff.toString();
    }
    
    
   //외부코드
    public static ArrayList<Integer> getArraytoInt(ArrayList<int[]> permutationList, int selectCount) {
    	ArrayList<Integer> result=new ArrayList<Integer>();
    	for(int i=0; i<permutationList.size(); i++) {
    		int num=Integer.parseInt(convertToString(permutationList.get(i),""));
    		if(Integer.toString(num).length()==selectCount) {
    			result.add(num);
    		}
    		
    	}
		return result;
	}
    
    private static int getPrimeNumCntFromNumList(ArrayList<Integer> numList) {
		int cnt=0;
		for(int i=0; i<numList.size(); i++) {
			int num=numList.get(i);
			boolean isPrimeNum=true;
			for(int j=2; j<num; j++) {
				if(num%j==0) {
					isPrimeNum=false;
					break;
				}
			}
			if(isPrimeNum&&num!=1&&num!=0)cnt++;
		}
		return cnt;
	}
	
}

