package pl.coderstrust;

import java.util.Arrays;

public class BubbleSort {
  public static void main(String[] args) {
    Integer[] numbers = {18, 9, 1, 3, 12, 17};
    System.out.println("numbers in array");
    for (int i = 0; i < numbers.length; i++) {
      System.out.print(numbers[i] + " ");
    }
    System.out.println();
    System.out.println("***************************************************");
    Integer[] results = bubblesort(numbers);
    System.out.println("results after BubbleSort : ");
    System.out.println(Arrays.toString(results));
  }

  public static Integer[] bubblesort(Integer[] numToSort) {
    Integer temp;
    Integer change;
    do {
      change = 0;
      for (int i = 0; i < numToSort.length - 1; i++) {
        if (numToSort[i] > numToSort[i + 1]) {
          change++;
          temp = numToSort[i];
          numToSort[i] = numToSort[i + 1];
          numToSort[i + 1] = temp;
        }
      }
    } while (change != 0);
    return numToSort;
  }
}
