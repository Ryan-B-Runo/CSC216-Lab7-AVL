
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

        if(value < n.getValue()){
            n.setLeft(insert(n.getLeft(), value));
        }else{
            n.setRight(insert(n.getRight(), value));
        }

        update(n);
        return balance(n);
    }

    public boolean insert(int value){
        if(!contains(root, value)){
            root = insert(root, value);
            nodeCount++;
            return true;
        }
        return false;
    }

    private void update(Node<Integer> n){
        int leftNodeHeight = (n.getLeft() == null) ? -1 : n.getLeft().getHeight();
        int rightNodeHeight = (n.getRight() == null) ? -1 : n.getRight().getHeight();

        n.setHeight(1 + Math.max(leftNodeHeight, rightNodeHeight));
        n.setBalanceFactor(rightNodeHeight - leftNodeHeight);
    }

    private Node<Integer> leftRotate(Node<Integer> n){
        Node<Integer> newParent = n.getRight();
        n.setRight(newParent.getLeft());
        newParent.setLeft(n);
        update(n);
        update(newParent);
        return newParent;
    }

    private Node<Integer> rightRotate(Node<Integer> n){
        Node<Integer> newParent = n.getLeft();
        n.setLeft(newParent.getRight());
        newParent.setRight(n);
        update(n);
        update(newParent);
        return newParent;
    }

    private Node<Integer> leftLeft(Node<Integer> n){
        return rightRotate(n);
    }
    private Node<Integer> rightRight(Node<Integer> n){
        return leftRotate(n);
    }
    private Node<Integer> rightLeft(Node<Integer> n){
        n.setRight(rightRotate(n.getRight()));
        return rightRight(n);
    }
    private Node<Integer> leftRight(Node<Integer> n){
        n.setLeft(leftRotate(n.getLeft()));
        return leftLeft(n);
    }

    private int findMax(Node<Integer> n){
        while(n.getRight() != null){
            n = n.getRight();
        }
        return n.getValue();
    }

    private int findMin(Node<Integer> n){
        while(n.getLeft() != null){
            n = n.getLeft();
        }
        return n.getValue();
    }

    private Node<Integer> balance(Node<Integer> n){
        if(n.getBalanceFactor() == -2){

            if(n.getLeft().getBalanceFactor() <= 0){    //left-left case
                return leftLeft(n);
            }else{                                      //left-right case
                return leftRight(n);
            }
        }else if(n.getBalanceFactor() == 2){
            if(n.getRight().getBalanceFactor() >= 0){   //right-right case
                return rightRight(n);
            }else{                                      //left-right case
                return rightLeft(n);
            }
        }
        return n;
    }

    private Node<Integer> delete(Node<Integer> n, int value){//not working
        if(n == null) return null;
        if(value < root.getValue()){
            n.setLeft(delete(n.getLeft(), value));
        }else if (value > root.getValue()){
            n.setRight(delete(n.getRight(), value));
        }else{
            if(n.getLeft() == null){
                return n.getRight();
            }else if(n.getRight() == null){
                return n.getLeft();
            }else{
                if(n.getLeft().getHeight() > n.getRight().getHeight()){
                    int successor = findMax(n.getLeft());
                    n.setValue(successor);
                    n.setLeft(delete(n.getLeft(), successor));
                }else{
                    int successor = findMin(n.getRight());
                    n.setValue(successor);
                    n.setRight(delete(n.getRight(), successor));
                }
            }
        }
        update(n);
        return balance(n);
    }

    public boolean delete(int value){
        if(contains(root, value)){
            root = delete(root, value);
            nodeCount--;
            return true;
        }
        return false;
    }

    private String serialize(Node<Integer> n){
        if(n == null){
            return "-,";
        }
        String left = serialize(n.getLeft());
        String right = serialize(n.getRight());
        return n.getValue() + "," + left + right;
    }

    public String serialize(){
        return serialize(root);
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

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }
}