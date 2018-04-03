//Anisha Aggarwal   CIS313  lab3    MaxHeap.java

public class MaxHeap<E extends Comparable> {
    private E[] myArray;
    private int maxSize;
    private int length;

    
    public MaxHeap(int s){
    	// Build the constructor
        maxSize = s;
        length = 0;
        myArray = (E[]) new Comparable[maxSize];
    }

	// helper functions
    public E[] getArray(){
        return myArray;
    }

    public void setArray(E[] newArray){
    	if (newArray.length > maxSize){
            //can't do anything, so just return
    		return;
    	}
        myArray = newArray;
        length = newArray.length;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public void setMaxSize(int ms){
        maxSize = ms;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int l){
        length = l;
    }

    //fuctaion to get the parent
     public int getParent(int index){
        return (index - 1) / 2;
    }
    //fuctaion to get the left child
    public int getLeftChild(int index){
        return 2 * index + 1;
    }
    //fuctaion to get the right child
    public int getRightChild(int index){
        return 2 * index + 2;
    }


    // Other Methods
    public void insert(E data){
    
    	// Insert an element into your heap.
        myArray[length] = data;

        // When adding a node to your heap, remember that for every node n, 
        // the value in n is less than or equal to the values of its children, 
        // but your heap must also maintain the correct shape.
        // (ie there is at most one node with one child, and that child is the left child.)
        // (Additionally, that child is farthest left possible.)
        int len = length;
        E temp;

        while (len > 0) {
            int parent = getParent(len);
            if (myArray[parent].compareTo(myArray[len]) < 0) {
                temp = myArray[parent];
                myArray[parent] = myArray[len];
                myArray[len] = temp;
            } else {
                break;
            }

            len = parent;
        }

        length++;
    	
    }

    public Comparable<E> maximum(){
        // return the minimum value in the heap
        if (myArray.length != 0) {
            return myArray[0];
        } else {
            return null;
        }
    }

    public Comparable<E> extractMax(){
        // remove and return the minimum value in the heap
        E toReturn = myArray[0];
        myArray[0] = myArray[length - 1];
        length--;
        heapify(0);
        return toReturn;
    }
    
    public void heapify(int i){
    	// helper function for reshaping the array
        int leftChild = getLeftChild(i);
        int rightChild = getRightChild(i);
        int max = i;
        int max_l = 0;  //holds max index of left
        int max_r = 0;  //holds max index of right
        
        if ((leftChild < length) && (myArray[leftChild].compareTo(myArray[i]) > 0)) {
            max_l = leftChild;
        }

        if ((rightChild < length) && (myArray[rightChild].compareTo(myArray[i]) > 0)) {
            max_r = rightChild;
        }

        //find max between left and right child
        if ((max_l > 0) && (max_r == 0)) {
            max = max_l;
        } else if ((max_r > 0) && (max_l == 0)) {
            max = max_r;
        } else if ((max_r > 0) && (max_l > 0)){
            max = (myArray[max_l].compareTo(myArray[max_r]) > 0) ? max_l : max_r;
        }

        if (max != i) {
            E temp = myArray[i];
            myArray[i] = myArray[max];
            myArray[max] = temp;
            heapify(max);
        }
    }
    
    public void buildHeap(E[] newArr){
		// used for the extra credit
        for(int i = 0; i < newArr.length; ++i) {
            insert(newArr[i]);
        }
	}
}






