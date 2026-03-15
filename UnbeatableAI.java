public class UnbeatableAI extends TicTacToeGame{
    protected int smartAl(boolean[] pickOfX, boolean[] pickOfO){
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

