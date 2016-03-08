package dynProg;

import java.util.Arrays;

/**
 * Created by Leon Wetzel
 * Date of creation 2-3-2016, 20:53
 * |
 * Authors: Leon Wetzel
 * |
 * Version: 1.0
 * Package: dynProg
 * Class:
 * Description:
 * |
 * |
 * Changelog:
 * 1.0:
 */
public class RecursiveSolver implements Solver {

    public static void main(String[] args) {
        int[] numbers = { 8, 3, 6, 15};
        int sum = 23;
        System.out.println(new RecursiveSolver().solve(numbers, sum));
    }

    /**
     * Solves problem.
     * @param numbers The array containing several numbers.
     * @param sum The sum.
     * @return Boolean indicating if problem has been solved.
     */
    @Override
    public boolean solve(int[] numbers, int sum) {
        if(checkBAndLength(numbers, sum)) {
            return true;
        }

        int[] cache = numbers.clone();
        for(int k = 0; k < numbers.length; k++) {
            if(numbers[k] == sum) {
                System.out.println(Arrays.toString(numbers) +  " is gelijk aan " + sum);
                return true;
            } else if(numbers[k] > 0) {
                if(numbers[k] <= sum) {
                    cache[k] = 0;
                    if(solve(cache, sum - numbers[k])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Searches the array.
     * For: Array.length == 0 && sum == 0
     * For: All values in array == 0
     * If found return true;
     * @param numbers The array which should be checked.
     * @param value The value which is used for the check.
     * @return Boolean indicating if check succeeded or not.
     */
    private boolean checkBAndLength(int[] numbers, int value) {
        if(numbers.length == 0 && value ==0) {
            return true;
        } else if(value == 0) {
            boolean success = true;
            for(int n : numbers) {
                if(n != 0 ) {
                    success = false;
                    break;
                }
            }
            return success;
        }
        return false;
    }
}
