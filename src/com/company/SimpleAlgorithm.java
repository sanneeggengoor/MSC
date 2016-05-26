package com.company;

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
            //System.out.println(parent);
        }
        System.out.println(parent.getCountSwaps() +"\t" + parent.getcountDistance());
    }

    private Genome invertFront(Genome parent, int num) {
        for (int i = 0; i < 25; i++) {
            if (parent.genome[i] == num) {
                if(num == i+1){
                    return parent;
                }
                return parent.invert(num, i + 1);
            }
        }
        return parent;
    }

    private Genome invertBack(Genome parent, int num) {
        for (int i = 0; i < 25; i++) {
            if (parent.genome[i] == num) {
                if(num == i+1){
                    return parent;
                }
                return parent.invert(i + 1, num);
            }
        }
        return parent;
    }
}
