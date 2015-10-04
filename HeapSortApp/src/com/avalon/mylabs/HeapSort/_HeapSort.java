package com.avalon.mylabs.HeapSort;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 23.03.13
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
public class _HeapSort<T extends Comparable<T>> { 

    //============ Template =================
    private void downHeap(T[] a, int k, int n) {
//	private void downHeap(T[] a, int curI, int heapSize) {
        //  процедура просеивания следующего элемента
        //  До процедуры: a[k+1]...a[n]  - пирамида
        //  После:  a[k]...a[n]  - пирамида
//*
        T topElem = a[k];
        int largerChild;

        while(k <= n/2) {  		// пока у a[k] есть дети
            largerChild = 2*k;
            //  выбираем меньшего сына
            if( largerChild < n && a[largerChild].compareTo(a[largerChild+1]) < 0 )
                largerChild++;
            if( topElem.compareTo(a[largerChild]) >= 0 ) break;
            // 	иначе
            a[k] = a[largerChild]; 	// переносим сына наверх
            k = largerChild;
        }
        a[k] = topElem;
//*/
/*
		T topElem = a[curI];
		int largerChild;

		while(curI < heapSize/2) {  		// пока у a[k] есть дети
			int leftChild = 2*curI+1;
			int rightChild = leftChild+1;
			//  выбираем большего сына
			if( leftChild < heapSize && a[leftChild].compareTo(a[rightChild]) < 0 )
					largerChild=rightChild;
			else
				largerChild=leftChild;

			if( topElem.compareTo(a[largerChild]) >= 0 ) break;
			// 	иначе
			a[curI] = a[largerChild]; 	// переносим сына наверх
			curI = largerChild;
		}
		a[curI] = topElem;
*/

    }

    public _HeapSort(T[] a) {
        int i;
        T temp;

        // строим пирамиду
        for(i=a.length/2-1; i >= 0; i--)
            downHeap(a, i, a.length-1);

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

}
