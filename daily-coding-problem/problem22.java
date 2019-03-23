class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0 || wordDict.size()==0)
            return new LinkedList<>();
        
        Map<String, List<String>> map = new HashMap<>();
        List<String> result = dfs(s, wordDict, map);
        return result;
    }
    private List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> map){
        if(map.containsKey(s))
            return map.get(s);
        
        List<String> result = new LinkedList<>();
        for(String word : wordDict) {
            if(s.startsWith(word)) {
                if(s.length() == word.length()) {
                    result.add(word);
                } else {
                    List<String> subList = dfs(s.substring(word.length()), wordDict, map);
                    for(String sub : subList) {
                        String newstr = (sub == "")? "" : " "+sub;
                        result.add(word+newstr);
                    }
                }
            }
        }
        map.put(s, result);
        return map.get(s);
    }
}
