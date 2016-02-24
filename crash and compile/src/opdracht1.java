/**
 * Created by Robert on 24-2-2016.
 */
public class opdracht1 {
    public static void main(String[] args) {
        test(new int[] {0, 1});
    }

    /**
     * Pinpoints the amount of towers needed to secure perimeter.
     * @param positions
     * @return Amount of towers necessary.
     */
    public static int test(int[] positions) {
        int i = positions[0];
        int biggest = 0;
        for(int item : positions) {
            if(item > biggest) {
                biggest = item;
            }
        }
        return 1;
    }
}
