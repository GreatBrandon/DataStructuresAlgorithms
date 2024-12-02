import java.util.Arrays;

public class Sort {
    public static int[] bubbleSort(int[] arr) {
        boolean swapped;
        boolean lastSwap = false;
        for (int i = 0; i < arr.length; i++) {
            swapped = false;
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true;
                    lastSwap = true;
                } else {
                    lastSwap = false;
                }
            }
            if (!swapped) {
                break; // exit bubble sort early if no swaps were made on the last pass
            } else if (!lastSwap) {
                i++; // skip next iteration of last 2 numbers were not swapped
            }
        }
        return arr;
    }
    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int smallestIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[smallestIndex] > arr[j]) {
                    smallestIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[smallestIndex];
            arr[smallestIndex] = temp;
        }
        return arr;
    }
    public static int[] insertionSort(int[] arr) {
        return null;
    }
    public static int[] radixSort(int[] arr) {
        return null;
    }
    public static int[] quickSort(int[] arr) {
        return null;
    }
}
