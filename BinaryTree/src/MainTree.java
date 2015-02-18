import java.util.Random;

/**
 * Created by user on 12.02.2015.
 */
public class MainTree {
    public static void main(String[] args) {
        Tree myTree = new Tree();
        myTree.addElement(10);
        myTree.addElement(7);
        myTree.addElement(14);
        myTree.addElement(6);
        myTree.addElement(11);
        myTree.addElement(16);
        myTree.addElement(8);
        myTree.addElement(4);
        myTree.addElement(12);
        myTree.addElement(15);
        myTree.addElement(20);
        myTree.removeElement(10);
        myTree.removeElement(6);
        System.out.println("My tree : \n");
        myTree.showTree();
    }
}
