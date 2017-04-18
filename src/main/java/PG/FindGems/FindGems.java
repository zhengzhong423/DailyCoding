package PG.FindGems;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * int slotNum: number of slots
 * String[] gems: gems in room
 * List<GemInfo> gemsInfoCollection
 * 
 * GemInfo {
 * 	String name;
 * 	Integer maxNum;
 * 	Integer val;
 * }
 */
public class FindGems {
	
	static class GemInfo {
		String name;
		Integer maxNum;
		Integer val;
		
		public GemInfo(String name, Integer maxNum, Integer val) {
			this.name = name;
			this.maxNum = maxNum;
			this.val = val;
		}
	}
	
	public static void main(String[] args) {
		int slotNum = 2;
		String[] gems = {"g1", "g3", "g1", "g1", "g1", "g1", "g3", "g3"};
		List<GemInfo> gemsInfoCollection = new LinkedList<>();
		gemsInfoCollection.add(new GemInfo("g1", 1, 3));
		gemsInfoCollection.add(new GemInfo("g2", 1, 8));
		gemsInfoCollection.add(new GemInfo("g3", 6, 2));
		
		System.out.println(new FindGems().maxValue(slotNum, gems, gemsInfoCollection));
	}
	
	public int maxValue(int slotNum, String[] gems, List<GemInfo> gemsInfoCollection) {
		Map<String, Integer> gemInRoom = getGemsInRoom(gems);
		Map<String, GemInfo> gemInfo = getGemInfo(gemsInfoCollection);
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (Map.Entry<String, Integer> entry: gemInRoom.entrySet()) {
			int numPerSlot = gemInfo.get(entry.getKey()).maxNum;
			int numInRoom = entry.getValue();
			
			for (int i = 0; i < numInRoom / numPerSlot; i++) {
				pq.add(numPerSlot * gemInfo.get(entry.getKey()).val);
			}
			pq.add((numInRoom % numPerSlot) * (gemInfo.get(entry.getKey()).val));
		}
		
		int num = 0;
		for (int i = 0; i < slotNum; i++) {
			num += pq.poll();
		}
		
		return num;
	}
	
	private Map<String, Integer> getGemsInRoom(String[] gems) {
		Map<String, Integer> retMap = new HashMap<>();
		for (String gem: gems) {
			retMap.put(gem, retMap.containsKey(gem) ? retMap.get(gem) + 1 : 1);
		}
		return retMap;
	}
	
	private Map<String, GemInfo> getGemInfo(List<GemInfo> gemsInfoCollection) {
		Map<String, GemInfo> retMap = new HashMap<>();
		for (GemInfo info: gemsInfoCollection) {
			retMap.put(info.name, info);
		}
		return retMap;
	}

}
