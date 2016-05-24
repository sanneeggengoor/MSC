package com.company.Swaps;

import com.company.Swaps.Genome;

public class SimpleAlgorithm {
    private Genome gen;

    public SimpleAlgorithm(Genome gen) {
        this.gen = gen;
    }

    public void findSolution() {
        Genome parent = gen;
        for (int i = 0; i < 12; i++) {
            parent = invertFront(parent, i + 1);
            parent = invertBack(parent, 25 - i);
            System.out.println(parent);
        }
    }

    private Genome invertFront(Genome parent, int num) {
        for (int i = 0; i < 25; i++) {
            if (parent.genome[i] == num) {
                return parent.invert(num, i + 1);
            }
        }
        return parent;
    }

    private Genome invertBack(Genome parent, int num) {
        for (int i = 0; i < 25; i++) {
            if (parent.genome[i] == num) {
                return parent.invert(i + 1, num);
            }
        }
        return parent;
    }
}
