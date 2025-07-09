import java.util.Iterator;

public class AVLTree{

    private Node<Integer> root;
    private int nodeCount;

    public int size(){
        return nodeCount;
    }
    public boolean isEmpty(){
        return nodeCount == 0;
    }

    private boolean contains(Node<Integer> n, int value){
        if(n == null) return false;
        //may have to change the signs#######################################################
        if(value < n.getValue()){
            return contains(n.getLeft(), value);
        }
        if(value > n.getValue()){
            return contains(n.getRight(), value);
        }
        return true;
    }

    public boolean contains(int value){
        return contains(root, value);
    }

    private Node<Integer> insert(Node<Integer> n, int value){
        if (n == null) return new Node(value);
        //may have to change the signs#######################################################
        if(value < n.getValue()){
            n.setLeft(insert(n.getLeft(), value));
        }else{
            n.setRight(insert(n.getRight(), value));
        }
        //balance here#######################################################################
        return n;
    }

    public boolean insert(int value){
        if(!contains(root, value)){
            root = insert(root, value);
            nodeCount++;
            return true;
        }
        return false;
    }

}

class Node<T>{
    private int balanceFactor, height;
    private T value;
    private Node<T> left, right;

    public Node(T value) {
        this.value = value;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public int getHeight() {
        return height;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

}