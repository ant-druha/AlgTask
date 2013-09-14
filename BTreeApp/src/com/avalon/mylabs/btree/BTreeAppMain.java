package com.avalon.mylabs.btree;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 23.03.13
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
public class BTreeAppMain {
    public static void main(String[] args) {
        BTree b1 = new BTree();
        b1.add(5);
        b1.add(1);
        b1.add(0);
        b1.add(-5);

        int[] iArrAdd = {0,1,5,9,8,7,4,2,3,6,0,-3,43,-57,-9,65,1};
        //int[] iArrDell = {0,1,-3,-57};
        int[] iArrDell = {5};

        System.out.println("test");
        BTree b2 = new BTree();
        BTree b3 = new BTree();
        for (int i=0; i<iArrAdd.length; i++){
            b1.add(iArrAdd[i]);
            b2.add(iArrAdd[i]);
            b3.add2(iArrAdd[i]);
        }
        b1.infix_traverse();
        b2.infix_traverse();
        b3.infix_traverse();
        //b1.print();
        b2.print();
        //b3.print();
        for (int i=0; i<iArrDell.length; i++){
            b1.remove(iArrDell[i]);
            b2.remove(iArrDell[i]);
            b3.remove(iArrDell[i]);
        }
        System.out.print("After removing: ");
        for (int i=0; i<iArrDell.length; i++){
            System.out.print(iArrDell[i]+", ");
        }
        System.out.println("\n");
        b1.infix_traverse();
        b2.infix_traverse();
        b3.infix_traverse();
        //b1.print();
        b2.print();
        //b3.print();
    }
}
