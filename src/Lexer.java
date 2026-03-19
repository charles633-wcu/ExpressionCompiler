import java.util.ArrayList;
import java.util.List;

// the lexer (tokenizer) reads the raw input string character by character
// and converts it into a list of tokens the parser can understand
public class Lexer {
    private String input; // the expression the user typed in
    private int pos = 0;  // keeps track of where we are in the string

    public Lexer(String input) {
        this.input = input;
    }

    // walks through every character and figures out what token it belongs to
    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (pos < input.length()) {
            char c = input.charAt(pos);

            if (Character.isWhitespace(c)) {
                // just skip spaces, they don't mean anything
                pos++;
            } else if (Character.isDigit(c)) {
                // keep reading digits until we hit something that isn't a digit
                // this handles multi-digit numbers like 123
                int start = pos + 1; // 1-based position of the first digit
                StringBuilder sb = new StringBuilder();
                while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                    sb.append(input.charAt(pos++));
                }
                tokens.add(new Token(TokenType.NUMBER, sb.toString(), start));
            } else if (c == '+') { tokens.add(new Token(TokenType.PLUS,   "+", pos + 1)); pos++; }
            else if (c == '-') { tokens.add(new Token(TokenType.MINUS,  "-", pos + 1)); pos++; }
            else if (c == '*') { tokens.add(new Token(TokenType.MULT,   "*", pos + 1)); pos++; }
            else if (c == '/') { tokens.add(new Token(TokenType.DIV,    "/", pos + 1)); pos++; }
            else if (c == '(') { tokens.add(new Token(TokenType.LPAREN, "(", pos + 1)); pos++; }
            else if (c == ')') { tokens.add(new Token(TokenType.RPAREN, ")", pos + 1)); pos++; }
            else {
                // anything we don't recognize is a problem — report the 1-based position
                throw new RuntimeException(
                    "Lexical Error: unexpected character '" + c + "' at position " + (pos + 1) + "\n" +
                    "  How it was caught: The lexer scans character by character. When it hit '" + c +
                    "' it didn't match any known token (digit, operator, or parenthesis), so it stopped immediately."
                );
            }
        }

        // always add an EOF token at the end so the parser knows when to stop
        tokens.add(new Token(TokenType.EOF, "EOF", input.length() + 1));
        return tokens;
    }
}
