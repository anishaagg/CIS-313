//Anisha Aggarwal	CIS 313	Lab1	EC.java

import java.util.Scanner;

public class EC {
	public static void main(String[] args){
	
		// Create a Scanner that reads system input
		Scanner scan = new Scanner(System.in);

		// Loop over the scanner's input
		// For each line of the input, send it to isPalindrome()
		// If isPalindrome returns true, print "This is a Palindrome." 
		// Otherwise print "Not a Palindrome."
		String input;
		if (scan.hasNextInt()) {
			int i = scan.nextInt(); 
			scan.nextLine();	//eat the newline character
			while (i > 0) {
				if (scan.hasNext()){
	        		input = scan.nextLine();
	        		//System.out.println(input);
	        		if(isPalindromeEC(input) == true) {
	        			System.out.println("This is a Palindrome.");
	        		} else {
	        			System.out.println("Not a Palindrome.");
	        		}

	     		}
	     		//System.out.println(i);
	     		i--;
			}
		}

		// Close the Scanner		
		scan.close();
	}
	
	public static boolean isPalindromeEC(String s){
	
		// Create a Stack and a TwoStackQueue of chars that represents the passed in string
		// Hint: While you loop through the given string, push the same char onto your stack
		//		 that you enqueue into your Queue. This way you can use dequeue to get 
		//       the string from left to right, but you pop the string from right to left
		Stack<Character> stack = new Stack<Character>();
		TwoStackQueue<Character> tsqueue = new TwoStackQueue<Character>();

		int len = s.length();	// int that will hold the length string
		char c;			// int that will hold a single char from s
		for(int i = 0; i < len; i++) {
			c = s.charAt(i);
			stack.push(c);
			tsqueue.enqueue(c);
		}

		/*	TESTING WHAT THE STACK AND QUEUE ARE
		System.out.println("Stack: ");
		stack.printStack();
		System.out.println("TwoStackQueue: ");
		queue.printTwoStackQueue();
		*/

		// Compare your Queue and Stack to see if the input String was a Palindrome or not	
		while ((!stack.isEmpty()) && (!tsqueue.isEmpty())) {
			Node<Character> temp_s = stack.pop();
			Node<Character> temp_tsq = tsqueue.dequeue();

			Character c_s = temp_s.getData();
			Character c_tsq = temp_tsq.getData();
			if (c_s != c_tsq){
				//System.out.println("Not a Palindrome.");
				return false;
			}
		}

		//System.out.println("This is a Palindrome.");
		return true;
	}
}
