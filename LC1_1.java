package logicbuild;

import java.util.Scanner;

public class LC1_1 {

	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int x = sc.nextInt();

        System.out.print("Enter second number: ");
        int y = sc.nextInt();

        int temp = x;
        x = y;
        y = temp;

        System.out.println("After swapping:");
        System.out.println("First number = " + x);
        System.out.println("Second number = " + y);

        sc.close();
	}

}
