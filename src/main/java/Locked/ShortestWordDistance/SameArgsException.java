package Locked.ShortestWordDistance;

/**
 * Created by zhonzhen on 7/27/16.
 */
public class SameArgsException extends RuntimeException {
    public SameArgsException(){
        super();
    }

    public SameArgsException(String Message){
        System.out.println(Message);
    }

}
