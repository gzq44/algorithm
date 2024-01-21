package apriceshittest;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/OrIXps/
 *
 * @author gzq44
 * @date 2024/01/01 15:24
 **/
public class LRUCache {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2,1);
        lruCache.put(1,1);
        lruCache.put(2,3);
        lruCache.put(4,1);
        lruCache.get(1);
        lruCache.get(2);
    }
    private static class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }
    static int capacity;
    static Node tmp;
    static HashMap<Integer, Node> map;

    public LRUCache(int _capacity) {
        capacity = _capacity;
        tmp = new Node(0,0);
        tmp.next = tmp;
        tmp.prev = tmp;
        map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        remove(node);
        putFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            putFront(node);
            node = map.get(key);
            node.value = value;
            return;
        }
        Node node = new Node(key, value);
        putFront(node);
        map.put(key, node);
        if (capacity < map.size()) {
            map.remove(tmp.prev.key);
            remove(tmp.prev);
        }
    }

    void putFront(Node node) {
        node.prev = tmp;
        node.next = tmp.next;
        tmp.next = node;
        node.next.prev = node;
    }

    void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }
}
