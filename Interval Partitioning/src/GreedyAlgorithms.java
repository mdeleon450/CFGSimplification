// Authors: Maik De Leon Lopez, Alfredo Delgado
// CSC401-01 Project 1 

import java.util.Scanner;

public class GreedyAlgorithms {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter the number of intervals:");
		int numIntervals = reader.nextInt();
		System.out.println("Please enter the start time of intervals:");
		int minTime = reader.nextInt();
		System.out.println("Please enter the end time of intervals");
		int maxTime = reader.nextInt();
		System.out.println("\n==========Initial Intervals==========\n");
		interval intervalArray[] = new interval[numIntervals]; // Create an array of intervals of size num intervals
		for (int i = 0; i < numIntervals; i++) {
			intervalArray[i] = new interval(i, minTime, maxTime); // Create these random intervals
			System.out.println(intervalArray[i]); // Print out these initial sorted intervals
		}
		System.out.println("\n==========Interval Scheduling==========\n");
		System.out.println(intervalSchedule(intervalArray)); // Find out the largest number of lectures in a classroom
		System.out.println("\n==========Interval Partitioning==========\n");
		intervalPartition(intervalArray); // Find out the minimum number of classes necessary to schedule all lectures
	}

	public static Classroom intervalSchedule(interval arr[]) {
		int size = arr.length;
		heapSort schedule = new heapSort(size); // Create a new minHeap with the same size as the sorted array
		for (int i = 0; i < size; i++) { // Sort jobs by finish times so that f1<=f2...<=fn
			arr[i].setPriority(arr[i].getEnd()); // Sets the minHeap priority based on end times
			schedule.add(arr[i]); // Adds the intervals to the minHeap and minHeap sorts them as they are entered
		}
		interval temp; // placeholder
		Classroom A = new Classroom(0, size); // Classroom A starts out empty
		A.addLecture(schedule.remove()); // Add the first lecture
		for (int i = 1; i < size; i++) {
			temp = schedule.remove(); // we check if the next lecture is compatible with the last lecture we have
										// added to the classroom
			if (temp.getStart() >= A.lastAdded().getEnd()) {
				A.addLecture(temp); // if it is we want to schedule that lecture in the classroom
			}
		}
		return A; // Return the classroom
	}

	public static void intervalPartition(interval arr[]) {
		int size = arr.length;
		Classroom classes[] = new Classroom[size]; // Worst case scenario we need a class for each lecture
		int classCount = 0; // Start with one classroom
		priorityQueue Q = new priorityQueue(size); // Used to keep classrooms
		heapSort sortedIntervals = new heapSort(size); // Used to sort the Intervals
		for (int i = 0; i < size; i++) { // Sort jobs by finish times so that s1<=s2...<=sn
			arr[i].setPriority(arr[i].getStart()); // Sets the minHeap priority based on end times
			sortedIntervals.add(arr[i]); // Adds the intervals to the minHeap and minHeap sorts them as they are entered
		}
		classes[0] = new Classroom(0, size); // Create the first classroom
		interval temp = sortedIntervals.remove();
		classes[0].addLecture(temp); // add the first lecture to the first classroom
		Q.offer(classes[0].lastAdded(), 0); // add this lecture and the room number to the queue
		for (int i = 1; i < size; i++) {
			interval j = Q.peek(); // the lecture added to the classroom
			interval s = sortedIntervals.remove(); // the next lecture to be considered
			if (s.getStart() >= j.getEnd()) { // if the lecture to be considered is compatible with the last lecture in
												// the classroom
				int t = Q.getIndex(0); // used to retrieve the classroom number
				classes[t].addLecture(s); // add that lecture to the classroom
				Q.poll(); // make sure we update the priority to that of the finishtime of S
				Q.offer(s, t, s.getEnd());
			} else {
				classCount++; // if the lecture considered is not compatible
				classes[classCount] = new Classroom(classCount, size); // create a new classroom
				classes[classCount].addLecture(s); // add that lecture to the classroom
				Q.offer(classes[classCount].lastAdded(), classCount); // set the priority to be the end time of that
																		// lecture
			}
		}
		for (int i = 0; i < classCount + 1; i++) {
			System.out.println(classes[i]); // print out the classes and the lectures held in them
		}
		System.out.println("\nMinimum Number of Classes Necessary: " + classCount); // prints the minimum number of
																					// classrooms needed
	}
}
