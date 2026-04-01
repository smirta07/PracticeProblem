import java.util.*;

public class P6 {

    // Linear search for exact threshold
    public static int linearSearch(int[] risks, int target) {
        int comparisons = 0;
        for (int i = 0; i < risks.length; i++) {
            comparisons++;
            if (risks[i] == target) {
                System.out.println("Linear search comparisons: " + comparisons);
                return i;
            }
        }
        System.out.println("Linear search comparisons: " + comparisons);
        return -1;
    }

    // Binary search for floor (largest <= target)
    public static int binaryFloor(int[] risks, int target) {
        int low = 0, high = risks.length - 1;
        int floorIndex = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (risks[mid] == target) {
                System.out.println("Binary search comparisons (floor): " + comparisons);
                return mid;
            } else if (risks[mid] < target) {
                floorIndex = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary search comparisons (floor): " + comparisons);
        return floorIndex;
    }

    // Binary search for ceiling (smallest >= target)
    public static int binaryCeiling(int[] risks, int target) {
        int low = 0, high = risks.length - 1;
        int ceilingIndex = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (risks[mid] == target) {
                System.out.println("Binary search comparisons (ceiling): " + comparisons);
                return mid;
            } else if (risks[mid] > target) {
                ceilingIndex = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println("Binary search comparisons (ceiling): " + comparisons);
        return ceilingIndex;
    }

    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100}; // sorted risk bands
        int threshold = 30;

        System.out.println("Sorted risks: " + Arrays.toString(risks));

        // Linear search
        int linearIndex = linearSearch(risks, threshold);
        if (linearIndex == -1) {
            System.out.println("Linear: threshold=" + threshold + " → not found");
        } else {
            System.out.println("Linear: threshold found at index " + linearIndex);
        }

        // Binary floor
        int floorIndex = binaryFloor(risks, threshold);
        if (floorIndex != -1)
            System.out.println("Binary floor(" + threshold + "): " + risks[floorIndex]);
        else
            System.out.println("Binary floor(" + threshold + "): none");

        // Binary ceiling
        int ceilingIndex = binaryCeiling(risks, threshold);
        if (ceilingIndex != -1)
            System.out.println("Binary ceiling(" + threshold + "): " + risks[ceilingIndex]);
        else
            System.out.println("Binary ceiling(" + threshold + "): none");
    }
}