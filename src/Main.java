import java.util.List;
import java.util.Scanner;

// entry point for the mini expression compiler
// walks the user through each phase: tokenizing, parsing, tree building, and evaluation
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // keep accepting expressions until the user types 'quit'
        while (true) {
            System.out.print("Enter math expression (or 'quit' to exit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) break;

            try {
                // phase 1: lexical analysis — break the string into tokens
                Lexer lexer = new Lexer(input);
                List<Token> tokens = lexer.tokenize();
                System.out.println("Step 1 (Tokens): " + tokens);

                // phase 2: parsing — check that the token order follows valid grammar
                Parser parser = new Parser(tokens);
                Node root = parser.parseExpression();

                // after a valid expression, the only legal next token is EOF
                // if anything else remains, there's a stray token in the input
                Token leftover = tokens.get(parser.getCurrentIndex());
                if (leftover.type != TokenType.EOF) {
                    throw new RuntimeException(
                        "Syntax Error: unexpected token '" + leftover.value + "' at position " + leftover.position + "\n" +
                        "  How it was caught: The parser finished building a complete expression, but\n" +
                        "  found '" + leftover.value + "' still remaining in the input.\n" +
                        "  A valid expression should end here — check for an extra operator or closing parenthesis."
                    );
                }

                System.out.println("Step 2 (Parsing): Success!");

                // phase 3: print the expression tree so we can see the structure
                System.out.println("Step 3 (Tree Structure):");
                root.print("", false);

                // phase 4: evaluate the tree by recursively computing each node
                int result = root.evaluate();
                System.out.println("Step 4 (Final Result): " + result);

            } catch (Exception e) {
                // split the message on the newline so the explanation prints on its own line
                String[] parts = e.getMessage().split("\n", 2);
                System.out.println("\n*** " + parts[0] + " ***");
                if (parts.length > 1) {
                    System.out.println(parts[1]);
                }
                System.out.println();
            }
        }
    }
}
