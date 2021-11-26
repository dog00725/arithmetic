package slidingwindow;

import java.util.*;

/**
 * @Author: huqs
 * @Date: 2021/10/4 10:38
 */
public class Solution {
    /**
     * 最小覆盖字串
     * 窗口区间左闭右开[left,right)
     */
    public String minWindow(String s, String t) {
        Map<Character,Integer> needs = new HashMap<>();
        Map<Character,Integer> windows = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            Integer charCount = needs.get(t.charAt(i));
            if (charCount == null){
                needs.put(t.charAt(i),1);
            }else{
                needs.put(t.charAt(i),charCount+1);
            }
            windows.put(t.charAt(i),0);
        }
        int start = -1;
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int valid = 0;
        while(right < s.length()){
            Character character = s.charAt(right);
            right++;
            if(needs.containsKey(character)){
                windows.put(character, windows.get(character)+1);
                if(windows.get(character).equals(needs.get(character))){
                    valid++;
                }
            }

            while(valid == needs.size()){
                if ((right - left) < minLength){
                    start = left;
                    minLength = right - left;
                }
                Character word = s.charAt(left);
                left++;
                if(windows.containsKey(word)){
                    windows.put(word,windows.get(word)-1);
                    if(windows.get(word) < needs.get(word)){
                        valid--;
                    }
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(start,start+minLength); // start+minLength 此处不用+1是由于窗口左闭右开，计算长度时已经+1
    }

    /**
     * 找到字符串中所有字母的异位词
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (null == s || s.length() == 0){
            return ans;
        }
        Map<Character,Integer> needs = new HashMap<>();
        Map<Character,Integer> windows = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            Integer charCount = needs.get(p.charAt(i));
            if (charCount == null){
                needs.put(p.charAt(i),1);
            }else{
                needs.put(p.charAt(i),charCount+1);
            }
            windows.put(p.charAt(i),0);
        }

        int minLength = Integer.MAX_VALUE;
        int valid = 0;
        int left = 0;
        int right = 0;
        while (right < s.length()){
            Character w = s.charAt(right++);
            if(windows.containsKey(w)){
                windows.put(w,windows.get(w)+1);
                if (windows.get(w).equals(needs.get(w))){
                    valid++;
                }
            }

            while(valid == needs.size() && minLength >= p.length()){
                if ((right - left) == p.length()){
                    minLength = p.length();
                    ans.add(left);
                }
                Character word = s.charAt(left);
                left++;
                if(windows.containsKey(word)){
                    windows.put(word,windows.get(word)-1);
                    if(windows.get(word) < needs.get(word)){
                        valid--;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 无重复字符的最长子串
     */
    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0){
            return 0;
        }

        Set<Character> set = new HashSet<>();
        int maxLength = 0;

        int right = 0;
        int left = 0;

        while(right < s.length()){
            Character w = s.charAt(right++);
            if (!set.contains(w)){
                set.add(w);
                continue;
            }
            while (set.contains(w)){
                Character word = s.charAt(left++);
                set.remove(word);
                maxLength = Math.max(maxLength,right-left);
            }
            set.add(w);

        }
        return Math.max(right - left,maxLength);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        String s = "aab";
        String s = "abcabcbb";
        System.out.println(solution.lengthOfLongestSubstring(s));
    }
}
