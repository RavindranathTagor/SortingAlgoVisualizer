package algorithms;

import visualizer.SortingPanel;

public class SelectionSort implements SortAlgorithm {
    @Override
    public void sort(int[] array, SortingPanel panel, int delay) throws InterruptedException {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                panel.highlightCompare(minIdx, j);
                panel.setExplanation("Comparing " + array[minIdx] + " and " + array[j]);
                Thread.sleep(panel.getDelay());
                panel.waitForNextStep();
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
                panel.clearHighlights();
            }
            if (minIdx != i) {
                panel.setExplanation("Swapping " + array[i] + " and " + array[minIdx]);
                int temp = array[minIdx];
                array[minIdx] = array[i];
                array[i] = temp;
                panel.highlightSwap(i, minIdx);
                Thread.sleep(panel.getDelay());
                panel.waitForNextStep();
                panel.clearHighlights();
            }
        }
        panel.setSorted(true);
        panel.setExplanation("Sorting complete! Array is now sorted.");
    }
}

