import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by user on 12.02.2015.
 */
public class Tree {
    protected Node root;
    public void initTreeFromFile(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream(path));
        while(sc.hasNext()){
            this.addElement(sc.nextInt());
        }
    }
    public void initRandom(int n,int r){
        Random rand = new Random();
        for(int i = 0; i < n; i++){
            this.addElement(rand.nextInt(r));
        }
    }
    public void showTree(Node node){
        if(node != null){
            System.out.println(node.getData());
            showTree(node.left);
            showTree(node.right);
        }
    }
    public void writeTreeToFile(String path) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream(path));
        traverseAndWrite(root,ps);
    }
    public Node getRoot() {
        return root;
    }
    public  void addElement(int n){
        if(root == null){
            root = new Node(n);
        }else {
            addElement(n, root);
        }
    }
    protected void addElement(int n, Node node){
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
    protected void traverseAndWrite(Node node,PrintStream ps){
        if(node != null){
            ps.println(node.getData());
            traverseAndWrite(node.left, ps);
            traverseAndWrite(node.right, ps);
        }
    }
    public boolean removeElement(int n){
        Node current = root;
        boolean isLeftSon = true;
        while(current.getData() != n){
            if(current.getData() > n){
                isLeftSon = true;
                current = current.left;
            }else if(current.getData() < n){
                isLeftSon = false;
                current = current.right;
            }
            if(current == null){
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
            int tempData = tmp.getData();
            removeElement(tmp.getData());
            current.setData(tempData);
        }else if(current.left == null){//only right
            if(current == root){
                root = current.right;
                root.father = null;
            }else if (isLeftSon){
                current.father.left = current.right;
                current.right.father = current.father;
            }else{
                current.father.right = current.right;
                current.right.father = current.father;
            }
            current = null;
        }else if (current.right == null){//only left
            if(current == root){
                root = current.left;
                root.father = null;
            }else if(isLeftSon){
                current.father.left = current.left;
                current.left.father = current.father;
            }else {
                current.father.right = current.left;
                current.left.father = current.father;
            }
            current = null;
        }
        return true;
    }
    public  int heightOfTree(){
        return heightOfTree(root);
    }
    protected int heightOfTree(Node node){
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
