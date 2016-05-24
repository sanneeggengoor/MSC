package com.company;

import com.company.Swaps.Genome;

public class Main {

    public static void main(String[] args) {
        public Genome[] makeTestSet(int number) {
            Genome[] testSet = new Genome[number];
            // Ik doe wel setSeed() maar toch komen er elke keer andere genomen uit? Hoe kan dat??, als dit wel lukt
            // hebben we namelijk gepaarde data, en dan kunnen we geloof ik makkelijker een test doen.
            rgen.setSeed(2);
            for (int i = 0; i<number; i++){
                testSet[i] = new Genome();
            }
            return testSet;

    }
}