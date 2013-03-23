package com.avalon.mylabs.btree;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 23.03.13
 * Time: 17:12
 * To change this template use File | Settings | File Templates.
 */
public class BTree {
    static class Node {

        int value;

        Node left, right;

        public Node(int val) {

            value = val;
            //left = right = null;	// this is a default behavior
        }
        private boolean isLeaf(){
            return (left==null && right==null);
        }
    }

    private Node root = null;

    private Node findLeaf(int val){

        Node x = root, y = null;

        while ( x!=null ){

            if ( x.value==val ){
                return null;
            }
            else{
                y = x;
                if ( val > x.value ){
                    x = x.right;
                }
                else{
                    x = x.left;
                }
            }
        }
        return y;
    }
    public void add2(int val){

        Node newNode = new Node(val);

        if (root==null)
        {
            root = newNode;
            return;
        }

        Node y = findLeaf(val);

        if ( y==null) return;	// уже элемент такой есть

        if ( val > y.value ){
            y.right = newNode;
        }
        else{
            y.left = newNode;
        }
    }

    public void add(int val) {
        if (root==null){
            // если дерево пусто - создаем корень
            root = new Node(val);
        }
        else{
            // иначе добавляем рекурсивно
            add(val,root);
        }
    }

    private void add(int val,Node curNode)	{
        // рассматриваем случаи когда
        // добавляемое значение больше или меньше
        // значения текущей ноды
        // если значение больше, проверяем есть ли потомки справа
        if ( val > curNode.value )	{
            // если правого потомка нет - создаем его
            if ( curNode.right==null )	{
                curNode.right = new Node(val);
            }
            // если правый потомок есть - рекурсивно спускаемся вправо
            else {
                add(val,curNode.right);
            }
        }
        // иначе
        else	{
            // если значение добавляемого элемента меньше, проверяем есть ли потомки слева
            if ( val < curNode.value ){
                // если потомков нет - создаем его
                if ( curNode.left==null )	{
                    curNode.left = new Node(val);
                }
                // если левый потомок есть - рекурсивно спускаемся влево
                else {
                    add(val,curNode.left);
                }
            }
        }
    }

    public void remove(int val){
        // перегруженный метод
        remove(val, root, null);
    }

    private static void remove(int val, Node curNode, Node parentNode){
        // если некущая нода - null - элемент не найден, возвращаемся
        if ( curNode==null ) return;
        // если значение добавляемого элемента больше значения элемента в текущей ноде
        // вызываем метод рекурсивно для правого поддерева
        if ( val > curNode.value )
            remove(val,curNode.right,curNode);
        // если значение добавляемого элемента меньше значения элемента в текущей ноде
        // вызываем метод рекурсивно для левого поддерева
        if ( val < curNode.value )
            remove(val,curNode.left,curNode);
        // если значение добавляемого элемента равно значению элемента в текущей ноде
        // рассматриваем 3 случая
        if ( val == curNode.value ){

            // 1st case:
            // у текущей ноды нет детей -> просто обнуляем указатель у родителя
            if ( curNode.left==null && curNode.right==null )	{
                if (parentNode!=null)	{
                    // если текущая нода - левый потомок - обнуляем его
                    if ( parentNode.left.value==curNode.value )
                        parentNode.left = null;
                        // иначе обнуляем правого потомка
                    else
                        parentNode.right = null;
                }
            }
            else{
                // 2nd case:
                // есть только левый или только правый потомок
                if ( curNode.left!=null && curNode.right==null || curNode.right!=null && curNode.left==null )	{
                    if ( parentNode!=null )	{
                        // если данный элемент - правый потомок для родителя
                        if (parentNode.right!=null && parentNode.right.value==curNode.value)
                            // устанавливаем ссылку правого потомка родителя на правого или
                            parentNode.right = curNode.left!=null ? curNode.left : curNode.right;
                            //	если данный элемент - левый потомок для родителя
                        else
                            // устанавливаем ссылку левого потомка родителя на правого или
                            // левого потомка данной ноды
                            parentNode.left = curNode.left!=null ? curNode.left : curNode.right;
                    }
                }
                // 3rd case(there are both child nodes):
                else	{
                    // если есть оба потомка
                    if ( curNode.left!=null && curNode.right!=null )	{
                        Node nodeMin = curNode.right;
                        // ищем нод с минимальным значением в правом поддереве
                        while ( nodeMin.left!=null )	{
                            nodeMin = nodeMin.left;
                        }
                        // меняем найденное мин. значение правого поддерева
                        // с удаляемым и рекурсивно удаляем это(удаляемое) значение из правого поддерева
                        // (будет 1й случай)
                        curNode.value = nodeMin.value;
                        remove(nodeMin.value,curNode.right,curNode);
                    }
                }
            }
        }
    }

    //INFIX_TRAVERSE
    public void infix_traverse(){
        if (root==null) return;
        infix_traverse(root);
        System.out.println();
    }
    private static void infix_traverse(Node curNode){
        if ( curNode.left!=null )
            infix_traverse(curNode.left);

        System.out.print(curNode.value+", ");

        if ( curNode.right!=null )
            infix_traverse(curNode.right);
    }

    public void print(){
        printTree(root, "");
    }

    //=============
    private static void printTree(Node curNode, String indent){
        if ( curNode==null )
            return;
        else {
            String childIndent = indent+"     ";
            if ( !curNode.isLeaf() )
                printTree(curNode.right, childIndent);

            if ( !curNode.isLeaf() )
                System.out.println(indent + curNode.value + " -->");
            else
                System.out.println(indent + curNode.value);
            //System.out.println(childIndent + curNode.value);

            if ( !curNode.isLeaf() )
                printTree(curNode.left, childIndent);

        }
    }
}
