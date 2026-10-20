package control;

import model.Car;
import model.Node;
import model.VehicleType;
import model.Wheel;
import repository.LinkedNodeCircuit;

/** Sistema das três rodas com movimento global simultâneo. */
public final class WheelSystem {
    private final LinkedNodeCircuit circuit = new LinkedNodeCircuit();

    // Cada roda é uma vista dos pontos do circuito global.
    private final Wheel wheel1 = new Wheel("Bola 1", 0, 1, 10, 11);
    private final Wheel wheel2 = new Wheel("Bola 2", 8, 3, 2, 9);
    private final Wheel wheel3 = new Wheel("Bola 3", 4, 5, 6, 7);

    public WheelSystem() {
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node nodeC = new Node("C");
        Node nodeA1 = new Node("A1");
        Node nodeA2 = new Node("A2");
        Node nodeA3 = new Node("A3");
        Node nodeA4 = new Node("A4");
        Node nodeB = new Node("B");
        Node node3 = new Node("3");
        Node node4 = new Node("4");

        // Percurso: 1 -> 2 -> C -> A1 -> A2 -> A3 -> A4 -> A1
        //           -> B -> 2 -> 3 -> 4 -> 1
        // As posições repetidas são cruzamentos visuais, não Nodes repetidos.
        circuit.add("1", node1);
        circuit.add("2-left", node2);
        circuit.add("C", nodeC);
        circuit.add("A1-left", nodeA1);
        circuit.add("A2", nodeA2);
        circuit.add("A3", nodeA3);
        circuit.add("A4", nodeA4);
        circuit.add("A1-right", null);
        circuit.add("B", nodeB);
        circuit.add("2-right", null);
        circuit.add("3", node3);
        circuit.add("4", node4);

        // Ordem pedida: 3=Carro, B=Mota, 1=Barco, A2=Carro, C=Mota.
        node3.placeCar(new Car("Veículo 1", VehicleType.CARRO));
        nodeB.placeCar(new Car("Veículo 2", VehicleType.MOTA));
        node1.placeCar(new Car("Veículo 3", VehicleType.BARCO));
        nodeA2.placeCar(new Car("Veículo 4", VehicleType.CARRO));
        nodeC.placeCar(new Car("Veículo 5", VehicleType.MOTA));
    }

    public void rotateOnce() {
        circuit.rotateSimultaneously();
    }

    public LinkedNodeCircuit getCircuit() {
        return circuit;
    }

    public Wheel getWheel1() {
        return wheel1;
    }

    public Wheel getWheel2() {
        return wheel2;
    }

    public Wheel getWheel3() {
        return wheel3;
    }
}
