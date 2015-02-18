/**
 * Created by user on 12.02.2015.
 */
public class Tree {
    protected Node root;
    protected int size;
    public Tree(){
        size = 0;
    }
    public void addElement(int n){
        if(root == null){
            root = new Node(n);
        }else {
            addElement(n, root);
        }
        size++;
    }
    public void addElement(int n, Node node){
        if(n > node.getData()){
            if(node.right == null){
                node.right = new Node(n);
                node.right.father = node;
            }else{
                addElement(n,node.right);
            }
        }else if(n < node.getData()){
            if(node.left == null){
                node.left = new Node(n);
                node.left.father = node;
            }else{
                addElement(n,node.left);
            }
        }
    }
    public void showTree(){
        showTree(root);
    }
    public void showTree(Node node){
        if(node != null){
            showTree(node.left);
            showTree(node.right);
            System.out.println(node.getData());
        }
    }
    public boolean removeElement(int n){
        Node current = root;
        //Node father = root;
        boolean isLeftSon = true;
        while(current.getData() != n){
            //father = current;
            if(current.getData() > n){
                isLeftSon = true;
                current = current.left;
            }else if(current.getData() < n){
                isLeftSon = false;
                current = current.right;
            }else if(current == null){
                return false;
            }
        }
        if(current.left == null && current.right == null){
            if(current == root){
                root = null;
            }else if(isLeftSon){
                current.father.left = null;
            }else{
                current.father.right = null;
            }
        }else if (current.left != null && current.right != null){//two nodes
            Node tmp = current.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            if(tmp.right != null){
                Node successor = tmp;
                removeElement(tmp.getData());
                successor.right = successor.left = null;
                if(current == root){
                    root = successor;
                }else if(isLeftSon){
                    current.father.left = successor;
                }else{
                    current.father.right = successor;
                }
                successor.left = current.left;
                successor.right = current.right;
            }else{
                if(current == root){
                    root = tmp;
                }else if(isLeftSon){
                    current.father.left = tmp;
                }else{
                    current.father.right = tmp;
                }
                tmp.left = current.left;
                tmp.right = current.right;
            }
        }else if(current.left == null){//only right
            if(current == root){
                root = current.left;
            }else if (isLeftSon){
                current.father.left = current.right;
            }else{
                current.father.right = current.right;
            }
        }else if (current.right == null){//only left
            if(current == root){
                root = current.right;
            }else if(isLeftSon){
                current.father.left = current.left;
            }else {
                current.father.right = current.left;
            }
        }
        return true;
    }
    public  int heightOfTree(){
        return heightOfTree(root);
    }
    private int heightOfTree(Node node){
        if(node == null)
            return 0;
        int left, right;
        if (node.left != null) {
            left = heightOfTree(node.left);
        }else {
            left = -1;
        }
        if (node.right != null) {
            right = heightOfTree(node.right);
        }else {
            right = -1;
        }
        return (left > right ? left : right) + 1;
    }
}
