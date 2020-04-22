package com.demo.test;

public class RotateArray {

    /**
     * 旋转数组k未
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {

        if(k==0||nums==null||nums.length<1)
            return ;



    }

    public static void main(String[] args) {
        int[] a = {-1, 2, 3};
        int k = 1;
        rotate(a,k);
        for (int i:a){
            System.out.print(i+"\t");
        }

    }
}
