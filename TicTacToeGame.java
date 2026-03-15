import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
public class TicTacToeGame {
    public void gamePlay(){
        Scanner scanner=new Scanner(System.in);
        boolean[] pickOfX=new boolean[9];
        boolean[] pickOfO=new boolean[9];
        boolean turnOfX=true;
        int choice;
        while(true){
            if (turnOfX) {
                displayBoard(pickOfX, pickOfO);
                while (true) {
                    System.out.print("Enter your choice(1-9): ");
                    try {
                        choice = scanner.nextInt();
                        if (choice <= 0 || choice > 9) {
                            System.out.println("That choice out of bound!");
                            continue;
                        }
                        if (pickOfX[choice - 1] || pickOfO[choice - 1]) {
                            System.out.println("That choice is already picked!");
                        } else {
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("That not a valid choice!");
                    }
                    scanner.nextLine();
                }
                pickOfX[choice-1]=true;
                turnOfX=false;
                if (isWinner(pickOfX)){
                    System.out.println("X is the winner!");
                    displayBoard(pickOfX,pickOfO);
                    break;
                }
            }
            else{
                choice=smartAl(pickOfX,pickOfO);
                pickOfO[choice]=true;
                turnOfX=true;
                if (isWinner(pickOfO)){
                    System.out.println("O is the winner!");
                    displayBoard(pickOfX,pickOfO);
                    break;
                }
            }
            if (isTie(pickOfX,pickOfO)){
                System.out.println("Tie game!");
                break;
            }
        }
        scanner.close();
    }
    private void displayBoard(boolean[] pickOfX,boolean[] pickOfO){
        for(int i=0;i<=6;i+=3){
            System.out.print("|");
            for (int j=i;j<i+3;j++){
                if (pickOfX[j]){
                    System.out.print("X");
                    System.out.print("|");
                    continue;
                }
                if (pickOfO[j]){
                    System.out.print("o");
                    System.out.print("|");
                    continue;
                }
                System.out.print(j+1);
                System.out.print("|");
            }
            System.out.println();
        }
    }
    private boolean isWinner(boolean[] picked){
        return (picked[0]&&picked[1]&&picked[2])||(picked[3]&&picked[4]&&picked[5])||(picked[6]&&picked[7]&&picked[8])||
                (picked[0]&&picked[3]&&picked[6])||(picked[1]&&picked[4]&&picked[7])||(picked[2]&&picked[5]&&picked[8])||
                (picked[0]&&picked[4]&&picked[8])||(picked[2]&&picked[4]&&picked[6]);
    }
    private boolean isTie(boolean[] pickOfX,boolean[] pickOfO){
        for(int i=0;i<9;i++){
            if(!pickOfX[i]&&!pickOfO[i]){
                return false;
            }
        }
        return true;
    }
    private int dumbAl(boolean[] pickOfX, boolean[] pickOfO){
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
    private int smartAl(boolean[] pickOfX, boolean[] pickOfO){
        int alchoice=-1;
        int best_score=Integer.MIN_VALUE;
        int cur_score;
        for (int i=0;i<9;i++){
            if (pickOfX[i]||pickOfO[i]){
                continue;
            }
            pickOfO[i]=true;
            cur_score=minimax(false,pickOfX,pickOfO,0);
            pickOfO[i]=false;
            if (cur_score>best_score){
                best_score=cur_score;
                alchoice=i;
            }
        }
        return alchoice;
    }
    private int minimax(boolean isAlTurn,boolean[] pickOfX, boolean[] pickOfO,int depth){
        int best_score;
        int cur_score;
        // base case
        if(isWinner(pickOfO)){
            return 10-depth;
        }
        else if (isWinner(pickOfX)){
            return -10+depth;
        }
        else if (isTie(pickOfX,pickOfO)){
            return 0;
        }
        // al turn
        if (isAlTurn){
            best_score=Integer.MIN_VALUE;
            for (int i=0;i<9;i++){
                if (pickOfX[i]||pickOfO[i]){
                    continue;
                }
                pickOfO[i]=true;
                cur_score=minimax(false,pickOfX,pickOfO,depth+1);
                pickOfO[i]=false;
                best_score=Math.max(best_score,cur_score);
            }
        }
        // human turn
        else{
            best_score=Integer.MAX_VALUE;
            for (int i=0;i<9;i++){
                if (pickOfX[i]||pickOfO[i]){
                    continue;
                }
                pickOfX[i]=true;
                cur_score=minimax(true,pickOfX,pickOfO,depth+1);
                pickOfX[i]=false;
                best_score=Math.min(best_score,cur_score);
            }
        }
        return best_score;
    }
}

