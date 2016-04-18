package com.company;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Sanne on 17-4-2016.
 */
public class Astar {
    Comparator<Genome> comparator = new GenomeComparator();
    private PriorityQueue<Genome> genomePrior;
    HashSet<Genome> allStates;
    Genome gen;
    boolean solutionFound;

    public Astar(Genome gen) {
        genomePrior = new PriorityQueue<Genome>(comparator);
        allStates = new HashSet<>();
        this.gen = gen;
        solutionFound = false;
    }

    public void findSolution(){
        genomePrior.add(gen);
        solutionFound = false;
        while (!solutionFound){
            createChildrenPrior();
        }
        Genome finalgen = genomePrior.poll();
        while(!finalgen.IsSolution()) {
            finalgen = genomePrior.poll();
        }
        finalgen.printPath();
    }

    private void createChildrenPrior() {
        Genome parent = genomePrior.poll();
        for (int i = 1; i < 25; i++) {
            for (int j = i; j < 26; j++) {
                if(parent.forbiddenBefore(i) && parent.forbiddenAfter(j)) {
                    Genome child = parent.invert(i, j);
                    child.count = parent.count + 1;
                    addChild(child);
                }
            }
        }
    }

    private void addChild(Genome child) {
        if (!allStates.contains(child)) {
            if(child.checkSolution()){
                solutionFound = true;
            }

            genomePrior.add(child);
            allStates.add(child);
        }
    }




}
