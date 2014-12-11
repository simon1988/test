package leet.easy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** 
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */
/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 *
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 *
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class MergeIntervals {
	public List<Interval> merge(List<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<Interval>();
        if(intervals==null||intervals.size()==0)return res;
        Collections.sort(intervals, new Comparator<Interval>(){
          public int compare(Interval a, Interval b){
              if(a.start==b.start){
                  return a.end-b.end;
              }
              return a.start-b.start;
          }  
        });
        for(int i=0;i<intervals.size();i++){
            Interval cur = intervals.get(i);
            while(i+1<intervals.size()&&cur.end>=intervals.get(i+1).start){
                cur.end=Math.max(cur.end,intervals.get(i+1).end);
                i++;
            }
            res.add(cur);
        }
        return res;
    }
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if(intervals==null||newInterval==null)return intervals;
        List<Interval> res = new ArrayList<Interval>();
        int i=0;
        while(i<intervals.size()&&intervals.get(i).end<newInterval.start){
            res.add(intervals.get(i));
            i++;
        }
        if(i<intervals.size()){
            newInterval.start=Math.min(newInterval.start,intervals.get(i).start);
        }
        res.add(newInterval);
        while(i<intervals.size()&&intervals.get(i).start<=newInterval.end){
            newInterval.end=Math.max(newInterval.end,intervals.get(i).end);
            i++;
        }
        while(i<intervals.size()){
            res.add(intervals.get(i));
            i++;
        }
        return res;
    }
	class Interval {
		int start;
		int end;

		public Interval() {
			start = 0;
			end = 0;
		}

		public Interval(int s, int e) {
			start = s;
			end = e;
		}
		
		@Override
		public String toString() {
			return "[" + start + ", " + end + "]";
		}
	}
}