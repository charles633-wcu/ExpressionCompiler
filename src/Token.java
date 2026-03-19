/*  a token is just a pair: what kind of thing it is (type) and the actual text (value)
 for example, the character '+' becomes Token(PLUS, "+")
 position stores where in the original input this token started (1-based index) */
public class Token {
    public TokenType type;
    public String value;
    public int position; // 1-based character position in the input string

    public Token(TokenType type, String value, int position) {
        this.type = type;
        this.value = value;
        this.position = position;
    }

    // formats the token nicely when we print the token list, e.g. [NUMBER, 3]
    @Override
    public String toString() {
        return String.format("[%s, %s]", type, value);
    }
}
