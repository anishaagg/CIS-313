//Anisha Aggarwal	CIS 313	Lab1	TwoStackQueue.java

public class TwoStackQueue<E> {
	private Stack<E> stack_1;
	private Stack<E> stack_2;
	
	public TwoStackQueue(){
		
		// We want to initialize our Queue to be empty
		// (ie) set head and tail to be null
		stack_1 = new Stack<E>();
		stack_2 = new Stack<E>();
		
	}
	
	public void enqueue(E newData){
	
		// push newData into stack_1
		// Hint: Think about what's different for the first node added to the TwoStackQueue
		stack_1.push(newData);

	}
	
	public Node<E> dequeue(){
	
		// Return the top of the TwoStackQueue
		// Pop node from stack_1 and push it into stack_2 - do until stack_1 is empty
		// Hint: Return null on a empty TwoStackQueue
		Node<E> temp;

		if(stack_1.isEmpty()) {
			return null;
		}
		
		while(!stack_1.isEmpty()){
			stack_2.push(stack_1.pop().getData());
		}

		temp = stack_2.pop();

		//we need to pop each node from stack_2 and push back into stack_1
		while(!stack_2.isEmpty()){
			stack_1.push(stack_2.pop().getData());
		}

		return temp;
	}
	
	public boolean isEmpty(){
	
		// Check if the TwoStackQueue is empty
		if(stack_1.isEmpty()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void printQueue(){

		// Loop through your TwoStackQueue and print each Node's data
		// to show FIFO order
		while(!stack_1.isEmpty()){
			stack_2.push(stack_1.pop().getData());
		}

		stack_2.printStack();

		// we need to pop each node from stack_2 and push back into stack_1
		while(!stack_2.isEmpty()){
			stack_1.push(stack_2.pop().getData());
		}
		
	}
}
