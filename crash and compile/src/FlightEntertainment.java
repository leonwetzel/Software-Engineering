import java.util.ArrayList;

/**
 * Created by Leon Wetzel
 * Date of creation 24-2-2016, 19:43
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
public class FlightEntertainment {
    private static int[] movies = new int[] {89, 76, 45, 87, 120, 34, 45, 56, 62, 1220};
    private static int flightLength = 250;

    public static void main(String[] args) {
        new FlightEntertainment();
    }

    public FlightEntertainment() {
        for(int i : this.getBest()) {
            System.out.println(i);
        }
    }

    private ArrayList<Integer> getBest() {
        int sum = 0;
        ArrayList<Integer> results = new ArrayList<>();
        for(int movie : movies) {
            sum += movie;
            if(sum > flightLength) {
                sum = 0;
            } else {
                results.add(movie);
            }
        }
        return results;
    }


}
