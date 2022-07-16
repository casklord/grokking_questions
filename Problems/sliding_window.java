import java.util.*;

class AverageOfSubarrayOfSizeK {
    public static double[] findAverages(int K, int[] numbers){
        double[] averages = new double[numbers.length - K + 1];
        double currentSum = 0;
        for(int i = 0; i < numbers.length; i ++){
            currentSum += numbers[i];
            if (i >= K - 1){
                System.out.println(currentSum);
                averages[i - K + 1] = currentSum/K;
                currentSum -= numbers[i - K + 1];
            }
        }
        return averages;
    }

    public static double[] findAveragesSolution(int K, int[] arr) {
        double[] result = new double[arr.length - K + 1];
        double windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
          windowSum += arr[windowEnd]; // add the next element
          // slide the window, we don't need to slide if we've not hit the required 
          // window size of 'k'
          if (windowEnd >= K - 1) {
            result[windowStart] = windowSum / K; // calculate the average
            windowSum -= arr[windowStart]; // subtract the element going out
            windowStart++; // slide the window ahead
          }
        }
    
        return result;
      }

    public static void main(String[] args) {
        double[] result = AverageOfSubarrayOfSizeK.findAverages(5, new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 });
        System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
    }
}

class MinSizeSubArraySum {
  public static int findMinSubArray(int S, int[] arr) {
    int windowSum = 0, minLength = Integer.MAX_VALUE;
    int windowStart = 0;
    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
      windowSum += arr[windowEnd]; // add the next element
      // shrink the window as small as possible until the 'windowSum' is smaller than 'S'
      while (windowSum >= S) {
        minLength = Math.min(minLength, windowEnd - windowStart + 1);
        windowSum -= arr[windowStart]; // subtract the element going out
        windowStart++; // slide the window ahead
      }
    }

    return minLength == Integer.MAX_VALUE ? 0 : minLength;
  }

  public static int findMinSubArraySolution(int S, int[] arr) {
    int windowSum = 0, minLength = Integer.MAX_VALUE;
    int windowStart = 0;
    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
      windowSum += arr[windowEnd]; // add the next element
      // shrink the window as small as possible until the 'windowSum' is smaller than 'S'
      while (windowSum >= S) {
        minLength = Math.min(minLength, windowEnd - windowStart + 1);
        windowSum -= arr[windowStart]; // subtract the element going out
        windowStart++; // slide the window ahead
      }
    }

    return minLength == Integer.MAX_VALUE ? 0 : minLength;
  }

    public static void main(String[] args) {
        int result = MinSizeSubArraySum.findMinSubArray(7, new int[] { 2, 1, 5, 2, 3, 2 });
        System.out.println("Smallest subarray length: " + result);
        result = MinSizeSubArraySum.findMinSubArray(7, new int[] { 2, 1, 5, 2, 8 });
        System.out.println("Smallest subarray length: " + result);
        result = MinSizeSubArraySum.findMinSubArray(8, new int[] { 3, 4, 1, 1, 6 });
        System.out.println("Smallest subarray length: " + result);
    }
}

class LongestSubstringKDistinct {

    public static int findLength(String str, int k) {
        int longest = 0;
        int windowStart = 0;
        Map<Character, Integer> freqs = new HashMap<>();
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd ++){
            char end = str.charAt(windowEnd);
            freqs.put(end, freqs.getOrDefault(end, 0) + 1);
            while (freqs.size() > k){
                char start = str.charAt(windowStart);
                freqs.put(start, freqs.get(start) - 1);
                if(freqs.get(start) == 0){
                    freqs.remove(start);

                }
                windowStart ++;
            }
            longest = Math.max(longest, windowEnd - windowStart + 1);
        }
        return longest;
    }
    public static int findLengthSolution(String str, int k) {

  
      int windowStart = 0, maxLength = 0;
      Map<Character, Integer> charFrequencyMap = new HashMap<>();
      // in the following loop we'll try to extend the range [windowStart, windowEnd]
      for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
        char rightChar = str.charAt(windowEnd);
        charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);
        // shrink the sliding window, until we are left with 'k' distinct characters in 
        // the frequency map
        while (charFrequencyMap.size() > k) {
          char leftChar = str.charAt(windowStart);
          charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
          if (charFrequencyMap.get(leftChar) == 0) {
            charFrequencyMap.remove(leftChar);
          }
          windowStart++; // shrink the window
        }
        // remember the maximum length so far
        maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
      }
  
      return maxLength;
    }
  
    public static void main(String[] args) {
      System.out.println("Length of the longest substring: " 
         + LongestSubstringKDistinct.findLength("araaci", 2));
      System.out.println("Length of the longest substring: " 
         + LongestSubstringKDistinct.findLength("araaci", 1));
      System.out.println("Length of the longest substring: " 
         + LongestSubstringKDistinct.findLength("cbbebi", 3));
    }
  }

class NoRepeatSubstring {
  
  public static int findLength(String str) {
    int windowStart = 0;
    int maxLength = 0;
    Map<Character, Integer> letters = new HashMap<>();

    for(int windowEnd = 0; windowEnd < str.length(); windowEnd ++){
      char end = str.charAt(windowEnd);
      // case when adding end letter causes there to be duplicates
      // increment window start until there is not
      while (letters.containsKey(end)){
        char start = str.charAt(windowStart);
        letters.put(start, letters.get(start) -1);
        if (letters.get(start) == 0){
          letters.remove(start);
        }
        windowStart ++;
      }
    maxLength = Math.max(maxLength, windowEnd - windowStart + 1);    
      letters.put(end, 1);
    }
    return maxLength;
  }

    public static int findLengthSolution(String str) {
      int windowStart = 0, maxLength = 0;
      Map<Character, Integer> charIndexMap = new HashMap<>();
      // try to extend the range [windowStart, windowEnd]
      for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
        char rightChar = str.charAt(windowEnd);
        // if the map already contains the 'rightChar', shrink the window from the 
        // beginning so that we have only one occurrence of 'rightChar'
        if (charIndexMap.containsKey(rightChar)) {
          // this is tricky; in the current window, we will not have any 'rightChar' after 
          // its previous index and if 'windowStart' is already ahead of the last index of
          // 'rightChar', we'll keep 'windowStart'
          windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
        }
        charIndexMap.put(rightChar, windowEnd); // insert the 'rightChar' into the map
        // remember the maximum length so far
        maxLength = Math.max(maxLength, windowEnd - windowStart + 1); 
      }
  
      return maxLength;
    }
  
    public static void main(String[] args) {
      System.out.println("Length of the longest substring: " 
                            + NoRepeatSubstring.findLength("kajhsdfklajshdkjflahlsjkdhflkajshdlfjkahsdkljfhaklsdjfhasdlfjahsd"));
      System.out.println("Length of the longest substring: " 
                            + NoRepeatSubstring.findLength("afiuflnrliauhzaweuifhaldkvcpaefakjdspaweaklsdfkajsdf"));
      System.out.println("Length of the longest substring: " 
                            + NoRepeatSubstring.findLength("qpoweipqweoiruqpoirtyqbwepcijabpiwecjbacwpjefcbapwijecbfaipweu"));
      System.out.println("Length of the longest substring: " 
                            + NoRepeatSubstring.findLengthSolution("kajhsdfklajshdkjflahlsjkdhflkajshdlfjkahsdkljfhaklsdjfhasdlfjahsd"));
      System.out.println("Length of the longest substring: " 
                            + NoRepeatSubstring.findLengthSolution("afiuflnrliauhzaweuifhaldkvcpaefakjdspaweaklsdfkajsdf"));
      System.out.println("Length of the longest substring: " 
                            + NoRepeatSubstring.findLengthSolution("qpoweipqweoiruqpoirtyqbwepcijabpiwecjbacwpjefcbapwijecbfaipweu"));
    }
  }
class StringAnagrams {
  public static List<Integer> findStringAnagrams(String str, String pattern) {

    List<Integer> perms = new ArrayList<>();
    int windowStart = 0;
    HashMap<Character, Integer> letters = new HashMap<>();
    int matched = 0;
    for (char current : pattern.toCharArray())
      letters.put(current, letters.getOrDefault(current, 0) + 1);
    

    for(int windowEnd = 0; windowEnd < str.length(); windowEnd ++){

      char end = str.charAt(windowEnd);
      if (letters.containsKey(end)) {
        letters.put(end, letters.get(end) - 1);
        if (letters.get(end) == 0)
          matched ++;
      }

      if (windowEnd >= pattern.length()){
        char start = str.charAt(windowStart);
        if (letters.containsKey(start)){
          if (letters.get(start) == 0) matched --;
          letters.put(start, letters.get(start) + 1);
        }
        windowStart ++;
      }

      if (matched == letters.size()){
        perms.add(windowStart);
      }
    }

    return perms;
  }

  public static List<Integer> findStringAnagramsSolution(String str, String pattern) {
    int windowStart = 0, matched = 0;
    Map<Character, Integer> charFrequencyMap = new HashMap<>();
    for (char chr : pattern.toCharArray())
      charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

    List<Integer> resultIndices = new ArrayList<Integer>();
    // our goal is to match all the characters from the map with the current window
    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
      char rightChar = str.charAt(windowEnd);
      // decrement the frequency of the matched character
      if (charFrequencyMap.containsKey(rightChar)) {
        charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
        if (charFrequencyMap.get(rightChar) == 0)
          matched++;
      }

      if (matched == charFrequencyMap.size()) // have we found an anagram?
        resultIndices.add(windowStart);

      if (windowEnd >= pattern.length() - 1) { // shrink the window
        char leftChar = str.charAt(windowStart++);
        if (charFrequencyMap.containsKey(leftChar)) {
          if (charFrequencyMap.get(leftChar) == 0)
            matched--; // before putting the character back, decrement the matched count
          // put the character back
          charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
        }
      }
    }

    return resultIndices;
  }
  public static void main(String[] args) {
    System.out.println(StringAnagrams.findStringAnagrams("ppqp", "pq"));
    System.out.println(StringAnagrams.findStringAnagrams("abbcabc", "abc"));
    System.out.println(StringAnagrams.findStringAnagramsSolution("ppqp", "pq"));
    System.out.println(StringAnagrams.findStringAnagramsSolution("abbcabc", "abc"));
  }
}










