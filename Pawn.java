import java.util.ArrayList;

public class Pawn extends Piece{
    public Pawn(boolean white){
        super(Type.Pawn, white);
    }

    public Pawn(Piece piece){
        this(piece.getColor());
    }

    @Override
    public boolean isValid(Game game, Move move){
      Cord from = move.getFrom();
      Cord to = move.getTo();
      boolean valid = false;
      Piece[][] board = game.getBoard();
      int mod = getColor()? 1 : -1;
      int dx = Math.abs(from.getRank() - to.getRank());
      int dy = mod * (to.getFile() - from.getFile());
      if(dy == 1 && dx == 0 
        && board[to.getFile()][to.getRank()].getType() == Type.Empty) 
        valid = true;
      else if(dy == 1 && dx == 1 
        && board[to.getFile()][to.getRank()].getType() != Type.Empty 
        && board[to.getFile()][to.getRank()].getColor() != getColor()) 
        valid = true;
      else if((from.getFile() == 1) || (from.getFile() == 6))
        if(dy == 2 && dx == 0 
        && board[to.getFile()][to.getRank()].getType() == Type.Empty 
        && board[to.getFile() - mod][to.getRank()].getType() == Type.Empty) 
        valid = true;
      else if(getColor() && game.getEnPassant() != null   //en passant white
    		  && game.getEnPassant().getRank() == 5
    		  && (from.getFile() == game.getEnPassant().getFile() + 1
    		  || (from.getFile() == game.getEnPassant().getFile() - 1))
    		  && to == game.getEnPassant())
    		  valid = true;
      else if(getColor() == false && game.getEnPassant() != null //en passant black
    		  && game.getEnPassant().getRank() == 2
    		  && (from.getFile() == game.getEnPassant().getFile() + 1
    		  || (from.getFile() == game.getEnPassant().getFile() - 1))
    		  && to == game.getEnPassant())
    		  valid = true;
      return valid && super.isValid(game, move);
    }

    @Override
    public void updateValue(){
        value = Constant.PAWN_VALUE;
    }

    @Override
    public void updateValue(Game game, Cord at){
        double worth = Constant.PAWN_VALUE;
        
        value = worth;
    }
    
    @Override
    public char toCharacter(){
        return isWhite? 'P' : 'p';
    }
}
