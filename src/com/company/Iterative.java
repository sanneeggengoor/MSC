package com.company;

import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Sanne on 17-3-2016.
 */
public class Iterative {
    Stack<Genome> deepening;
    HashSet<String> listAll;
    int maxDepth;
    static int MAX;

    public Iterative() {
        deepening = new Stack<>();
        listAll = new HashSet<>();
        maxDepth = 1;
    }

    public void createChildrenStack() {
        Genome parent = deepening.pop();
        if (parent.count <= maxDepth) {
            for (int i = 0; i < 25; i++) {
                for (int j = i; j < 25; j++) {
                    Genome child = parent.invert(i, j);
                    child.count = parent.count + 1;
                    if (!listAll.contains(child)) {

                        deepening.push(child);
                        listAll.add(child.toString());
                    }
                }
            }
        }
    }

    private boolean checkIfOk() {
        Genome state = deepening.peek();
        for (int i = 0; i < 25; i++) {
            if (state[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfEmpty() {
        return deepening.empty();
    }
}

