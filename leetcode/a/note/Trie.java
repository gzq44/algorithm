package a.note;

/**
 * 208
 *
 * @author gzq44
 * @date 2024/02/18 23:22
 **/
public class Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();
//        trie.insert("apple");
        trie.search("a");
        trie.startsWith("app");
    }
    Trie[] trie;
    boolean f;
    public Trie() {
        trie = new Trie[26];
        f = false;
    }

    public void insert(String word) {
        Trie[] tmp = trie;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (tmp[idx] == null) tmp[idx] = new Trie();
            if (i == word.length() - 1) tmp[idx].f = true;
            tmp = tmp[idx].trie;
        }
    }

    public boolean search(String word) {
        Trie[] tmp = trie;
        for (int i = 0; i < word.length() - 1; i++) {
            int idx = word.charAt(i) - 'a';
            if (tmp[idx] == null) return false;
            tmp = tmp[idx].trie;
        }
        return tmp[word.charAt(word.length() - 1) - 'a'] == null ? false : tmp[word.charAt(word.length() - 1) - 'a'].f;
    }



    public boolean startsWith(String word) {
        Trie[] tmp = trie;
        for (int i = 0; i < word.length() - 1; i++) {
            int idx = word.charAt(i) - 'a';
            if (tmp[idx] == null) return false;
            tmp = tmp[idx].trie;
        }
        return tmp[word.charAt(word.length() - 1) - 'a'] != null;
    }
}
