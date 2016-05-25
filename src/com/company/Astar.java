package com.company;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Astar {
    Comparator<Genome> comparator = new GenomeComparator();
    private PriorityQueue<Genome> genomePrior;
    private HashSet<Genome> allStates;
    private Genome gen;
    private boolean swapType;
    private boolean solutionFound;
    private long countStates;

    public Astar(Genome gen, boolean type) {
        swapType= type;
        genomePrior = new PriorityQueue<>(comparator);
       allStates = new HashSet<>();
        this.gen = gen;
        solutionFound = false;
    }

    // Deze functie vindt een oplossing voor het genoom.
    public void findSolution(){
        // integer die het aantal bezochte states volgt (onafhankelijk van hoe vaak de queue geleegd is)
        countStates = 0;
        // genoom wordt toegevoegd aan de priority queue
        genomePrior.add(gen);
        // boolean die onthoudt of de oplossing al gevonden is
        solutionFound = false;

        // zolang de oplossing nog niet gevonden is, wordt er doorgezocht
        while (!solutionFound){

            // deze functie maakt alle kindnodes die bij het bovenste genoom van de queue horen
            createChildrenPrior();

            // wanneer de queu groter is geworden dan 5.000.000, wordt er van de achterkant een stuk afgehaald.
            if(genomePrior.size() > 5000000) {
                resize();
            }

            // print soms het aantal bezochte states tot nu toe.
            if(countStates % 2000 <= 3){
                System.out.println(countStates);
            }
        }

        // als er een oplossing is gevonden zit deze niet automatisch bovenaan, want er zijn nog meer kinderen
        // gemaakt hierna. Dus moet er van de bovenkant afgehaald worden totdat de oplossing wel gevonden is.
        Genome finalgen = genomePrior.poll();
        while(!finalgen.IsSolution()) {
            finalgen = genomePrior.poll();
        }
        // daarna wordt hij weer teruggelegd zodat hij zo weer teruggevonden kan worden.
        genomePrior.add(finalgen);
        finalgen.printPath();
    }

    private void createChildrenPrior() {
        // bovenste wordt eraf gehaald
        Genome parent = genomePrior.poll();
        //System.out.println(parent.countDistance);
        for (int i = 1; i < 25; i++) {
            for (int j = i; j < 26; j++) {
                if(parent.forbiddenBefore(i) && parent.forbiddenAfter(j)) {
                    Genome child = parent.invert(i, j);
                    child.changeType(swapType);
                    addChild(child);
                    countStates++;
                }
            }
        }
    }

    private void resize(){
        PriorityQueue<Genome> genomePrior2 = new PriorityQueue<>(comparator);
        for(int i = 0; i < 2000000; i++) {
            genomePrior2.add(genomePrior.poll());
        }
        genomePrior = genomePrior2;
        System.out.println("-------new queue-------");

    }


    public Genome getFinalGen(){
        return genomePrior.peek();
    }

    private void addChild(Genome child) {
        //String childString = child.toString();
        if (!allStates.contains(child)) {
            solutionFound = child.IsSolution();

            genomePrior.add(child);
           allStates.add(child);
        }
    }




}
