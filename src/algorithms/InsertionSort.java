package algorithms;

import visualizer.SortingPanel;

public class InsertionSort implements SortAlgorithm {
    @Override
    public void sort(int[] array, SortingPanel panel, int delay) throws InterruptedException {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                panel.highlightCompare(j, j + 1);
                panel.setExplanation("Comparing " + array[j] + " and " + key);
                Thread.sleep(panel.getDelay());
                panel.waitForNextStep();
                array[j + 1] = array[j];
                panel.highlightSwap(j, j + 1);
                Thread.sleep(panel.getDelay());
                panel.waitForNextStep();
                panel.clearHighlights();
                j--;
            }
            array[j + 1] = key;
            panel.clearHighlights();
        }
        panel.setSorted(true);
        panel.setExplanation("Sorting complete! Array is now sorted.");
    }
}

