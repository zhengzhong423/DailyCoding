package FB;

import java.util.HashMap;
import java.util.Map;

public class PatternMatch {

	public static void main(String[] args) {
		Map<Character, String> map = new HashMap<Character, String>();
		Map<String, Character> invertedMap = new HashMap<String, Character>();
		PatternMatch pm = new PatternMatch();
		System.out.println(pm.patternMatch("AAAAB", "bababababbbbb", map, invertedMap));
	}
	
	public boolean patternMatch(String pattern, String str, Map<Character, String> map,
			Map<String, Character> invertedMap) {
		boolean ret = false;
		
		if (pattern.length() == 0 && str.length() == 0) {
			System.out.println(map);
			return true;
		}
		
		if (pattern.length() == 0 || str.length() == 0) {
			return false;
		}
		
		char ch = pattern.charAt(0);
		if (map.containsKey(ch)) {
			if (str.length() < map.get(ch).length()) {
				return false;
			}
			if (map.get(ch).equals(str.substring(0, map.get(ch).length()))) {
				return patternMatch(pattern.substring(1), 
						str.substring(map.get(ch).length()), 
						map, invertedMap);
			} else {
				return false;
			}
		}
		
		for (int i = 1; i <= str.length(); i++) {
			if (invertedMap.containsKey(str.substring(0, i))) {
				continue;
			}
			Map<Character, String> mapClone = new HashMap<Character, String>(map);
			Map<String, Character> invertedMapClone = new HashMap<String, Character>(invertedMap);
			mapClone.put(ch, str.substring(0, i));
			invertedMapClone.put(str.substring(0, i), ch);
			ret = ret | patternMatch(pattern.substring(1), 
					str.substring(mapClone.get(ch).length()), 
					mapClone, invertedMapClone);
		}
		return ret;
	}

}
