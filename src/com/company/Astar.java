package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Astar {
    Comparator<Genome> comparator = new GenomeComparator();
    private PriorityQueue<Genome> genomePrior;
    private Genome gen;
    private boolean swapType;
    private boolean solutionFound;
    private long countStates;

    public Astar(Genome gen, boolean type_swap) {
        swapType= type_swap;
        genomePrior = new PriorityQueue<>(comparator);
        this.gen = gen;
        solutionFound = false;
    }

    public void findSolution(){
        countStates = 0;
        genomePrior.add(gen);
        solutionFound = false;

        while (!solutionFound){
            createChildrenPrior();

            if(genomePrior.size() > 5000000) {
                resize();
            }
            if(countStates % 2000 <= 3){
                System.out.println(countStates);
            }
        }

        // als er een oplossing is gevonden zit deze niet automatisch bovenaan,
        // want er zijn nog meer kinderen gemaakt hierna. Dus moet er van de
        // bovenkant afgehaald worden totdat de oplossing wel gevonden is.
        Genome finalgen = genomePrior.poll();
        while(!finalgen.IsSolution()) {
            finalgen = genomePrior.poll();
        }
        // daarna wordt hij weer teruggelegd zodat hij zo weer teruggevonden
        // kan worden.
        genomePrior.add(finalgen);
        finalgen.printPath();
    }

    private void createChildrenPrior() {
        Genome parent = genomePrior.poll();
        for (int i = 1; i < 25; i++) {
            if(parent.forbiddenBefore(i)) {
                for (int j = i; j < 26; j++) {
                    if (parent.forbiddenAfter(j)) {
                        Genome child = parent.invert(i, j);
                        child.changeType(swapType);
                        addChild(child);
                        countStates++;

                        if (solutionFound) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private void resize(){
        PriorityQueue<Genome> genomePrior2 = new PriorityQueue<>(comparator);
        for(int i = 0; i < 4500000; i++) {
            genomePrior2.add(genomePrior.poll());
        }
        genomePrior = genomePrior2;
        System.out.println("-------new queue-------");

    }

    public Genome getFinalGen(){
        return genomePrior.peek();
    }

    private void addChild(Genome child) {
        solutionFound = child.IsSolution();
        genomePrior.add(child);
    }

}
