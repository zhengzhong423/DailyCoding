package CodeJam.PancakeFlip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Pancake {
	
	final private static String inputFileName = 
			"/Users/zhzheng/Documents/DailyCoding/src/main/java/CodeJam/PancakeFlip/B-large-practice.in.txt";
	final private static String outputFileName = 
			"/Users/zhzheng/Documents/DailyCoding/src/main/java/CodeJam/PancakeFlip/B-large-practice.out.txt";

	public static void main(String[] args) {
		Pancake pancake = new Pancake();
		
		int numTC = 0;
		
		BufferedWriter bw = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFileName));
			numTC = Integer.parseInt(br.readLine());
			bw = new BufferedWriter(new FileWriter(outputFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder sb = new StringBuilder();
		
		
		for(int i = 0; i < numTC; i++) {
			char[] chs = null;
			try {
				chs = br.readLine().toCharArray();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sb.append("Case #" + (i+1) + ": ");
			Boolean[] input = new Boolean[chs.length];
			Arrays.fill(input, false);
			for(int j = 0; j < input.length; j++) {
				if (chs[j] == '+') {
					input[j] = true;
				}
			}
			sb.append(pancake.getFlipNums(input, input.length - 1));
			sb.append("\n");
		}
		
		System.out.println(sb);
		try {
			bw.write(sb.toString());
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public Integer getFlipNums(Boolean[] input, Integer k) {
		for(int i = k; i >= 0; i--) {
			if (!input[i]) {
				Boolean[] newList = flip(input, i);
				return 1 + getFlipNums(newList, i - 1);
			}
		}
		return 0;
	}

	private Boolean[] flip(Boolean[] input, Integer end) {
		Boolean[] arr = new Boolean[input.length];
		for (int i = 0; i <= end; i++) {
			arr[i] = !input[i];
		}
 		return arr;
	}

}
