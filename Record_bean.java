package android_server;

public class Record_bean implements Comparable<Record_bean>{
	String username;
	int step;
	int best;
	@Override
	public int compareTo(Record_bean bean) {
		// TODO Auto-generated method stub
		return (this.step-this.best) - (bean.step-bean.best);
	}
	
}
