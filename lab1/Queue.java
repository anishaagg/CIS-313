//Anisha Aggarwal	CIS 313	Lab1	Queue.java
public class Queue<E> {
	private Node<E> head;
	private Node<E> tail;
	
	public Queue(){
		
		// We want to initialize our Queue to be empty
		// (ie) set head and tail to be null
		head = null;
		tail = null;
		
	}
	
	public void enqueue(E newData){
	
		// Create a new node whose data is newData and whose next node is null
		// Update head and tail
		// Hint: Think about what's different for the first node added to the Queue
		Node<E> temp = new Node<E>(newData, null);

		if (head == null) {
			head = temp;
			tail = temp;
		} else {
			tail.setNext(temp);
			tail = temp;
		}

	}
	
	public Node<E> dequeue(){
	
		// Return the head of the Queue
		// Update head
		// Hint: The order you implement the above 2 tasks matters, so use a temporary node
	 	//	     to hold important information
		// Hint: Return null on a empty Queue
		Node<E> temp;

		if (head != null) {
			temp = head;
			// checking if there is only one element, if so set tail to null
			if (head == tail) {
				tail = null;
			}
			head = head.getNext();
			return temp;
		} else {
			return head;
		}
		
	}
	
	public boolean isEmpty(){
	
		// Check if the Queue is empty
		if (head == null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void printQueue(){

		// Loop through your queue and print each Node's data
		Node<E> temp;

		// traverse through queue using temp node so we don't lose head
		temp = head;
		while (temp != null) {
			System.out.print(temp.getData());
			temp = temp.getNext();
		}
		System.out.println();
	}
}
