package com.company;

/**
 * Created by Maaike on 3-3-2016.
 */
import java.util.*;

public class Stacks {
    Stack<Genome> genomeStack;

    public void createChildrenStack(){
        Genome parent = genomeStack.pop();

        for(int i = 0; i<25; i++){
            for(int j = i ; j < 25; j++){
                Genome child = parent.invert(i,j);
                child.countSwaps = parent.countSwaps + 1;
                genomeStack.push(child);
            }

        }


    }

    public static void makeStack() {
        Stack genomeStack = new Stack();
    }

}
