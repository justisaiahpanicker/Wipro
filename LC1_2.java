package logicbuild;

import java.util.Scanner;

public class LC1_2 {

	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of a: ");
        int a = sc.nextInt();

        System.out.print("Enter value of b: ");
        int b = sc.nextInt();

        System.out.print("Enter value of c: ");
        int c = sc.nextInt();


        int temp = a;

        a = c;
        c = b;
        b = temp;


        System.out.println("After rotation:");

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);


        sc.close();
	}

}
