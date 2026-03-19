import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token peek() { return tokens.get(current); }
    private Token consume() { return tokens.get(current++); }

    // E -> T { (+|-) T } [cite: 16]
    public Node parseExpression() {
        Node node = parseTerm();
        while (peek().type == TokenType.PLUS || peek().type == TokenType.MINUS) {
            TokenType op = consume().type;
            node = new BinOpNode(node, op, parseTerm());
        }
        return node;
    }

    // T -> F { (*|/) F } [cite: 17]
    private Node parseTerm() {
        Node node = parseFactor();
        while (peek().type == TokenType.MULT || peek().type == TokenType.DIV) {
            TokenType op = consume().type;
            node = new BinOpNode(node, op, parseFactor());
        }
        return node;
    }

    // F -> (E) | number [cite: 18-19]
    private Node parseFactor() {
        if (peek().type == TokenType.LPAREN) {
            consume(); // '('
            Node node = parseExpression();
            if (consume().type != TokenType.RPAREN) {
                throw new RuntimeException("Syntax Error: Missing ')'");
            }
            return node;
        } else if (peek().type == TokenType.NUMBER) {
            return new NumberNode(Integer.parseInt(consume().value));
        } else if (peek().type == TokenType.MINUS) { // Support for unary [cite: 32]
            consume(); // '-'
            return new BinOpNode(new NumberNode(0), TokenType.MINUS, parseFactor());
        }
        throw new RuntimeException("Syntax Error: Unexpected token " + peek().value);
    }
}