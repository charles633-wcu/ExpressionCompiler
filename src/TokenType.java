// all the different kinds of tokens our lexer can produce
// NUMBER is for any integer, the rest are operators, parens, and EOF to signal the end
public enum TokenType {
    NUMBER, PLUS, MINUS, MULT, DIV, LPAREN, RPAREN, EOF
}
