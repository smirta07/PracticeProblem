import java.util.*;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class P3 {

    // Merge Sort (ascending)
    public static void mergeSort(Trade[] trades) {
        if (trades.length < 2) return;
        mergeSortHelper(trades, 0, trades.length - 1);
    }

    private static void mergeSortHelper(Trade[] trades, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortHelper(trades, left, mid);
        mergeSortHelper(trades, mid + 1, right);
        merge(trades, left, mid, right);
    }

    private static void merge(Trade[] trades, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        System.arraycopy(trades, left, L, 0, n1);
        System.arraycopy(trades, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) {
                trades[k++] = L[i++];
            } else {
                trades[k++] = R[j++];
            }
        }

        while (i < n1) trades[k++] = L[i++];
        while (j < n2) trades[k++] = R[j++];
    }

    // Quick Sort (descending)
    public static void quickSort(Trade[] trades) {
        quickSortHelper(trades, 0, trades.length - 1);
    }

    private static void quickSortHelper(Trade[] trades, int low, int high) {
        if (low < high) {
            int p = lomutoPartition(trades, low, high);
            quickSortHelper(trades, low, p - 1);
            quickSortHelper(trades, p + 1, high);
        }
    }

    private static int lomutoPartition(Trade[] trades, int low, int high) {
        Trade pivot = trades[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (trades[j].volume > pivot.volume) { // Descending
                i++;
                Trade temp = trades[i];
                trades[i] = trades[j];
                trades[j] = temp;
            }
        }

        Trade temp = trades[i + 1];
        trades[i + 1] = trades[high];
        trades[high] = temp;
        return i + 1;
    }

    // Merge two sorted Trade arrays (ascending)
    public static Trade[] mergeSortedLists(Trade[] list1, Trade[] list2) {
        int n1 = list1.length, n2 = list2.length;
        Trade[] merged = new Trade[n1 + n2];
        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            if (list1[i].volume <= list2[j].volume) {
                merged[k++] = list1[i++];
            } else {
                merged[k++] = list2[j++];
            }
        }

        while (i < n1) merged[k++] = list1[i++];
        while (j < n2) merged[k++] = list2[j++];

        return merged;
    }

    // Compute total volume
    public static int totalVolume(Trade[] trades) {
        int sum = 0;
        for (Trade t : trades) sum += t.volume;
        return sum;
    }

    public static void main(String[] args) {

        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        // Merge Sort ascending
        Trade[] mergeSortTrades = Arrays.copyOf(trades, trades.length);
        mergeSort(mergeSortTrades);
        System.out.println("MergeSort Ascending: " + Arrays.toString(mergeSortTrades));

        // Quick Sort descending
        Trade[] quickSortTrades = Arrays.copyOf(trades, trades.length);
        quickSort(quickSortTrades);
        System.out.println("QuickSort Descending: " + Arrays.toString(quickSortTrades));

        // Merge morning + afternoon trades
        Trade[] morningTrades = {new Trade("trade1", 100), new Trade("trade2", 300)};
        Trade[] afternoonTrades = {new Trade("trade3", 500)};
        Trade[] mergedTrades = mergeSortedLists(morningTrades, afternoonTrades);
        System.out.println("Merged Trades: " + Arrays.toString(mergedTrades));

        System.out.println("Total Volume: " + totalVolume(mergedTrades));
    }
}