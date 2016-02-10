/**
 * Created by Leon Wetzel
 * Date of creation 9-2-2016, 13:21
 * |
 * Authors: Leon Wetzel
 * |
 * Version: 1.0
 * Package: PACKAGE_NAME
 * Class:
 * Description:
 * |
 * |
 * Changelog:
 * 1.0:
 */

/**
 * Contains a method which calculates the sum of values of two arrays.
 * @author Leon Wetzel
 *
 */
public class Main {
    public static void main(String[] args) {
        try {
            int[] results = getArraySum(new int[] {3, 6, 9}, new int[] {2, 6, 5});
            for (int item : results) {
                System.out.println(item);
            }
        } catch(ArraySizeException exception) {
            System.err.println(exception.getMessage());
        } finally {
            System.out.println("Nog een fijne dag!");
        }
    }

    /**
     * Get an array that contains the sum of values of two arrays.
     * @param a array containing an amount of integers
     * @param b array containing an amount of integers
     * @return An array that contains the sum of values of a and b.
     */
    public static int[] getArraySum(int[] a, int[] b) throws ArraySizeException {
        if(a.length != b.length) {
            // arrays bevatten niet evenveel waarden
            throw new ArraySizeException("Hmm, de lengtes van je arrays komen niet overeen!" +
                    " Je eerste array bevat " + a.length + " items en je tweede array bevat " +
                    b.length + " items.");
        } else {
            // maak een nieuwe array die de resultaten bevat...
            int[] result = new int[a.length];
            for(int i = 0; i < a.length; i++) {
                result[i] = a[i] + b[i];
            }
            return result;
        }
    }
}