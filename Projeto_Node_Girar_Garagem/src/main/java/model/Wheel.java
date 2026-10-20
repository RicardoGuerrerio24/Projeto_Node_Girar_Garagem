package model;

import repository.LinkedNodeCircuit;

/**
 * Uma roda é uma vista de quatro posições do circuito global.
 * A rotação não é executada nesta classe: é feita uma única vez no circuito.
 */
public final class Wheel {
    private final String id;
    private final int[] circuitPositions;

    public Wheel(String id, int top, int right, int bottom, int left) {
        this.id = id;
        this.circuitPositions = new int[]{top, right, bottom, left};
    }

    public String getId() {
        return id;
    }

    public Node getNodeAt(int slot, LinkedNodeCircuit circuit) {
        return circuit.getNodeAt(circuitPositions[slot]);
    }

    public int getCircuitPosition(int slot) {
        return circuitPositions[slot];
    }
}
