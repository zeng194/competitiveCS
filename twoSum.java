import java.util.*;
public class twoSum{
    /*
    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    You can return the answer in any order.
    */  
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a = 0; a < nums.length; a++) {
            int other = target - nums[a];
            if (map.containsKey(other)) {
                int[] solution = new int[2];
                solution[0] = map.get(other);
                solution[1] = a;
                return solution;
            }
            else {
                map.put(nums[a], a);
            }
        }
        return new int[2];
    }
}