import java.util.List;

// the parser takes the token list from the lexer and builds an expression tree (AST)
// it uses recursive descent parsing, which mirrors the grammar rules directly in code:
//   E → E + T | E - T | T
//   T → T * F | T / F | F
//   F → (E) | number | -F
public class Parser {
    private List<Token> tokens;
    private int current = 0; // index of the token we're currently looking at

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    // look at the current token without consuming it
    private Token peek() { return tokens.get(current); }

    // grab the current token and move forward
    private Token consume() { return tokens.get(current++); }

    // lets Main check whether any tokens are left over after parsing
    public int getCurrentIndex() { return current; }

    // handles + and - (lowest precedence, so parsed last / outermost)
    // keeps looping as long as there's a + or - to handle left-to-right chaining
    public Node parseExpression() {
        Node node = parseTerm();
        while (peek().type == TokenType.PLUS || peek().type == TokenType.MINUS) {
            TokenType op = consume().type;
            node = new BinOpNode(node, op, parseTerm());
        }
        return node;
    }

    // handles * and / (higher precedence than + and -)
    // same idea as parseExpression but for multiplication and division
    private Node parseTerm() {
        Node node = parseFactor();
        while (peek().type == TokenType.MULT || peek().type == TokenType.DIV) {
            TokenType op = consume().type;
            node = new BinOpNode(node, op, parseFactor());
        }
        return node;
    }

    // handles the smallest units: numbers, parenthesized expressions, and unary minus
    private Node parseFactor() {
        if (peek().type == TokenType.LPAREN) {
            // found a '(' so we recurse into a full expression inside the parens
            consume(); // eat the '('
            Node node = parseExpression();
            Token next = consume();
            if (next.type != TokenType.RPAREN) {
                // if there's no closing ')' that's a syntax error
                throw new RuntimeException(
                    "Syntax Error: expected ')' but found '" + next.value + "' at position " + next.position + "\n" +
                    "  How it was caught: The parser opened a '(' and recursively parsed the inner expression.\n" +
                    "  When the inner expression finished, it expected a closing ')' to match the opening '('.\n" +
                    "  Instead it found '" + next.value + "', which cannot legally appear here."
                );
            }
            return node;
        } else if (peek().type == TokenType.NUMBER) {
            // just a plain number, wrap it in a NumberNode
            return new NumberNode(Integer.parseInt(consume().value));
        } else if (peek().type == TokenType.MINUS) {
            // unary minus like -3, we treat it as (0 - value)
            consume(); // eat the '-'
            return new BinOpNode(new NumberNode(0), TokenType.MINUS, parseFactor());
        }

        // if we get here, something unexpected showed up where a number or '(' was required
        Token bad = peek();
        throw new RuntimeException(
            "Syntax Error: unexpected token '" + bad.value + "' at position " + bad.position + "\n" +
            "  How it was caught: The parser was expecting a number, a '(' to start a sub-expression,\n" +
            "  or a '-' for a negative value, but found '" + bad.value + "' instead.\n" +
            "  This usually means an operator is missing its right-hand operand (e.g. '4 -)')."
        );
    }
}
