package com.avalon.mylabs.HeapSort;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 23.03.13
 * Time: 17:35
 * To change this template use File | Settings | File Templates.
 */
public class HeapSortAppMain {
    public static void main(String[] args) {
        int[] iArr = {1,2,6,3,7,0,-23,5};

        int[] iArr2 = {1,2,65,43,-239,4,65,6,3,7,0,-23,5};

        printIArr(iArr);
        HeapSort.sort(iArr);
        printIArr(iArr);

        printIArr(iArr2);
        HeapSort.sort2(iArr2);
        printIArr(iArr2);

        System.out.println("\nInteger sorting:");
        Integer[] iArr3 = {1,2,65,43,-239,4,65,6,3,7,0,-23,5};
        printIArrI(iArr3);
        HeapSort.sort2I(iArr3);
        printIArrI(iArr3);


        Integer[] iArr4 = {1,2,65,-239,6,3,0,-23};
        System.out.println("\nTemplate Integer/String sorting:");
        printIArrI(iArr4);

        _HeapSort<Integer> hs = new _HeapSort<Integer>(iArr4);
        printIArrI(iArr4);

        String[] iArr5 = {"aaa","abc","efz","zzz","vgf","sa","be"};
        printIArrS(iArr5);
        _HeapSort<String> hs2 = new _HeapSort<String>(iArr5);
        printIArrS(iArr5);

        System.out.println();
    }

    public static void printIArr(int[] arr) {
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println();
    }

    public static void printIArrI(Integer[] arr) {
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println();
    }
    public static void printIArrS(String[] arr) {
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println();
    }
}
