//Anisha Aggarwal   CIS 313 Lab2

import java.util.Scanner;

public class lab2 {
    public static void main(String[] args) {

        // Loop over the Scanner

        Scanner input = new Scanner(System.in);

		int data;
		BST<Integer> myBST = new BST<Integer>();

        // Split each line into the task and the corresponding number (if one exists)
		int NumCommands = input.nextInt();
		input.nextLine();

		// Depending on what the input task was, preform the corresponding function
        //      (ie) insert, delete, find, inoder, preorder, or postorder
        // Hint: Use a switch-case statement

		for (int i=0; i < NumCommands; i++) {
	        String str = input.nextLine();

	        // Split input string into words
	        String[] array = str.split(" ");

	        switch (array[0]) {
	            case "insert":
	                data = Integer.parseInt(array[1]);
	                myBST.insert(data);
	            	break;
	            case "delete":
	                data = Integer.parseInt(array[1]);
	                myBST.delete(data);
	                break;
	            case "find":
	            	data = Integer.parseInt(array[1]);
	            	myBST.find(data).getData();
	            	break;
	      		case "preorder":
					myBST.traverse(array[0], myBST.getRoot());
					System.out.print("\n");
	      			break;
		     	case "postorder":
		     		myBST.traverse(array[0], myBST.getRoot());
		     		System.out.print("\n");
		     		break;
		     	case "inorder":
		     		myBST.traverse(array[0], myBST.getRoot());
		     		System.out.print("\n");
		        	break;
		     }
		}
		// Don't forget to close your Scanner!
		input.close();
    }
}