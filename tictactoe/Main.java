package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("---------");
        System.out.printf("| %c %c %c |%n", input.charAt(0), input.charAt(1), input.charAt(2));
        System.out.printf("| %c %c %c |%n", input.charAt(3), input.charAt(4), input.charAt(5));
        System.out.printf("| %c %c %c |%n", input.charAt(6), input.charAt(7), input.charAt(8));
        System.out.println("---------");
    }
}
