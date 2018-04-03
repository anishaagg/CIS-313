//Anisha Aggarwal   CIS313  lab3    pQueue.java

public class pQueue<E extends Comparable> {
    private MaxHeap myHeap;

    public pQueue (int s) {
        // Build the Constructor
        myHeap = new MaxHeap(s);
    }

    public void insert(E data){
        myHeap.insert(data);
    }

    public Comparable<E> maximum(){
        Comparable<E> max = myHeap.maximum();
        System.out.print(max);
        System.out.print("\n");
        return max;
    }

    public Comparable<E> extractMax(){
        Comparable<E> temp = myHeap.extractMax();
        System.out.print(temp);
        System.out.print("\n");
        return temp;
    }

    public boolean isEmpty(){
        // Return true when the priority queue is empty
        // Hint: Do the actual printing in your lab3.java
        if (myHeap.getLength() == 0) {
            return true;
        } else {
            return false;
        }
    }

	public void build(E[] arr){
    	// used for the extra credit
        myHeap.buildHeap(arr);
    }
    
    public void print(){
        // print out ”Current Queue: ” 
        System.out.print("Current Queue: ");
        // followed by each element separated by a comma. 
        // Do not add spaces between your elements.
        for (int i = 0; i < myHeap.getLength(); ++i) {
            System.out.print(myHeap.getArray()[i]);
            if (i < (myHeap.getLength() - 1)) {
                System.out.print(',');
            }
        }
        System.out.print('\n');
    }
}
