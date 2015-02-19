import java.io.FileNotFoundException;

/**
 * Created by user on 12.02.2015.
 */
public class MainTree {
    public static void main(String[] args) throws FileNotFoundException {
        TreeWays myTree = new TreeWays();
        myTree.initTreeFromFile("C:\\Users\\user\\IdeaProjects\\BinaryTree\\src\\input.txt");
        myTree.traverseAndFind(myTree.getRoot());
        myTree.deleteMaxKey();
        myTree.writeTreeToFile("C:\\Users\\user\\IdeaProjects\\BinaryTree\\src\\output.txt");
    }
}
