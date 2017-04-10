package Arrays.NextElement;

import java.util.Arrays;

public class NextElement {

	public static void main(String[] args) {
		NextElement nextElement = new NextElement();
		System.out.println(nextElement.nextGreaterElement(12));
	}
	
	public int nextGreaterElement(int n) {
        char[] chs = String.valueOf(n).toCharArray();
        int index = -1;
        for (int i = chs.length - 1; i >= 1; i--) {
            if (chs[i] > chs[i - 1]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return -1;
        }
        swap(chs, index - 1);
        Arrays.sort(chs, index, chs.length);
        return Integer.parseInt(new String(chs));
    }
    
    private void swap(char[] chs, int i) {
    	int j = i;
        for (int m = chs.length - 1; m > i; m--) {
            if (chs[m] > chs[i]) {
                j = m;
            }
        }
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

}
