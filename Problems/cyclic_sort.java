import java.util.*;

class CyclicSort {

  public static void sort(int[] nums){

    for (int i = 0; i < nums.length; i ++){
      while(nums[i] != i + 1)
        swap(nums, i, nums[i] - 1);
    }

  }
  public static void sortSolution(int[] nums) {
      int i = 0;
      while (i < nums.length) {
        int j = nums[i] - 1;
        if (nums[i] != nums[j])
            swap(nums, i, j);
        else
            i++;
      }
  }
  
  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    int[] arr = new int[] { 3, 1, 5, 4, 2 };
    CyclicSort.sort(arr);
    for (int num : arr)
      System.out.print(num + " ");
    System.out.println();

    arr = new int[] { 2, 6, 4, 3, 1, 5 };
    CyclicSort.sort(arr);
    for (int num : arr)
      System.out.print(num + " ");
    System.out.println();

    arr = new int[] { 1, 5, 6, 4, 3, 2 };
    CyclicSort.sort(arr);
    for (int num : arr)
      System.out.print(num + " ");
    System.out.println();
  }
}

class MissingNumber {

  public static int findMissingNumber(int[] nums) {
    int total = 0;
    for(int i = 0; i <= nums.length; i ++){
      total += i;
    }
    for(int num : nums){
      total -= num;
    }
    return total;

  }
  public static int findMissingNumberSolution(int[] nums) {
    int i = 0;
    while (i < nums.length) {
      if (nums[i] < nums.length && nums[i] != nums[nums[i]])
        swap(nums, i, nums[i]);
      else
        i++;
    }

    // find the first number missing from its index, that will be our required number
    for (i = 0; i < nums.length; i++)
      if (nums[i] != i)
        return i;

    return nums.length;
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    System.out.println(MissingNumber.findMissingNumber(new int[] { 4, 0, 3, 1 }));
    System.out.println(MissingNumber.findMissingNumber(
                                         new int[] { 8, 3, 5, 2, 4, 6, 0, 1 }));
  }
}

class AllMissingNumbers {

  public static List<Integer> findNumbers(int[] nums) {
    List<Integer> missing = new ArrayList<>();
    for(int i = 0; i < nums.length; i ++){
      while(nums[i] != i + 1){
        printList(nums);
        if(nums[i] == nums[nums[i] - 1]){
          break;
        } else {
          swap(nums, i, nums[i] - 1);
        }
      }
    }

    for(int i = 0; i < nums.length; i ++ ){
      if (nums[i] != i + 1)
        missing.add(i + 1);
    }
    return missing;
  }

  public static void printList(int[] nums){
    System.out.print("[");
    for (int i = 0; i < nums.length; i ++){
      System.out.print(nums[i]);
      if (i != nums.length - 1)
        System.out.print(", ");
    }
    System.out.println("]");
  }

  public static List<Integer> findNumbersS(int[] nums) {
    int i = 0;
    while (i < nums.length) {
      if (nums[i] != nums[nums[i] - 1])
        swap(nums, i, nums[i] - 1);
      else
        i++;
    }

    List<Integer> missingNumbers = new ArrayList<>();
    for (i = 0; i < nums.length; i++)
      if (nums[i] != i + 1)
        missingNumbers.add(i + 1);

    return missingNumbers;
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    List<Integer> missing = AllMissingNumbers.findNumbers(
                                                  new int[] { 2, 3, 1, 8, 2, 3, 5, 1 });
    System.out.println("Missing numbers: " + missing);

    missing = AllMissingNumbers.findNumbers(new int[] { 2, 4, 1, 2 });
    System.out.println("Missing numbers: " + missing);

    missing = AllMissingNumbers.findNumbers(new int[] { 2, 3, 2, 1 });
    System.out.println("Missing numbers: " + missing);
  }
}