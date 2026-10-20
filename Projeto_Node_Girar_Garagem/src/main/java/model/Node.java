package model;

/**
 * O objeto que viaja pelas posições das rodas.
 * O carro está dentro do Node, portanto move-se automaticamente com ele.
 */
public final class Node {
    private final String id;
    private Car car;

    public Node(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("O identificador do nó é obrigatório.");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    /** Garante que um nó nunca recebe dois carros. */
    public void placeCar(Car car) {
        if (this.car != null) {
            throw new IllegalStateException("O nó " + id + " já tem o carro " + this.car + ".");
        }
        if (car == null) {
            throw new IllegalArgumentException("O carro não pode ser null.");
        }
        this.car = car;
    }

    public String description() {
        return car == null ? id : id + "[" + car + "]";
    }

    @Override
    public String toString() {
        return description();
    }
}
