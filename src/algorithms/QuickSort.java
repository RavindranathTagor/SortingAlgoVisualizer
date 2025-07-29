package algorithms;

import visualizer.SortingPanel;

public class QuickSort implements SortAlgorithm {
    @Override
    public void sort(int[] array, SortingPanel panel, int delay) throws InterruptedException {
        quickSort(array, 0, array.length - 1, panel);
        panel.setSorted(true);
        panel.setExplanation("Sorting complete! Array is now sorted.");
    }

    private void quickSort(int[] array, int low, int high, SortingPanel panel) throws InterruptedException {
        if (low < high) {
            int pi = partition(array, low, high, panel);
            quickSort(array, low, pi - 1, panel);
            quickSort(array, pi + 1, high, panel);
        }
    }

    private int partition(int[] array, int low, int high, SortingPanel panel) throws InterruptedException {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            panel.highlightCompare(j, high);
            panel.setExplanation("Comparing " + array[j] + " and pivot " + pivot);
            Thread.sleep(panel.getDelay());
            panel.waitForNextStep();
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                panel.highlightSwap(i, j);
                Thread.sleep(panel.getDelay());
                panel.waitForNextStep();
            }
            panel.clearHighlights();
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        panel.highlightSwap(i + 1, high);
        Thread.sleep(panel.getDelay());
        panel.waitForNextStep();
        panel.clearHighlights();
        return i + 1;
    }
}

