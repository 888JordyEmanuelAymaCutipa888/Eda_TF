class AVLTree {

    Node root;

    // A utility function to get the height of the tree
    int height(Node N) {
        if (N == null) {
            return 0;
        }

        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null) {
            return 0;
        }

        return height(N.left) - height(N.right);
    }
    
    Node insert(int indice, int oracion, String valor){
        return insert(this.root, indice, oracion, valor);
    }

    Node insert(Node node, int indice, int oracion, String valor) {

        /* 1. Perform the normal BST insertion */
        if (node == null) {
            return (new Node(indice, oracion, valor));
        } 
        if (valor.compareTo(node.valor) < 0) {
            node.left = insert(node.left, indice, oracion, valor);
        } else if (valor.compareTo(node.valor) > 0) {
            node.right = insert(node.right, indice, oracion, valor);
        } else {
            node.ubicacionesPalabras.add(new UbicacionPalabra(indice, oracion));
            return node;
        }
        // Duplicate keys not allowed

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left), height(node.right));

        /* 3. Get the balance factor of this ancestor
			node to check whether this node became
			unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && (valor.compareTo(node.valor)) < 0) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && (valor.compareTo(node.valor)) > 0) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && (valor.compareTo(node.valor)) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && (valor.compareTo(node.valor)) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.valor + " " + node.ubicacionesPalabras.toString());
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    
    String inOrden(){
        if (this.root != null) {
           return inOrden(this.root);
        }
        return "*";
    }
    String inOrden(Node node){
        if(node.left == null && node.right == null){
            return "("+node.valor+": "+node.ubicacionesPalabras.toString()+")";
        }else if(node.left != null && node.right == null){
            return inOrden(node.left) + "("+node.valor+": "+node.ubicacionesPalabras.toString()+")";
        }else if(node.left == null && node.right != null){
            return "("+node.valor+ " "+node.ubicacionesPalabras.toString()+")" + inOrden(node.right);
        }else{
            return inOrden(node.left) + "("+node.valor+": "+node.ubicacionesPalabras.toString()+")" + inOrden(node.right);
        }
    }
    Node getNode(String valor){
      return getNode(this.root, valor);
    }

    Node getNode(Node node, String valor){
        /* 1. Perform the normal BST insertion */
        if (node == null) {
            return null;
        } else if (valor.compareTo(node.valor) < 0) {
            return getNode(node.left, valor);
        } else if (valor.compareTo(node.valor) > 0) {
            return getNode(node.right, valor);
        } else {
            return node;
        }
    }
}
