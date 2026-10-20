package model;

/** Tipos de veículo apresentados no diagrama. */
public enum VehicleType {
    CARRO("Carro"),
    MOTA("Mota"),
    BARCO("Barco");

    private final String displayName;

    VehicleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
