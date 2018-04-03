//Anisha Aggarwal	CIS313	lab2EC

import java.util.Scanner;

public class lab2EC {
    public static void main(String[] args) {

        // Loop over the Scanner

        Scanner input = new Scanner(System.in);

		int data;
		BST<Integer> myBST_1 = new BST<Integer>();
		BST<Integer> myBST_2 = new BST<Integer>();

		// Depending on what the input task was, preform the corresponding function
        //      (ie) insert, delete
        // Hint: Use a switch-case statement

		//myBSD_1 being read in
		 // Split each line into the task and the corresponding number (if one exists)
		int NumCommands = input.nextInt();
		input.nextLine();
		for (int i=0; i < NumCommands; i++) {
	        String str = input.nextLine();

	        // Split input string into words
	        String[] array = str.split(" ");

	        switch (array[0]) {
	            case "insert":
	                data = Integer.parseInt(array[1]);
	                myBST_1.insert(data);
	            	break;
	            case "delete":
	                data = Integer.parseInt(array[1]);
	                myBST_1.delete(data);
	                break;
		     }
		}

		//myBSD_2 being read in
		// Split each line into the task and the corresponding number (if one exists)
		int NumCommands = input.nextInt();
		input.nextLine();

		for (int i=0; i < NumCommands; i++) {
	        String str = input.nextLine();

	        // Split input string into words
	        String[] array = str.split(" ");

	        switch (array[0]) {
	            case "insert":
	                data = Integer.parseInt(array[1]);
	                myBST_2.insert(data);
	            	break;
	            case "delete":
	                data = Integer.parseInt(array[1]);
	                myBST_2.delete(data);
	                break;
		     }
		}

		boolean same_shape;
		same_shape = isSameShape(myBST_1.getRoot(), myBST_2.getRoot());
		if (same_shape == true) {
			System.out.print("The trees have the same shape.");
		} else {
			System.out.print("The trees do not have the same shape.");
		}

		// Don't forget to close your Scanner!
		input.close();
    }
}

