package view;

import model.Node;
import model.Wheel;
import repository.LinkedNodeCircuit;

/** Desenha as três rodas e combina Nodes que se intercetam. */
public final class TerminalView {
    private static final int HEIGHT = 15;
    private static final int WIDTH = 78;
    private static final int[] CENTERS = {12, 36, 60};
    private static final int CENTER_Y = 7;

    private TerminalView() {
        // Classe utilitária.
    }

    /** Limpa o terminal e desenha um frame sem texto auxiliar. */
    public static void printState(LinkedNodeCircuit circuit,
                                  Wheel wheel1, Wheel wheel2, Wheel wheel3) {
        System.out.print("\033[H\033[2J");
        char[][] canvas = new char[HEIGHT][WIDTH];
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                canvas[row][column] = ' ';
            }
        }

        drawCircle(canvas, CENTERS[0]);
        drawCircle(canvas, CENTERS[1]);
        drawCircle(canvas, CENTERS[2]);

        // Pontos normais das três rodas.
        place(canvas, CENTERS[0], 3, label(wheel1.getNodeAt(0, circuit)));
        place(canvas, CENTERS[0], 11, label(wheel1.getNodeAt(2, circuit)));
        place(canvas, CENTERS[0] - 10, CENTER_Y, label(wheel1.getNodeAt(3, circuit)));
        place(canvas, CENTERS[1], 3, label(wheel2.getNodeAt(0, circuit)));
        place(canvas, CENTERS[1], 11, label(wheel2.getNodeAt(2, circuit)));
        place(canvas, CENTERS[2], 3, label(wheel3.getNodeAt(0, circuit)));
        place(canvas, CENTERS[2] + 10, CENTER_Y, label(wheel3.getNodeAt(1, circuit)));
        place(canvas, CENTERS[2], 11, label(wheel3.getNodeAt(2, circuit)));

        // Cruzamentos: cada um combina as duas entradas visuais do percurso.
        place(canvas, 24, CENTER_Y,
                combinedLabel(circuit.getNodeAt(1), circuit.getNodeAt(9)));
        place(canvas, 48, CENTER_Y,
                combinedLabel(circuit.getNodeAt(3), circuit.getNodeAt(7)));

        for (char[] row : canvas) {
            System.out.println(new String(row).stripTrailing());
        }
        System.out.flush();
    }

    private static void drawCircle(char[][] canvas, int centerX) {
        int radiusX = 11;
        int radiusY = 6;

        for (int y = CENTER_Y - radiusY; y <= CENTER_Y + radiusY; y++) {
            double normalizedY = (double) (y - CENTER_Y) / radiusY;
            int distanceX = (int) Math.round(radiusX * Math.sqrt(Math.max(0, 1 - normalizedY * normalizedY)));
            int left = centerX - distanceX;
            int right = centerX + distanceX;
            canvas[y][left] = y == CENTER_Y - radiusY || y == CENTER_Y + radiusY ? '.' : '(';
            canvas[y][right] = y == CENTER_Y - radiusY || y == CENTER_Y + radiusY ? '.' : ')';
        }

        for (int x = centerX - 6; x <= centerX + 6; x++) {
            canvas[CENTER_Y - radiusY][x] = '-';
            canvas[CENTER_Y + radiusY][x] = '-';
        }
    }

    private static String combinedLabel(Node first, Node second) {
        String firstLabel = label(first);
        String secondLabel = label(second);
        if (firstLabel.isEmpty()) {
            return secondLabel;
        }
        if (secondLabel.isEmpty()) {
            return firstLabel;
        }
        return firstLabel + " & " + secondLabel;
    }

    private static void place(char[][] canvas, int centerX, int row, String text) {
        int start = centerX - text.length() / 2;
        for (int i = 0; i < text.length(); i++) {
            int column = start + i;
            if (column >= 0 && column < WIDTH) {
                canvas[row][column] = text.charAt(i);
            }
        }
    }

    private static String label(Node node) {
        if (node == null) {
            return "";
        }
        if (node.getCar() != null) {
            return node.getCar().getType().getDisplayName();
        }
        return node.getId();
    }
}
