package com.company;

import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Sanne on 17-3-2016.
 */
public class Iterative {
    Stack<Genome> deepening;
    HashSet<Genome> listAll;
    int maxDepth;
    Genome gen;
    static int MAX;

    public Iterative(Genome gen) {
        deepening = new Stack<>();
        listAll = new HashSet<>();
        maxDepth = 1;
        this.gen = gen;
    }


    public void findSolution(){
        deepening.push(gen);
        int count = 0;
        while (true){
            if (deepening.isEmpty() || deepening.peek().count==maxDepth){

                maxDepth++;
                deepening.push(gen);
                System.out.println("a"+listAll.size());
                listAll.clear();

            } else if (checkIfOk()){
                 break;
            }

            createChildrenStack();
            count++;
            //System.out.println(count);
            //System.out.println(maxDepth);
            //System.out.println(deepening.size());
        }
    }

    private void createChildrenStack() {
        Genome parent = deepening.pop();
        //System.out.println("count"+parent.count);
        //System.out.println(parent);
        //System.out.println(listAll.size());
        if (parent.count < maxDepth) {
            for (int i = 1; i < 25; i++) {
                for (int j = i; j < 26; j++) {
                    Genome child = parent.invert(i, j);
                    child.count = parent.count + 1;
                    //System.out.println("count child "+ child.count);
                    if (!listAll.contains(child)) {
                        //System.out.println(child.toString());
                        if (child.count < maxDepth) {
                            deepening.push(child);
                        }
                        listAll.add(child);
                    }
                }
            }
        }
    }

    private boolean checkIfOk() {
        Genome state = deepening.peek();
        for (int i = 0; i < 25; i++) {
            if (state.genome[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

}

