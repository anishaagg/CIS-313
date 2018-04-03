//Anisha Aggarwal	CIS313	Lab3 	lab3.java

import java.util.Scanner;


public class lab3 {
    public static void main(String[] args) {
    
    	// Loop over the Scanner
		Scanner input = new Scanner(System.in);


        // Split each line into the task and the corresponding number (if one exists)
        int NumCommands = input.nextInt();
        input.nextLine();   //eats new line character

        int size = NumCommands;
        pQueue<Integer> my_pq = new pQueue<Integer>(size);

        // Depending on what the input task was, preform the corresponding function
        //      (ie) insert, maximum, extractMax, isEmpty, or print
        // Hint: Use a switch-case statement
		for (int i=0; i < NumCommands; i++) {
            String str = "";
            if (input.hasNextLine()) {
                str = input.nextLine();
            } else {
                break;
            }
            
            String[] array = str.split(" ");
        	
        	switch (array[0]) {
        		case "insert":
        			my_pq.insert(Integer.parseInt(array[1]));
        			break;
        		case "maximum":
        			my_pq.maximum();
        			break;
        		case "extractMax":
        			my_pq.extractMax();
        			break;
        		case "isEmpty":
        			if (my_pq.isEmpty()) {
        				System.out.print("Empty\n");
        			} else {
        				System.out.print("Not Empty\n");
        			}
        			break;
        		case "print":
        			my_pq.print();
        			break;
                case "build":
                    String remove = array[1].substring(1, array[1].length() - 1);

                    String[] str_array = remove.split(",");
                    Integer[] num_array = new Integer[str_array.length];
                    for(int j = 0; j < str_array.length; ++j) {
                        num_array[j] = Integer.parseInt(str_array[j]);
                    }
                    my_pq.build(num_array);
                    break;
                default:
                    System.out.print("Bad input\n");
                    break;
        	}
        }

        // Don't forget to close your Scanner!
        input.close();
        
    }
}
