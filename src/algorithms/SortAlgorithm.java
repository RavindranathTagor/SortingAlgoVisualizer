package algorithms;

import visualizer.SortingPanel;

public interface SortAlgorithm {
    void sort(int[] array, SortingPanel panel, int delay) throws InterruptedException;
}
