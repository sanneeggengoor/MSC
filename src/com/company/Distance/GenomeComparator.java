package com.company.Distance;

import java.util.Comparator;

public class GenomeComparator implements Comparator<Genome> {

    public int compare(Genome a, Genome b){
        return (int)(a.getscoreDistance() - b.getscoreDistance());
    }
}
