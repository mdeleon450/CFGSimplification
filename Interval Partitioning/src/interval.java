// Authors: Maik De Leon Lopez, Alfredo Delgado
// CSC401-01 Project 1 

import java.util.Random;

public class interval {
	private int id; // used to identify which lecture
	private int startTime; // the minimum start time
	private int endTime = -1; // the max end time, set to -1 for troubleshooting
	private int priority = -1;

	public interval(int id, int minTime, int maxTime) {
		this.id = id;
		Random rand = new Random();
		this.startTime = rand.nextInt((maxTime - minTime) + 1) + minTime; // creates a random number from minTime to
																			// maxTime
		while (this.startTime >= this.endTime) { // while the startTime is larger than the endTime
			this.endTime = rand.nextInt((maxTime - minTime) + 1) + minTime; // Keep generating a valid interval endTime
		}
	}

	public int getId() {
		return this.id;
	}

	public int getStart() {
		return this.startTime;
	}

	public int getEnd() {
		return this.endTime;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return this.priority;
	}

	public String toString() { // Formating for printing an interval
		return "[" + id + ", [" + this.startTime + ", " + this.endTime + "]]";
	}
}
