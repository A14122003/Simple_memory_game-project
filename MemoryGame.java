import java.util.*;

public class MemoryGame {
    private static final int BOARD_SIZE = 4;  // 4x4 grid (16 cards)
    private static String[][] board;          // 2D array for the card values
    private static boolean[][] revealed;     // 2D array to track revealed cards
    private static int pairsFound = 0;        // Count of pairs found
    private static String[] values = {"A", "B", "C", "D", "E", "F", "G", "H"}; // Card values

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        board = new String[BOARD_SIZE][BOARD_SIZE];
        revealed = new boolean[BOARD_SIZE][BOARD_SIZE];
        
        // Initialize and shuffle the board
        initializeBoard();
        
        // Main game loop
        while (pairsFound < (BOARD_SIZE * BOARD_SIZE) / 2) {
            printBoard();
            System.out.println("Enter coordinates of two cards to flip (row and column between 0 and 3):");
            
            // Get first card
            System.out.print("First card (row column): ");
            int row1 = scanner.nextInt();
            int col1 = scanner.nextInt();
            
            // Get second card
            System.out.print("Second card (row column): ");
            int row2 = scanner.nextInt();
            int col2 = scanner.nextInt();
            
            // Flip the cards
            if (isValidMove(row1, col1) && isValidMove(row2, col2)) {
                if (board[row1][col1].equals(board[row2][col2])) {
                    revealed[row1][col1] = true;
                    revealed[row2][col2] = true;
                    pairsFound++;
                    System.out.println("Match found!");
                } else {
                    System.out.println("No match! Try again.");
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
            
            // Check if the game is over
            if (pairsFound == (BOARD_SIZE * BOARD_SIZE) / 2) {
                printBoard();
                System.out.println("Congratulations! You've matched all pairs!");
            }
        }
        
        scanner.close();
    }

    // Initialize the board with shuffled pairs of values
    private static void initializeBoard() {
        List<String> cards = new ArrayList<>(Arrays.asList(values));
        cards.addAll(Arrays.asList(values)); // Duplicate values for pairs
        Collections.shuffle(cards);  // Shuffle to randomize the cards

        int cardIndex = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = cards.get(cardIndex++);
                revealed[i][j] = false;  // Initially no cards are revealed
            }
        }
    }

    // Print the board state, showing only revealed cards
    private static void printBoard() {
        System.out.println("\nBoard:");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (revealed[i][j]) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print("? ");
                }
            }
            System.out.println();
        }
    }

    // Check if the selected card position is valid
    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && !revealed[row][col];
    }
}
/*
output

Board:
? ? ? ? 
? ? ? ? 
? ? ? ? 
? ? ? ? 
Enter coordinates of two cards to flip (row and column between 0 and 3):
First card (row column): 0 0
Second card (row column): 1 0

No match! Try again.

Board:
? ? ? ? 
? ? ? ? 
? ? ? ? 
? ? ? ? 
Enter coordinates of two cards to flip (row and column between 0 and 3):
First card (row column): 0 0
Second card (row column): 0 1

Match found!

Board:
A ? ? ? 
A ? ? ? 
? ? ? ? 
? ? ? ? 
Enter coordinates of two cards to flip (row and column between 0 and 3):

Congratulations! You've matched all pairs!

Board:
A A B B 
C C D D 
E E F F 
G G H H 
*/