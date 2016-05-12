package com.company;

import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Sanne on 17-3-2016.
 */
public class Iterative {
    Stack<Genome> genomeStack;
    HashSet<Genome> allStates;
    int maxDepth;
    Genome gen;
    static int MAX;
    boolean solutionFound;
    long minTime;

    public Iterative(Genome gen) {
        genomeStack = new Stack<>();
        allStates = new HashSet<>();
        maxDepth = 1;
        this.gen = gen;
        minTime = 1000;
    }

    public void findSolution(){
        while(true){
            maxDepth = 1;
            long startTime = System.currentTimeMillis();
            genomeStack.push(gen);
            solutionFound = false;
            while (!solutionFound){
                if (genomeStack.isEmpty() || genomeStack.peek().countSwaps==maxDepth){

                    maxDepth++;
                    //System.out.println("=========" + maxDepth);
                    genomeStack.push(gen);
                    allStates.clear();
                }
                if(maxDepth == 3) {
                    System.out.println("=========" + maxDepth);
                    long endTime   = System.currentTimeMillis();
                    long runTime = endTime - startTime;
                    if(runTime < minTime) {
                        minTime = runTime;
                        System.out.println(minTime);
                    }
                    break;
                }
                createChildrenStack();
            }
        }
    }

    private void createChildrenStack() {
        Genome parent = genomeStack.pop();
        for (int i = 1; i < 25; i++) {
            for (int j = i; j < 26; j++) {
                if(parent.forbiddenBefore(i) && parent.forbiddenAfter(j)) {
                    Genome child = parent.invert(i, j);
                    child.countSwaps = parent.countSwaps + 1;
                    addChild(child);
                    if(solutionFound) {
                        return;
                    }
                }
            }
        }
    }

    private void addChild(Genome child) {
        if (!allStates.contains(child)) {
            solutionFound = child.checkSolution();
            if (child.countSwaps < maxDepth) {
                genomeStack.push(child);
            }
            allStates.add(child);
        }
    }



}

