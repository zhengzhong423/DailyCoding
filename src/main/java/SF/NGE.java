package SF;

import java.util.Stack;

/**
 * Created by zhonzhen on 11/19/16.
 */
public class NGE {

    public static void main(String[] args) {
        NGE nge = new NGE();

        int[] ts1 = {1, 2, 3, 4};
        int[] ts2 = {8, 4, 6, 5, 9, 0};
        int[] ts3 = {4, 3, 2, 1};

        outputList(nge.getNGE(ts1));
        outputList(nge.getNGE(ts2));
        outputList(nge.getNGE(ts3));
    }

    private static void outputList(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " , ");
        }
        System.out.println();
    }

    private int[] getNGE (int[] arr) {
        int[] ret = new int[arr.length];
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                    int index = stack.pop();
                    ret[index] = arr[i];
                }
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            ret[stack.pop()] = -1;
        }

        return ret;
    }


}
