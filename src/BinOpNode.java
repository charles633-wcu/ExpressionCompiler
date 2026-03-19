public class BinOpNode extends Node {
    private Node left;
    private TokenType op;
    private Node right;

    public BinOpNode(Node left, TokenType op, Node right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public int evaluate() {
        switch (op) {
            case PLUS:  return left.evaluate() + right.evaluate();
            case MINUS: return left.evaluate() - right.evaluate();
            case MULT:  return left.evaluate() * right.evaluate();
            case DIV:   return left.evaluate() / right.evaluate();
            default: throw new RuntimeException("Unknown operator");
        }
    }

    @Override
    public void print(String prefix, boolean isLeft) {
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + op);
        left.print(prefix + (isLeft ? "│   " : "    "), true);
        right.print(prefix + (isLeft ? "│   " : "    "), false);
    }
}