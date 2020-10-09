import java.util.*;

public class MainTest {

	public static void main(String[] args) {
//		HashMap<String,String> a=new HashMap<String,String>();
//		a.put("a", "b");
//		a.put("b", "b");
//		a.put("c", "b");
//		System.out.println(a);
//		a.clear();
//		System.out.println(a.isEmpty());
//		System.out.println();
//		int[][] jobs= {{0, 3},{1,9},{2,6}};
		int[][] jobs= {{0, 50}, {1, 20}, {15, 3}, {10, 4}, {100, 5}};
		System.out.println(Solution.solution(jobs)); 
	}

}

class Solution {
    public static int solution(int[][] jobs) {
        int answer = 0;
        ArrayList<JobVo> jobList=getVoFromArray(jobs);
        ArrayList<JobVo> waitList = new ArrayList<>();
        HashMap<String,Object> processMap=new HashMap<String,Object>();
        
        JobVo vo=getFirstReqJob(jobList);
        processMap.put("job", vo);
        processMap.put("endTime",vo.getReqTime()+vo.getDuringTime());
        
        int time=vo.getReqTime();
        do{
        	time++;
//        	System.out.println(time);
        	addWaitListOfReqTime(jobList,waitList, time);
        	if(processMap.isEmpty()) {
        		if(waitList.size()>=1) {
        			addProcessMap(processMap,waitList, time);
        		}
        	}else {
        		if(time==(Integer)processMap.get("endTime")) {
            		answer+=time-(Integer)((JobVo)processMap.get("job")).getReqTime();
            		addProcessMap(processMap,waitList, time);
            	}
        	}
        	
        	
        }while(waitList.size()!=0||!processMap.isEmpty()||jobList.size()!=0);
        
        return answer/jobs.length;
    }

	private static void addProcessMap(HashMap<String, Object> processMap, ArrayList<JobVo> waitList,int time) {
		int sum=9999999;
		int index=-1;
		for(int i=0; i<waitList.size(); i++) {
			int duringTime=waitList.get(i).getDuringTime()+time-waitList.get(i).getReqTime();
			if(duringTime<sum) {
				sum=duringTime;
				index=i;
			}
		}
		processMap.clear();
		if(index!=-1) {
			JobVo vo=new JobVo();
			vo.setReqTime(waitList.get(index).getReqTime());
			vo.setDuringTime(waitList.get(index).getDuringTime());
			processMap.put("job", vo);
			processMap.put("endTime", time+vo.getDuringTime());
			waitList.remove(index);
		}
	}

	private static void addWaitListOfReqTime(ArrayList<JobVo> jobList, ArrayList<JobVo> waitList, int time) {
		for(int i=0; i<jobList.size(); i++) {
			if(jobList.get(i).getReqTime()==time) {
				JobVo vo=new JobVo();
				vo.setReqTime(jobList.get(i).getReqTime());
				vo.setDuringTime(jobList.get(i).getDuringTime());
				waitList.add(vo);
				jobList.remove(i);
				break;
			}
		}
		
	}

	

	private static JobVo getFirstReqJob(ArrayList<JobVo> jobList) {
		JobVo result=new JobVo();
		int reqTime=1001;
		int num=-1;
		for(int i=0; i<jobList.size(); i++) {
			if(reqTime>=jobList.get(i).getReqTime()) {
				result.setReqTime(jobList.get(i).getReqTime());
				result.setDuringTime(jobList.get(i).getDuringTime());
				reqTime=jobList.get(i).getReqTime();
				num=i;
			}
		}
		if(num!=-1) {
			jobList.remove(num);
		}
		return result;
	}

	private static ArrayList<JobVo> getVoFromArray(int[][] jobs) {
		ArrayList<JobVo> result=new ArrayList<JobVo>();
		for(int i=0; i<jobs.length; i++) {
			JobVo vo=new JobVo();
			vo.setReqTime(jobs[i][0]);
			vo.setDuringTime(jobs[i][1]);
			result.add(vo);
		}
		return result;
	}
}
class JobVo{
	private int reqTime;
	private int duringTime;
	public int getReqTime() {
		return reqTime;
	}
	public void setReqTime(int reqTime) {
		this.reqTime = reqTime;
	}
	public int getDuringTime() {
		return duringTime;
	}
	public void setDuringTime(int duringTime) {
		this.duringTime = duringTime;
	}
	@Override
	public String toString() {
		return "JobVo [reqTime=" + reqTime + ", duringTime=" + duringTime + "]";
	}
	
	
}

