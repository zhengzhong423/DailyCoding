package Amazon.Celebrity;

/**
 * Created by zhonzhen on 7/22/16.
 */
public class Relation {
    int[][] matrix;
    public Relation(){
    }

    public Relation(int[][] matrix){
        this.matrix = matrix;
    }

    boolean knows(int a, int b){
        if(a >= matrix.length || a < 0)
            throw new ArrayIndexOutOfBoundsException(a);
        if(b >= matrix.length || b < 0)
            throw new ArrayIndexOutOfBoundsException(b);
        return matrix[a][b] == 1;
    }
}
