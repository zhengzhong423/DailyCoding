package CodeJam.Insomnia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Insomnia {
	final private static String inputFileName = 
			"/Users/zhzheng/Documents/DailyCoding/src/main/java/CodeJam/Insomnia/A-large-practice.in.txt";
	final private static String outputFileName = 
			"/Users/zhzheng/Documents/DailyCoding/src/main/java/CodeJam/Insomnia/A-large-practice.out.txt";

	public static void main(String[] args) {
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
			int num = 0;
			try {
				num = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sb.append("Case #" + (i+1) + ": ");
			if (num == 0)
				sb.append("INSOMNIA");
			else
				sb.append(String.valueOf(getNumber(num)));
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
	
	public static Integer getNumber(Integer num) {
		Set<Integer> set = new HashSet<Integer>();
		int i = 1;
		while(num * i < Integer.MAX_VALUE - num) {
			int newNum = i * num;
			while(newNum != 0) {
				set.add(newNum % 10);
				newNum = newNum / 10;
			}
			if (set.size() == 10) {
				return num * i;
			}
			i++;
		}
		return -1;
	}

}
