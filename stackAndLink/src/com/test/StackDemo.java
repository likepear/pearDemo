package com.test;

import java.util.Iterator;

/**
 * 初始栈
 * @param <T>
 */
public class StackDemo<T> implements  Iterable<T> {

    private int count; //数量

    private InnerLink root ; //根节点

    public StackDemo(){
        this.count = 0;
        root = null;
    }

    public StackDemo(T t){
        this();
        this.root = new InnerLink(t);
        this.count++;
    }


    /**
     * 压栈
     * @return
     */
    public void push(T t){

        if(root==null){
            root = new InnerLink(t);
        }else{
            InnerLink oldRoot = root;
            root = new InnerLink(t);
            root.next = oldRoot;
        }
        this.count++;

    }

    /**
     * 出栈
     * @return
     */
    public T pop(){
        return null;
    }

    /**
     * 获取栈中元素数量
     * @return
     */
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InnerLink getRoot() {
        return root;
    }

    public void setRoot(InnerLink root) {
        this.root = root;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackInterator<T>();
    }



    private class InnerLink<T> {

        private T value;
        private InnerLink<T> next;

        public InnerLink(){
            super();
        }

        public InnerLink(T t){
            this.value = t;
        }

        private T getValue(){
            return this.value;
        }

        private InnerLink<T> getNext(){
            return this.next;
        }

        private  InnerLink<T> setValue(T t){
            this.value = t;
            return this;
        }

    }

    private class StackInterator<T> implements Iterator<T>{

        private InnerLink<T> stackRoot = root ;
        private int sum = count ;

        @Override
        public boolean hasNext() {
            return sum!=0;
        }

        @Override
        public T next() {
            T t = stackRoot.getValue();
            stackRoot = stackRoot.getNext();
            this.sum--;
            return t;
        }
    }

    public static void main(String[] args) {
        StackDemo stack = new StackDemo();
        stack.push("pear");
        stack.push("like");

        for(Object obj:stack){
            System.out.println(obj);
        }

    }


}
