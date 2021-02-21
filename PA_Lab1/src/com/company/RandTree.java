//Tema realizata de Smau Adrian-Constantin, anul 2, grupa B5
package com.company;

import java.util.Random;

public class RandTree {
    Random random = new Random();
    int level;
    int value;

    public RandTree() {
        this.level=(-1);
        this.value=(-1);
    }

    void generateTree(int nr){
        System.out.print("\n");
        level++;
        value++;
        for(int j=0;j<level;j++)
            System.out.print(" ");
        System.out.printf("node%d",value);
        for(int i=0;i<random.nextInt((nr+1));i++){
            generateTree(nr);
            level--;
            value--;
        }
    }
}
