public class CantUndo extends Exception{
    	public CantUndo(){
    		super("Play at least 1 time before undo.");
    	}
    }
