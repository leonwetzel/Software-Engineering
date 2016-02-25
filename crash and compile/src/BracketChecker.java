import java.util.ArrayList;

/**
 * Created by Leon Wetzel
 * Date of creation 24-2-2016, 20:01
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
public class BracketChecker {
    private char[] openers = new char[] {'{', '[', '('};
    private char[] closers = new char[] {'}', ']', ')'};

    private ArrayList<Character> opened = new ArrayList<>();

    public static void main(String[] args) {
        new BracketChecker();
    }

    public BracketChecker() {
        String input = "{ [ ] ( ) }";
        System.out.println(checkInput(input));
    }

    public boolean checkInput(String input) {
        input = input.trim();

        for (char character : input.toCharArray()) {
            for(int i = 0; i < input.toCharArray().length; i++) {
                switch (character) {
                    case '{':
                        if(opened.contains('{')) {
                            return false;
                        } else {
                            opened.add('{');
                        }
                        break;
                    case '(':
                        if(opened.contains('(')) {
                            return false;
                        } else {
                            opened.add('(');
                        }
                        break;
                    case '[':
                        if(opened.contains('[')) {
                            return false;
                        } else {
                            opened.add('[');
                        }
                        break;
                    case '}':
                        if(opened.contains('{')) {
                            return true;
                        } else {
                            opened.add('}');
                        }
                        break;
                    case ')':
                        if(opened.contains('(')) {
                            return true;
                        } else {
                            opened.add(')');
                        }
                        break;
                    case ']':
                        if(opened.contains('[')) {
                            return true;
                        } else {
                            opened.add(']');
                        }
                        break;
                    default:
                        return false;
                }
            }
        }
        return false;
    }
}
