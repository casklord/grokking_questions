import java.util.*;

class PairWithTargetSum {

    public static int[] search(int[] arr, int targetSum) {
        int start = 0; int end = arr.length - 1;
        while(start < end){
            if (arr[start] + arr[end] < targetSum) start ++;
            if (arr[start] + arr[end] > targetSum) end --;
            if (arr[start] + arr[end] == targetSum) return new int[] { start, end};

        }
        return new int[] { -1, -1};
    }
    public static int[] searchSolution(int[] arr, int targetSum) {
      int left = 0, right = arr.length - 1;
      while (left < right) {
        int currentSum = arr[left] + arr[right];
        if (currentSum == targetSum)
          return new int[] { left, right }; // found the pair
  
        if (targetSum > currentSum)
          left++; // we need a pair with a bigger sum
        else
          right--; // we need a pair with a smaller sum
      }
      return new int[] { -1, -1 };
    }
  
    public static void main(String[] args) {
      int[] result = PairWithTargetSum.search(new int[] { 1, 2, 3, 4, 6 }, 6);
      System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
      result = PairWithTargetSum.search(new int[] { 2, 5, 9, 11 }, 11);
      System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
    }
  }
  
class RemoveDuplicates {

  public static int remove(int[] arr) {
    int removals = 0;
    int prev = arr[0];
    for(int i = 1; i < arr.length; i ++){

      if (arr[i] == prev) {
        removals ++;
      } else {
        prev = arr[i];
      }


    }
    return removals;
  
  }
  
  public static int removeSolution(int[] arr) {
      int nextNonDuplicate = 1; // index of the next non-duplicate element
      for (int i = 0; i < arr.length; i++) {
      if (arr[nextNonDuplicate - 1] != arr[i]) {
          arr[nextNonDuplicate] = arr[i];
          nextNonDuplicate++;
      }
      }

      return nextNonDuplicate;
  }

  public static void main(String[] args) {
      int[] arr = new int[] { 2, 3, 3, 3, 6, 9, 9 };
      System.out.println(RemoveDuplicates.remove(arr) + " removals");

      arr = new int[] { 2, 2, 2, 11 };
      System.out.println(RemoveDuplicates.remove(arr) + " removals");

      arr = new int[] { 2, 2, 2, 3, 3, 4, 4, 5, 5, 5, 6, 8, 11 };
      System.out.println(RemoveDuplicates.remove(arr) + " removals");
  }
}

class SortedArraySquares {

  public static int[] makeSquares(int[] arr) {
    int n = arr.length;
    int[] squares = new int[n];
    int squareIndex = n - 1;
    int start = 0; int end = arr.length - 1;
    while(start < end){
      int startSquare = arr[start] * arr[start];
      int endSquare = arr[end] * arr[end];
      if (startSquare > endSquare){
        squares[squareIndex] = startSquare;
        start ++;
      } else {
        squares[squareIndex] = endSquare;
        end --;
      }
      squareIndex --;
    }
  
    return squares;
  }

  public static int[] makeSquaresSolution(int[] arr) {
    int n = arr.length;
    int[] squares = new int[n];
    int highestSquareIdx = n - 1;
    int left = 0, right = arr.length - 1;
    while (left <= right) {
      int leftSquare = arr[left] * arr[left];
      int rightSquare = arr[right] * arr[right];
      if (leftSquare > rightSquare) {
        squares[highestSquareIdx--] = leftSquare;
        left++;
      } else {
        squares[highestSquareIdx--] = rightSquare;
        right--;
      }
    }
    return squares;
  }

  public static void main(String[] args) {

    int[] result = SortedArraySquares.makeSquares(new int[] { -2, -1, 0, 2, 3 });
    for (int num : result)
      System.out.print(num + " ");
    System.out.println();

    result = SortedArraySquares.makeSquares(new int[] { -3, -1, 0, 1, 2 });
    for (int num : result)
      System.out.print(num + " ");
    System.out.println();
  }
}

class TripletWithSmallerSum {

  public static int searchTriplets(int[] arr, int target) {
    Arrays.sort(arr);
    int total = 0;
    for(int i = 0; i < arr.length - 2; i ++){
      int targetDiff = arr[i];
      total += searchPair(arr, target - arr[i], i);
    }
    return total;
  }

  public static int searchTripletsSolution(int[] arr, int target) {
    Arrays.sort(arr);
    int count = 0;
    for (int i = 0; i < arr.length - 2; i++) {
      count += searchPair(arr, target - arr[i], i);
    }
    return count;
  }

  private static int searchPair(int[] arr, int targetSum, int fixed) {
    int total = 0;
    int start = fixed + 1; int end = arr.length - 1;
    while(start < end){
      int sum = arr[start] + arr[end];
      if (sum < targetSum){
        total ++;
        start ++;
      } else {
        end --;
      }
    }
    return total;
  }
  private static int searchPairSolution(int[] arr, int targetSum, int first) {
    int count = 0;
    int left = first + 1, right = arr.length - 1;
    while (left < right) {
      if (arr[left] + arr[right] < targetSum) { // found the triplet
        // since arr[right] >= arr[left], therefore, we can replace arr[right] by any 
        // number between left and right to get a sum less than the target sum
        count += right - left;
        left++;
      } else {
        right--; // we need a pair with a smaller sum
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] test1 = { -1, 0, 2, 3, 1, 4, 2, 0, -1, 3, 2, 0, 0 };
    System.out.println(
        TripletWithSmallerSum.searchTriplets(test1, 3));
    System.out.println(
        TripletWithSmallerSum.searchTriplets(new int[] { -1, 4, 2, 1, 3 }, 5));
      System.out.println(
          TripletWithSmallerSum.searchTripletsSolution(test1, 3));
      System.out.println(
          TripletWithSmallerSum.searchTripletsSolution(new int[] { -1, 4, 2, 1, 3 }, 5));
  }
}

class SubarrayProductLessThanK {

  public static List<List<Integer>> findSubarrays(int[] arr, int target) {
    List<List<Integer>> subArrays = new ArrayList<>();
    int windowStart = 0; int windowEnd = 0;

    while(windowEnd < arr.length) {
      
      double product = 1;
      List<Integer> current = new ArrayList<>();
      for(int i = windowStart; i <= windowEnd; i ++){
        product *= arr[i];
        current.add(arr[i]);
      }
      if (product < target && product != 1){
        for (int i = 0; i < current.size(); i ++){
          subArrays.add(current.subList(i, current.size()));
        }
        windowEnd ++;
      } else {
        windowStart ++;
      }

    }
    return subArrays;
  }
  public static List<List<Integer>> findSubarraysSorted(int[] arr, int target) {
    List<List<Integer>> result = new ArrayList<>();
    double product = 1;
    int left = 0;
    for (int right = 0; right < arr.length; right++) {
      product *= arr[right];
      while (product >= target && left < arr.length)
        product /= arr[left++];
      // since the product of all numbers from left to right is less than the target 
      // therefore, all subarrays from left to right will have a product less than the 
      // target too; to avoid duplicates, we will start with a subarray containing only 
      // arr[right] and then extend it
      List<Integer> tempList = new LinkedList<>();
      for (int i = right; i >= left; i--) {
        tempList.add(0, arr[i]);
        result.add(new ArrayList<>(tempList));
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(
          SubarrayProductLessThanK.findSubarrays(new int[] { 2, 5, 3, 10 }, 30));
    System.out.println(
          SubarrayProductLessThanK.findSubarrays(new int[] { 8, 2, 6, 5 }, 50));
  }
}

class DutchFlag {

  public static void sort(int[] arr) {

    int start = 0; int end = arr.length - 1;

    for (int i = 0; i <= end;){
      if(arr[i] == 0){
        swap(arr, i, start);
        i ++;
        start ++;
      } else if (arr[i] == 2) {
        swap(arr, i, end);
        end --;
      } else {
        i++;
      }
    }
  }

  public static void sortSolution(int[] arr) {
    // all elements < low are 0 and all elements > high are 2
    // all elements from >= low < i are 1
    int low = 0, high = arr.length - 1;
    for (int i = 0; i <= high;) {
      if (arr[i] == 0) {
        swap(arr, i, low);
        // increment 'i' and 'low'
        i++;
        low++;
      } else if (arr[i] == 1) {
        i++;
      } else { // the case for arr[i] == 2
        swap(arr, i, high);
        // decrement 'high' only, after the swap the number at index 'i' could be 0, 1, 
        //  or 2
        high--;
      }
    }
  }
  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  private static void swapSolution(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    int[] arr = new int[] { 1, 0, 2, 1, 0 };
    DutchFlag.sort(arr);
    for (int num : arr)
      System.out.print(num + " ");
    System.out.println();

    arr = new int[] { 2, 2, 0, 1, 2, 0 };
    DutchFlag.sort(arr);
    for (int num : arr)
      System.out.print(num + " ");
  }
}

class BackspaceCompare {
  public static boolean compare(String str1, String str2) {
    int i = str1.length() - 1;
    int j = str2.length() - 1;
    while(true){
      char current1 = str1.charAt(i);
      char current2 = str2.charAt(j);
      while(current1 == '#'){
        i -= 2;
        current1 = str1.charAt(i);
      }
      while(current2 == '#'){
        i -= 2;
        current2 = str2.charAt(i);
      }

    }

  }
  public static boolean compareSolution(String str1, String str2) {
    // use two pointer approach to compare the strings
    int index1 = str1.length() - 1, index2 = str2.length() - 1;
    while (index1 >= 0 || index2 >= 0) {

      int i1 = getNextValidCharIndex(str1, index1);
      int i2 = getNextValidCharIndex(str2, index2);

      if (i1 < 0 && i2 < 0) // reached the end of both the strings
        return true;

      if (i1 < 0 || i2 < 0) // reached the end of one of the strings
        return false;

      if (str1.charAt(i1) != str2.charAt(i2)) // check if the characters are equal
        return false;

      index1 = i1 - 1;
      index2 = i2 - 1;
    }

    return true;
  }

  private static int getNextValidCharIndex(String str, int index) {
    int backspaceCount = 0;
    while (index >= 0) {
      if (str.charAt(index) == '#') // found a backspace character
        backspaceCount++;
      else if (backspaceCount > 0) // a non-backspace character
        backspaceCount--;
      else
        break;

      index--; // skip a backspace or a valid character
    }
    return index;
  }

  public static void main(String[] args) {
    System.out.println(BackspaceCompare.compare("xy#z", "xzz#"));
    System.out.println(BackspaceCompare.compare("xy#z", "xyz#"));
    System.out.println(BackspaceCompare.compare("xp#", "xyz##"));    
    System.out.println(BackspaceCompare.compare("xywrrmp", "xywrrmu#p"));
  }
}
