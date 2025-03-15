package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] input = new char[9];
        Arrays.fill(input, '_');
        Main.printBoard(input);
        for (char symbol : "XOXOXOXOX".toCharArray()) {
            int index = Main.getMove(scanner, input);
            input[index] = symbol;
            Main.printBoard(input);
            String gameStatus = Main.gameStatus(input);
            if (!gameStatus.equals("Game not finished")) {
                System.out.println(gameStatus);
                break;
            }
        }
    }

    private static void printBoard(char[] input) {
        System.out.println("---------");
        System.out.printf("| %c %c %c |%n", input[0], input[1], input[2]);
        System.out.printf("| %c %c %c |%n", input[3], input[4], input[5]);
        System.out.printf("| %c %c %c |%n", input[6], input[7], input[8]);
        System.out.println("---------");
    }

    private static int getMove(Scanner scanner, char[] input) {
        int index;
        while (true) {
            String move = scanner.nextLine();
            char[] moveChars = move.toCharArray();
            for (char c : moveChars) {
                if (!"1234567890 ".contains(String.valueOf(c))) {
                    System.out.println("You should enter numbers!");
                    continue;
                }
            }
            String[] coordinates = move.split(" ");
            int x = Integer.parseInt(coordinates[0]) - 1;
            int y = Integer.parseInt(coordinates[1]) - 1;
            if (x < 0 || x > 2 || y < 0 || y > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            index = x * 3 + y;
            if (input[index] != '_') {
                System.out.println("This cell is occupied!");
                continue;
            } else {
                break;
            }
        }
        return index;
    }

    private static String gameStatus(char[] input) {
        /*
        long xs = input.chars().filter(c -> c == 'X').count();
        long os = input.chars().filter(c -> c == 'O').count();
        if (Math.abs(xs - os) > 1) {
            return "Impossible";
        }
         */

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
                .map(line -> String.format("%c%c%c", input[line[0]], input[line[1]], input[line[2]]))
                .toArray(String[]::new);
        long xWins = Arrays.stream(lines).filter(line -> line.equals("XXX")).count();
        long oWins = Arrays.stream(lines).filter(line -> line.equals("OOO")).count();
        if (xWins == 1 && oWins == 0) {
            return "X wins";
        } else if (xWins == 0 && oWins == 1) {
            return "O wins";
        } else if (xWins == 0 && oWins == 0) {
            if (new String(input).contains("_")) {
                return "Game not finished";
            } else {
                return "Draw";
            }
        }
        /*
        else {
            return "Impossible";
        }
         */
        return "";
    }
}
