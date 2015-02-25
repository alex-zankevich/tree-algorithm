/**
 * Created by user on 12.02.2015.
 */
public class Node {
    private int data;
    public Node right;
    public Node left;
    public Node father;
    public Node(int data){
        this.data = data;
        right = left = father = null;
    }
    public int getData(){
        return data;
    }
    public void setData(int data){
        this.data = data;
    }
}
