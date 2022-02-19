import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Integer;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Exception;
import java.io.FileNotFoundException;

public class hex implements hexbase{
		private char hexCells[][];		// these fields are same with hw4: cells, temps, etc.
		private int tempCells[];
		private int size, enemy;	
		private boolean IsItFirstMove, IsTopLineFinished, IsBotLineFinished;
		private int topchar, botchar;
		private int topnumber, botnumber;
		private int numberOfPlays;		// for controling temps
		private int turn;
		private int botTempsInt[][];
		private boolean botTempsBool[][];
		private String filename;
		private int ctemp, itemp;

		private JTextField textField;	// text entry
        private textHandler texthandler;		// action handlers
        private focusHandler focushandler;
        private buttonHandler buttonhandler;
		private JFrame hexFrame;				// 2 frames for options and game
		private JFrame optionsFrame;
		private JButton buttons[][];			// button for every cell
        private boolean GameOpened, GameOver;	// restriction for opening game more than 1 and is game over boolean
		private JButton undoButton, resetButton, saveButton, loadButton;	// interaction buttons for options
		private JRadioButton checkBox1, checkBox2;		// jradios for pvp and pve
		private boolean LoadedGame;		// if new game loades, Game() needs to act differently so i need a if(LoadedGame)
	
	public hex(){
		IsItFirstMove = true;
		LoadedGame = IsTopLineFinished = IsBotLineFinished = GameOver = GameOpened = false;
		turn = 1;
		optionsFrame = new JFrame("Options");				// main frame -> options
        optionsFrame.setDefaultCloseOperation(optionsFrame.EXIT_ON_CLOSE);	// if press exit button game ends
		optionsFrame.getContentPane().setBackground(new Color(176,173,173));	// options background color
		JLabel AskText = new JLabel("Enter size of the Hex game:");	 // top of textfield to instruction 
		AskText.setForeground(new Color(1,1,1));	
		checkBox1 =new JRadioButton("Player vs Player",true);    	// 2 jradios for pvp and pve
		checkBox2 =new JRadioButton("Player vs Computer");  
		undoButton = new JButton("Undo");			// interaction buttons
		resetButton = new JButton("Reset");
		saveButton = new JButton("Save");
		loadButton = new JButton("Load");
		buttonhandler = new buttonHandler();
		saveButton.addActionListener(buttonhandler);		// handlers for buttons
		loadButton.addActionListener(buttonhandler);
		resetButton.addActionListener(buttonhandler);
		undoButton.addActionListener(buttonhandler);
		saveButton.setBounds(50,210,70,30);		// bounds for order on options
		saveButton.setOpaque(false);
		loadButton.setBounds(160,210,70,30);
		loadButton.setOpaque(false);
		undoButton.setBounds(50,170,70,30);
		undoButton.setOpaque(false);
		resetButton.setBounds(160,170,70,30);
		resetButton.setOpaque(false);
		ButtonGroup bg = new ButtonGroup();  	// for jradios to set false if other one is true
		checkBox1.setOpaque(false);			// set background transparent for a nice view
		checkBox2.setOpaque(false);
		checkBox1.setBounds(75, 100, 150, 20);
		checkBox2.setBounds(75, 130, 150, 20);
		bg.add(checkBox1);		// adding radios to buttongroup
		bg.add(checkBox2);
		textField = new JTextField("Then press enter...");	// main textfield to write and enter
		focushandler = new focusHandler();			// if user clicks options, textfield becomes empty to write
        textField.addFocusListener(focushandler);
		textField.setBounds(75, 50, 150, 20);
		AskText.setBounds(75, 30, 180, 20);
		optionsFrame.add(saveButton);		// adding buttons and others
		optionsFrame.add(loadButton);
		optionsFrame.add(undoButton);
		optionsFrame.add(resetButton);
		optionsFrame.add(AskText);
		optionsFrame.add(textField);
		optionsFrame.add(checkBox1);	
		optionsFrame.add(checkBox2);
		optionsFrame.setSize(300,300);
		optionsFrame.setLayout(null);
		optionsFrame.setFocusableWindowState(false);
        optionsFrame.setVisible(true);
        optionsFrame.setFocusableWindowState(true);
        texthandler = new textHandler();
        hexFrame = new JFrame("Hex Game");
        textField.addActionListener(texthandler);
    }

    private void setCharacter(int i,int j, char x){
    	hexCells[i][j] = x;
    }

	public void Game(){		// main game frame
		checkBox1.setEnabled(false);
		checkBox2.setEnabled(false);
    	GameOpened = true;
    	hexCells = new char[size][size];
    	tempCells = new int[size * size];
    	botTempsInt = new int[size * size][4];
    	botTempsBool = new boolean[size * size][3];
    	numberOfPlays = 0;
        GridLayout grid = new GridLayout(size,size,0,0);
        Container container = hexFrame.getContentPane();
		buttons = new JButton[size][size]; // array of buttons
		for(int i = 0;i < size;++i)	// adding buttons to grid
        	for(int j = 0;j < size;++j){
        		buttons[i][j] = new JButton(".");
        		buttons[i][j].addActionListener(buttonhandler);
        		hexFrame.add(buttons[i][j]);
        	}
		if(LoadedGame){		// if new game loades i create new frame for it and destroy the old one
			try{
			LoadGame();
			}
			catch(FileNotFoundException ferror){
				JOptionPane.showMessageDialog( null, ferror.getMessage(), "Exception: FileNotFoundException", JOptionPane.ERROR_MESSAGE );
			}
		}
		else{				// if not loades creates a new game
      	    for(int i = 0;i < size;++i)
	        	for(int j = 0;j < size;++j){
	        		hexCells[i][j] = '.';
	        		buttons[i][j].setBackground(new Color(0,0,0));
        	}
		}
        hexFrame.setLayout(grid);
        hexFrame.setBackground(new Color(0,0,0));
        hexFrame.setSize(500,500);
    	hexFrame.setVisible(true);
    }

    public boolean isGameFinished(){ // same as hw4 starts if edges has 'x' or 'o' and goes recursively searches for others. if it bump to other wall then player wins
    	int i;
		char symbol;
		if(turn == 1){ 			 // Left-Right walls
			symbol = 'x';
			for(i = 0;i < size;i++){			// if there is a symbol in the left column it starts to search for others
				if(hexCells[i][0] == symbol) break;
			}
			if(i == size) return false;
			FindSymbol(0,i,symbol);
		}
		else if(turn == 2){		// top-bot walls
			symbol = 'o';
			for(i = 0;i < size;i++){		// if there is a symbol in the bot row it starts to search for others
				if(hexCells[0][i] == symbol) break;
			}
			if(i == size) return false;
			FindSymbol(i,0,symbol);
		}
		
		return GameOver;
    }

    public void FindSymbol(int x,int y,char symbol){						// for player1 searches for 'x', jumps to that 'x' and uppercase the 
		setCharacter(y, x, java.lang.Character.toUpperCase(symbol));							// previous 'x' to 'X' to prevent jumping back.
		buttons[y][x].setText(String.valueOf(java.lang.Character.toUpperCase(symbol)));
		if(turn == 1 && x == size - 1){											// End of the functions if game doesnt end program lowercase them again.
			GameOver = true;
			return;
		}
		if(turn == 2 && y == size - 1){
			GameOver = true;
			return;
		}
							// Searches 6 different ways for lowercase symbols by the PlayerType and controls if numbers (x and y) are out of the array

		if(y - 1 > -1 && hexCells[y - 1][x] == symbol) FindSymbol(x,y-1,symbol);
		if(y - 1 > -1  && x + 1 < size && hexCells[y - 1][x + 1] == symbol) FindSymbol(x + 1,y - 1,symbol);
		if(x + 1 < size && hexCells[y][x + 1] == symbol) FindSymbol(x + 1,y,symbol);
		if(y + 1 < size && hexCells[y + 1][x] == symbol) FindSymbol(x,y + 1,symbol);
		if(y + 1 < size && x - 1 > -1 && hexCells[y + 1][x - 1] == symbol) FindSymbol(x - 1,y + 1,symbol);
		if(x - 1 > -1 && hexCells[y][x - 1] == symbol) FindSymbol(x - 1,y,symbol);

		if(GameOver == false){
			setCharacter(y,x,symbol);	//  If game doesnt end, program lowercases for next time
			buttons[y][x].setText(String.valueOf(symbol));
		}
	}

	public void Play() {	// same as hw4 searches right place for bot
		numberOfPlays--;
		ctemp = getj();
		itemp = geti();
		numberOfPlays++;
		if(IsItFirstMove == true){
			IsItFirstMove = false;
			if(size == ctemp + 1)  // If at first move player selects the rightmost column
				ctemp = (ctemp - 1);
			else
				ctemp = (ctemp + 1);	// In first move program selects the 'right' of the player
			topchar = botchar = ctemp;					// Program stores the rightmost, leftmost
			topnumber = botnumber = itemp;					// lowest and highest selection and continues from that point
			buttons[itemp][ctemp].setBackground(new Color(155,29,92));
			buttons[itemp][ctemp].setText("o");
			hexCells[itemp][ctemp]= 'o';
		if(itemp == 0)
			IsBotLineFinished = true;
		else if(itemp == size - 1)
			IsTopLineFinished = true;
		}
		else{
			if(itemp > topnumber && IsTopLineFinished == false){			// if player selects higher temp.row from the topnumber, computer goes to down
				itemp = (topnumber);
				ctemp = (topchar);
				try{
					BotPointPlace(true);			// if there is a symbol in the top row program goes to bot
				}
				catch(WrongIndex error){
				}
			}
			else if(itemp < botnumber && IsBotLineFinished == false){		// if lower from the botnumber goes to up
				itemp = (botnumber);
				ctemp = (botchar);
				try{
					BotPointPlace(false);		// if there is a symbol in the bot row program goes to top
				}
				catch(WrongIndex error){
				}
			}
			else{		// if between top and bot numbers program first tries to go to up, if there is already a symbol in the top row it goes to bottom
				if(IsTopLineFinished == false){
					itemp = (topnumber);
					ctemp = (topchar);
					try{
						BotPointPlace(true);
					}
					catch(WrongIndex error){
					}
				}
				else{
					itemp = (botnumber);
					ctemp = (botchar);
					try{
						BotPointPlace(false);
					}
					catch(WrongIndex error){
					}
				}
			}
		}
		tempCells[numberOfPlays] = itemp * size + ctemp;	// stores ij of hexCells[i][j]
		assignBotTemps();
		numberOfPlays ++;
	}

	private void assignBotTemps(){
		botTempsInt[numberOfPlays][0] = botchar;
		botTempsInt[numberOfPlays][1] = topchar;
		botTempsInt[numberOfPlays][2] = botnumber;
		botTempsInt[numberOfPlays][3] = topnumber;
		botTempsBool[numberOfPlays][0] = IsItFirstMove;
		botTempsBool[numberOfPlays][1] = IsTopLineFinished;
		botTempsBool[numberOfPlays][2] = IsBotLineFinished;
	}


	public void BotPointPlace(boolean IsItTop)throws WrongIndex{
		int direction;
		             					// Places the next symbol by the IsItTop selection
		if(IsItTop){			// Function first tries to go straight up or down
			++topnumber;		// then tries cross paths
			itemp = topnumber;					

									// at last computes the shortest way between right and left and goes that way
		if(PointControl(itemp,ctemp)){											// to down
			if(itemp < 0 || itemp >= size || ctemp < 0 || ctemp >= size)
				throw new WrongIndex();
			buttons[itemp][ctemp].setBackground(new Color(155,29,92));
			buttons[itemp][ctemp].setText("o");
			hexCells[itemp][ctemp] = 'o';
		}
		else if(PointControl(itemp, ctemp - 1) && ctemp - 1 > -1){	// cross
			ctemp = (ctemp - 1);
			--topchar;
			if(itemp < 0 || itemp >= size || ctemp < 0 || ctemp >= size)
				throw new WrongIndex();
			buttons[itemp][ctemp].setBackground(new Color(155,29,92));
			buttons[itemp][ctemp].setText("o");
			hexCells[itemp][ctemp] = 'o';
		}
		else{
			direction = FindBestWayDirection();						// right or left			
			--topnumber;
			itemp = (topnumber);

			if(PointControl(itemp, ctemp + direction)){
				ctemp = (ctemp + direction);
				topchar +=direction;
				if(itemp < 0 || itemp >= size || ctemp < 0 || ctemp >= size)
					throw new WrongIndex();
				buttons[itemp][ctemp].setBackground(new Color(155,29,92));
				buttons[itemp][ctemp].setText("o");
				hexCells[itemp][ctemp] = 'o';
			}
			else if(PointControl(itemp, ctemp - direction)){
				ctemp = (ctemp - direction);
				topchar -= direction;
				if(itemp < 0 || itemp >= size || ctemp < 0 || ctemp >= size)
					throw new WrongIndex();
				buttons[itemp][ctemp].setBackground(new Color(155,29,92));
				buttons[itemp][ctemp].setText("o");
				hexCells[itemp][ctemp] = 'o';
			}
		}				
		if(itemp + 1 == size)			// this means computer placed a symbol to last line
			IsTopLineFinished = true;
	}

	else if(IsItTop == false){
		--botnumber;
		itemp = botnumber;
		
		if(PointControl(itemp, ctemp)){			// to up
			if(itemp < 0 || itemp >= size || ctemp < 0 || ctemp >= size){
				++botnumber;
				itemp = botnumber;
				throw new WrongIndex();
			}
			buttons[itemp][ctemp].setBackground(new Color(155,29,92));
			buttons[itemp][ctemp].setText("o");
			hexCells[itemp][ctemp] = 'o';
		}											
		else if(PointControl(itemp, ctemp + 1) && ctemp + 1 < 12){	// cross
			++botchar;
			ctemp = (botchar);
			if(itemp < 0 || itemp >= size || ctemp < 0 || ctemp >= size){
				--botchar;
				ctemp = (botchar);
				throw new WrongIndex();
			}
			buttons[itemp][ctemp].setBackground(new Color(155,29,92));
			buttons[itemp][ctemp].setText("o");
			hexCells[itemp][ctemp] = 'o';
		}
		else{
			direction = FindBestWayDirection();						// right or left			
			++botnumber;
			itemp = (botnumber);

			if(PointControl(itemp, ctemp + direction)){
				botchar += direction;
				ctemp = (botchar);
				if(itemp < 0 || itemp >= size || ctemp < 0 || ctemp >= size){
					botchar -= direction;
					ctemp = (botchar);
					throw new WrongIndex();
				}
				buttons[itemp][ctemp].setBackground(new Color(155,29,92));
				buttons[itemp][ctemp].setText("o");
				hexCells[itemp][ctemp] = 'o';
			}
			else if(PointControl(itemp, ctemp - direction)){
				botchar -= direction;
				ctemp = (botchar);
				if(itemp < 0 || itemp >= size || ctemp < 0 || ctemp >= size){
					botchar += direction;
					ctemp = (botchar);
					throw new WrongIndex();
				}
				buttons[itemp][ctemp].setBackground(new Color(155,29,92));
				buttons[itemp][ctemp].setText("o");
				hexCells[itemp][ctemp] = 'o';
			}
		}
		if(itemp == 0)			// this means computer placed a symbol to first line
			IsBotLineFinished = true;
	}
}


	public int FindBestWayDirection(){	//	for computer, if top-topleft-topright or bot-botleft-botright
		int right = 0,left = 0;			//  is not empty, computer computes the shortest way to go
		
		while(hexCells[itemp][ctemp - left] == 'x') {
			++left;
			if(ctemp - left == 0){		// restriction for bounds of hexCells
				if(hexCells[itemp][ctemp - left] == 'x')
					++left;
				break;
			}
		}
		while(hexCells[itemp][ctemp + right] == 'x'){
			++right;
	  		if(ctemp + right == size - 1){			// restriction for bounds of hexCells
	  			if(hexCells[itemp][ctemp + right] == 'x')
					++right;
				break;
	 		}
		}
		if(ctemp + right == size)
			return -1;		// end of array
		else if(ctemp - left == -1) 
			return 1;	// end of array
		else if(right > left)
			return -1;
		else 
			return 1;
	}

	private boolean PointControl(int i, int j){
		if(hexCells[i][j] == '.')
			return true;
		else
			return false;	
	}

	private int geti(){
		return (tempCells[numberOfPlays] - tempCells[numberOfPlays] % size) / size;
	}
	private int getj(){
		return tempCells[numberOfPlays] % size;
	}


	public void SaveGame(String filename)throws IOException{
		FileWriter file = new FileWriter(filename);
		file.write(size+" "+enemy+" "+turn);
		if(enemy == 2){
			int ist = IsTopLineFinished ? 1 : 0;
			int isb = IsBotLineFinished ? 1 : 0;
			file.write(" "+topnumber+" "+botnumber+" "+ist+" "+isb+" "+topchar+" "+botchar+"\n");
		}
		else
			file.write("\n");
		int intbuffer;
					// Saving to a file by the order
		for(int i = 0;i < size;++i){
			for(int j = 0;j < size;++j){
				intbuffer =  hexCells[i][j];
				file.write(intbuffer+" ");
			}
			file.write("\n");
		}
		JOptionPane.showMessageDialog( null, "Files saved to: " + filename, "Game saved", JOptionPane.PLAIN_MESSAGE); 
		file.close();
	}

	public void LoadGame()throws FileNotFoundException{
		Scanner sc = new Scanner(new File(filename));
		String s;
		s = sc.next();
		size = Integer.parseInt(s);
		textField.setText("Size is: " +size);
		s = sc.next();
		enemy = Integer.parseInt(s);
		if(enemy == 1)
			checkBox1.setSelected(true);
		if(enemy == 2)
			checkBox2.setSelected(true);
		s = sc.next();
		turn = Integer.parseInt(s);
		if(enemy == 2){
			s = sc.next();
			topnumber = Integer.parseInt(s);
			s = sc.next();
			botnumber = Integer.parseInt(s);
			s = sc.next();
			int ist = Integer.parseInt(s);
			s = sc.next();
			System.out.println(s);
			int isb = Integer.parseInt(s);
			if(ist == 1)
				IsTopLineFinished = true;
			else
				IsTopLineFinished = false;
			if(isb == 1)
				IsBotLineFinished = true;
			else
				IsBotLineFinished = false;
			s = sc.next();
			topchar = Integer.parseInt(s);
			s = sc.next();
			botchar = Integer.parseInt(s);
		}
		int intbuffer;
					// Saving to a file by the order
		for(int i = 0;i < size;++i){
			for(int j = 0;j < size;++j){
				s = sc.next();
				intbuffer = Integer.parseInt(s);
				hexCells[i][j] = (char)intbuffer;;
				if(hexCells[i][j] == 'x'){
					buttons[i][j].setBackground(new Color(174, 104, 24));
					buttons[i][j].setText("x");
				}
				else if(hexCells[i][j] == 'o'){
					buttons[i][j].setBackground(new Color(155, 29, 92));
					buttons[i][j].setText("o");
				}
				else if(hexCells[i][j] == '.'){
					buttons[i][j].setBackground(new Color(0, 0, 0));
	    			buttons[i][j].setText(".");
				}
			}
		}
		sc.close();
	}

    public class buttonHandler implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		try{
	    		if(e.getSource() == undoButton && !GameOver && GameOpened){		// undo
	    			if(numberOfPlays == 0){		// if there is no undo
	    				throw new CantUndo();
	    			}
	    			numberOfPlays--;
	    			buttons[geti()][getj()].setBackground(new Color(0,0,0));
	    			buttons[geti()][getj()].setText(".");
	    			hexCells[geti()][getj()] = '.';
	    			if(enemy == 1){
		    			if(turn == 1)
		    				turn = 2;
		    			else
		    				turn = 1;
	    			}
	    			else{	// does it 1 more time for bot
	    			if(numberOfPlays != 1){
		    			botchar = botTempsInt[numberOfPlays - 2][0];
						topchar = botTempsInt[numberOfPlays - 2][1];
						botnumber = botTempsInt[numberOfPlays - 2][2];
						topnumber = botTempsInt[numberOfPlays - 2][3];
						IsItFirstMove = botTempsBool[numberOfPlays - 2][0];
						IsTopLineFinished = botTempsBool[numberOfPlays - 2][1];
						IsBotLineFinished = botTempsBool[numberOfPlays - 2][2];		
	    			}
	    			else{
	    				IsItFirstMove = true;
						IsTopLineFinished = IsBotLineFinished = false;
	    			}
	    			numberOfPlays--;
	    			buttons[geti()][getj()].setBackground(new Color(0,0,0));
	    			buttons[geti()][getj()].setText(".");
	    			hexCells[geti()][getj()] = '.';
    				}
    			}
    		}
    		catch(CantUndo error){
    			JOptionPane.showMessageDialog( null, error.getMessage(), "Exception: CantUndo", JOptionPane.ERROR_MESSAGE );
    		}
    		if(e.getSource() == resetButton && !GameOver && GameOpened){		// reset
    			for(int i = 0;i < size;i++){
    				for(int j = 0;j < size;j++){
    					buttons[i][j].setBackground(new Color(0,0,0));
    					buttons[i][j].setText(".");
    					hexCells[i][j] = '.';
    				}
    			}
		    	tempCells = new int[size * size];
		    	botTempsInt = new int[size * size][4];
		    	botTempsBool = new boolean[size * size][3];
		    	IsItFirstMove = true;
				IsTopLineFinished = IsBotLineFinished = false;
				numberOfPlays = 0;
				turn = 1;
    		}
    		else if(e.getSource() == saveButton && !GameOver && GameOpened){		// save
    			try{
    			SaveGame(JOptionPane.showInputDialog( "Enter filename" ));
    			}
    			catch(IOException ferror){}
    		}
    		else if(e.getSource() == loadButton){				// load, even before any game started
    			filename = new String(JOptionPane.showInputDialog( "Enter filename" ));
    			hexFrame.dispose();		// destroys old frame
    			try{
    			Scanner forsize = new Scanner(new File(filename));
    			size = Integer.parseInt(forsize.next());
    			LoadedGame = true;
    			GameOver = false;
    			hexFrame = new JFrame("Hex Game");
    			Game();				// creates a new game
    			}
    			catch(FileNotFoundException freadererror){
    				JOptionPane.showMessageDialog( null, freadererror.getMessage(), "Exception: FileNotFoundException", JOptionPane.ERROR_MESSAGE );
    			}
    		}
    		else{
    			if(!GameOver){		// if game ends there will be no input
		    		for(int i = 0;i < size;++i){
		    			for(int j = 0;j < size;++j)
		    				if(e.getSource() == buttons[i][j] && buttons[i][j].getText() == "."){	// finding source button of the action
		    					if(turn == 1){
									buttons[i][j].setBackground(new Color(174,104,24));
			    					buttons[i][j].setText("x");
			    					hexCells[i][j] = 'x';
			    					if(isGameFinished()){
			    						JOptionPane.showMessageDialog( null, "You won the game Player " + turn, "Congratz", JOptionPane.INFORMATION_MESSAGE ); 
			    						break;
			    					}
			    					tempCells[numberOfPlays] = i * size + j;
			    					numberOfPlays++;	
			    					turn = 2;
			    					if(enemy == 2 && !GameOver){	// calls bot to play
			    						Play();
			    						if(isGameFinished()){
				    						JOptionPane.showMessageDialog( null, "Bot beated you", "Shame", JOptionPane.INFORMATION_MESSAGE ); 
				    					}
				    					turn = 1;
			    					}
								}
								else if(turn == 2 && enemy == 1){
									buttons[i][j].setBackground(new Color(155,29,92));
			    					buttons[i][j].setText("o");
			    					hexCells[i][j] = 'o';	
			    					if(isGameFinished()){
			    						JOptionPane.showMessageDialog( null, "You won the game Player " + turn, "Congratz", JOptionPane.INFORMATION_MESSAGE ); 
			    					}
			    					tempCells[numberOfPlays] = i * size + j;
			    					numberOfPlays++;
			    					turn = 1;
								}
		    					break;
		    				}
		  		  	}
		  		}
    		}
    	}
    }
    
    

	public class textHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){  
			try{
				String s = "";
				if(e.getSource() == textField){
					s = String.format( "%s", e.getActionCommand() );
					size = Integer.parseInt(s);
					if(!(size < 21 && size > 5))
						throw new WrongSize();
					if(!GameOpened){
						if(checkBox1.isSelected())
							enemy = 1;
						else
							enemy = 2;
						Game();
					}
				}
			}
			catch(WrongSize error){
				JOptionPane.showMessageDialog( null, error.getMessage(), "Exception: WrongSize", JOptionPane.ERROR_MESSAGE );
			}
		}
	}

	public class focusHandler implements FocusListener{	// if user clicks options, textfield becomes empty to write
            public void focusGained(FocusEvent e){
                textField.setText("");
            }
            public void focusLost(FocusEvent e){

            }
    }

    
}