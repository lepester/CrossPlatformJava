package lab;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int n = 0;
        while (true) // ввод числа строк
        {
            System.out.println("Enter a number of strings: ");
            Scanner sc1 = new Scanner(System.in);
            try {
                n = sc1.nextInt();
                break;
            } catch (InputMismatchException fg) {
                // если введенное значение не является числом
                System.out.print("Must be an Integer! ");
            }
        }
        // создание массива строк
        String[] str = new String[n];
        Scanner sc2 = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.println("Enter string №" + (i + 1));
            str[i] = sc2.nextLine();
        }
        //сортировка массива строк по длине
        for (int i = 0; i < str.length - 1; i++) {
            for (int j = i + 1; j > str.length; j++) {

                if (str[i].length() > str[j].length()) {
                    String temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                }
            }
        }

        int maxlength = str[0].length();
        System.out.println("Strings' length in ascending order: ");
        for (String s : str) {
            System.out.print(s);
            System.out.print(" ");
            System.out.println("Length is = " + s.length());
        }
        }
    }
