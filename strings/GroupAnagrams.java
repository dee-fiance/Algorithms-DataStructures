import java.util.*;

public class GroupAnagrams {
	private String sortChars(String s) {
	    char[] content = s.toCharArray();
	    Arrays.sort(content);
	    return new String (content);
	} 
	public ArrayList<ArrayList<String>> sort(String[] array) { 
		HashMap<String, ArrayList<String>> mapList = new HashMap<String, ArrayList<String>>();
		for (String s : array) {
		        String key = sortChars(s);
		        ArrayList<String> list;
		        if(mapList.containsKey(key))
		        		list = mapList.get(key);
		        else list = new ArrayList<String>();
		        		
				list.add(s);
		        mapList.put(key, list);
		        System.out.println("Key: "+key + " list-> "+list);
		}
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		result.addAll(mapList.values());
		return result;
	}

    public static void main(String[] args)  {
    		GroupAnagrams h = new GroupAnagrams();
    		String[] input = new String[] {"eat", "tea","tan","ate","nat","bat"};
    		ArrayList<ArrayList<String>> output = h.sort(input);
    		System.out.println(output);
    }
}
