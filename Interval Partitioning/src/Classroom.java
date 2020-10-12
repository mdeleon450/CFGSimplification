// Authors: Maik De Leon Lopez, Alfredo Delgado
// CSC401-01 Project 1 

public class Classroom {
	private int id; // Used to identify the classroom
	private interval array[]; // Holds all the lectures
	private int size; // used to check if there is any lectures being held

	public Classroom(int id, int capacity) {
		this.id = id;
		this.array = new interval[capacity];
		this.size = 0;
	}

	public interval lastAdded() {
		if (this.size == 0) {
			return null;
		}
		return array[size - 1]; // returns the last lecture added
	}

	public void addLecture(interval lect) {
		this.array[this.size] = lect; // adds a lecture to our lecture array
		this.size++;
	}

	public String toString() { // prints out the formatting for a classroom and its lectures
		String s = "Class [id=" + this.id + ", intervals=[";
		if (this.size == 0) {
			return "Class [id=" + this.id + ", intervals= empty]";
		}
		for (int i = 0; i < this.size - 1; i++) {
			s += "[" + array[i].getId() + "[" + array[i].getStart() + ", " + array[i].getEnd() + "]], ";
		}
		s += "[" + array[size - 1].getId() + "[" + array[size - 1].getStart() + ", " + array[size - 1].getEnd()
				+ "]] ]";
		return s;
	}
}
