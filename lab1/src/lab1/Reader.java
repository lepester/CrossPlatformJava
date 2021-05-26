package lab1;

import java.util.*;

public class Reader {
    int i;
    int k;
    public void Scan() {
        System.out.println("Enter 1st number:");
        Scanner scn1 = new Scanner(System.in);
        i = scn1.nextInt();
        System.out.println("Enter 2nd number:");
        Scanner scn2 = new Scanner(System.in);
        k = scn2.nextInt();

        System.out.println("Hello, world");
    }
}

