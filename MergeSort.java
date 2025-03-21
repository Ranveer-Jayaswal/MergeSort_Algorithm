import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MergeSort {
    public static void main(String[] args) {
        int[] sizes = {10000, 100000};

        for (int size : sizes) {
            System.out.println("\nSorting " + size + " random integers:\n");

            ArrayList<Integer> list1 = generateRandomList(size);
            ArrayList<Integer> list2 = new ArrayList<>(list1);
            ArrayList<Integer> list3 = new ArrayList<>(list1);

            long startTime, endTime;    

            startTime = System.currentTimeMillis();
            int insertionIterations = insertionSort(list1);
            endTime = System.currentTimeMillis();
            System.out.println("Insertion Sort - Iterations: " + insertionIterations + ", Time: " + (endTime - startTime) + " ms");

            startTime = System.currentTimeMillis();
            int selectionIterations = selectionSort(list2);
            endTime = System.currentTimeMillis();
            System.out.println("Selection Sort - Iterations: " + selectionIterations + ", Time: " + (endTime - startTime) + " ms");

            startTime = System.currentTimeMillis();
            int mergeIterations = mergeSort(list3, 0, list3.size() - 1);
            endTime = System.currentTimeMillis();
            System.out.println("Merge Sort - Iterations: " + mergeIterations + ", Time: " + (endTime - startTime) + " ms");
        }
    }

    public static ArrayList<Integer> generateRandomList(int size) {
        ArrayList<Integer> list = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt());
        }
        return list;
    }

    public static int insertionSort(ArrayList<Integer> list) {
        int iterations = 0;
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
                iterations++;
            }
            list.set(j + 1, key);
            iterations++;
        }
        return iterations;
    }

    public static int selectionSort(ArrayList<Integer> list) {
        int iterations = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                iterations++;
                if (list.get(j) < list.get(minIndex)) {
                    minIndex = j;
                }
            }
            Collections.swap(list, minIndex, i);
        }
        return iterations;
    }

    public static int mergeSort(ArrayList<Integer> list, int left, int right) {
        int iterations = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;
            iterations += mergeSort(list, left, mid);
            iterations += mergeSort(list, mid + 1, right);
            iterations += merge(list, left, mid, right);
        }
        return iterations;
    }

    public static int merge(ArrayList<Integer> list, int left, int mid, int right) {
        int iterations = 0;
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        ArrayList<Integer> leftPart = new ArrayList<>();
        ArrayList<Integer> rightPart = new ArrayList<>();

        for (int i = 0; i < leftSize; i++) leftPart.add(list.get(left + i));
        for (int i = 0; i < rightSize; i++) rightPart.add(list.get(mid + 1 + i));

        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftPart.get(i) <= rightPart.get(j)) {
                list.set(k, leftPart.get(i));
                i++;
            } else {
                list.set(k, rightPart.get(j));
                j++;
            }
            k++;
            iterations++;
        }

        while (i < leftSize) {
            list.set(k, leftPart.get(i));
            i++;
            k++;
            iterations++;
        }

        while (j < rightSize) {
            list.set(k, rightPart.get(j));
            j++;
            k++;
            iterations++;
        }
        return iterations;
    }
}
