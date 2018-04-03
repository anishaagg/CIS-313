//Anisha Aggarwal   CIS 313 Lab2

public class BST<E extends Comparable<E>> {
    private Node<E> root;

    public BST(){
        root = null;
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){

        // Find the right spot in the tree for the new node
        // Make sure to check if anything is in the tree
        // Hint: if a node n is null, calling n.getData() will cause an error
        Node<E> temp = root;
        Node<E> parent_node;
        if (root == null) {
            root = new Node<E>(data);
        } else if (temp.getData() != data) {
            //check if the data is greater than or less than root
            while ((temp.getData().compareTo(data) < 0) || (temp.getData().compareTo(data) > 0)){
                parent_node = temp;
                if (temp.getData().compareTo(data) < 0) {
                    parent_node = temp;
                    temp = temp.getRightChild();
                    if (temp == null) {
                        temp = new Node<E>(data);
                        parent_node.setRightChild(temp);
                        temp.setParent(parent_node);
                        break;
                    }
                } else {
                    temp = temp.getLeftChild();
                    if (temp == null) {
                        temp = new Node<E>(data);
                        parent_node.setLeftChild(temp);
                        temp.setParent(parent_node);
                        break;
                    }
                }

            }
        }

    }

    public Node<E> find(E data){

        // Search the tree for a node whose data field is equal to data
        Node<E> temp = root;
        while ((temp != null) && (temp.getData() != data)) {
            if (temp.getData().compareTo(data) < 0) {
                temp = temp.getRightChild();
            } else {
                temp = temp.getLeftChild();
            }
        }
        //temp will either be null or has found data
        return temp;
    }

    public void delete(E data){
        // Find the right node to be deleted
        Node<E> temp = root;    //temp node to traverse through the array
        Node<E> temp_min;   //temp node to traverse and find a min

        temp = find(data);
        //check if temp was not able to find data and exit (nothing to delete)
        if (temp == null) {
            //System.out.print("temp == null");
            return;
        }

        Node<E> left = temp.getLeftChild();
        Node<E> right = temp.getRightChild();
        // If said node has no children, simply remove it by setting its parent to point to null instead of it.
        if ((left == null) && (right == null)) {
                if (temp.getParent() != null) {
                        if (temp.getParent().getData().compareTo(temp.getData()) < 0) {
                                temp.getParent().setRightChild(null);
                        } else {
                                temp.getParent().setLeftChild(null);
                        }
                }
            //System.out.print("no child");
            return;
        }
        // If said node has one child, delete it by making its parent point to its child.
        else if (((right != null) && (left == null)) || ((left != null) && (right == null))) {
            if (left == null) {
                if (temp.getParent() != null) {
                        if (temp.getParent().getData().compareTo(temp.getData()) < 0) {
                                temp.getParent().setRightChild(temp.getRightChild());
                        } else {
                                temp.getParent().setLeftChild(temp.getRightChild());
                        }
                }
                temp.getRightChild().setParent(temp.getParent());
                //System.out.print("1 child, right");
            } else {
                if (temp.getParent() != null) {
                        if (temp.getParent().getData().compareTo(temp.getData()) < 0) {
                                temp.getParent().setRightChild(temp.getLeftChild());
                        } else {
                                temp.getParent().setLeftChild(temp.getLeftChild());
                        }
                }
                temp.getLeftChild().setParent(temp.getParent());
                //System.out.print("1 child, left");
            }
            return;
        }

        // If said node has two children, then replace it with its successor,
        //          and remove the successor from its previous location in the tree.
        // The successor of a node is the left-most node in the node's right subtree.
        // If the value specified by delete does not exist in the tree, then the structure is left unchanged.
        // Hint: You may want to implement a findMin() method to find the successor when there are two children
        else if ((right != null) && (left != null)) {
            temp_min = temp;    //assign temp_min to temp so that we can find the min in correct subtree
            while (temp_min.getLeftChild() != null) {
                temp_min = temp_min.getLeftChild();
            }
            temp.setData(temp_min.getData());

            //set the Left of parent of min node to null
            temp_min.getParent().setLeftChild(null);
            //System.out.print("2 children");
        }

    }

    public void traverse(String order, Node<E> top) {
        if (top != null){
            switch (order) {
                case "preorder":
                    System.out.print(top.getData());
                    System.out.print(" ");
                    traverse(order, top.getLeftChild());
                    traverse(order, top.getRightChild());
                    break;

                case "inorder":
                    traverse(order, top.getLeftChild());
                    System.out.print(top.getData());
                    System.out.print(" ");
                    traverse(order, top.getRightChild());
                    break;

                case "postorder":
                    traverse(order, top.getLeftChild());
                    traverse(order, top.getRightChild());
                    System.out.print(top.getData());
                    System.out.print(" ");
                    break;
            }
        }
    }

    public boolean isSameShape(Node<E> n1, Node<E> n2) {
        if ((n1 == null) && (n2 == null)) {
            return true;
        } else if (((n1 != null) && (n2 == null)) || ((n2 != null) && (n1 == null))){
            return false;
        } else {
            boolean rightSameShape = isSameShape(n1.getRightChild(), n2.getRightChild());
            boolean leftSameShape = isSameShape(n1.getLeftChild(), n2.getLeftChild());
            return (rightSameShape && leftSameShape);
        }
    }
}
