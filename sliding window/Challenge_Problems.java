import java.util.*;

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