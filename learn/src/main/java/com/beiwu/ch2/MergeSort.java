package com.beiwu.ch2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoubing
 * @date 2020-12-25 17:05
 */
public class MergeSort {

    public static void swap(List<Integer> src, int source, int target) {
        int tmp = src.get(source);
        src.set(source, src.get(target));
        src.set(target, tmp);
    }

    public static void mergeSort(List<Integer> src, int fromIndex, int toIndex) {
        if (toIndex == fromIndex)
            return;
        else if (toIndex == (fromIndex + 1)) {
            if (src.get(fromIndex) > src.get(toIndex)) {
                //交换
                swap(src, fromIndex, toIndex);
            }
            return;
        }
        if (toIndex - fromIndex >= 1) {
            int mid = (fromIndex + toIndex) / 2;
            mergeSort(src, fromIndex, mid);
            mergeSort(src, mid + 1, toIndex);
            //将两个部分 放到一起
            int index1 = fromIndex;
            int index2 = mid + 1;
            List<Integer> sorted = new ArrayList<>();

            while (index1 <= mid || index2 <= toIndex) {
                if (index1 > mid) {
                    sorted.add(src.get(index2++));
                    continue;
                }
                if (index2 > toIndex) {
                    sorted.add(src.get(index1++));
                    continue;
                }
                // 如果走到这里 说明两个都没有完
                if (src.get(index1) <= src.get(index2)) {
                    sorted.add(src.get(index1++));
                } else {
                    sorted.add(src.get(index2++));
                }
            }
            for (int i = fromIndex; i <= toIndex; i++) {
                src.set(i, sorted.get(i-fromIndex));
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> unSortedNum = MakeArray.makeArray();

        List<Integer> trySort = new ArrayList<>();
        trySort.add(1);
        trySort.add(10);
        trySort.add(8);
        trySort.add(8);
        trySort.add(3);
        trySort.add(2);
        trySort.add(12);

        mergeSort(unSortedNum, 0, trySort.size() - 1);
        System.out.println(unSortedNum);


    }
}
