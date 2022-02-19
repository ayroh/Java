import java.io.IOException;
import java.lang.Exception;
import java.io.FileNotFoundException;

public interface hexbase{
	void Game();
	boolean isGameFinished();
	void LoadGame()throws FileNotFoundException;
	void SaveGame(String filename)throws IOException;
	void Play();
	void FindSymbol(int x,int y,char symbol);
	void BotPointPlace(boolean IsItTop)throws WrongIndex;
	int FindBestWayDirection();
}