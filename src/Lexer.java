import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private String input;
    private int pos = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (pos < input.length()) {
            char c = input.charAt(pos);
            if (Character.isWhitespace(c)) {
                pos++;
            } else if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                    sb.append(input.charAt(pos++));
                }
                tokens.add(new Token(TokenType.NUMBER, sb.toString()));
            } else if (c == '+') { tokens.add(new Token(TokenType.PLUS, "+")); pos++; }
            else if (c == '-') { tokens.add(new Token(TokenType.MINUS, "-")); pos++; }
            else if (c == '*') { tokens.add(new Token(TokenType.MULT, "*")); pos++; }
            else if (c == '/') { tokens.add(new Token(TokenType.DIV, "/")); pos++; }
            else if (c == '(') { tokens.add(new Token(TokenType.LPAREN, "(")); pos++; }
            else if (c == ')') { tokens.add(new Token(TokenType.RPAREN, ")")); pos++; }
            else {
                throw new RuntimeException("Lexical Error: Unexpected character '" + c + "'");
            }
        }
        tokens.add(new Token(TokenType.EOF, "EOF"));
        return tokens;
    }
}