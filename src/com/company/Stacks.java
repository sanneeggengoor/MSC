package com.company;

/**
 * Created by Maaike on 3-3-2016.
 */
import java.util.*;

public class Stacks {
    Stack<Genome> genomeStack;

    public void createChildrenStack(){
        Genome parent = genomeStack.pop();
        int i = 0;
        while (true){
            for(int j = i ; j < 25; j++){
                Genome child = parent.invert(i,j);
            }
        }

    }

    public static void makeStack() {
        Stack genomeStack = new Stack();
    }

}
