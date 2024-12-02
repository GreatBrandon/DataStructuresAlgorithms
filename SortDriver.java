import java.util.Arrays;

public class SortDriver {
    public static void main(String[] args) {
        int[] arr = {18,17,13,26,10,14,24};

        System.out.println(Arrays.toString(Sort.bubbleSort(arr)));
        System.out.println(Arrays.toString(Sort.selectionSort(arr)));
    }
}
