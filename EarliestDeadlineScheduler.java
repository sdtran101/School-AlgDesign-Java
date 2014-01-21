package hwk03;

import java.util.Iterator;
import java.util.Set;

public class EarliestDeadlineScheduler implements IScheduler {

	public class Interval implements IScheduler.IInterval {

		private int startT, endT;
		
		public Interval() {
			
		}
		
		public Interval(int start, int end) {
			this.startT = start;
			this.endT = end;
		}
		
		public void setStartTime(int start) {
			this.startT = start;
		}
		
		public void setEndTime(int end) {
			this.endT = end;
		}
		
		@Override
		public int getStartTime() {
			// TODO Auto-generated method stub
			return startT;
		}

		@Override
		public int getEndTime() {
			// TODO Auto-generated method stub
			return endT;
		}
		
	}
	@SuppressWarnings("null")
	@Override
	public Set<IInterval> optimalSchedule(Set<IInterval> s) {
		// TODO Auto-generated method stub
		Set<IInterval> sol = null;
		Iterator<IInterval> iter;
		IInterval currentInt, earliestInt;
		
		while(!s.isEmpty()) {
			iter = s.iterator();
			earliestInt = iter.next();
			
			// Find the earliest end time
			while(iter.hasNext()) {
				currentInt = iter.next();
				if(currentInt.getEndTime() < earliestInt.getStartTime()) {
					earliestInt = currentInt;
				}
			}
			
			sol.add(earliestInt);	// Add job to the solution
			s.remove(earliestInt);	// remove the earliest job from the set
			
			iter = s.iterator();
			currentInt = iter.next();
			
			// Remove all jobs that start before the earliest job
			while(iter.hasNext()) {
				if(currentInt.getStartTime() < earliestInt.getEndTime()) {
					s.remove(currentInt);	// remove jobs that has conflict
				}
				currentInt = iter.next();	// Keep iterate through the set
			}
			
		}
		
		return sol;
	}

}
