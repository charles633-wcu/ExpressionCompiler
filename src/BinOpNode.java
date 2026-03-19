// a binary operation node — represents things like (left + right) or (left * right)
// it always has exactly two children: a left side and a right side
public class BinOpNode extends Node {
    private Node left;      // the left operand (could be a number or another expression)
    private TokenType op;   // the operator: PLUS, MINUS, MULT, or DIV
    private Node right;     // the right operand

    public BinOpNode(Node left, TokenType op, Node right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    // recursively evaluates both sides first, then applies the operator
    @Override
    public int evaluate() {
        switch (op) {
            case PLUS:  return left.evaluate() + right.evaluate();
            case MINUS: return left.evaluate() - right.evaluate();
            case MULT:  return left.evaluate() * right.evaluate();
            case DIV:   return left.evaluate() / right.evaluate(); // note: integer division
            default: throw new RuntimeException("Unknown operator");
        }
    }

    // prints this operator node, then recursively prints left and right children
    // the prefix string builds up the indentation and branch lines as we go deeper
    @Override
    public void print(String prefix, boolean isLeft) {
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + op);
        left.print(prefix + (isLeft ? "│   " : "    "), true);
        right.print(prefix + (isLeft ? "│   " : "    "), false);
    }
}
