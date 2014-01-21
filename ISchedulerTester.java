package hwk03;

import hwk03.IScheduler.IInterval;
import hwk03.EarliestDeadlineScheduler.Interval;

import java.util.Set;

public class ISchedulerTester {
	
	public static void main(String[] args) {
		EarliestDeadlineScheduler test = new EarliestDeadlineScheduler();
		
		Set<IInterval> s = null;
//		test = new EarlyDeadline(0, 3);
		
		IInterval i;
		test.optimalSchedule(s);
		
	}
}
