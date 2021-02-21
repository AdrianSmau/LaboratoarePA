//Tema realizata de Smau Adrian-Constantin, anul 2, grupa B5
package com.company;

public class Graph {
    int index;
    void ConnectedComponents(int n, int[][] matrix){
        int components = 0;
        boolean[] visited = new boolean[n];
        for(int v=0; v < n ; v++){
            if(!visited[v]){
                System.out.println("The component with the index " + (components+1) + ":");
                DFS(v,visited,n,matrix);
                components++;
                System.out.print("\n");
            }
        }
        System.out.println("We have " + components + " components!");
        if(components==1){
            System.out.println("Our graph is connected! Now, let's generate the partial tree!...");
            int[][] treeMatrix = new int[n][n];
            int[] treeVisited = new int[n];
            index = 0;
            for(int i=0;i<n;i++){
                treeVisited[i] = 0;
                for(int j=0;j<n;j++){
                    treeMatrix[i][j]=0;
                }
            }
            for(int i=0;i<n;i++){
                visited[i]=false;
            }
            for(int v=0;v<n;v++){
                if(!visited[v]){
                    TreeDFS(v,visited,n,matrix,treeVisited);
                    System.out.print("~END~\n");
                    System.out.println("The matrix of the partial tree will now be displayed!...");
                    for(int i=0;i<(treeMatrix.length-1);i++){
                        treeMatrix[treeVisited[i]][treeVisited[(i+1)]]=1;
                        treeMatrix[treeVisited[(i+1)]][treeVisited[i]]=1;
                    }
                    for(int i=0;i< treeMatrix.length;i++){
                        for(int j=0;j< treeMatrix.length;j++){
                            System.out.printf("%d",treeMatrix[i][j]);
                            System.out.print(" ");
                        }
                        System.out.print("\n");
                    }
                }
            }
        }
    }
    void DFS(int v,boolean[] visited,int n,int[][] matrix){
        visited[v]=true;
        System.out.printf("%d",(v+1));
        System.out.print(" ");
        for(int i=0;i<n;i++){
            if(!visited[i] && matrix[v][i]==1) {
                DFS(i, visited, n, matrix);
            }
        }
    }
    void TreeDFS(int v,boolean[] visited,int n,int[][] matrix,int[] treeVisited){
        visited[v]=true;
        treeVisited[index] = v;
        index++;
        System.out.printf("%d -> ",(v+1));
        for(int i=0;i<n;i++){
            if(!visited[i] && matrix[v][i]==1) {
                TreeDFS(i, visited, n, matrix,treeVisited);
            }
        }
    }

}
