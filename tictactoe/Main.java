package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Main.printBoard(input);
        System.out.println(gameStatus(input));
    }

    private static void printBoard(String input) {
        System.out.println("---------");
        System.out.printf("| %c %c %c |%n", input.charAt(0), input.charAt(1), input.charAt(2));
        System.out.printf("| %c %c %c |%n", input.charAt(3), input.charAt(4), input.charAt(5));
        System.out.printf("| %c %c %c |%n", input.charAt(6), input.charAt(7), input.charAt(8));
        System.out.println("---------");
    }

    private static String gameStatus(String input) {
        long xs = input.chars().filter(c -> c == 'X').count();
        long os = input.chars().filter(c -> c == 'O').count();
        if (Math.abs(xs - os) > 1) {
            return "Impossible";
        }

        int[][] lineIndices = {
                // rows
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                // columns
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                // diagonals
                {0, 4, 8},
                {2, 4, 6},
        };
        String[] lines = Arrays
                .stream(lineIndices)
                .map(line -> Main.lineFromIndices(input, line))
                .toArray(String[]::new);
        long xWins = Arrays.stream(lines).filter(line -> line.equals("XXX")).count();
        long oWins = Arrays.stream(lines).filter(line -> line.equals("OOO")).count();
        if (xWins == 1 && oWins == 0) {
            return "X wins";
        } else if (xWins == 0 && oWins == 1) {
            return "O wins";
        } else if (xWins == 0 && oWins == 0) {
            if (input.contains("_")) {
                return "Game not finished";
            } else {
                return "Draw";
            }
        } else {
            return "Impossible";
        }
    }

    private static String lineFromIndices(String input, int[] indices) {
        return String.format("%c%c%c", input.charAt(indices[0]), input.charAt(indices[1]), input.charAt(indices[2]));
    }
}
