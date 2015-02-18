/**
 * Created by user on 16.02.2015.
 */
public class TreeWays extends Tree{
    private int count = -1;
    private int maxHeight = 0;
    private int countMaxElement = 0;
    private int maxElemt = 0;
    private void nullData(){
        maxHeight = countMaxElement = 0;
    }
    private void countWays(Node node){
        count++;
        if(node != null){
            if(node.left == null && node.right == null){
                if(count > maxHeight){
                    maxHeight = count;
                    countMaxElement = 1;
                }else if(count == maxHeight){
                    ++countMaxElement;
                }
            }
            countWays(node.left);
            countWays(node.right);
        }
        --count;
    }
    private boolean doCount(Node node){
        countWays(node.left);
        int m = countMaxElement;
        nullData();
        countWays(node.right);
        int n = countMaxElement;
        return (m*n)%2 !=0 ;
    }
    private void checkNechetWays(Node node){
        if(node != null){
            if(doCount(node) && node.getData() > maxElemt){
                maxElemt = node.getData();
            }
            checkNechetWays(node.left);
            checkNechetWays(node.right);
        }
    }
    public void deleteNeccessary(){
        checkNechetWays(root);
        removeElement(maxElemt);
        System.out.println(maxElemt);
    }
}
