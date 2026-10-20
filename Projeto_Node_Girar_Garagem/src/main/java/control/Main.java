package control;

import view.TerminalView;

/** Executa a animação contínua das três rodas. */
public final class Main {
    private static final long DELAY_MILLISECONDS = 650;

    private Main() {
        // Classe de entrada.
    }

    public static void main(String[] args) {
        WheelSystem system = new WheelSystem();

        while (true) {
            TerminalView.printState(system.getCircuit(), system.getWheel1(),
                    system.getWheel2(), system.getWheel3());
            system.rotateOnce();
            sleep(DELAY_MILLISECONDS);
        }
    }

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.exit(0);
        }
    }
}
