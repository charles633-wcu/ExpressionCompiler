import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Setup the scanner to get the user's math expression [cite: 34-35]
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter math expression: ");
        String input = scanner.nextLine();

        try {
            // 2. Lexical Analysis: Convert the string into a list of tokens [cite: 10-13, 27]
            Lexer lexer = new Lexer(input);
            List<Token> tokens = lexer.tokenize();
            System.out.println("Step 1 (Tokens): " + tokens);

            // 3. Parsing: Check the syntax and build the tree [cite: 14, 21-22, 29]
            Parser parser = new Parser(tokens);
            Node root = parser.parseExpression();
            System.out.println("Step 2 (Parsing): Success!");

            // 4. Trace Output: Print the tree structure as text [cite: 25, 30]
            System.out.println("Step 3 (Tree Structure):");
            root.print("", false);

            // 5. Evaluation: Calculate the final number [cite: 23-24, 31, 42]
            int result = root.evaluate();
            System.out.println("Step 4 (Final Result): " + result);

        } catch (Exception e) {
            // If the user types something invalid, catch it here 
            System.out.println("Error found: " + e.getMessage());
        }
    }
}