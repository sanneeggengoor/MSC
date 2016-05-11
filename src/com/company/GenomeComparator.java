package com.company;

import java.util.Comparator;

/**
 * Created by Sanne on 17-4-2016.
 */
public class GenomeComparator implements Comparator<Genome> {

    public int compare(Genome a, Genome b){
        return (int)(a.scoreDistance - b.scoreDistance);
    }
}
