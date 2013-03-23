package com.avalon.mylabs.HeapSort;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 23.03.13
 * Time: 17:38
 * To change this template use File | Settings | File Templates.
 */
public class HeapSort {

    private static void downHeap(int[] a, int k, int n) {
        //  процедура просеивания следующего элемента
        //  До процедуры: a[k+1]...a[n]  - пирамида
        //  После:  a[k]...a[n]  - пирамида
        int new_elem;
        int child;
        new_elem = a[k];

        while(k <= n/2) {  		// пока у a[k] есть дети
            child = 2*k;
            //  выбираем меньшего сына
            if( child < n && a[child] < a[child+1] )
                child++;
            if( new_elem >= a[child] ) break;
            // 	иначе
            a[k] = a[child]; 	// переносим сына наверх
            k = child;
        }
        a[k] = new_elem;
    }

    public static void sort2(int[] a) {
        int i, temp;

        // строим пирамиду
        for(i=a.length/2-1; i >= 0; i--) downHeap(a, i, a.length-1);

        // теперь a[0]...a[size-1] пирамида
        for(i=a.length-1; i > 0; i--) {
            // 	меняем первый с последним
            temp=a[i];
            a[i]=a[0];
            a[0]=temp;
            // восстанавливаем пирамидальность a[0]...a[i-1]
            downHeap(a, 0, i-1);
        }
    }

    private static void downHeapI(Integer[] a, int curI, int heapSize) {
        //  процедура просеивания следующего элемента
        //  До процедуры: a[k+1]...a[n]  - пирамида
        //  После:  a[k]...a[n]  - пирамида
        Integer topElem = a[curI];
        int smallerChild;

        while(curI < heapSize/2) {  		// пока у a[k] есть дети
            int leftChild = 2*curI+1;
            int rightChild = leftChild+1;
            //  выбираем большего сына
            if( leftChild < heapSize && a[leftChild] < a[rightChild] )
                smallerChild=rightChild;
            else
                smallerChild=leftChild;
            if( topElem >= a[smallerChild] ) break;
            // 	иначе
            a[curI] = a[smallerChild]; 	// переносим сына наверх
            curI = smallerChild;
        }
        a[curI] = topElem;
    }

    public static void sort2I(Integer[] a) {
        int i;
        Integer temp;

        // строим пирамиду
        for(i=a.length/2-1; i >= 0; i--) downHeapI(a, i, a.length-1);

        // теперь a[0]...a[size-1] пирамида
        for(i=a.length-1; i > 0; i--) {
            // 	меняем первый с последним
            temp=a[i];
            a[i]=a[0];
            a[0]=temp;
            // восстанавливаем пирамидальность a[0]...a[i-1]
            downHeapI(a, 0, i-1);
        }
    }

    private static void fnSortHeap(int array[], int arr_ubound){
        int i, o;
        int lChild, rChild, mChild, root, temp;
        root = (arr_ubound-1)/2;

        for(o = root; o >= 0; o--){
            for(i=root;i>=0;i--){
                lChild = (2*i)+1;
                rChild = (2*i)+2;
                if((lChild <= arr_ubound) && (rChild <= arr_ubound)){
                    if(array[rChild] >= array[lChild])
                        mChild = rChild;
                    else
                        mChild = lChild;
                }
                else{
                    if(rChild > arr_ubound)
                        mChild = lChild;
                    else
                        mChild = rChild;
                }

                if(array[i] < array[mChild]){
                    temp = array[i];
                    array[i] = array[mChild];
                    array[mChild] = temp;
                }
            }
        }
        temp = array[0];
        array[0] = array[arr_ubound];
        array[arr_ubound] = temp;
        return;
    }

    public static void sort(int[] arr){
        for(int i=arr.length; i>1; i--){
            fnSortHeap(arr, i - 1);
        }
    }

}
