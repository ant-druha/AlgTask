package com.avalon.mylabs.GraphApp;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 23.03.13
 * Time: 17:30
 * To change this template use File | Settings | File Templates.
 */

public class Graph {
    private final int MAX_VERTS;
    private final Integer INFINITY = Integer.MAX_VALUE;
    private Vertex vertexList[];	// список вершин
    private int adjMat[][];			// матрица смежности
    private int nVerts;				// текущее кол-во вершин (в графе)
    private int currentVert;
    private PriorityQueue thePQ;	// приоритетная очередь, будем заносить в нее ребра
    private int nTree;				// кол-во вершин в дереве

    public Graph() {
        this(20);
    }

    public Graph(int maxVerts) {
        MAX_VERTS =  maxVerts;
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];		// матрица смежности
        nVerts = 0;
        for (int i=0; i<MAX_VERTS; i++)
            for (int j=0; j<MAX_VERTS; j++)
                adjMat[i][j] = INFINITY;
        thePQ = new PriorityQueue(MAX_VERTS);
    }
    public void addVertex(char lb) {				// добавление вершины в граф
        vertexList[nVerts++] = new Vertex(lb);
    }
    public void addEdge(int start, int end, int weight) {	// добавление дуги в граф
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }
    public void displayVertex(int v) {
        System.out.println(vertexList[v].getLabel());
    }
    public void mstw() {	// постоение минимального основного дерева
        currentVert = 0;	// начиная с ячейки 0
        int weight = 0;
        while ( nTree < nVerts-1 ) {						// пока не все вершины графа включены в дерево
            vertexList[currentVert].setIsInTree(true);		// включение currentVert в дерево
            ++nTree;
            // вставка в приоритетную очередь ребер, смежных с currentVert
            for (int i=0; i<nVerts; i++) {					// для каждой вершины
                if ( i==currentVert )						// пропустить, если текущая вершина
                    continue;
                if ( vertexList[i].IsInTree() )				// пропустить, если уже в дереве
                    continue;
                int distance = adjMat[currentVert][i];		// извлекаем вес до очередной вершины
                if ( distance==INFINITY )					// пропустить если нет ребер (тут проверяется условие смежности)
                    continue;
                putInPQ(i,distance);
            }
            if ( thePQ.size()==0 ) {						// если очередь не содержит вершин
                System.out.println(" Grapgh not connected");
                return;
            }
            // удаление ребра с минимальным расстояние из очереди
            Edge theEdge = thePQ.removeMin();
            int sourceVert = theEdge.getSrcVert();
            currentVert = theEdge.getDestVert();
            weight+=theEdge.getDistance();

            // вывод ребра от начальной до текущей вершины
            System.out.print( vertexList[sourceVert].getLabel() );
            System.out.print( vertexList[currentVert].getLabel() + "(" + theEdge.getDistance() + ")" );
            System.out.print(" ");
        }
        System.out.println("\nTotal weight: " + weight);
        // минимальное остовное дерево пострено
        for (int i=0; i<nVerts; i++)				// снятие пометки с вершин
            vertexList[i].setIsInTree(false);
    }
    public void putInPQ(int newVert, int newDist) {
        // существует ли другое ребро с той же конечной вершиной?
        int queueIndex = thePQ.findIndxOf(newVert);		// получение индекса вершины
        if ( queueIndex!= -1 ) {						// если ребро существует
            Edge tempEdge = thePQ.peekN(queueIndex);	// получить его
            int oldDist = tempEdge.getDistance();
            if ( oldDist > newDist ) {					// если новое ребро короче,
                thePQ.removeN(queueIndex);				// удалить старое ребро
                thePQ.insert( new Edge(currentVert, newVert, newDist) ); // вставка нового ребра
            }
            // иначе ничего не делается. оставляем старую вершину
        }
        else {		// ребра с той же конечной вершиной не существует -> вставка новго ребра
            thePQ.insert( new Edge(currentVert, newVert, newDist) );
        }
    }

}

class Vertex {
    public char label;
    public boolean isInTree;

    public Vertex(char lab) {
        label = lab;
        isInTree = false;
    }
    public char getLabel() { return label; }
    public void setIsInTree(boolean to) { isInTree = to; }
    public boolean IsInTree() { return isInTree; }
}

class PriorityQueue {
    private Heap theHeap;
    private int maxSize;
    private int currentSize;

    public PriorityQueue(int mx) {
        maxSize = mx;
        currentSize = 0;
        theHeap = new Heap(maxSize);
    }

    public void insert(Edge arch) {
        theHeap.insert(arch);
        ++currentSize;
    }
    public Edge removeMin() {
        --currentSize;
        return theHeap.removeMin();
    }
    public void removeN(int index) {	// удаление елемента в позиции N
        // допущение - в графе не могут быть веса меньше -2^31 в дан. сл-е
        theHeap.change(index, Integer.MIN_VALUE);
        theHeap.removeMin();
        --currentSize;
    }
    public Edge peekMin() {	return theHeap.peekMin(); }
    public int size() {	return currentSize;	}
    public boolean isEmpty() { return (currentSize==0); }
    public Edge peekN(int index) { return theHeap.peekN(index); }

    public int findIndxOf(int findDex) {	// поиск элемента с заданным значением destVert (индекс конечной вершины ребра)
        for (int i=0; i<currentSize; i++)
            if ( theHeap.peekN(i).getDestVert()==findDex )
                return i;
        return -1;
    }

}	//PriorityQueue

class Heap {
    private Edge heapArray[];
    private int maxSize;		// размер массива
    private int currentSize;	// кол-во узлов в массиве

    public Heap(int mx) {
        maxSize = mx;		//  MAX_VERTS = 20 ??
        currentSize = 0;
        heapArray = new Edge[maxSize];
    }
    public boolean isEmpty() {
        return currentSize==0;
    }

    // ============================== insert ==============================
    public boolean insert(Edge item) {
        if ( currentSize==maxSize )		// если массив заполнен
            return false;
        heapArray[currentSize] = item;	// добавление нового узла и зазмещение в конце массива
        trickleUp(currentSize++);		// алгоритм смещения вверх
        return true;
    }
    // ============================== insert ==============================

    public void trickleUp(int index) {
        int parent = (index-1)/2;
        Edge bottom = heapArray[index];
        while ( index > 0 && heapArray[parent].getDistance() > bottom.getDistance() ) {		// было < !!
            heapArray[index] = heapArray[parent]; 	// узел смещается вниз
            index = parent;							// индекс перемещается вверх
            parent = (parent-1)/2;					// parent < - его родитель
        }
        heapArray[index] = bottom;
    }

    // ============================== remove ==============================
    public Edge removeMin() {						// удаление элемента с минимальным ключем (весом)
        // (предполагается что список не пуст)
        Edge root = heapArray[0];					// сохранение корня
        heapArray[0] = heapArray[--currentSize];	// корень <- последний узел
        trickleDown(0);								// корневой узел смещается вниз
        return root;								// возвращаем удаленный узел
    }
    // ============================== remove ==============================

    public Edge peekMin() {	return heapArray[0]; }
    public Edge peekN(int index) {	return heapArray[index]; }

    public void trickleDown(int index) {
        int smallerChild;
        Edge top = heapArray[index];				// сохранение корня
        while ( index < currentSize/2 )	{			// пока есть хотя бы один потомок (было <)
            int leftChild = 2*index+1;
            int rightChild = leftChild+1;
            // определение меньшего потомка
            if ( rightChild < currentSize && 				// левый потомок существует?  (!!правый!!) (1е замечание)
                    heapArray[leftChild].getDistance() > 						// !! знак !! (был <)
                            heapArray[rightChild].getDistance() )
                smallerChild = rightChild;
            else
                smallerChild = leftChild;
            if ( top.getDistance()<= heapArray[smallerChild].getDistance() )		// !! знак!! (был >=)
                break;
            heapArray[index] = heapArray[smallerChild];		// потомок сдвигается вверх
            index = smallerChild;							// переход вниз
        }
        heapArray[index] = top;								// index <- корень
    }
    // изменение ключа (приоритета)
    public boolean change (int index, int newValue) {
        if ( index<0 || index>=currentSize )
            return false;
        int oldValue = heapArray[index].getDistance();		// сохранение старого ключа
        heapArray[index].setDistance(newValue);				// присваивание нового ключа (значения)
        if (oldValue < newValue)							// если узел повышается
            trickleDown(index);								// выполняется смещение вниз
        else												// если понижается
            trickleUp(index);								// смещение вверх
        return true;
    }
}	// Heap

class Edge {
    public int srcVert;		// индекс начальной вершины ребра
    public int destVert;	// индекс конечной вершины ребра
    public int distance;	// расстояние от начала до конца

    public Edge(int sv, int dv, int d) {
        srcVert = sv;
        destVert = dv;
        distance = d;
    }
    public int getDistance() { return distance; }
    public void setDistance(int newDst) { distance = newDst; }
    public void setSrcVert(int newSrcVert) { srcVert = newSrcVert; }
    public void setDestVert(int newDestVert) { destVert = newDestVert; }
    public int getDestVert() { return destVert; }
    public int getSrcVert() { return srcVert; }
}	// Edge
