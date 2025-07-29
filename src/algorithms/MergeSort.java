package algorithms;

import visualizer.SortingPanel;

public class MergeSort implements SortAlgorithm {
    @Override
    public void sort(int[] array, SortingPanel panel, int delay) throws InterruptedException {
        mergeSort(array, 0, array.length - 1, panel);
        panel.setSorted(true);
        panel.setExplanation("Sorting complete! Array is now sorted.");
    }

    private void mergeSort(int[] array, int left, int right, SortingPanel panel) throws InterruptedException {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid, panel);
            mergeSort(array, mid + 1, right, panel);
            merge(array, left, mid, right, panel);
        }
    }

    private void merge(int[] array, int left, int mid, int right, SortingPanel panel) throws InterruptedException {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; ++i)
            L[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[mid + 1 + j];
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            panel.highlightCompare(left + i, mid + 1 + j);
            panel.setExplanation("Comparing " + L[i] + " and " + R[j]);
            Thread.sleep(panel.getDelay());
            panel.waitForNextStep();
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            panel.clearHighlights();
            k++;
        }
        while (i < n1) {
            array[k++] = L[i++];
        }
        while (j < n2) {
            array[k++] = R[j++];
        }
    }
}

