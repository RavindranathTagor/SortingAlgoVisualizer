package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import algorithms.BubbleSort;
import algorithms.SelectionSort;
import algorithms.SortAlgorithm;
import algorithms.InsertionSort;
import algorithms.MergeSort;
import algorithms.QuickSort;

public class SortingPanel extends JPanel {

    private int[] array;
    private int compareIndex1 = -1;
    private int compareIndex2 = -1;
    private int swapIndex1 = -1;
    private int swapIndex2 = -1;
    private boolean isSorted = false;
    private JSlider speedSlider;
    private boolean stepMode = false;
    private boolean waitForStep = false;
    private JLabel explanationLabel;
    private JComboBox<String> algorithmDropdown;
    private String[] algorithms = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort"};


    private final Object stepLock = new Object();


    public SortingPanel() {
        setBackground(new Color(30, 30, 30));
        setLayout(new BorderLayout());

        generateArray();

        // --- Top Panel: User Controls ---
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(30, 30, 30));
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // padding

        JTextField inputField = new JTextField(25);
        JButton useInputButton = styledButton("Use My Input");
        JButton generateButton = styledButton("Generate Random");
        JButton sortButton = styledButton("Sort");
        JButton stepModeButton = styledButton("Step Mode: OFF");
        JButton stepButton = styledButton("Step");

        speedSlider = new JSlider(1, 100, 50);
        speedSlider.setBackground(new Color(30, 30, 30));
        speedSlider.setForeground(Color.WHITE);
        speedSlider.setMajorTickSpacing(25);
        speedSlider.setMinorTickSpacing(5);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        explanationLabel = new JLabel("Welcome to the Bubble Sort Visualizer");
        explanationLabel.setForeground(Color.WHITE);  // text color
        explanationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(explanationLabel, BorderLayout.SOUTH); // Put it at the bottom of the panel



        // Add controls row-by-row
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        controlPanel.add(new JLabel(styledText("Enter numbers:")), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        controlPanel.add(inputField, gbc);

        gbc.gridx = 3; gbc.gridwidth = 1;
        controlPanel.add(useInputButton, gbc);

        gbc.gridx = 0; gbc.gridy++;
        controlPanel.add(generateButton, gbc);
        gbc.gridx = 1;
        controlPanel.add(sortButton, gbc);
        gbc.gridx = 2;
        controlPanel.add(stepModeButton, gbc);
        gbc.gridx = 3;
        controlPanel.add(stepButton, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 4;
        controlPanel.add(new JLabel(styledText("Speed Control:")), gbc);
        gbc.gridy++;
        controlPanel.add(speedSlider, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        controlPanel.add(new JLabel(styledText("Algorithm:")), gbc);
        gbc.gridx = 2; gbc.gridwidth = 2;
        algorithmDropdown = new JComboBox<>(algorithms);
        algorithmDropdown.setBackground(new Color(30, 30, 30));
        algorithmDropdown.setForeground(Color.WHITE);
        controlPanel.add(algorithmDropdown, gbc);

        add(controlPanel, BorderLayout.NORTH);

        // --- Button Actions ---
        stepModeButton.addActionListener(e -> {
            stepMode = !stepMode;
            stepModeButton.setText("Step Mode: " + (stepMode ? "ON" : "OFF"));
        });

        stepButton.addActionListener(e -> {
            synchronized (stepLock) {
                waitForStep = false;
                stepLock.notify();
            }
        });

        useInputButton.addActionListener(e -> {
            String inputText = inputField.getText();
            String[] parts = inputText.split(",");
            int[] customArray = new int[parts.length];
            try {
                for (int i = 0; i < parts.length; i++) {
                    customArray[i] = Integer.parseInt(parts[i].trim());
                }
                array = customArray;
                isSorted = false;
                repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid comma-separated numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        generateButton.addActionListener(e -> {
            generateArray();
            isSorted = false;
            repaint();
        });

        sortButton.addActionListener(e -> {
            new Thread(() -> {
                try {
                    String selected = (String) algorithmDropdown.getSelectedItem();
                    SortAlgorithm sorter;
                    if ("Bubble Sort".equals(selected)) {
                        sorter = (array, panel, delay) -> BubbleSort.sort(array, panel, delay);
                    } else if ("Selection Sort".equals(selected)) {
                        sorter = new SelectionSort();
                    } else if ("Insertion Sort".equals(selected)) {
                        sorter = new InsertionSort();
                    } else if ("Merge Sort".equals(selected)) {
                        sorter = new MergeSort();
                    } else if ("Quick Sort".equals(selected)) {
                        sorter = new QuickSort();
                    } else {
                        setExplanation("Unknown algorithm selected.");
                        return;
                    }
                    sorter.sort(array, this, getDelay());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }).start();
        });
    }

    private void generateArray() {
        Random rand = new Random();
        array = new int[80];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(400) + 50;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(30, 30, 30));

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / array.length;

        for (int i = 0; i < array.length; i++) {
            if (i == compareIndex1 || i == compareIndex2) {
                g2.setColor(Color.YELLOW);
            } else if (i == swapIndex1 || i == swapIndex2) {
                g2.setColor(Color.RED);
            } else if (isSorted) {
                g2.setColor(new Color(144, 238, 144)); // light green
            } else {
                g2.setColor(new Color(0, 255, 255)); // cyan
            }

            int x = i * barWidth;
            int y = height - array[i];
            g2.fillRoundRect(x, y, barWidth - 2, array[i], 5, 5);
        }
    }

    public int getDelay() {
        return 101 - speedSlider.getValue();
    }

    public int[] getArray() {
        return array;
    }

    public void highlightCompare(int i, int j) {
        compareIndex1 = i;
        compareIndex2 = j;
        swapIndex1 = swapIndex2 = -1;
        repaint();
    }

    public void highlightSwap(int i, int j) {
        swapIndex1 = i;
        swapIndex2 = j;
        compareIndex1 = compareIndex2 = -1;
        repaint();
    }

    public void clearHighlights() {
        compareIndex1 = compareIndex2 = swapIndex1 = swapIndex2 = -1;
        repaint();
    }

    public void setSorted(boolean sorted) {
        isSorted = sorted;
        repaint();
    }

    public void waitForNextStep() {
        if (stepMode) {
            waitForStep = true;
            synchronized (stepLock) {
                while (waitForStep) {
                    try {
                        stepLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void setExplanation(String explanation) {
        explanationLabel.setText(explanation);
    }


    private JButton styledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(50, 50, 50));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        return button;
    }

    private String styledText(String label) {
        return "<html><font color='white' face='SansSerif'>" + label + "</font></html>";
    }
}
