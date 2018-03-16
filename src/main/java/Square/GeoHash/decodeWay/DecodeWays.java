package Square.GeoHash.decodeWay;


import java.util.ArrayList;
import java.util.List;

public class DecodeWays {
    public static void main (String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        System.out.println(decodeWays.getDecodeStr(new int[] {0,2,3,2,0,2}));
    }
    public List<String> getDecodeStr(int[] nums) {
        List<List<String>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.get(0).add("");
        for (int i = 0; i < nums.length; i++) {
            result.add(new ArrayList<>());
            if (nums[i] != 0) {
                result.get(i + 1).addAll(addOneChar(result.get(i), (char) (nums[i] + 'a')));
                if (i > 0 && (nums[i - 1] == 1 || (nums[i - 1] == 2 && nums[i] <= 6))) {
                    result.get(i + 1).addAll(addOneChar(result.get(i - 1),
                            (char) ('a' + (nums[i - 1] * 10 + nums[i]))));
                }
            } else if (nums[i] == 0) {
                if (i > 0 && (nums[i - 1] == 1 || nums[i - 1] == 2)) {
                    result.get(i + 1).addAll(addOneChar(result.get(i - 1),
                            (char) ('a' + (nums[i - 1] * 10 + nums[i]))));
                } else {
                    return new ArrayList<>();
                }
            }
        }

        return result.get(nums.length);
    }

    private List<String> addOneChar(List<String> list, char ch) {
        List<String> result = new ArrayList<>();
        for (String str : list) {
            result.add(str + ch);
        }
        return result;
    }
}
