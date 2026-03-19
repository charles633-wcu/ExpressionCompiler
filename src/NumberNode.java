public class NumberNode extends Node {
    private int value;

    public NumberNode(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }

    @Override
    public void print(String prefix, boolean isLeft) {
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + value);
    }
}