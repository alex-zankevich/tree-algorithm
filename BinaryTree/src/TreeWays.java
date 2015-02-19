import java.util.ArrayList;

/**
 * Created by user on 16.02.2015.
 */
public class TreeWays extends Tree{
    private ArrayList<Integer> keys;
    private int count = -1;
    private int maxHeight = 0;
    private int countMaxElement = 0;
    public TreeWays(){
        keys = new ArrayList<Integer>();
    }
    private void nullData(){
        maxHeight = countMaxElement = 0;
        count = -1;
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
    public int checkWays(Node node){

        countWays(node.left);
        int maxCountN = countMaxElement;
        int maxHeightN = maxHeight+1;
        nullData();

        countWays(node.right);
        int maxCountM = countMaxElement;
        int maxHeightM = maxHeight+1;
        nullData();

        if(node == root){
            return maxCountM*maxCountN;
        }

        if(maxHeightM == maxHeightN){
            maxCountM += maxCountN;
        }

        if(node.right == null && node.left == null){
            maxCountM = maxCountN = 1;
            maxHeightM = maxHeightN = 0;
        }

        if(maxHeightN > maxHeightM){
            int tmpHeight = maxHeightM;
            int tmpCount = maxCountM;

            maxHeightM = maxHeightN;
            maxCountM = maxCountN;

            maxHeightN = tmpHeight;
            maxCountN = tmpCount;

        }

        if(node == root){
            return maxCountM*maxCountN;
        }

        Node current = node.father;
        boolean isLeft = current.left == node ? true : false;
        int counter = 1;

        int maxCountTmp = 0;
        int maxHeightTmp = 0;

        while(current != null){
            if(isLeft){
                countWays(current.right);
            }else{
                countWays(current.left);
            }
            if(countMaxElement == 0){
                maxCountTmp = 1;
            }else{
                maxCountTmp = countMaxElement;
            }
            maxHeightTmp = maxHeight+1+counter;
            nullData();

            if(maxHeightTmp > maxHeightN){
                maxHeightN = maxHeightTmp;
                maxCountN = maxCountTmp;
            }else if(maxHeightTmp == maxHeightN){
                maxCountN += maxCountTmp;
            }
            if(current != root) {
                isLeft = current.father.left == current ? true : false;
                current = current.father;
            }else{
                break;
            }
            counter++;
        }
        return maxCountM*maxCountN;
    }
    public void traverseAndFind(Node node){
        if(node != null){
            traverseAndFind(node.right);
            if(checkWays(node) % 2 != 0){
                keys.add(node.getData());
            }
            traverseAndFind(node.left);
        }
    }
    public void deleteMaxKey(){
        removeElement(keys.get(0));
    }
}
