package graph;

import java.util.*;

public class Graph1 {

    int size;
    int[][] adj;
    boolean[] deleteVertex;
    int[] color;

    public Graph1(int size) {
        this.size = size;
        this.adj = new int[size][size];
        deleteVertex = new boolean[size];
        color = new int[size];
        for (int i = 0; i < deleteVertex.length; i++) {
            deleteVertex[i] = true;
        }
        for (int i = 0; i < color.length; i++) {
            color[i] = -10;
        }
    }

    public void addEdge(int x, int y) {
        this.adj[x][y] = 1;
        this.adj[y][x] = 1;
    }

    public boolean isEdge(int v1, int v2) {
        boolean t = false;
        if (adj[v1][v2] == 1) {
            t = true;
        }
        return t;
    }

    public int vertexDegree(int ver) {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.adj[ver][i] == 1) {
                count++;
            }
        }
        return count;
    }

    public boolean thereISVertex(boolean[] x) {
        boolean t = false;
        for (int i = 0; i < x.length; i++) {
            if (x[i] == true) {
                t = true;
            }
        }
        return t;
    }

    public int findVertexWithMinDegree(boolean[] x) {
        int v = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            if ((x[i] == true) && (color[i] == -10)) {
                if (vertexDegree(i) < min) {
                    v = i;
                    min = vertexDegree(i);
                }
            }
        }
        return v;
    }

    public int findVertexWithMaxDegree(boolean[] x) {
        int v = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if ((x[i] == true) && (color[i] == -10)) {
                if (vertexDegree(i) > max) {
                    v = i;
                    max = vertexDegree(i);
                }
            }
        }
        return v;
    }

    public LinkedList<Integer> findIS1() {
        LinkedList<Integer> is = new LinkedList<Integer>();
    
        boolean[] deleteVertex1 = new boolean[size];
      
        System.arraycopy(deleteVertex, 0, deleteVertex1, 0, deleteVertex1.length);

        while (thereISVertex(deleteVertex1)) {
            int x = findVertexWithMinDegree(deleteVertex1);
            is.add(x);
            deleteVertex1[x] = false;
            for (int i = 0; i < size; i++) {
                if (adj[x][i] == 1) {
                    deleteVertex1[i] = false;
                }

            }

        }
        return is;

    }

    public LinkedList<Integer> findIS2() {
        LinkedList<Integer> is = new LinkedList<Integer>();

        boolean[] deleteVertex1 = new boolean[size];
        System.arraycopy(deleteVertex, 0, deleteVertex1, 0, deleteVertex1.length);
        while (thereISVertex(deleteVertex1)) {
            int x = findVertexWithMaxDegree(deleteVertex1);
            is.add(x);
            deleteVertex1[x] = false;
            for (int i = 0; i < size; i++) {
                if (adj[x][i] == 1) {
                    deleteVertex1[i] = false;
                }

            }

        }
        return is;

    }

    public int[] colorFunction1() {
        LinkedList<Integer> is;
        int countColor = 0;
        while (thereISVertex(deleteVertex)) {
            is = findIS1();
            for (int x : is) {
                color[x] = countColor;
                deleteVertex[x] = false;
            }

            countColor++;
            is.clear();
        }
        return color;
    }

    public int[] colorFunction2() {
        LinkedList<Integer> is;
        int countColor = 0;
        while (thereISVertex(deleteVertex)) {
            is = findIS2();
            for (int x : is) {
                color[x] = countColor;
                deleteVertex[x] = false;
            }

            countColor++;
            is.clear();
        }
        return color;
    }

    public static void main(String[] args) {

    }

}
