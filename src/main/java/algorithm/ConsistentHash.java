package algorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;


public class ConsistentHash<T> {

    private final int numberOfReplicas;
    private volatile TreeMap<Integer, List<T>> circle = new TreeMap<>();

    private static final int circleSize = 188833;

    public ConsistentHash(int numberOfReplicas, Collection<T> nodes) {
        this.numberOfReplicas = numberOfReplicas;
        for (T node : nodes) {
            addNode(circle, node);
        }
    }

    public synchronized void add(T node) {
        TreeMap<Integer, List<T>> newCircle = copyCircle();
        addNode(newCircle, node);
        this.circle = newCircle;
    }

    public synchronized void remove(T node)	{
        TreeMap<Integer, List<T>> newCircle = copyCircle();
        remove(newCircle, node);
        this.circle = newCircle;
    }

    private TreeMap<Integer, List<T>> copyCircle() {
        TreeMap<Integer, List<T>> newTree = new TreeMap<>();

        for (Map.Entry<Integer, List<T>> entry : circle.entrySet())	{
            List<T> list = new ArrayList<T>();
            list.addAll(entry.getValue());
            newTree.put(entry.getKey(), list);
        }
        return newTree;
    }

    private void addNode(TreeMap<Integer, List<T>> circle, T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int key = hashMd5(node.toString() + i);
            List<T> list = circle.get(key);
            if (list == null) {
                list = new ArrayList<T>();
                circle.put(key, list);
            }
            if (!containsNode(list, node)) {
                list.add(node);
            }
        }
    }

    private void removeNodeToList(List<T> list, T node)	{
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (node.equals(it.next())) {
                it.remove();
            }
        }
    }

    private boolean containsNode(List<T> list, T node) {
        for (T t : list) {
            if (t.equals(node))	{
                return true;
            }
        }
        return false;
    }
    
    private void remove(TreeMap<Integer, List<T>> circle, T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            // 节点与虚拟节点映射关系
            int key = hashMd5(node.toString() + i);
            List<T> list = circle.get(key);
            if (list != null) {
                if (list.contains(node)) {
                    removeNodeToList(list, node);
                }
                if (list.isEmpty())	{
                    circle.remove(key);
                }
            }
        }
    }

    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hash = hashMd5(key);
        Map.Entry<Integer, List<T>> entry = circle.ceilingEntry(hash);
        List<T> node = null;
        if (entry == null) {//if empty, go back
            node = circle.firstEntry().getValue();
        }
        else {
            node = entry.getValue();
        }
        if (node != null && !node.isEmpty()) {
            return node.get(0);
        }
        return null;
    }

    private static int hashCode(byte[] bytes) {
        int hash = 0;
        for (byte b : bytes) {
            hash = hash * 31 + ((int) b & 0xFF);
            if (hash > 0x4000000) {
                hash = hash % 0x4000000;
            }
        }
        return hash;
    }

    private  int hashMd5(Object o) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(o.toString().getBytes());
            int hashCode = hashCode(bytes);
            return hashCode % circleSize;
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String ss[]) throws InterruptedException {
        List<String> ips = new ArrayList<String>();
        ips.add("114.113.1.101:1");
        ips.add("114.113.1.103:3");
        ips.add("114.113.1.102:2");
        ConsistentHash<String> c = new ConsistentHash<>(200, ips);

        Map<String, Integer> map = new HashMap<>();
        for (int i = 1; i < 60 * 10000; i++) {
            String key = c.get(UUID.randomUUID().toString().substring(0, 16));
            Integer r = map.get(key);
            if (r == null) {
                map.put(key, 1);
            }
            else {
                map.put(key, r + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }

        String s = c.get("" + 500000);

        System.out.println(s);

        c.remove("114.113.1.103:3");

        s = c.get("" + 500000);
        System.out.println(s);

        map = new HashMap<>();
        for (int i = 1; i < 60 * 10000; i++) {
            String key = c.get("" + i);
            Integer r = map.get(key);
            if (r == null)	{
                map.put(key, 1);
            }
            else {
                map.put(key, r + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet())	{
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }
}
