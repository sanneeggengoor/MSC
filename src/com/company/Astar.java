package com.company;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Astar {
    Comparator<Genome> comparator = new GenomeComparator();
    private PriorityQueue<Genome> genomePrior;
    private HashSet<Genome> allStates;
    private Genome gen;
    private boolean solutionFound;
    private int countStates;

    public Astar(Genome gen) {
        genomePrior = new PriorityQueue<>(comparator);
        allStates = new HashSet<>();
        this.gen = gen;
        solutionFound = false;
    }

    public void findSolution(){
        countStates = 0;
        genomePrior.add(gen);
        // dit is die 0 die hij telkens uitprint, dan weet je hoeveel heuristische hij al heeft opgelost.
        System.out.println(gen.getcountDistance());
        solutionFound = false;
        while (!solutionFound){
            createChildrenPrior();
            if(genomePrior.size() > 5000000) {
                PriorityQueue<Genome> genomePrior2 = new PriorityQueue<>(comparator);
                for(int i = 0; i < 4500000; i++) {
                    genomePrior2.add(genomePrior.poll());
                }
                genomePrior = genomePrior2;
                System.out.println("-------new queue-------");
            }
            if(countStates % 2000 == 0){
                System.out.println(countStates);
            }
        }
        Genome finalgen = genomePrior.poll();
        while(!finalgen.IsSolution()) {
            finalgen = genomePrior.poll();
        }
        genomePrior.add(finalgen);
        finalgen.printPath();
    }

    private void createChildrenPrior() {
        Genome parent = genomePrior.poll();
        //System.out.println(parent.countDistance);
        for (int i = 1; i < 25; i++) {
            for (int j = i; j < 26; j++) {
                if(parent.forbiddenBefore(i) && parent.forbiddenAfter(j)) {
                    Genome child = parent.invert(i, j);
                    addChild(child);
                    countStates++;
                }
            }
        }
    }

    public Genome getFinalGen(){
        return genomePrior.peek();
    }

    private void addChild(Genome child) {
        //if (!allStates.contains(child)) {
            solutionFound = child.IsSolution();

            genomePrior.add(child);
            //allStates.add(child);
        //}
    }




}
