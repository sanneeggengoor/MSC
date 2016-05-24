package com.company;

import java.util.HashSet;
import java.util.Stack;

public class Iterative {
    private Stack<Genome> genomeStack;
    private HashSet<Genome> allStates;
    private int maxDepth;
    private Genome gen;
    private boolean solutionFound;
    private long minTime;
    private int countStates;

    public Iterative(Genome gen) {
        genomeStack = new Stack<>();
        allStates = new HashSet<>();
        maxDepth = 1;
        this.gen = gen;
        minTime = 10000;
    }

    public void findSolution(){
        countStates = 0;
        maxDepth = 1;
        long startTime = System.currentTimeMillis();
        genomeStack.push(gen);
        solutionFound = false;
        while (!solutionFound){
            if (genomeStack.isEmpty() || genomeStack.peek().getCountSwaps() == maxDepth) {
                maxDepth++;
                System.out.println(maxDepth);
                genomeStack.push(gen);
                allStates.clear();
            }
            createChildrenStack();
            if(countStates % 200 == 0) {
                System.out.println(countStates);
            }
        }
    }

    public void findHeuristicSolution(){
        countStates = 0;
        maxDepth = 1;
        long startTime = System.currentTimeMillis();
        genomeStack.push(gen);
        solutionFound = false;
        while (!solutionFound){
            if (genomeStack.isEmpty() || genomeStack.peek().getCountSwaps() == maxDepth) {
                maxDepth++;
                System.out.println(maxDepth);
                genomeStack.push(gen);
                allStates.clear();
            }
            createChildrenStack_Heuristic();
            if(countStates % 200 == 0) {
                System.out.println(countStates);
            }
        }
    }

    // Returns the shortest time the algorithm reached depth 3 after
    // number_iterations iterations
    public long reachDepth3(int number_iterations) {
        for (int i = 0; i < number_iterations; i++) {
            countStates = 0;
            maxDepth = 1;
            long startTime = System.currentTimeMillis();
            genomeStack.push(gen);
            solutionFound = false;
            while (!solutionFound) {
                if (genomeStack.isEmpty() || genomeStack.peek().getCountSwaps() == maxDepth) {
                    maxDepth++;
                    genomeStack.push(gen);
                    allStates.clear();
                }
                if (maxDepth == 3) {
                    long endTime = System.currentTimeMillis();
                    long runTime = endTime - startTime;
                    if (runTime < minTime) {
                        minTime = runTime;
                        System.out.println(minTime);
                    }
                    break;
                }
                createChildrenStack();
            }
        }
        return minTime;
    }

    // Returns the shortest time the algorithm reached depth 3 using the
    // heuristic after number_iterations iterations
    public long reachDepth3Heuristic(int number_iterations) {
        for (int i = 0; i < number_iterations; i++) {
            countStates = 0;
            maxDepth = 1;
            long startTime = System.currentTimeMillis();
            genomeStack.push(gen);
            solutionFound = false;
            while (!solutionFound) {
                if (genomeStack.isEmpty() || genomeStack.peek().getCountSwaps() == maxDepth) {
                    maxDepth++;
                    genomeStack.push(gen);
                    allStates.clear();
                }
                if (maxDepth == 3) {
                    long endTime = System.currentTimeMillis();
                    long runTime = endTime - startTime;
                    if (runTime < minTime) {
                        minTime = runTime;
                        System.out.println(minTime);
                    }
                    break;
                }
                createChildrenStack_Heuristic();
            }
        }
        return minTime;
    }

    private void createChildrenStack_Heuristic() {
        Genome parent = genomeStack.pop();
        for (int i = 1; i < 25; i++) {
            for (int j = i; j < 26; j++) {
                if(parent.forbiddenBefore(i) && parent.forbiddenAfter(j)) {
                    Genome child = parent.invert(i, j);
                    addChild(child);
                    countStates++;
                    if (solutionFound) {
                        return;
                    }
                }
            }
        }
    }

    private void createChildrenStack() {
        Genome parent = genomeStack.pop();
        for (int i = 1; i < 25; i++) {
            for (int j = i; j < 26; j++) {
                Genome child = parent.invert(i, j);
                addChild(child);
                countStates++;
                if (solutionFound) {
                    return;
                }
            }
        }
    }

    private void addChild(Genome child) {
        if (!allStates.contains(child)) {
            solutionFound = child.IsSolution();
            if (child.getCountSwaps() < maxDepth) {
                genomeStack.push(child);
            }
            allStates.add(child);
        }
    }



}

