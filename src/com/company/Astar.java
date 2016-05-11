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
        solutionFound = false;
        while (!solutionFound){
            createChildrenPrior();
            countParents++;
            if(countParents % 200 == 0){
                System.out.println(countParents);
            }
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
                    //System.out.println("AScore" + child.aStarscore());
                    //System.out.println("Score" + child.score);
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
