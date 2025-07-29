package visualizer;

import javax.swing.*;

public class SortingVisualizer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sorting Algorithm Visualizer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null); // Center on screen

            SortingPanel panel = new SortingPanel();
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}
