import java.util.*;

public class P5 {

    // Linear search for first occurrence
    public static int linearFirst(String[] logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                System.out.println("Linear first occurrence comparisons: " + comparisons);
                return i;
            }
        }
        System.out.println("Linear first occurrence comparisons: " + comparisons);
        return -1;
    }

    // Linear search for last occurrence
    public static int linearLast(String[] logs, String target) {
        int comparisons = 0;
        int lastIndex = -1;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                lastIndex = i;
            }
        }
        System.out.println("Linear last occurrence comparisons: " + comparisons);
        return lastIndex;
    }

    // Binary search for exact match (first occurrence)
    public static int binarySearchFirst(String[] logs, String target) {
        int low = 0, high = logs.length - 1;
        int comparisons = 0;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            int cmp = logs[mid].compareTo(target);

            if (cmp == 0) {
                result = mid;
                high = mid - 1; // look left for first occurrence
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary search comparisons: " + comparisons);
        return result;
    }

    // Binary search count of target occurrences
    public static int binarySearchCount(String[] logs, String target) {
        int first = binarySearchFirst(logs, target);
        if (first == -1) return 0;

        // Find last occurrence using binary
        int low = first, high = logs.length - 1;
        int last = first;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = logs[mid].compareTo(target);
            if (cmp == 0) {
                last = mid;
                low = mid + 1; // search right
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return last - first + 1;
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};
        Arrays.sort(logs); // Binary search requires sorted logs
        System.out.println("Sorted logs: " + Arrays.toString(logs));

        String target = "accB";

        int firstLinear = linearFirst(logs, target);
        System.out.println("Linear first occurrence of " + target + ": index " + firstLinear);

        int lastLinear = linearLast(logs, target);
        System.out.println("Linear last occurrence of " + target + ": index " + lastLinear);

        int firstBinary = binarySearchFirst(logs, target);
        int countBinary = binarySearchCount(logs, target);
        System.out.println("Binary search first occurrence of " + target + ": index " + firstBinary + ", count=" + countBinary);
    }
}