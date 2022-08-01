import java.util.*;


class Interval {
  int start;
  int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
};

class MergeIntervals {

  public static List<Interval> merge(List<Interval> intervals){

    if (intervals.size() < 2)
      return intervals;

    Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
    List<Interval> mergedIntervals = new ArrayList<>();
    Iterator<Interval> listIter = intervals.iterator();
    Interval current = listIter.next();
    int start = current.start;
    int end = current.end;

    while (listIter.hasNext()){
      current = listIter.next();
      if (current.start <= end){ // new interval lies in prev interval so we hve to create a new interval
        end = Math.max(current.end, end);
      } else {
        mergedIntervals.add(new Interval(start, end));
        start = current.start;
        end = current.end;
      }
    }
    mergedIntervals.add(new Interval(start, end));
    return mergedIntervals;


  }

  public static List<Interval> mergeSolution(List<Interval> intervals) {
    if (intervals.size() < 2)
      return intervals;

    // sort the intervals by start time
    Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

    List<Interval> mergedIntervals = new LinkedList<Interval>();
    Iterator<Interval> intervalItr = intervals.iterator();
    Interval interval = intervalItr.next();
    int start = interval.start;
    int end = interval.end;

    while (intervalItr.hasNext()) {
      interval = intervalItr.next();
      if (interval.start <= end) { // overlapping intervals, adjust the 'end'
        end = Math.max(interval.end, end);
      } else { // non-overlapping interval, add the previous interval and reset
        mergedIntervals.add(new Interval(start, end));
        start = interval.start;
        end = interval.end;
      }
    }
    // add the last interval
    mergedIntervals.add(new Interval(start, end));

    return mergedIntervals;
  }

  public static void main(String[] args) {
    List<Interval> input = new ArrayList<Interval>();
    input.add(new Interval(1, 4));
    input.add(new Interval(2, 5));
    input.add(new Interval(7, 9));
    System.out.print("Merged intervals: ");
    for (Interval interval : MergeIntervals.merge(input))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input = new ArrayList<Interval>();
    input.add(new Interval(6, 7));
    input.add(new Interval(2, 4));
    input.add(new Interval(5, 9));
    System.out.print("Merged intervals: ");
    for (Interval interval : MergeIntervals.merge(input))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input = new ArrayList<Interval>();
    input.add(new Interval(1, 4));
    input.add(new Interval(2, 6));
    input.add(new Interval(3, 5));
    System.out.print("Merged intervals: ");
    for (Interval interval : MergeIntervals.merge(input))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();
  }
}

class InsertInterval {
  public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {

    if (intervals == null || intervals.size() == 0)
      return Arrays.asList(newInterval);

    int start = newInterval.start;
    int end = newInterval.end;
    List<Interval> newIntervals = new ArrayList<>();
    Boolean inserted = false;
    
    for (Interval i : intervals){
      // if the current interval is after newInterval and doesn't overlap then insert
      if (!inserted && i.start > end){
        newIntervals.add(new Interval(start, end));
        inserted = true;
      }

      // if the current interval does not overlap with newInterval
      if (i.end < start || i.start > end ){
        newIntervals.add(i);
      } else if (i.start < end) {
        start = Math.min(start, i.start);
        end = Math.max(end, i.end);
      }      
    }

    // if new interval would be the last inserted
    if (!inserted)
      newIntervals.add(new Interval(start, end));
    
    return newIntervals;
  }

  public static List<Interval> insertSolution(List<Interval> intervals, Interval newInterval) {
    if (intervals == null || intervals.isEmpty())
      return Arrays.asList(newInterval);

    List<Interval> mergedIntervals = new ArrayList<>();

    int i = 0;
    // skip (and add to output) all intervals that come before the 'newInterval'
    while (i < intervals.size() && intervals.get(i).end < newInterval.start)
      mergedIntervals.add(intervals.get(i++));

    // merge all intervals that overlap with 'newInterval'
    while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
      newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
      newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
      i++;
    }

    // insert the newInterval
    mergedIntervals.add(newInterval);

    // add all the remaining intervals to the output
    while (i < intervals.size())
      mergedIntervals.add(intervals.get(i++));

    return mergedIntervals;
  }

  public static void main(String[] args) {
    List<Interval> input = new ArrayList<Interval>();
    input.add(new Interval(1, 3));
    input.add(new Interval(5, 7));
    input.add(new Interval(8, 12));
    System.out.print("Intervals after inserting the new interval: ");
    for (Interval interval : InsertInterval.insert(input, new Interval(4, 6)))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input = new ArrayList<Interval>();
    input.add(new Interval(1, 3));
    input.add(new Interval(5, 7));
    input.add(new Interval(8, 12));
    System.out.print("Intervals after inserting the new interval: ");
    for (Interval interval : InsertInterval.insert(input, new Interval(4, 10)))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input = new ArrayList<Interval>();
    input.add(new Interval(2, 3));
    input.add(new Interval(5, 7));
    System.out.print("Intervals after inserting the new interval: ");
    for (Interval interval : InsertInterval.insert(input, new Interval(1, 4)))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();
  }
}

class IntervalsIntersection {
  public static Interval[] merge(Interval[] arr1, Interval[] arr2) {

    List<Interval> intersection = new ArrayList<>();
    int i = 0; int j = 0;

    while(i < arr1.length && j < arr2.length){
      Interval i1 = arr1[i];
      Interval i2 = arr2[j];
      
      if (
        (i1.start >= i2.start && i1.start <= i2.end) || // i1 in i2
        (i2.start >= i1.start && i2.start <= i1.end)    // i2 in i1
          ) {
        intersection.add(new Interval(Math.max(i1.start, i2.start), Math.min(i1.end, i2.end)));
      }

      if (i1.end < i2.end){
        i ++;
      } else {
        j ++;
      }
      
    }

    return intersection.toArray(new Interval[intersection.size()]);

  }
  public static Interval[] mergeSolution(Interval[] arr1, Interval[] arr2) {
    List<Interval> result = new ArrayList<Interval>();
    int i = 0, j = 0;
    while (i < arr1.length && j < arr2.length) {
      // check if the interval arr[i] intersects with arr2[j]
      // check if one of the interval's start time lies within the other interval
      if ((arr1[i].start >= arr2[j].start && arr1[i].start <= arr2[j].end)
          || (arr2[j].start >= arr1[i].start && arr2[j].start <= arr1[i].end)) {
        // store the intersection part
        result.add(new Interval(Math.max(arr1[i].start, arr2[j].start), 
                                Math.min(arr1[i].end, arr2[j].end)));
      }

      // move next from the interval which is finishing first
      if (arr1[i].end < arr2[j].end)
        i++;
      else
        j++;
    }

    return result.toArray(new Interval[result.size()]);
  }

  public static void main(String[] args) {
    Interval[] input1 = new Interval[] { new Interval(1, 3), new Interval(5, 6), 
                                         new Interval(7, 9) };
    Interval[] input2 = new Interval[] { new Interval(2, 3), new Interval(5, 7) };
    Interval[] result = IntervalsIntersection.merge(input1, input2);
    System.out.print("Intervals Intersection: ");
    for (Interval interval : result)
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input1 = new Interval[] { new Interval(1, 3), new Interval(5, 7), 
                              new Interval(9, 12) };
    input2 = new Interval[] { new Interval(5, 10) };
    result = IntervalsIntersection.merge(input1, input2);
    System.out.print("Intervals Intersection: ");
    for (Interval interval : result)
      System.out.print("[" + interval.start + "," + interval.end + "] ");
  }
}

class ConflictingAppointments {
  public static boolean canAttendAllAppointments(Interval[] intervals) {
    
    Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

    for (int i = 1; i < intervals.length; i ++){
      if (intervals[i].start < intervals[i - 1].end)
        return false;
    }

    return true;
  }

  public static boolean canAttendAllAppointmentsSolution(Interval[] intervals) {
    // sort the intervals by start time
    Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

    // find any overlapping appointment
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i].start < intervals[i - 1].end) {
        // please note the comparison above, it is "<" and not "<="
        // while merging we needed "<=" comparison, as we will be merging the two
        // intervals having condition "intervals[i].start == intervals[i - 1].end" but
        // such intervals don't represent conflicting appointments as one starts right
        // after the other
        return false;
      }
    }
    return true;
  }


  public static void main(String[] args) {
    Interval[] intervals = { new Interval(1, 4), new Interval(2, 5), 
                             new Interval(7, 9) };
    boolean result = ConflictingAppointments.canAttendAllAppointments(intervals);
    System.out.println("Can attend all appointments: " + result);

    Interval[] intervals1 = { new Interval(6, 7), new Interval(2, 4), 
                              new Interval(8, 12) };
    result = ConflictingAppointments.canAttendAllAppointments(intervals1);
    System.out.println("Can attend all appointments: " + result);

    Interval[] intervals2 = { new Interval(4, 5), new Interval(2, 3), 
                              new Interval(3, 6) };
    result = ConflictingAppointments.canAttendAllAppointments(intervals2);
    System.out.println("Can attend all appointments: " + result);
  }
}

