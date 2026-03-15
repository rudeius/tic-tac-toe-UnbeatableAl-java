import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToeGame {
    public void gamePlay(){
        Scanner scanner=new Scanner(System.in);
        boolean[] pickOfX=new boolean[9];
        boolean[] pickOfO=new boolean[9];
        boolean turnOfX=true;
        int choice;
        UnbeatableAI smartAI=new UnbeatableAI();
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
                choice=smartAI.smartAl(pickOfX,pickOfO);
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
    protected boolean isWinner(boolean[] picked){
        return (picked[0]&&picked[1]&&picked[2])||(picked[3]&&picked[4]&&picked[5])||(picked[6]&&picked[7]&&picked[8])||
                (picked[0]&&picked[3]&&picked[6])||(picked[1]&&picked[4]&&picked[7])||(picked[2]&&picked[5]&&picked[8])||
                (picked[0]&&picked[4]&&picked[8])||(picked[2]&&picked[4]&&picked[6]);
    }
    protected boolean isTie(boolean[] pickOfX,boolean[] pickOfO){
        for(int i=0;i<9;i++){
            if(!pickOfX[i]&&!pickOfO[i]){
                return false;
            }
        }
        return true;
    }

}

