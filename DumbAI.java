import java.util.Random;

public class DumbAI {
    protected int dumbAl(boolean[] pickOfX, boolean[] pickOfO){
        int alChoice;
        while (true){
            Random random= new Random();
            alChoice=random.nextInt(0,9);
            if (pickOfX[alChoice]||pickOfO[alChoice]){
                continue;
            }
            break;
        }
        return alChoice;
    }

}
