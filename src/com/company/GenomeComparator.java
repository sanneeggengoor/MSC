package com.company;

import java.util.Comparator;

public class GenomeComparator implements Comparator<Genome> {

    public int compare(Genome a, Genome b){
        return (int)(a.getscoreSwap() - b.getscoreSwap());
        //return (int)(a.getscoreDistance() - b.getscoreDistance());
    }
}
