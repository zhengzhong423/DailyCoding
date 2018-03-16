package Twitter.NextPermutaion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 21534  -- 21543
// 21453  -- 21534
// 12354  -- 12435
// 54321
public class NextPermutation {
    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        String cur = "123";
        Set<String> set = new HashSet<>();
        while(!set.contains(cur)) {
            set.add(cur);
            System.out.println(cur);
            cur = np.nextPermutation(cur);
        }

    }
    public String nextPermutation(String input) {
        // find last inc start index
        int index = -1;
        char[] chs = input.toCharArray();
        for (int i = input.length() - 1; i >= 1; i--) {
            if (chs[i] > chs[i - 1]) {
                index = i - 1;
                break;
            }
        }
        if (index == -1) {
            return reverse(input);
        }

        // find a minimum digit larger than this num
        int endIndex = index;
        for (int i = input.length() - 1; i > index; i--) {
            if (chs[i] > chs[index]) {
                endIndex = i;
                break;
            }
        }

        swap(chs, index, endIndex);

        Arrays.sort(chs, index + 1, input.length());

        return new String(chs);
    }

    private void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    private String reverse(String input) {
        char[] chs = input.toCharArray();
        int i = 0, j = chs.length - 1;
        while(i < j) {
            swap(chs, i, j);
            i++;
            j--;
        }
        return new String(chs);
    }
}
