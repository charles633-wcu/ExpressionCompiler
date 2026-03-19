// base class for all nodes in the expression tree (AST)
// every node needs to be able to evaluate itself and print itself
// NumberNode and BinOpNode both extend this
public abstract class Node {

    // recursively computes the value of this node and everything below it
    public abstract int evaluate();

    // prints this node as part of the tree, using prefix lines to show structure
    // isLeft tells us whether to use ├── or └── for the branch style
    public abstract void print(String prefix, boolean isLeft);
}
