package hwk03;

import java.util.Iterator;
import java.util.Set;

public class BruteForceScheduler implements IScheduler {

	public class Interval implements IScheduler.IInterval {

		private int startT, endT;

		/**
		 * Default Constructor.
		 */
		public Interval() {

		}

		/**
		 * Constructor with a start and end time parameter.
		 * 
		 * @param start
		 *            - start time.
		 * @param end
		 *            - end time.
		 */
		public Interval(int start, int end) {
			this.startT = start;
			this.endT = end;
		}

		/**
		 * Set the start time.
		 * 
		 * @param start
		 *            - start time.
		 */
		public void setStartTime(int start) {
			this.startT = start;
		}

		/**
		 * Set the end time.
		 * 
		 * @param end
		 *            - end time.
		 */
		public void setEndTime(int end) {
			this.endT = end;
		}

		/**
		 * Get the start time.
		 */
		@Override
		public int getStartTime() {
			// TODO Auto-generated method stub
			return startT;
		}

		/**
		 * Get the end time.
		 */
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
		Set<IInterval> returnSol = null;
		Set<IInterval> sol = null;
		Set<IInterval> curSol = null;
		Iterator<IInterval> iter;
		IInterval currentInt, firstInt = null;

		int currentItem = 0;
		while (currentItem < s.size()) {
			curSol = s;
			iter = curSol.iterator();
			currentItem++;
			// This will go to the next job in the iterator
			// to ensure that each job gone through the
			// the loop
			for (int i = 0; i < currentItem; i++)
				firstInt = iter.next();

			sol.add(firstInt);
			curSol.remove(firstInt);
			// Find the next non-conflict jobs
			while (!curSol.isEmpty()) {
				currentInt = iter.next();
				if (currentInt.getStartTime() > firstInt.getEndTime())
					firstInt = currentInt;

				sol.add(firstInt);
				curSol.remove(firstInt);

				iter = curSol.iterator();
				currentInt = iter.next();

				// Check for non-conflict jobs and add it to the solution
				// Interval set
				while (iter.hasNext()) {
					currentInt = iter.next();
					if (currentInt.getStartTime() < firstInt.getEndTime()) {
						curSol.remove(currentInt);
					}
					currentInt = iter.next();
				}
			}
			// Check to see if the returnSol has bigger solution set than current
			// Solution Set
			if (returnSol.size() < sol.size()) {
				returnSol = sol;
			}
		}

		return returnSol;
	}

}
