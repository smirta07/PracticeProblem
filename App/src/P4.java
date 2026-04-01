import java.util.*;

class Asset {
    String symbol;
    double returnRate; // in percent, e.g., 12.5
    double volatility; // lower is less risky

    public Asset(String symbol, double returnRate, double volatility) {
        this.symbol = symbol;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return symbol + ":" + returnRate + "%";
    }
}

public class P4 {

    // Merge Sort (ascending by returnRate, stable)
    public static void mergeSort(Asset[] assets) {
        if (assets.length < 2) return;
        mergeSortHelper(assets, 0, assets.length - 1);
    }

    private static void mergeSortHelper(Asset[] assets, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortHelper(assets, left, mid);
        mergeSortHelper(assets, mid + 1, right);
        merge(assets, left, mid, right);
    }

    private static void merge(Asset[] assets, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        System.arraycopy(assets, left, L, 0, n1);
        System.arraycopy(assets, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                assets[k++] = L[i++];
            } else {
                assets[k++] = R[j++];
            }
        }
        while (i < n1) assets[k++] = L[i++];
        while (j < n2) assets[k++] = R[j++];
    }

    // Quick Sort (descending by returnRate, then ascending volatility)
    public static void quickSort(Asset[] assets) {
        quickSortHelper(assets, 0, assets.length - 1);
    }

    private static void quickSortHelper(Asset[] assets, int low, int high) {
        if (low < high) {
            int pivotIndex = medianOfThree(assets, low, high); // better pivot
            Asset pivot = assets[pivotIndex];
            swap(assets, pivotIndex, high); // move pivot to end

            int partitionIndex = partition(assets, low, high, pivot);
            quickSortHelper(assets, low, partitionIndex - 1);
            quickSortHelper(assets, partitionIndex + 1, high);
        }
    }

    private static int partition(Asset[] assets, int low, int high, Asset pivot) {
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (compareDesc(assets[j], pivot) > 0) {
                i++;
                swap(assets, i, j);
            }
        }
        swap(assets, i + 1, high);
        return i + 1;
    }

    // Descending returnRate, tie-breaker: ascending volatility
    private static int compareDesc(Asset a1, Asset a2) {
        if (a1.returnRate != a2.returnRate) {
            return Double.compare(a1.returnRate, a2.returnRate);
        }
        return Double.compare(a2.volatility, a1.volatility) * -1;
    }

    private static void swap(Asset[] assets, int i, int j) {
        Asset temp = assets[i];
        assets[i] = assets[j];
        assets[j] = temp;
    }

    // Median-of-3 pivot selection
    private static int medianOfThree(Asset[] assets, int low, int high) {
        int mid = low + (high - low) / 2;
        Asset a = assets[low], b = assets[mid], c = assets[high];

        if ((a.returnRate >= b.returnRate && a.returnRate <= c.returnRate) ||
                (a.returnRate <= b.returnRate && a.returnRate >= c.returnRate))
            return low;
        else if ((b.returnRate >= a.returnRate && b.returnRate <= c.returnRate) ||
                (b.returnRate <= a.returnRate && b.returnRate >= c.returnRate))
            return mid;
        else
            return high;
    }

    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12.0, 1.2),
                new Asset("TSLA", 8.0, 2.5),
                new Asset("GOOG", 15.0, 1.8)
        };

        Asset[] mergeSortAssets = Arrays.copyOf(assets, assets.length);
        mergeSort(mergeSortAssets);
        System.out.println("MergeSort Ascending: " + Arrays.toString(mergeSortAssets));

        Asset[] quickSortAssets = Arrays.copyOf(assets, assets.length);
        quickSort(quickSortAssets);
        System.out.println("QuickSort Descending: " + Arrays.toString(quickSortAssets));
    }
}