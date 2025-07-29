package algorithms;

import visualizer.SortingPanel;

public class BubbleSort {
    public static void sort(int[] array, SortingPanel panel, int delay) throws InterruptedException {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {

                panel.highlightCompare(j, j + 1);
                panel.setExplanation("Comparing " + array[j] + " and " + array[j + 1]);
                Thread.sleep(panel.getDelay());
                panel.waitForNextStep();

                if (array[j] > array[j + 1]) {
                    panel.setExplanation("Swapping " + array[j] + " and " + array[j + 1]);
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    panel.highlightSwap(j, j + 1);
                    Thread.sleep(panel.getDelay());
                    panel.waitForNextStep();
                } else {
                    panel.setExplanation(array[j] + " is already less than " + array[j + 1] + ", no swap");
                    Thread.sleep(panel.getDelay());
                    panel.waitForNextStep();
                }

                panel.clearHighlights();
            }
        }

        panel.setSorted(true);
        panel.setExplanation("Sorting complete! Array is now sorted.");
    }
}
