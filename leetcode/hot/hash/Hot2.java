package hot.hash;

import java.util.HashMap;

/**
 * 2
 *
 * @author gzq44
 * @date 2024/02/16 12:48
 **/
public class Hot2 {
    public static void main(String[] args) {
        new Hot2().isIsomorphic("@[\\]^_`{|}~?\"bcdefg&ijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!a#$%h'()*+,-./:;<=> @[\\]^_`{|}~?\"bcdefg&ijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!a#$%h'()*+,-./:;<=> @[\\]^_`{|}~?\"bcdefg&ijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!a#$%h'()*+,-./:;<=> @[\\]^_`{|}~?\"bcdefg&ijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!a#$%h'()*+,-./:;<=> @[\\]^_`{|}~?\"bcdefg&ijklmnopqrstuvwxyzABCD", "@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCD");
    }
    public boolean isIsomorphic(String s, String t) {
        //记录字母的上一个位置
        HashMap<Integer, Integer> cnt1 = new HashMap();
        HashMap<Integer, Integer> cnt2 = new HashMap();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int sv = s.charAt(i);
            int tv = t.charAt(i);
            if (cnt1.get(sv) != cnt2.get(tv)) {
                return false;
            }
            else {
                cnt1.put(sv, i);
                cnt2.put(tv, i);
            }
        }
        return true;
    }
}
