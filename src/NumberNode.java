// a leaf node in the tree — just holds a single integer value
// this is always at the bottom of the tree since numbers have no children
public class NumberNode extends Node {
    private int value;

    public NumberNode(int value) {
        this.value = value;
    }

    // a number just returns itself, nothing to compute
    @Override
    public int evaluate() {
        return value;
    }

    // prints the number with the right tree branch characters
    @Override
    public void print(String prefix, boolean isLeft) {
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + value);
    }
}
