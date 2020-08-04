package com.jh.trie;

import java.util.HashMap;

/**
 * 前缀数
 * 使用字符的ascii码来表示一条路
 */
public class TrieTree {

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }

    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            return box.getOrDefault(word, 0);
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    /**
     * 方式1:只有26个小写字母的前缀树
     */
    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] nexts;

        public Node1() {
            pass = 0;
            end = 0;
            nexts = new Node1[26];
        }
    }

    public static class Trie1 {
        private Node1 root;

        public Trie1() {
            this.root = new Node1();
        }

        public void insert(String word) {
            if (word == null) return;
            Node1 node = root;
            root.pass++;
            char[] str = word.toCharArray();
            int path;
            for (char c : str) {
                path = c - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node1();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;//当走到字符的最后一个节点时, end++
        }

        //查询这个单词之前加入过几次
        public int search(String word) {
            if (word == null) return 0;
            char[] str = word.toCharArray();
            Node1 node = root;
            int path;
            for (char c : str) {
                path = c - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;
        }

        //假如加入了2个元素: ab,abcdg, 我们删除abcdg时, 不光需要修改pass和end值,当pass为0时,需要直接断开
        public void delete(String word) {
            if (search(word) != 0) {
                char[] str = word.toCharArray();
                Node1 node = root;
                node.pass--;
                int path;
                for (char c : str) {
                    path = c - 'a';
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }
        }

        //查询指定前缀出现过几次
        public int prefixNumber(String pre) {
            if (pre == null) return 0;
            char[] str = pre.toCharArray();
            Node1 node = root;
            int path;
            for (char c : str) {
                path = c - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;
        }
    }


    /**
     * 方式1:可以支持任意字符的前缀数
     */
    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie2 {
        private Node2 root;

        public Trie2() {
            this.root = new Node2();
        }

        public void insert(String word) {
            if (word == null) return;
            Node2 node = root;
            root.pass++;
            char[] str = word.toCharArray();
            int path;
            for (char c : str) {
                path = c;
                if (!node.nexts.containsKey(path)) {
                    node.nexts.put(path, new Node2());
                }
                node = node.nexts.get(path);
                node.pass++;
            }
            node.end++;//当走到字符的最后一个节点时, end++
        }

        public int search(String word) {
            if (word == null) return 0;
            Node2 node = root;
            char[] str = word.toCharArray();
            int path;
            for (char c : str) {
                path = c;
                if (!node.nexts.containsKey(path)) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.end;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                Node2 node = root;
                node.pass--;
                char[] str = word.toCharArray();
                int path;
                for (char c : str) {
                    path = c;
                    if (--node.nexts.get(path).pass == 0) {
                        node.nexts.remove(path);
                        return;
                    }
                    node = node.nexts.get(path);
                }
                node.end--;
            }
        }


        //查询指定前缀出现过几次
        public int prefixNumber(String pre) {
            if (pre == null) return 0;
            char[] str = pre.toCharArray();
            Node2 node = root;
            int path;
            for (char c : str) {
                path = c;
                if (node.nexts.get(path) == null) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.pass;
        }
    }
}
