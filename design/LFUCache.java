/*
Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: 
get and put.
get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, 
it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, 
when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
Follow up:
Could you do both operations in O(1) time complexity?
*/

import java.util.*;

public class LFUCache {
	Map<Integer, Integer> keyValue; // stores key-value pairs
	Map<Integer, Integer> keyFreq; // stores key-frequency pairs
	Map<Integer, LinkedHashSet<Integer>> freqKeys; // stores keys against frequency to deal with keys with same frequency and min frequency
	int capacity;
	int size;
	int minFreq; // min frequency in the system
	LFUCache(int c) {
		capacity=c;
		size=0;
		minFreq=0;
		keyValue = new HashMap<Integer, Integer>();
		keyFreq = new HashMap<Integer, Integer>();
		freqKeys = new HashMap<Integer, LinkedHashSet<Integer>>();
	}
	
	public int get(int key) {
		if(keyValue.containsKey(key)) {
			int r = keyValue.get(key);
			// update freq data structures
			updateFrequency(key);
			return r;
		} else return -1;
	}

	private void updateFrequency(int key) {
		if(keyFreq.containsKey(key)) {
			int f = keyFreq.get(key);
			keyFreq.put(key, f+1);
			//remove key from f freq
			LinkedHashSet<Integer> l1 = freqKeys.get(f);
			l1.remove(key);
			if(l1.isEmpty() && minFreq == f) 
				minFreq = f+1;
			freqKeys.put(f, l1);
			//add key to f+1 freq
			LinkedHashSet<Integer> l2 = freqKeys.get(f);
			l2.add(key);
			freqKeys.put(f+1, l2);
		} else {
			keyFreq.put(key, 1);
			if(freqKeys.containsKey(1)) {
				freqKeys.get(1).add(key);
			} else {
				LinkedHashSet<Integer> l1 = new LinkedHashSet<Integer>();
				l1.add(key);
				freqKeys.put(1, l1);
			}
		}
	}
	
	public void put(int key, int value) {
		if(keyValue.containsKey(key)) {
			keyValue.put(key, value);
			updateFrequency(key);
		} else {
			if(size >= capacity) {
				LinkedHashSet<Integer> l1 = freqKeys.get(minFreq);
				int e = l1.iterator().next();
				l1.remove(e);
				keyValue.remove(e);
				keyFreq.remove(e);
			} else {
				size++; 
			}
			keyValue.put(key, value);
			minFreq=1;
			updateFrequency(key);
		}
	}

	public static void main(String[] args) {
	  LFUCache l = h.new LFUCache(5);
	  l.put(1, 10); l.put(2, 20); l.put(3, 30);
	  l.put(4, 40);l.put(5, 50);
	  l.put(6, 60);
	  System.out.println(l.get(1)); //-1
	  l.get(4);l.get(4);l.get(4);
	  l.get(3);l.get(3);
	  l.get(2);
	  System.out.println(l.get(5)); //50
	  l.put(7, 70);
	  System.out.println(l.get(6)); //-1
	}
} 
