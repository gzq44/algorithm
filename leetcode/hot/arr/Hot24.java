package hot.arr;

import java.util.ArrayList;
import java.util.List;

/**
 * 24
 *
 * @author gzq44
 * @date 2024/02/14 12:32
 **/
public class Hot24 {
    public static void main(String[] args) {
        new Hot24().fullJustify(new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"}, 20);
    }
    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;

        List<List<String>> res = new ArrayList();
        List<String> tmp = new ArrayList();
        List<Integer> len = new ArrayList();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (cnt + words[i].length() + tmp.size() <= maxWidth) {
                cnt += words[i].length();
                tmp.add(words[i]);
            } else {
                res.add(new ArrayList(tmp));
                len.add(cnt);
                cnt = words[i].length();
                tmp.clear();
                tmp.add(words[i]);
            }
        }
        List<String> ans = new ArrayList();
        //deal res
        for (int i = 0; i < res.size(); i++) {
            int emt = maxWidth - len.get(i);
            List<String> ss = res.get(i);
            if (ss.size() == 1) {
                ans.add(f(ss, ss.get(0).length(), maxWidth));
                continue;
            }
            int pl = emt % (ss.size() - 1);
            int avg = emt / (ss.size() - 1);
            StringBuffer avgEmt = new StringBuffer();
            for (int j = 0; j < avg; j++) {
                avgEmt.append(" ");
            }
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < ss.size() - 1; j++) {
                sb.append(ss.get(j));
                sb.append(avgEmt.toString());
                if (pl > 0) {
                    sb.append(" ");
                    pl--;
                }
            }
            sb.append(ss.get(ss.size() - 1));
            ans.add(sb.toString());
        }
        //tmp must be last
        ans.add(f(tmp, cnt, maxWidth));
        return ans;
    }

    String f(List<String> ss, int len, int md) {
        int pl = md - len - ss.size() + 1;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ss.size() - 1; i++) {
            sb.append(ss.get(i));
            sb.append(" ");
        }
        sb.append(ss.get(ss.size() - 1));
        for (int i = 0; i < pl; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }


}
