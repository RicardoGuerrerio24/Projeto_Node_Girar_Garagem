package model;

/** Um veículo fica associado a um Node e acompanha-o quando o nó muda de posição. */
public final class Car {
    private final String id;
    private final VehicleType type;

    public Car(String id, VehicleType type) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("O identificador do veículo é obrigatório.");
        }
        if (type == null) {
            throw new IllegalArgumentException("O tipo do veículo é obrigatório.");
        }
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }

    @Override
    public String toString() {
        return id + " (" + type + ")";
    }
}
