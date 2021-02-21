//Tema realizata de Smau Adrian-Constantin, anul 2, grupa B5
package com.company;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Compulsory
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Compulsory part of the homework!...");
        System.out.println();
        System.out.println("Hello World!");
        String[] languages = {"C","C++","C#","Python","Go","Rust","JavaScript","PHP","Swift","Java"};
        int n = (int) (Math.random() * 1000000);
        System.out.println("Numarul random este: " + n);
        n *= 3;
        System.out.println("Numarul dupa ce il inmultim cu 3: " + n);
        n += 0b10101;
        System.out.println("Acum, il adunam cu numarul binar 10101: " + n);
        n += 0xFF;
        System.out.println("Il adunam cu numarul hexadecimal FF: " + n);
        n *= 6;
        System.out.println("In final, il inmultim cu 6: " + n);
        int result = 0;
        while (n != 0) {
            result += n % 10;
            n /= 10;
        }
        System.out.println("Suma cifrelor numarului obtinut anterior este: " + result + "\n" + "Acum, facem suma cifrelui acestui rezultat pana cand ramanem cu un numar format dintr-o singura cifra:");
        int new_result = 0;
        while(result > 9){
            while(result != 0) {
                new_result += result % 10;
                result /= 10;
            }
            result = new_result;
            new_result = 0;
        }
        System.out.println("Suma cifrelor acestui numar facuta pana cand ramane un numar de o singura cifra este: " + result);
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
        // Optional
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Optional part of the homework!...");
        System.out.println();
        // We start the timewatch
        long start = System.nanoTime();
        System.out.println("The number received as a command line argument is: " + args[0]);
        try{
        n = Integer.parseInt(args[0]); } catch (NumberFormatException err){
            System.out.println("Input-ul nu este un numar valid...");
            System.exit(-1);
        }
        int[][] matrix = new int [n][n];
        Random rand;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i == j){
                    matrix[i][j] = 1;
                }
                else{
                    if(i<j){
                    rand = new Random();
                    matrix[i][j] = rand.nextInt(2);
                    matrix[j][i] = matrix[i][j];
                    }
                }
            }
        }
        for(int i=0;i< matrix.length;i++){
            for(int j=0;j< matrix.length;j++){
                System.out.printf("%d",matrix[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        Graph g = new Graph();
        System.out.println("Now, we display the connected components!...");
        g.ConnectedComponents(n,matrix);
        // We stop the timewatch
        long end = System.nanoTime();
        long elapsed = end - start;
        System.out.println("Execution time for generating the graph, finding its connected components and, eventually, generating the partial tree: " + elapsed + " nanoseconds...");
        System.out.println("This translates to approximately " + (elapsed/1000000000) + " second(s)!...");
        //Bonus
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Bonus part of the homework!...");
        RandTree t = new RandTree();
        System.out.println();
        System.out.println("Acum, vom genera un arbore binar random, pe care il vom reprezenta textual!...");
        t.generateTree(2);
        System.out.println();
    }
}
