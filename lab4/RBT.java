public class RBT<E extends Comparable<E>> {
    private Node<E> root;
    private WrongTree wt;

    public RBT(){
        root = null;
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
        // Preform a regular insert
        // Check to make sure the tree remains an RBT tree
    	boolean done = false;
        Node<E> temp = root;

        while(!done){
            if (root == null) {
                root = new Node<E>(data);
                temp = root;
                temp.setColor('B');
                done = true;
            } else if (temp.getData().compareTo(data) > 0) {
                if (temp.getLeftChild() == null){
                    temp.setLeftChild(new Node<E>(data));
                    temp.getLeftChild().setParent(temp);
                    done = true;
                }
                temp = temp.getLeftChild();
            } else if (temp.getData().compareTo(data) <= 0) {
                if (temp.getRightChild() == null) {
                    temp.setRightChild(new Node<E>(data));
                    temp.getRightChild().setParent(temp);
                    done = true;
                }
                temp = temp.getRightChild();
            }
        }

        if (temp != null) {
        	temp.setColor('R');
        }

		fixInsert(temp);    	
    }

    public Node<E> search(E data){
        // Return the node that corresponds with the given data
        // Note: No need to worry about duplicate values added to the tree
		boolean done = false;
        Node<E> temp = root;


        while(!done){
            if (temp == null){
                return null;
            }
            if(temp.getData().compareTo(data) == 0){
                done = true;
            } else if (temp.getData().compareTo(data) > 0){
                temp = temp.getLeftChild();
            } else if (temp.getData().compareTo(data) < 0){
                temp = temp.getRightChild();
            }
        }
        return temp;
    }

    public void delete(E data){
    	// Preform a regular delete
    	// Check to make sure the tree remains an RBT tree
    	Node<E> temp = search(data);
        if (temp == null) {
            return;
        }
        
        Node<E> replacement = new Node(null);

        boolean isRight;
        boolean isLeft;
        boolean isRoot;

        boolean hasChildren;
        boolean hasLeft;
        boolean hasRight;
        boolean hasBoth;

        // Find out if the node to be deleted is the root or if it is a left/right child
        if (temp == root) {
            isRoot = true;
            isLeft = false;
            isRight = false;
        } else if (temp == temp.getParent().getLeftChild()) {
            isRoot = false;
            isLeft = true;
            isRight = false;
        } else {
            isRoot = false;
            isLeft = false;
            isRight = true;
        }

        // Find out if the node to be deleted has children
        // If it does, find out if it has a Right/Left Child or both
        if (temp.getLeftChild() == null && temp.getRightChild() == null) {
            hasChildren = false;
            hasBoth = false;
            hasLeft = false;
            hasRight = false;
        } else if (temp.getLeftChild() != null && temp.getRightChild() != null){
            hasChildren = true;
            hasBoth = true;
            hasLeft = true;
            hasRight = true;
        } else if (temp.getLeftChild() == null){
            hasChildren = true;
            hasBoth = false;
            hasRight = true;
            hasLeft = false;
        } else {
            hasChildren = true;
            hasBoth = false;
            hasRight = false;
            hasLeft = true;
        }

        // Seperate cases if the node to be deleted is the root of the tree
        if (isRoot){
            // Seperate cases depending on the number of children the node to be deleted has
            if (!hasChildren){
                root = replacement;
            } else if (hasBoth){
                replacement = getMin(temp.getRightChild());
                if (replacement == temp.getRightChild()){
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setParent(temp.getParent());
                    replacement.getLeftChild().setParent(replacement);
                    root = replacement;
                } else {
                    if (replacement.getParent().getLeftChild() == replacement){
                        replacement.getParent().setLeftChild(null);
                    } else if (replacement.getParent().getRightChild() == replacement){
                        replacement.getParent().setRightChild(null);
                    }
                    replacement.setParent(temp.getParent());
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setRightChild(temp.getRightChild());
                    temp.getLeftChild().setParent(replacement);
                    temp.getRightChild().setParent(replacement);
                    root = replacement;
                }

            } else if (hasLeft && !hasRight){
                temp.getLeftChild().setParent(null);
                root = temp.getLeftChild();
            } else if (hasRight && !hasLeft) {
                temp.getRightChild().setParent(null);
                root = temp.getRightChild();
            }
        } else {
            // This is the case where it isn't the root node
            if (!hasChildren){
                if (isLeft){
                    temp.getParent().setLeftChild(null);
                } else if (isRight){
                    temp.getParent().setRightChild(null);
                }
            } else if (hasBoth){
                replacement = getMin(temp.getRightChild());
                if (replacement == temp.getRightChild()){
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setParent(temp.getParent());
                    replacement.getLeftChild().setParent(replacement);
                } else {
                    if (replacement.getParent().getLeftChild() == replacement){
                        replacement.getParent().setLeftChild(null);
                    } else if (replacement.getParent().getRightChild() == replacement){
                        replacement.getParent().setRightChild(null);
                    }
                    replacement.setParent(temp.getParent());
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setRightChild(temp.getRightChild());
                    temp.getLeftChild().setParent(replacement);
                    temp.getRightChild().setParent(replacement);
                }
                if(isLeft){
                    temp.getParent().setLeftChild(replacement);
                } else if (isRight){
                    temp.getParent().setRightChild(replacement);
                }

            } else if (hasLeft && !hasRight){
                temp.getLeftChild().setParent(temp.getParent());
                temp.getParent().setLeftChild(temp.getLeftChild());
            } else if (hasRight && !hasLeft) {
                temp.getRightChild().setParent(temp.getParent());
                temp.getParent().setRightChild(temp.getRightChild());
            }
        }

        if (temp.getColor() == 'B') {
        	fixDelete(replacement);
        }
    	

    }

    public void traverse(String order, Node<E> top) {
        // Preform a preorder traversal of the tree
    	if (order == "preorder") {
	    	if (top != null) {
	    		System.out.print(top.getData().toString() + " ");
	    		traverse("preorder", top.getLeftChild());
	    		traverse("preorder", top.getRightChild());
	    	}
    	} else {
    		System.out.println("Invalid order choice.");
    	}
    }

	public Node<E> getMin(Node<E> top){
        boolean done = false;
        Node<E> temp = top;
        while(!done) {
            if (temp.getLeftChild() == null) {
                done = true;
            } else {
                temp = temp.getLeftChild();
            }
        }
        return temp;
    }

    public void rightRotate(Node<E> y){
    	
    	/*
    	If x is the root of the tree to rotate with left child subtree T1 and right child y, 
    	where T2 and T3 are the left and right children of y:
			x becomes left child of y and T3 as its right child of y
			T1 becomes left child of x and T2 becomes right child of x
		*/
		Node<E> x = y.getLeftChild();
    	y.setLeftChild(x.getRightChild());
    	if(x.getRightChild() != null){
    		x.getRightChild().setParent(y);
    	}

    	if(x != null) {
	    	x.setParent(y.getParent());
    	}

    	if(y.getParent() == null){
    		root = x;
    	}else if(y == y.getParent().getRightChild()){
    		y.getParent().setRightChild(x);
    	}else{
    		y.getParent().setLeftChild(x);
    	}
    	x.setRightChild(y);
    	if (y != null) {
    		y.setParent(x);
    	}
    }

    public void leftRotate(Node<E> x){
    
    	/*
    	If y is the root of the tree to rotate with right child subtree T3 and left child x, 
    	where T1 and T2 are the left and right children of x:
			y becomes right child of x and T1 as its left child of x
			T2 becomes left child of y and T3 becomes right child of y
		*/
		Node<E> y = x.getRightChild();
    	x.setRightChild(y.getLeftChild());
    	if(y.getLeftChild() != null){
    		y.getLeftChild().setParent(x);
    	}
    	y.setParent(x.getParent());
    	if(x.getParent() == null){
    		root = y;
    	}else if(x == x.getParent().getLeftChild()){
    		x.getParent().setLeftChild(y);
    	}else{
    		x.getParent().setRightChild(y);
    	}
    	y.setLeftChild(x);
    	if (x != null) {
    		x.setParent(y);
    	}
    	
    }
    
    // HINT: You may want to create extra methods such as fixDelete or fixInsert
    public void fixInsert(Node<E> x) {

    	while ((x != root) && (x.getParent().getColor() == 'R')) {
    		Node<E> uncle = null;
    		if (x.getParent() == x.getParent().getParent().getLeftChild()) {
    			uncle = x.getParent().getParent().getRightChild();
    			if ((uncle != null) && (uncle.getColor() == 'R')) {
    				x.getParent().setColor('B');
    				uncle.setColor('B');
    				x.getParent().getParent().setColor('R');
    				x = x.getParent().getParent();
    				continue;
    			}
    			if (x == x.getParent().getRightChild()) {
    				x = x.getParent();
    				leftRotate(x);
    			}
    			x.getParent().setColor('B');
    			x.getParent().getParent().setColor('R');
    			rightRotate(x.getParent().getParent());
    		} else {
    			uncle = x.getParent().getParent().getLeftChild();
    			if ((uncle != null) && (uncle.getColor() == 'R')){
    				x.getParent().setColor('B');
    				uncle.setColor('B');
    				x.getParent().getParent().setColor('R');
    				x = x.getParent().getParent();
    				continue;
    			}

    			if (x == x.getParent().getLeftChild()) {
    				x = x.getParent();
    				rightRotate(x);
    			}
    			x.getParent().setColor('B');
    			x.getParent().getParent().setColor('R');
    			leftRotate(x.getParent().getParent());
    		}
    	}
    	root.setColor('B');
    }

    public void fixDelete(Node<E> x) {

    	while (x != root && x.getColor() == 'B') {
    		if (x == x.getParent().getLeftChild()) {
    			Node<E> temp = x.getParent().getRightChild();
    			if(temp.getColor() == 'R'){
    				temp.setColor('B');
    				x.getParent().setColor('R');
    				leftRotate(x.getParent());
    				temp = x.getParent().getRightChild();
    			}
			
				if ((temp.getRightChild() == null) || (temp.getLeftChild() == null)) {
    				System.out.println("children are null");
    				break;
    			} 


    			if ((temp.getLeftChild().getColor() == 'B') && (temp.getRightChild().getColor() == 'B')){
    				temp.setColor('R');
    				x = x.getParent();
    				//continue;
    			} else {
    				if (temp.getRightChild().getColor() == 'B') {
    					temp.getLeftChild().setColor('B');
    					temp.setColor('R');
    					rightRotate(temp);
    					temp = x.getParent().getRightChild();
    				}
    				temp.setColor(x.getParent().getColor());
    				x.getParent().setColor('B');
    				temp.getRightChild().setColor('B');
    				leftRotate(x.getParent());
    				x = root;
    			}

    		} else {
    			Node<E> temp = x.getParent().getLeftChild();
    			if (temp.getColor() == 'R') {
    				temp.setColor('B');
    				x.getParent().setColor('R');
    				rightRotate(x.getParent());
    				temp = x.getParent().getLeftChild();
    			}

    			if ((temp.getRightChild() == null) || (temp.getLeftChild() == null)) {
    				System.out.println("children are null");
    				break;
    			} 

    			if ((temp.getRightChild().getColor() == 'B') && (temp.getLeftChild().getColor() == 'B')) {
    				temp.setColor('R');
    				x = x.getParent();
    				//continue;
    			} else {
    				if (temp.getLeftChild().getColor() == 'B') {
    					temp.getRightChild().setColor('B');
    					temp.setColor('R');
    					leftRotate(temp);
    					temp = x.getParent().getLeftChild();
    				}
    				temp.setColor(x.getParent().getColor());
    				x.getParent().setColor('B');
    				temp.getLeftChild().setColor('B');
    				rightRotate(x.getParent());
    				x = root;
    			}
    		}
    	}
    	x.setColor('B');
    }

    public boolean isRBT(Node<E> n){

    	//check if either is red or black
    	if (isRBT_traverse(n) == false) {
    		return false;
    	}

    	// check if root is black
    	if (n.getColor() != 'b') {
    		return false;
    	}

    	//check if a node is red, then both children are black
    	if (isRBT_traverse_check3(n) == false) {
    		return false;
    	}
    	
    	//check every path from given node has same black depth
    	if (!isRBT_balanced(n)) {
    		return false;
    	}

    	return true;
    }

    private boolean isRBT_traverse(Node<E> top) {
	    if (top != null) {
	    	if ((top.getColor() != 'r') || (top.getColor() != 'b')) {
	    		return false;
	    	}
	    	return (isRBT_traverse(top.getLeftChild()) && isRBT_traverse(top.getRightChild()));
	    }
	    return true;
    }

    private boolean isRBT_traverse_check3(Node<E> top) {
	    if (top != null) {
	    	if (top.getColor() == 'r') {
	    		if (((top.getLeftChild() != null) && (top.getLeftChild().getColor() != 'b')) ||
	    			((top.getRightChild() != null) && (top.getRightChild().getColor() != 'b'))) {
	    				return false;
	    		}
	    		return true;
	    	}
	    	return (isRBT_traverse_check3(top.getLeftChild()) && isRBT_traverse_check3(top.getRightChild()));
	    }
	    return true;
    }

    private boolean rbt_depth(Node<E> root, int max_d, int min_d) {

    	if (root == null) {
        	max_d = 0;
        	min_d = 0;
        	return true;
    	}
 
 		// max and min heights of left subtree
	    int lmax = 0;
	    int lmin = 0;
	    // max and min heights of right subtree
	    int rmax = 0;
	    int rmin = 0;
	 
	    // check if left subtree is balanced
	    if (rbt_depth(root.getLeftChild(), lmax, lmin) == false)
	        return false;
	 
	    // check if right subtree is balanced
	    if (rbt_depth(root.getRightChild(), rmax, rmin) == false)
	        return false;
	 
	    // set the max and min heights of this node
	    max_d = (lmax > rmax ? lmax:rmax) + 1;
	    min_d = (lmin > rmin ? rmin:lmin) + 1;
	 
	    // check if this node is balanced
	    if (max_d <= 2 * min_d)
	        return true;
	 
	    return false;
	}

	private boolean isRBT_balanced(Node<E> root) {
		int max_d = 0;
		int min_d = 0;

		return rbt_depth(root, max_d, min_d);	 
	}

}




