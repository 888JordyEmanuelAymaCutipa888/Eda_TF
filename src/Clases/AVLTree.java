class AVLTree {

  int cantidadDeTextos;
  Node root;
  int maxPalabrasPorOracion;

  void setCantidadDeTextos(int cantidad){
    this.cantidadDeTextos = cantidad;
  }
  int height(Node N) {
    if (N == null) {
      return 0;
    }

    return N.height;
  }

  int max(int a, int b) {
    return (a > b) ? a : b;
  }

  Node rightRotate(Node y) {
    Node x = y.left;
    Node T2 = x.right;

    x.right = y;
    y.left = T2;

    y.height = max(height(y.left), height(y.right)) + 1;
    x.height = max(height(x.left), height(x.right)) + 1;

    return x;
  }

  Node leftRotate(Node x) {
    Node y = x.right;
    Node T2 = y.left;

    y.left = x;
    x.right = T2;

    x.height = max(height(x.left), height(x.right)) + 1;
    y.height = max(height(y.left), height(y.right)) + 1;

    return y;
  }

  int getBalance(Node N) {
    if (N == null) {
      return 0;
    }

    return height(N.left) - height(N.right);
  }

  Node insert(int texto, int indice, int oracion, String valor){
    return insert(this.root, texto, indice, oracion, valor);
  }

  Node insert(Node node,int texto, int indice, int oracion, String valor) {


    if (node == null) {
      return (new Node(texto, indice, oracion, valor));
    } 
    if (valor.compareTo(node.valor) < 0) {
      node.left = insert(node.left, texto, indice, oracion, valor);
    } else if (valor.compareTo(node.valor) > 0) {
      node.right = insert(node.right, texto, indice, oracion, valor);
    } else {
      node.ubicacionesPalabras.add(new UbicacionPalabra(texto, indice, oracion));
      return node;
    }

    node.height = 1 + max(height(node.left), height(node.right));

    int balance = getBalance(node);

    if (balance > 1 && (valor.compareTo(node.valor)) < 0) {
      return rightRotate(node);
    }

    if (balance < -1 && (valor.compareTo(node.valor)) > 0) {
      return leftRotate(node);
    }

    if (balance > 1 && (valor.compareTo(node.valor)) > 0) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }

    if (balance < -1 && (valor.compareTo(node.valor)) < 0) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }

    return node;
  }

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

  void setCantidadDePalabras(int maxCantidadPalabras) {
    this.maxPalabrasPorOracion = maxCantidadPalabras;
  }
}
