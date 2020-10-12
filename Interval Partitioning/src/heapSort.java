// Author: Maik De Leon Lopez, Alfredo Delgado
// CSC401-01 Project 1 

public class heapSort {
	private interval[] nodeList; // An array of intervals
	private int capacity; // Max capacity of the array
	private int size; // How many Elements are in the array

	public heapSort(int capacity) { // Creates the heap
		this.capacity = capacity;
		nodeList = new interval[this.capacity];
		this.size = 0;
	}

	// Method that adds a interval to the minHeap based on its priority
	public void add(interval data) {
		if (size > capacity) { // if the size is larger than capacity of the minHeap then display error
			System.out.println("Invalid Index!");
			return;
		}
		int current = size; // Otherwise we add the data to the end of the array (at size)
		nodeList[current] = data;
		size++; // Increment the size

		while (nodeList[current].getPriority() < nodeList[parent(current)].getPriority()) {
			swap(current, parent(current)); // Check if the priority of the node we added is higher than the parents
			current = parent(current); // If so swap and swap until the root if necessary
		}
	}

	// This method allows us to adjust the priority of the data, and adjusts the
	// whole minHeap accordingly
	public void add(interval data, int key) {
		if (size > capacity) { // if the size is larger than capacity of the minHeap then display error
			System.out.println("Invalid Index!");
			return;
		}
		int current = size; // Otherwise we add the data to the end of the array (at size)
		nodeList[current].setPriority(key);
		nodeList[current] = data;
		size++; // Increment the size

		while (nodeList[current].getPriority() < nodeList[parent(current)].getPriority()) {
			swap(current, parent(current)); // Check if the priority of the node we added is higher than the parents
			current = parent(current); // If so swap and swap until the root if necessary
		}
	}

	// Method that removes a interval from the minHeap (highest priority is
	// returned)
	public interval remove() {
		if (size == 0) { // If the size is 0 then it is empty
			System.out.println("Heap is Empty");
			return null;
		}
		int current = 0; // Otherwise we remove the root
		interval temp = nodeList[current];
		nodeList[current] = nodeList[--size]; // Move the last node added to the root, then we Min-Heapify
		while (hasLeftChild(current) || hasRightChild(current)) { // While the root has a left or right child
			if (hasLeftChild(current) && hasRightChild(current)) { // if it has both left and right
				if (nodeList[leftChild(current)].getPriority() < nodeList[rightChild(current)].getPriority()) {
					swap(current, leftChild(current)); // if the left priority is lower(meaning higher priority)
					current = leftChild(current); // then swap the root with the left child
				} else {// if the right priority is lower
					swap(current, rightChild(current)); // then swap the root with the right
					current = rightChild(current);
				}
			} else if (hasLeftChild(current)) { // If the root only has a left child swap with it
				swap(current, leftChild(current));
				current = leftChild(current);
			} else if (hasRightChild(current)) { // If the root only has a right child swap with it
				swap(current, rightChild(current));
				current = rightChild(current);
			}
		}
		return temp;
	}

	public interval peek() {
		return nodeList[0]; // The root is the min value
	}

	public boolean isEmpty() {
		return this.size == 0; // if the size is 0 then it is empty
	}

	private int parent(int pos) {
		return (pos - 1) / 2; // parent index is calculated by (pos-1)/2
	}

	private int leftChild(int pos) {
		return (2 * pos) + 1; // how left child index is calculated
	}

	private int rightChild(int pos) {
		return (2 * pos) + 2; // how right child index is calculated
	}

	private boolean hasRightChild(int pos) {
		return rightChild(pos) <= size; // if the index is a valid one (meaning less than or equal to size)
	}

	private boolean hasLeftChild(int pos) {
		return leftChild(pos) <= size; // if left child index is valid
	}

	// Helper Method that swaps the contents of two positions
	private void swap(int fpos, int spos) {
		interval temp = nodeList[fpos];
		nodeList[fpos] = nodeList[spos];
		nodeList[spos] = temp;
	}

}
