import java.util.Scanner;

public class lab4 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String input;
        String task;
        int num;
        RBT<Integer> myRBT = new RBT<Integer>();
        WrongTree<Integer> myWT = new WrongTree<Integer>(10);
        RBT<Integer> myRBT2 = new RBT<Integer>();

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            String[] phrases = input.split(" ");
            task = phrases[0];
            switch(task) {
                case "insert" :
                    num = Integer.parseInt(phrases[1]);
                    myRBT.insert(num);
                    break;
                case "delete" :
                    num = Integer.parseInt(phrases[1]);
                    myRBT.delete(num);
                    break;
                case "search" :
                    num = Integer.parseInt(phrases[1]);
                    Node<Integer> found = myRBT.search(num);
                    if (found == null){
                        System.out.println("Not Found");
                    } else {
                        System.out.println("Found");
                    }
                    break;
                case "traverse" :
                    myRBT.traverse("preorder", myRBT.getRoot());
                    System.out.println();
                    break;
                case "exit" :
                    // TODO:
                    // Exit correctly
                	System.out.println("Successful Exit");
                    break;
                case "test" :
                	// TODO:
                	// Implement for EC purposes
                	boolean check = myRBT2.isRBT(myWT.getRoot());
                	System.out.println(myWT.getTime());
                	if (check) {
                		System.out.println("true");
                	} else {
                		System.out.println("false");
                	}
                	break;
                default:
                    break;
            }
        }
        sc.close();
    }
}