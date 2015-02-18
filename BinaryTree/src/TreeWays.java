/**
 * Created by user on 16.02.2015.
 */
public class TreeWays extends Tree{
    private int count = -1;
    private int maxHeight = 0;
    private int countMaxElement = 0;
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
        int maxHeightN = maxHeight;
        nullData();

        countWays(node.right);
        int maxCountM = countMaxElement;
        int maxHeightM = maxHeight;
        nullData();

        if(maxHeightN > maxHeightM){
            int tmpHeight = maxHeightM;
            int tmpCount = maxCountM;

            maxHeightM = maxHeightN;
            maxCountM = maxCountN;

            maxHeightN = tmpHeight;
            maxCountN = tmpCount;

        }


        Node current = node.father;
        boolean isLeft = current.left == node ? true : false;
        int counter = 1;
        int maxCurrent = 0;

        int maxCountTmp = 0;
        int maxHeightTmp = 0;

        while(current != root){
            if(isLeft){
                countWays(current.right);
            }else{
                countWays(current.left);
            }

            maxCountTmp = countMaxElement;
            maxHeightTmp = maxHeight+1+counter;
            nullData();

            if(maxHeightTmp > maxHeightN){
                maxHeightN = maxHeightTmp+counter+1;
                maxCountN = maxCountTmp;
            }else if(maxHeightTmp == maxHeightN){
                maxCountN += maxCountTmp;
            }

            if(current.father != root){
                isLeft = current.father.left == current ? true : false;
                current = current.father;
            }else {
                isLeft = current.father.left == current ? true : false;
                current = current.father;
                if(isLeft){
                    countWays(current.right);
                }else{
                    countWays(current.left);
                }

                maxCountTmp = countMaxElement;
                maxHeightTmp = maxHeight+1+counter;
                nullData();

                if(maxHeightTmp > maxHeightN){
                    maxHeightN = maxHeightTmp+counter+1;
                    maxCountN = maxCountTmp;
                }else if(maxHeightTmp > maxHeightN){
                    maxCountN += maxCountTmp;
                }
            }
            counter++;
        }
        return maxCountM*maxCountN;
    }
    public void traverseAndFind(Node node){
        if(node != null){
            traverseAndFind(node.right);
            traverseAndFind(node.left);
            //NeccessaryFunction
        }
    }
}
