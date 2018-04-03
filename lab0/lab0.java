//Anisha Aggarwal CIS313  lab0.java

import java.util.Scanner;
import java.lang.Math;

public class lab0 {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    int numProblems = scanner.nextInt();
    for(int i = 0; i < numProblems; ++i){
      int a = scanner.nextInt();
      int b = scanner.nextInt();
      int g = gcd(a,b);
      int l = lcm(a,b);
      System.out.println(g + " " + l);
    }
  }

  public static int gcd(int a, int b){
    // Find the greatest common divisor of a and b
    // Hint: Use Euclids Algorithm

    //take the absolute value of a and b to only deal with positive integeres
    a = Math.abs(a);
    b = Math.abs(b);

    if ((a == 0) || (b == 0)) {
      return 1;
    }
	
	// Euclidean Algorithm for GCD
	int r;
    while (b != 0) {
    	r = a % b;
    	if (r == 0) {
    		return b;
    	} else {
    		a = b;
    		b = r;
    	}
    }

    return 1;
  }

  public static int lcm(int a, int b){
    // Find the least common multiple of a and b
    // Hint: Use the gcd of a and b
    int lcm = 0;

    lcm = (a*b)/gcd(a,b);

    return lcm;
  }
}

