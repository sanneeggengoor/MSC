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
    int timesResized;
    int numOfQueue;
    boolean solutionFound;

    public Astar(Genome gen) {
        genomePrior = new PriorityQueue<Genome>(comparator);
        // Even bij zetten dat we deze een set hebben gemaakt voor geheugenbesparing
        allStates = new HashSet<>();
        this.gen = gen;
        solutionFound = false;
        numOfQueue = 0;
        timesResized = 0;
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
            if(countParents % 200 == 0){
                //System.out.println(countParents);
            }
        }
        Genome finalgen = genomePrior.poll();
        while(!finalgen.IsSolution()) {
            finalgen = genomePrior.poll();
        }
        genomePrior.add(finalgen);
        //finalgen.printPath();
    }

    private void createChildrenPrior() {
        numOfQueue--;
        Genome parent = genomePrior.poll();
        if(genomePrior.size()>4000000){
            timesResized++;
            resize();
        }
        if(genomePrior.size() %1000 == 0){
            System.out.println(timesResized+ "  " +genomePrior.size());
        }
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

    private void resize(){
        Genome[] newPQ = new Genome[1000000];
        for(int i = 0; i < 1000000; i++){
            Genome gen = genomePrior.poll();
            newPQ[i] = gen;
        }
        genomePrior.clear();
        int j = 1000000;
        while (j>0){
            j--;
            genomePrior.add(newPQ[j]);
            newPQ[j]=null;
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
            numOfQueue ++;
            genomePrior.add(child);
            allStates.add(child);
        }
    }




}
