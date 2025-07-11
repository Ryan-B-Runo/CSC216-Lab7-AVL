public class Driver {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Inserts four nodes into the tree
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);


//            // Prints out a serialized tree which might look like "4,3,5,nil,nil,nil,6"
//            // The actual output will depend on the pattern you use to serialize the tree
//            System.out.println(tree.serialize());
//
//            // Deletes a node from the tree
//            tree.delete(6);
//
//            // Prints out a serialized tree which might look like "4,3,5"
//            // The actual output will depend on the pattern you use to serialize the tree
//            System.out.println(tree.serialize());
    }
}
