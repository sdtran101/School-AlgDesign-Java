package hwk03;

import hwk03.IScheduler.IInterval;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class SmartBruteForceScheduler implements IScheduler {

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

	@Override
	public Set<IInterval> optimalSchedule(Set<IInterval> s) {
		// TODO Auto-generated method stub

		ArrayList<Set<IInterval>> sol = getSol(s);
		
		sol = sorted(sol);

		return sol.get(sol.size() - 1);
	}

	@SuppressWarnings("null")
	private ArrayList<Set<IInterval>> getSol(Set<IInterval> s) {
		// TODO Auto-generated method stub
				ArrayList<Set<IInterval>> returnSol = null;
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
					
						returnSol.add(sol);
				}

				return returnSol;
	}

	/**
	 * This method sort the input ArrayList using quick sort algorithm.
	 * 
	 * @param input
	 *            the ArrayList of integers.
	 * @return sorted ArrayList of integers.
	 */
	private ArrayList<Set<IInterval>> sorted(ArrayList<Set<IInterval>> input) {

		if (input.size() <= 1) {
			return input;
		}

		int middle = (int) Math.ceil((double) input.size() / 2);
		int pivot = input.get(middle).size();
		Set<IInterval> pivotInt = input.get(pivot);
		ArrayList<Set<IInterval>> less = new ArrayList<Set<IInterval>>();
		ArrayList<Set<IInterval>> greater = new ArrayList<Set<IInterval>>();

		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).size() <= pivot) {
				if (i == middle) {
					continue;
				}
				less.add(input.get(i));
			} else {
				greater.add(input.get(i));
			}
		}

		return concatenate(sorted(less), pivotInt, sorted(greater));

	}

	/**
	 * Join the less array, pivot integer, and greater array to single array.
	 * 
	 * @param less
	 *            integer ArrayList with values less than pivot.
	 * @param pivotInt
	 *            the pivot integer.
	 * @param greater
	 *            integer ArrayList with values greater than pivot.
	 * @return the integer ArrayList after join.
	 */
	private ArrayList<Set<IInterval>> concatenate(
			ArrayList<Set<IInterval>> less, Set<IInterval> pivotInt,
			ArrayList<Set<IInterval>> greater) {
		// TODO Auto-generated method stub
		ArrayList<Set<IInterval>> list = new ArrayList<Set<IInterval>>();
		
		for (int i = 0; i < less.size(); i++) {
			list.add(less.get(i));
		}
		
		list.add(pivotInt);
		
		for (int i = 0; i < greater.size(); i++) {
			list.add(greater.get(i));
		}
		return null;
	}
}
