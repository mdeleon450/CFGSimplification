// Authors: Maik De Leon Lopez, Alfredo Delgado
// CSC401-01 Project 1 

public class priorityQueue { // Implemented using heapSort
	private heapSort minHeap;	
	private int capacity;	// Max size the minHeap could be
	private int size;	// What size it is currently
	private int index[];	// Used to determine what classroom we are in currently
	private int i;		// incremented to point to the right index 

	// Constructor
	public priorityQueue(int capacity) {
		this.capacity = capacity;
		minHeap = new heapSort(capacity + 1);
		this.size = 0;
		this.index = new int[capacity];
		this.i = 0;
	}

	// Method adds an interval into the minHeap
	public boolean offer(interval item, int index) {
		if (size >= capacity) { // If the size is outside of the bounds display error
			System.out.println("Invalid Index");
			return false;
		} else {
			this.index[i] = index;	// saves the classroom number we are in
			this.i++;	// changes the classroom number to the next one
			minHeap.add(item); // We can add and increment size
			size++;	// update the size
			return true;
		}
	}

	// Method adds an interval into the minHeap
	public boolean offer(interval item, int index, int key) {
		if (size >= capacity) { // If the size is outside of the bounds display error
			System.out.println("Invalid Index");
			return false;
		} else {
			this.index[i] = index;	// saves the classroom number we are currently in
			this.i++;		// changes the number to the next classroom
			minHeap.add(item, key); // add and update the priority of the item
			size++;	// update the size
			return true;
		}
	}

	// Method removes an interval from the minHeap (the one with highest priority)
	public interval poll() {
		if (minHeap.isEmpty()) { // If the Queue is empty display an error
			System.out.println("Queue is Empty");
			return null;
		} else {
			interval temp = (interval) minHeap.remove();
			size--; // We can remove and decrement size
			return temp;
		}
	}

	public interval peek() {
		return (interval) minHeap.peek(); // Can directly use the minHeap's peek method
	}

	public String toString() { // Not necessary but used to debug
		return minHeap.toString();
	}

	public boolean isEmpty() {
		return size == 0; // if the size is 0 then it is empty
	}

	public int getIndex(int target) {
		return this.index[target];
	}

}
