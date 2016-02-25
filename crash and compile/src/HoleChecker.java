/**
 * Created by Leon Wetzel
 * Date of creation 24-2-2016, 20:49
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
public class HoleChecker {
    public static void main(String[] args) {
        new HoleChecker();
    }

    public HoleChecker() {
        System.out.println(getAmountOfHoles(65));
    }

    public int getAmountOfHoles(int number) {
        int holes = 0;
        for(int i = 0; i <= number; i++) {
            switch(i) {
                case 0:
                case 4:
                case 6:
                case 9:
                    holes++;
                    break;
                case 8:
                    holes += 2;
                    break;
                default:
            }
        }
        return holes;
    }
}
