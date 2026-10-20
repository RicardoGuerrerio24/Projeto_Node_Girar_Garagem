package repository;

import model.Node;

/**
 * Lista circular de posições do percurso global.
 *
 * Existem 12 posições de movimento, mas apenas 10 Nodes reais. As posições
 * 2-left/2-right e A1-left/A1-right são duas entradas do mesmo cruzamento
 * visual. Uma volta copia o estado antigo e aplica todas as mudanças ao mesmo
 * tempo, permitindo colisões sem criar Nodes duplicados.
 */
public final class LinkedNodeCircuit {
    private static final class Position {
        private final String location;
        private Node occupant;
        private Position next;
        private Position previous;

        private Position(String location, Node occupant) {
            this.location = location;
            this.occupant = occupant;
        }
    }

    private Position first;
    private Position last;
    private int size;

    public void add(String location, Node node) {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("O nome da posição é obrigatório.");
        }
        if (findPosition(location) != null) {
            throw new IllegalArgumentException("A posição " + location + " já existe.");
        }

        Position newPosition = new Position(location, node);
        if (first == null) {
            first = newPosition;
            last = newPosition;
            first.next = first;
            first.previous = first;
        } else {
            newPosition.previous = last;
            newPosition.next = first;
            last.next = newPosition;
            first.previous = newPosition;
            last = newPosition;
        }
        size++;
    }

    /**
     * Move todos os Nodes simultaneamente para a posição seguinte.
     * O estado antigo é copiado antes de qualquer alteração.
     */
    public void rotateSimultaneously() {
        Node[] oldState = new Node[size];
        Position current = first;
        for (int i = 0; i < size; i++) {
            oldState[i] = current.occupant;
            current = current.next;
        }

        current = first;
        for (int i = 0; i < size; i++) {
            // A posição i recebe o conteúdo antigo da posição anterior.
            int previousIndex = (i - 1 + size) % size;
            current.occupant = oldState[previousIndex];
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    public Node getNodeAt(int index) {
        return getPosition(index).occupant;
    }

    public String getLocationAt(int index) {
        return getPosition(index).location;
    }

    public int positionOf(Node searchedNode) {
        Position current = first;
        for (int i = 0; i < size; i++) {
            if (current.occupant == searchedNode) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    private Position findPosition(String location) {
        Position current = first;
        for (int i = 0; i < size; i++) {
            if (current.location.equals(location)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private Position getPosition(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Posição inválida: " + index);
        }
        Position current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}
