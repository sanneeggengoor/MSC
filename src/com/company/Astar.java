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
        int countParents = 0;
        genomePrior.add(gen);
        // dit is die 0 die hij telkens uitprint, dan weet je hoeveel heuristische hij al heeft opgelost.
        System.out.println(gen.countDistance);
        solutionFound = false;
        while (!solutionFound){
            createChildrenPrior();
            countParents++;
            if(genomePrior.size() > 400000) {
                PriorityQueue<Genome> genomePrior2 = new PriorityQueue<Genome>(comparator);
                for(int i = 0; i < 200000; i++) {
                    genomePrior2.add(genomePrior.poll());
                }
                genomePrior = genomePrior2;
                System.out.println("-------new queue-------");
            }
            if(countParents % 200 == 0){
                System.out.println(countParents);
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
                    //System.out.println("AScore" + child.aStarscore());
                    //System.out.println("Score" + child.score);
                    addChild(child);
                }
            }
        }
    }

    public Genome getFinalGen(){
        Genome finalGen = genomePrior.peek();
        return finalGen;
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
