package com.company;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Sanne on 21-4-2016.
 */
public class AstarDeep {

    Comparator<Genome> comparator = new GenomeComparator();
    private PriorityQueue<Genome> genomePrior;
    HashSet<Genome> allStates;
    Genome gen;
    boolean solutionFound;
    int maxDeep;

    public AstarDeep(Genome gen) {
        genomePrior = new PriorityQueue<Genome>(comparator);
        allStates = new HashSet<>();
        this.gen = gen;
        solutionFound = false;
    }

    public void findSolution(){
        int countParents = 0;
        maxDeep = (int)gen.aStarscore();

        solutionFound = false;
        while(!solutionFound) {
            genomePrior.add(gen);
            while (!solutionFound && countParents < 1000) {
                createChildrenPrior();
                countParents++;
                if (countParents % 200 == 0) {
                    System.out.println(countParents);
                }
            }
            genomePrior.clear();
            //genomePrior.add(gen);
            maxDeep++;
            countParents = 0;
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
                if(parent.forbiddenBefore(i) && parent.forbiddenAfter(j) && parent.count <= maxDeep) {
                    Genome child = parent.invert(i, j);
                    child.count = parent.count + 1;
                    child.movedGenes = parent.movedGenes + Math.abs(i - j);
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
