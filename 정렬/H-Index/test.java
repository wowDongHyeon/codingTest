import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class MainTest {

	public static void main(String[] args) {

		int [] citations= {4,5,9,10,12,3};
		System.out.println(Solution.solution(citations));
	}
	
}

class Solution {
    public static int solution(int[] citations) {
        int answer = 0;
        int cnt;
        int maxQuote=getMaxQuote(citations);
        for(int i=maxQuote; i>=0; i--) {
        	cnt=HowManyMoreQuote(citations,i);
        	if(cnt>=i) {
        		answer=i;
        		break;
        	}
        }
        return answer;
    }

	private static int HowManyMoreQuote(int[] citations, int quote) {
		int cnt=0;
		for(int i=0; i<citations.length; i++) {
			if(citations[i]>=quote) {
				cnt++;
			}
		}
		return cnt;
	}

	private static int getMaxQuote(int[] citations) {
		int max=0;
		for(int i=0; i<citations.length; i++) {
			if(max<=citations[i]) {
				max=citations[i];
			}
		}
		return max;
	}
}
