package com.demo.impl;

import com.demo.annotation.ValueNotNull;
import com.sun.istack.internal.NotNull;

import javax.xml.bind.ValidationEvent;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 红黑树实现类
 * 平衡二叉树特性：
 * 1 父节点左边的元素小于该元素 右边的元素大于改元素
 * 2 一层最多只有两个节点
 * 3 所有叶子节点到根节点距离相等
 * 红黑树特性 ：
 * 1 所有红节点都是左子节点    根节点为黑色
 * 2 没有左右节点都是红节点的父节点
 * 3 所有黑节点到根节点的距离都是一样的
 */
public class BlackRedTree {

    private Node root ; //根节点

    private final boolean RED = true;
    private final boolean BLACK = false;

    /**
     * 节点内部类
     */
    private class Node{

        private String key;    //键
        private Object value;  //值
        private Node left;     //左子节点
        private Node right;    //右子节点
        private int N;         //节点数量
        private boolean color; //颜色

        private Node(String key,Object value,int N,boolean color){
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }

        private Node(){
            super();
            this.color = BLACK;
            this.N = 1;
        }

    }

    /**
     * 获取红黑树节点的数目
     * @return
     */
    public int size(){
        return size(root);
    }

    private int size(Node root){
        if(root==null)
            return 0;
        return root.N;
    }

    /**
     * 红黑树插入元素
     * @param key
     * @param val
     */
    public void put(@NotNull String key,Object val){
        this.root = put(this.root,key,val);
        this.root.color = BLACK;
    }

    public Node put(Node n,String key,Object val){
        if(n==null)
            return new Node(key,val,1,RED);  //新增节点默认是红节点
        int cmp = compareKey(n.key, key);
        if(cmp==0) n.value = val;
        if(cmp<0){
            n.right = put(n.right, key, val);
        }
        if(cmp>0){
            n.left = put(n.left, key,val);
        }

        //重新调整树结构
        if(isRed(n.right)&&!isRed(n.left)){
            //如果右节点为红，且左子节点不为红
            n = rotateLeft(n);
        }
        if(isRed(n.left)&&isRed(n.left.left)){
            //如果左子节点为红，且左子节点的左节点也为红
            n = rotateRight(n);
        }
        if(isRed(n.left)&&isRed(n.right)){
            //如果左右子节点都为红，则变色
            flipColors(n);
        }

        n.N = size(n.left)+size(n.right)+1;  //重新计算节点个数
        StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
        for(StackTraceElement element:stackTrace){
            System.out.println(element.getMethodName());
        }

        return n;
    }

    /**
     * 根据key获取元素的值
     * @param key
     * @return
     */
    public Object get(@NotNull String key){
        return get(this.root,key);
    }

    private Object get(Node root,@NotNull String key){
        if(root==null||root.key==null)
            return null;
        int cmp = compareKey(root.key, key);
        if(cmp<0){
            get(root.right,key);
        }else if (cmp>0){
            get(root.left,key);
        }
        return root.value;
    }

    /**
     * 获取根节点的key
     * @return
     */
    public String getRootKey(){
        return root==null?null:root.key;
    }

    /**
     * 获取根节点的val
     * @return
     */
    public Object getRootVal(){
        return root==null?null:root.value;
    }

    /**
     * 获取tree中最小的key
     * @return
     */
    public String min(){
        return root==null?null:min(root).key;
    }

    private Node min(Node node){
        if(node.left==null)
            return node;
        return min(node.left);
    }

    /**
     * 获取tree中最大的key
     * @return
     */
    public String max(){
        return root==null?null:max(root).key;
    }

    private Node max(Node node){
        if(node.right==null)
            return node;
        return max(node.right);
    }

    /**
     * 获取大于等于给定key最小的key
     * 思路：以中间节点作比较，如果key大于root.key,则要么返回null,要么能在右子树中能找到
     * 如果key小于root.key，则可能能在左子树找到，找不到则返回中间节点。
     * 如果key等于root.key,则返回中间节点。
     * @param key
     * @return
     */
    public String ceil(String key){
        Node node = ceil(root, key);
        if(node==null)
            return null;
        return node.key;
    }

    private Node ceil(Node node,String key){
        if(node==null)
            return null;
        int cmp = compareKey(node.key, key);
        if(cmp>0){
            Node ceil = ceil(node.left, key);
            if(ceil!=null)
                return ceil;
        }
        if(cmp<0){
            return ceil(node.right, key);
        }
        return node;
    }

    /**
     * 获取小于等于给定key的最大的key
     * 思路：以中间节点做比较，如果key小于root.key，则要么返回null要么在左子树能找到
     * 如果key大于root.key，则如果能找到，则返回，找不到，则返回中间节点。
     * 如果key等于root.key，则返回中间节点
     * @param key
     * @return
     */
    public String floor(@NotNull String key){
        Node node = floor(root, key);
        if(node==null)
            return null;
        return node.key;
    }

    private Node floor(Node n,@NotNull String key){
        if(n==null)
            return null;
        int cmp = compareKey(n.key, key);
        if(cmp>0){   //在左子树去找
            return floor(n.left,key);
        }
        if(cmp<0){  //去右子树找
            Node x = floor(n.right, key);
            if(x!=null)
                return x;
        }
        return n;
    }

    /**
     * 判断tree中是否存在该键
     * @param key
     * @return
     */
    public boolean contains(@ValueNotNull String key){
        return root==null?false:contains(root,key);
    }

    private boolean contains(Node n,String key){
        if(n==null)
            return false;
        int cmp = compareKey(n.key, key);
        if(cmp<0){
            return contains(n.right,key);
        }
        if(cmp>0){
            return contains(n.left,key);
        }
        return true;
    }

    /**
     * 红黑树左旋
     * @param h
     * @return
     */
    private Node rotateLeft(Node h){
        Node n = h.right;
        h.right = n.left;
        n.left = h;
        n.N = h.N;
        n.color = h.color;
        h.color = RED; //所有左子节点都是红节点
        h.N = size(h.left)+size(h.right)+1;

        return n;
    }

    /**
     * 红黑树右旋
     * @param h
     * @return
     */
    private Node rotateRight(Node h){
        Node n = h.left;
        h.left = n.right;
        n.right = h;
        n.N = h.N;
        n.color = h.color;
        h.color = RED;
        h.N = size(h.left)+size(h.right)+1;

        return n;
    }

    /**
     * 颜色翻转  子节点变黑，本身变红
     * @param h
     */
    private void flipColors(Node h){
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }

    /**
     * 比较两个key的大小
     * @param key1
     * @param key2
     * @return
     */
    private int compareKey(@NotNull String key1,String key2){
        return key1.compareTo(key2);
    }

    /**
     * 判断一个节点是否为红节点 如果为空则返回false
     * @param node
     * @return
     */
    private boolean isRed(Node node){
        if(node==null)
            return false;
        return node.color==RED;
    }

    public static void main(String[] args) {

        BlackRedTree tree = new BlackRedTree();
        tree.put("A", 1);
        tree.put("M", 13);
        tree.put("Z", 26);
        tree.put("C", 3);
        tree.put("E", 5);
        tree.put("O", 17);
        System.out.println(tree.getRootKey());
        System.out.println(tree.getRootVal());
        System.out.println(tree.size());
        System.out.println(tree.contains("z"));
        System.out.println(tree.floor("O"));
        System.out.println(tree.ceil("K"));

    }



}



