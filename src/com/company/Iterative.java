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

    public Iterative(Genome gen) {
        genomeStack = new Stack<>();
        allStates = new HashSet<>();
        maxDepth = 1;
        this.gen = gen;
    }

    public void findSolution(){
        genomeStack.push(gen);
        solutionFound = false;
        while (!solutionFound){
            if (genomeStack.isEmpty() || genomeStack.peek().count==maxDepth){

                maxDepth++;
                System.out.println("=========" + maxDepth);
                genomeStack.push(gen);
                allStates.clear();

            }

            createChildrenStack();
        }
    }

    private void createChildrenStack() {
        Genome parent = genomeStack.pop();
        for (int i = 1; i < 25; i++) {
            for (int j = i; j < 26; j++) {
                if(parent.forbiddenBefore(i) && parent.forbiddenAfter(j)) {
                    Genome child = parent.invert(i, j);
                    child.count = parent.count + 1;
                    addChild(child);
                    if(solutionFound) {
                        return;
                }
            }
        }
    }

    private void addChild(Genome child) {
        if (!allStates.contains(child)) {
            solutionFound = child.checkSolution();
            if (child.count < maxDepth) {
                genomeStack.push(child);
            }
            allStates.add(child);
        }
    }



}

