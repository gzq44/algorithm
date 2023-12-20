package apriceshittest;


import org.omg.PortableInterceptor.INACTIVE;
import utils.TreeNode;

import java.util.*;

/**
 * shit
 *
 * @author gzq44
 * @date 2023/08/08 21:04
 **/
public class Main {

    public static void main(String[] args) {
        new Main().groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        int n = strs.length;
        for (int i = 0; i < n; i++) {
            int[] arr = new int[26];
            for (int j = 0; j < strs[i].length(); j++) {
                arr[strs[i].charAt(j) - 'a']++;
            }
            String s = Arrays.toString(arr);
            List<String> orDefault = map.getOrDefault(s, new ArrayList<>());
            orDefault.add(strs[i]);
            map.put(s, orDefault);
        }
        ArrayList<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, List<String>> e : map.entrySet()) {
            ans.add(e.getValue());
        }
        return ans;
    }
}

