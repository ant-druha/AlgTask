package com.avalon.mylabs.GraphApp;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 23.03.13
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */
public class GraphAppMain {
    public static void main(String[] args) {
        Graph theGraph = new Graph();

        theGraph.addVertex('A');		// 0
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');
        theGraph.addVertex('F');		// 5

        theGraph.addEdge(0, 1, 6);		// AB 6
        theGraph.addEdge(0, 3, 4);		// AD 4
        theGraph.addEdge(1, 2, 10);		// BC 10
        theGraph.addEdge(1, 3, 7);		// BD 7
        theGraph.addEdge(1, 4, 7);		// BE 7
        theGraph.addEdge(2, 3, 8);		// CD 8
        theGraph.addEdge(2, 4, 5);		// CE 5
        theGraph.addEdge(2, 5, 6);		// CF 6
        theGraph.addEdge(3, 4, 12);		// DE 12
        theGraph.addEdge(4, 5, 7);		// EF 7

        System.out.print("Minimum spanning tree: ");
        theGraph.mstw();
        System.out.println();

        //=======================
        Graph theGraph2 = new Graph(10);

        theGraph2.addVertex('A');		// 0
        theGraph2.addVertex('B');
        theGraph2.addVertex('C');
        theGraph2.addVertex('D');
        theGraph2.addVertex('E');
        theGraph2.addVertex('F');		// 5
        theGraph2.addVertex('G');		//
        theGraph2.addVertex('H');		//


        theGraph2.addEdge(0, 1, 2);		// AB 2
        theGraph2.addEdge(0, 3, 3);		// AD 3
        theGraph2.addEdge(0, 4, 12);	// AE 12
        theGraph2.addEdge(1, 2, 5);		// BC 5
        theGraph2.addEdge(2, 3, 0);		// CD 0
        theGraph2.addEdge(2, 7, 6);		// CH 6
        theGraph2.addEdge(3, 4, 1);		// DE 1
        theGraph2.addEdge(3, 5, 7);		// DF 7
        theGraph2.addEdge(3, 6, 1);		// DG 1
        theGraph2.addEdge(4, 5, 10);	// DF 10
        theGraph2.addEdge(5, 6, 1);		// FG 1
        theGraph2.addEdge(6, 7, 5);		// GH 5

        System.out.print("Minimum spanning tree 2: ");
        theGraph2.mstw();
        System.out.println();

    }
}
