package com.demo.test;

public class DistinctArray {

    public static int removeDuplicates(int[] nums) {


        if(nums==null||nums.length<=0)
            return 0;
        int k = 0; //第二个数组元素角标
        int zero = 0; //第二个元素中0的标识
        int[] newArray = new int[nums.length];

        for(int i=0;i<nums.length;i++){

            if(i==0){
                newArray[i] = nums[i];
                k++;
            }else{
                boolean flag = true;
                for(int j=0;j<=k;j++){
                    if(newArray[j]==nums[i]){
                        if(nums[i]==0&&zero<1){
                            zero++;
                            continue;
                        }
                        flag = false;

                    }

                }
                if(flag){
                    newArray[k] = nums[i];
                    k++;
                }
            }

        }
        for(int i=0;i<k;i++){
            nums[i] = newArray[i];
        }

        return k;

    }

    /**
     * 双指针元素去重(只去除连续重复数)
     * @param nums
     * @return
     */
    public static int removeDuplicates2(int[] nums) {
        if(nums != null && nums.length == 0){
            return 0;
        }
        int result = 1; //慢指针
        for(int i=0; i<nums.length-1; i++){ //n个数比较n-1次  i快指针
            if(nums[i] != nums[i+1]){
                nums[result] = nums[i+1];
                result+=1;
            }
        }
        return result;
    }



    public static void main(String[] args) {

        int [] a = {-1,0,0,0,0,2,2,3};
        int [] b = {-1,0,0,0,0,2,-1,3};
        int duplicates = removeDuplicates(a);
        System.out.println(duplicates);

        int removeDuplicates2 = removeDuplicates2(b);
        System.out.println(removeDuplicates2);

    }


}