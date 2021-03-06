package cards;
import java.util.Scanner;

public class Problem
{
    private Candidates candidates   = new Candidates();
    private Solution   solution     = new Solution();
    private Scanner    reader       = new Scanner(System.in);

    public static void main(String[] args) {
        new Problem().solve();
    }

    public void solve() {
        System.out.println(candidates);
        System.out.println(solution);
        // reader.nextLine();
        int index = 0;
        while (index < candidates.size()) {
            if (solution.fits(candidates.get(index))) {
                solution.record(candidates.remove(index)); //move candidate to solution
                if (solution.complete()) {
                    solution.show();
                    break;
                } else {
                    solve();
                }
                candidates.add(index, solution.eraseRecording()); //move candidate to candidates
            }
            index++;
            if(index == 8) index = 0;
        }
    }
}
        
          
         









