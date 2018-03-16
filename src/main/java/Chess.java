public class Chess {
	String [][] checkerboard= new String [8][8];
	String [] input = new String[3];
	public String move(String tempPiece, String [] str) {
		boolean test = getPosition(str);
		if(test == true) {
			input = str;
			char piece = tempPiece.charAt(0);
			if(piece=='K') {
				return king(tempPiece);
			} else if(piece=='Q') {
				return queen(tempPiece);
			} else if(piece=='R') {
				return rook(tempPiece);
			} else if(piece=='B') {
				return bishop(tempPiece);
			} else if(piece=='N') {
				return knight(tempPiece);
			} else if(piece=='P') {
				return pawn(tempPiece);
			} else {
				return "illegal input";
			}
		} else {
			return "illegal input";
		}
	}
	
	public String check(String intialPosition, String possiblePosition) {
		String legalPosition = "";
		int initialX = intialPosition.charAt(1)-'a';
		int initialY = Integer.parseInt(intialPosition.substring(2,3))-1;
		char color = checkerboard[initialX][initialY].charAt(0);
		int temp = 0;
		if(color == 'w') {
			temp = 0;
		} else if(color == 'b') {
			temp = 1;
		}
		String [] tempPosition = possiblePosition.split(",");
		String kingpos = "";
		String [] mypieces = input[temp].split(",");
		for(int m=0;m<mypieces.length;m++) {
			if(mypieces[m].charAt(0)=='K') {
				kingpos = mypieces[m].substring(1,3);
			}
		}
		for(int i=0;i<tempPosition.length;i++) {
			String afterPosition = tempPosition[i];
			int afterX = afterPosition.charAt(0)-'a';
			int afterY = Integer.parseInt(afterPosition.substring(1,2))-1;
			String recover = checkerboard[afterX][afterY];
			checkerboard[afterX][afterY] = checkerboard[initialX][initialY];
			checkerboard[initialX][initialY] = null;
			String [] pieces = input[1-temp].split(",");
			boolean test = true;
			for(int m=0;m<pieces.length;m++) {
				if(!pieces[m].substring(1,3).equals(afterPosition)) {
					String pos = checkMove(pieces[m]);
					if(pos.contains(kingpos)) {
						test = false;
					}
				}
			}
			checkerboard[initialX][initialY] = checkerboard[afterX][afterY];
			checkerboard[afterX][afterY] = recover;
			if(test == true) {
				legalPosition = legalPosition+afterPosition+",";
			}
			
		}
		if(!legalPosition.equals("")) {
			legalPosition = legalPosition.substring(0,legalPosition.length()-1);
		} else {
			legalPosition = "no legal move";
		}
		return legalPosition;
	}
	
	public String checkMove(String tempPiece) {
		char piece = tempPiece.charAt(0);
		if(piece=='K') {
			return king(tempPiece);
		} else if(piece=='Q') {
			return queen(tempPiece);
		} else if(piece=='R') {
			return rook(tempPiece);
		} else if(piece=='B') {
			return bishop(tempPiece);
		} else if(piece=='N') {
			return knight(tempPiece);
		} else if(piece=='P') {
			return pawn(tempPiece);
		} else {
			return "illegal input";
		}
	}
	
	public boolean getPosition(String [] str) {
		for(int i=0;i<1;i++) {
			if(str[i].equals("")) {
				System.out.println("illegal Input");
				return false;
			} else {
				String [] temp = str[i].split(",");
				for(int j=0;j<temp.length;j++) {
					if(temp[j].length()!=3) {
						return false;
					}
				}
			}
		}
		if(str[2].length()!=3) {
			return false;
		}
		for(int i = 0;i<2;i++) {
			String [] temp = str[i].split(",");
			int numberK=0,numberR=0,numberB=0,numberQ=0,numberN=0,numberP=0;
			for(int j=0;j<temp.length;j++) {
				char a = temp[j].charAt(1);
				int x = a-'a';
				int y = Integer.parseInt(temp[j].substring(2,3))-1;
				String content = new String();
				if(temp[j].substring(0,1).equals("K")) {
					numberK++;
				}
				if(temp[j].substring(0,1).equals("R")) {
					numberR++;
				}
				if(temp[j].substring(0,1).equals("B")) {
					numberB++;
				}
				if(temp[j].substring(0,1).equals("Q")) {
					numberQ++;
				}
				if(temp[j].substring(0,1).equals("N")) {
					numberN++;
				}
				if(temp[j].substring(0,1).equals("P")) {
					numberP++;
				}
				if(numberK >= 2) {
					return false;
				}
				if(numberR > 10) {
					return false;
				}
				if(numberB > 10) {
					return false;
				}
				if(numberQ > 10) {
					return false;
				}
				if(numberN > 10) {
					return false;
				}
				if(numberP > 8) {
					return false;
				}
				if(i == 0) {
					content = "w"+temp[j].substring(0,1);
				} else if(i == 1) {
					content = "b"+temp[j].substring(0,1);
				}
				if(checkerboard[x][y]==null) {
					checkerboard[x][y] = content;
				} else {
					return false;
				}
			}
		}
		return true;
	}
	
	public String castling (String piece) {
		int x = piece.charAt(1)-'a';
		int y = Integer.parseInt(piece.substring(2,3))-1;
		char color = checkerboard[x][y].charAt(0);
		String possiblePosition = "";
		for(int i=x+1;i<8;i++) {
			if(checkerboard[i][y]==null) {
				
			} else if(checkerboard[i][y].charAt(1)=='R' && checkerboard[i][y].charAt(0) == color && i-x>=3) {
				int ascii = 'a'+x+2;
				char a = (char)ascii;
				int b = y+1;
				possiblePosition = possiblePosition+a+b+",";
			} else {
				break;
			}
		}
		for(int i=x-1;i>=0;i--) {
			if(checkerboard[i][y]==null) {
				
			} else if(checkerboard[i][y].charAt(1)=='R' && checkerboard[i][y].charAt(0) == color && x-i>=3) {
				int ascii = 'a'+x-2;
				char a = (char)ascii;
				int b = y+1;
				possiblePosition = possiblePosition+a+b+",";
			} else {
				break;
			}
		}
		return possiblePosition;
	}
	
	public String king(String piece) {
		int x = piece.charAt(1)-'a';
		int y = Integer.parseInt(piece.substring(2,3))-1;
		char color = checkerboard[x][y].charAt(0);
		String possiblePosition = "";
		int bottomx,bottomy,topx,topy;
		if(x-1>=0) {
			bottomx=x-1;
		} else {
			bottomx=0;
		}
		if(y-1>=0) {
			bottomy=y-1;
		} else {
			bottomy=0;
		}
		if(x+1<8) {
			topx=x+1;
		} else {
			topx=7;
		}
		if(y+1<8) {
			topy=y+1;
		} else {
			topy=7;
		}
		for(int i=bottomx;i<=topx;i++) {
			for(int j=bottomy;j<=topy;j++) {
				if(checkerboard[i][j]==null) {
					int ascii = 'a'+i;
					char a = (char)ascii;
					int b = j+1;
					possiblePosition = possiblePosition+a+b+",";
				} else if(checkerboard[i][j]!=null){
					if(checkerboard[i][j].charAt(0) == color) {
					} else {
						int ascii = 'a'+i;
						char a = (char)ascii;
						int b = j+1;
						possiblePosition = possiblePosition+a+b+",";
					}
				}
			}
		}
		possiblePosition =  possiblePosition+castling(piece);
		if(!possiblePosition.equals("")) {
			if(possiblePosition.charAt(possiblePosition.length()-1)==',') {
				possiblePosition = possiblePosition.substring(0,possiblePosition.length()-1);
			}
		} else {
			possiblePosition = "no possible move";
		}
		return possiblePosition;
	}
	public String rook(String piece) {
		int x = piece.charAt(1)-'a';
		int y = Integer.parseInt(piece.substring(2,3))-1;
		char color = checkerboard[x][y].charAt(0);
		String possiblePosition = "";
		for(int i = x+1;i<8;i++) {
			if(checkerboard[i][y]==null) {
				int ascii = 'a'+i;
				char a = (char)ascii;
				int b = y+1;
				possiblePosition = possiblePosition+a+b+",";
			} else if(checkerboard[i][y]!=null){
				if(checkerboard[i][y].charAt(0) == color) {
					break;
				} else {
					int ascii = 'a'+i;
					char a = (char)ascii;
					int b = y+1;
					possiblePosition = possiblePosition+a+b+",";
					break;
				}
			}
		}
		for(int i = x-1;i>=0;i--) {
			if(checkerboard[i][y]==null) {
				int ascii = 'a'+i;
				char a = (char)ascii;
				int b = y+1;
				possiblePosition = possiblePosition+a+b+",";
			} else if(checkerboard[i][y]!=null){
				if(checkerboard[i][y].charAt(0) == color) {
					break;
				} else {
					int ascii = 'a'+i;
					char a = (char)ascii;
					int b = y+1;
					possiblePosition = possiblePosition+a+b+",";
					break;
				}
			}
		}
		for(int i = y+1;i<8;i++) {
			if(checkerboard[x][i]==null) {
				int ascii = 'a'+x;
				char a = (char)ascii;
				int b = i+1;
				possiblePosition = possiblePosition+a+b+",";
			} else if(checkerboard[x][i]!=null){
				if(checkerboard[x][i].charAt(0) == color) {
					break;
				} else {
					int ascii = 'a'+x;
					char a = (char)ascii;
					int b = i+1;
					possiblePosition = possiblePosition+a+b+",";
					break;
				}
			}
		}
		for(int i = y-1;i>=0;i--) {
			if(checkerboard[x][i]==null) {
				int ascii = 'a'+x;
				char a = (char)ascii;
				int b = i+1;
				possiblePosition = possiblePosition+a+b+",";
			} else if(checkerboard[x][i]!=null){
				if(checkerboard[x][i].charAt(0) == color) {
					break;
				} else {
					int ascii = 'a'+x;
					char a = (char)ascii;
					int b = i+1;
					possiblePosition = possiblePosition+a+b+",";
					break;
				}
			}
		}
		if(!possiblePosition.equals("")) {
			if(possiblePosition.charAt(possiblePosition.length()-1)==',') {
				possiblePosition = possiblePosition.substring(0,possiblePosition.length()-1);
			}
		} else {
			possiblePosition = "no possible move";
		}
		return possiblePosition;
	}
	
	public String bishop(String piece) {
		int x = piece.charAt(1)-'a';
		int y = Integer.parseInt(piece.substring(2,3))-1;
		char color = checkerboard[x][y].charAt(0);
		String possiblePosition = "";
		boolean top = true;
		boolean bottom = true;
		for(int i=x+1;i<8;i++) {
			int length = Math.abs(x-i);
			if(i!=x) {
				int topy,bottomy;
				topy = y+length;
				bottomy= y-length;
				if(topy<8 && top == true) {
					if(checkerboard[i][topy]==null) {
						int ascii = 'a'+i;
						char a = (char)ascii;
						int b = topy+1;
						possiblePosition = possiblePosition+a+b+",";
					} else if(checkerboard[i][topy]!=null){
						if(checkerboard[i][topy].charAt(0) == color) {
							top = false;
						} else {
							int ascii = 'a'+i;
							char a = (char)ascii;
							int b = topy+1;
							possiblePosition = possiblePosition+a+b+",";
							top = false;
						}
					}
				}
				if(bottomy>=0 && bottom == true) {
					if(checkerboard[i][bottomy]==null) {
						int ascii = 'a'+i;
						char a = (char)ascii;
						int b = bottomy+1;
						possiblePosition = possiblePosition+a+b+",";
					} else if(checkerboard[i][bottomy]!=null){
						if(checkerboard[i][bottomy].charAt(0) == color) {
							bottom = false;
						} else {
							int ascii = 'a'+i;
							char a = (char)ascii;
							int b = bottomy+1;
							possiblePosition = possiblePosition+a+b+",";
							bottom = false;
						}
					}
				}
			}
		}
		top = true;
		bottom = true;
		for(int i=x-1;i>=0;i--) {
			int length = Math.abs(x-i);
			if(i!=x) {
				int topy,bottomy;
				topy = y+length;
				bottomy= y-length;
				if(topy<8 && top == true) {
					if(checkerboard[i][topy]==null) {
						int ascii = 'a'+i;
						char a = (char)ascii;
						int b = topy+1;
						possiblePosition = possiblePosition+a+b+",";
					} else if(checkerboard[i][topy]!=null){
						if(checkerboard[i][topy].charAt(0) == color) {
							top = false;
						} else {
							int ascii = 'a'+i;
							char a = (char)ascii;
							int b = topy+1;
							possiblePosition = possiblePosition+a+b+",";
							top = false;
						}
					}
				}
				if(bottomy>=0 && bottom == true) {
					if(checkerboard[i][bottomy]==null) {
						int ascii = 'a'+i;
						char a = (char)ascii;
						int b = bottomy+1;
						possiblePosition = possiblePosition+a+b+",";
					} else if(checkerboard[i][bottomy]!=null){
						if(checkerboard[i][bottomy].charAt(0) == color) {
							bottom = false;
						} else {
							int ascii = 'a'+i;
							char a = (char)ascii;
							int b = bottomy+1;
							possiblePosition = possiblePosition+a+b+",";
							bottom = false;
						}
					}
				}
			}
		}
		if(!possiblePosition.equals("")) {
			if(possiblePosition.charAt(possiblePosition.length()-1)==',') {
				possiblePosition = possiblePosition.substring(0,possiblePosition.length()-1);
			}
		} else {
			possiblePosition = "no possible move";
		}
		return possiblePosition;
	}
	
	public String queen(String piece) {
		String possiblePosition = "";
		if(bishop(piece) == "no possible move" && rook(piece) == "no possible move") {
			possiblePosition = "possiblePosition";
		}
		if(bishop(piece) == "no possible move" && rook(piece) != "no possible move") {
			possiblePosition = rook(piece);
		}
		if(bishop(piece) != "no possible move" && rook(piece) == "no possible move") {
			possiblePosition = bishop(piece);
		}
		if(bishop(piece) != "no possible move" && rook(piece) != "no possible move") {
			possiblePosition = bishop(piece)+","+rook(piece);
		}
		return possiblePosition;
	}
	
	public String knight(String piece) {
		int x = piece.charAt(1)-'a';
		int y = Integer.parseInt(piece.substring(2,3))-1;
		char color = checkerboard[x][y].charAt(0);
		String possiblePosition = "";
		for(int i = x-2;i<=x+2;i++) {
			if(i>=0 && i<8 && i!=x) {
				int length = Math.abs(x-i);
				int m = 3-length;
				if(y+m<8) {
					if(checkerboard[i][y+m]==null) {
						int ascii = 'a'+i;
						char a = (char)ascii;
						int b = y+m+1;
						possiblePosition = possiblePosition+a+b+",";
					} else if(checkerboard[i][y+m]!=null){
						if(checkerboard[i][y+m].charAt(0) == color) {
						} else {
							int ascii = 'a'+i;
							char a = (char)ascii;
							int b = y+m+1;
							possiblePosition = possiblePosition+a+b+",";
						}
					}
				}
				if(y-m>=0) {
					if(checkerboard[i][y-m]==null) {
						int ascii = 'a'+i;
						char a = (char)ascii;
						int b = y-m+1;
						possiblePosition = possiblePosition+a+b+",";
					} else if(checkerboard[i][y-m]!=null){
						if(checkerboard[i][y-m].charAt(0) == color) {
						} else {
							int ascii = 'a'+i;
							char a = (char)ascii;
							int b = y-m+1;
							possiblePosition = possiblePosition+a+b+",";
						}
					}
				}
			}
		}
		if(!possiblePosition.equals("")) {
			if(possiblePosition.charAt(possiblePosition.length()-1)==',') {
				possiblePosition = possiblePosition.substring(0,possiblePosition.length()-1);
			}
		} else {
			possiblePosition = "no possible move";
		}
		return possiblePosition;
	}
	
	public String pawn(String piece) {
		int x = piece.charAt(1)-'a';
		int y = Integer.parseInt(piece.substring(2,3))-1;
		char color = checkerboard[x][y].charAt(0);
		String possiblePosition = "";
		if(color=='w') {
			if(y == 1) {
				for(int i=y+1;i<=y+2 && i<8;i++) {
					if(checkerboard[x][i]==null) {
						int ascii = 'a'+x;
						char a = (char)ascii;
						int b = i+1;
						possiblePosition = possiblePosition+a+b+",";
					}
				}
			} else {
				if(y+1<8 && checkerboard[x][y+1]==null) {
					int ascii = 'a'+x;
					char a = (char)ascii;
					int b = y+2;
					possiblePosition = possiblePosition+a+b+",";
				}
			}
			if(y+1<8 && x-1>=0) {
				if(checkerboard[x-1][y+1] != null) {
					if(checkerboard[x-1][y+1].charAt(0) != color) {
						int ascii = 'a'+x-1;
						char a = (char)ascii;
						int b = y+2;
						possiblePosition = possiblePosition+a+b+",";
					}
				}
				
			}
			if(y+1<8 && x+1<8) {
				if(checkerboard[x+1][y+1] != null) {
					if(checkerboard[x+1][y+1].charAt(0) != color) {
						int ascii = 'a'+x+1;
						char a = (char)ascii;
						int b = y+2;
						possiblePosition = possiblePosition+a+b+",";
					}
				}
			}
		} else if(color=='b') {
			if(y == 6) {
				for(int i=y-1;i>=y-2 && i>=0;i--) {
					if(checkerboard[x][i]==null) {
						int ascii = 'a'+x;
						char a = (char)ascii;
						int b = i+1;
						possiblePosition = possiblePosition+a+b+",";
					}
				}
			} else {
				if(y-1>=0 && checkerboard[x][y-1]==null) {
					int ascii = 'a'+x;
					char a = (char)ascii;
					int b = y;
					possiblePosition = possiblePosition+a+b+",";
				}
			}
			if(y-1>=0 && x-1>=0) {
				if(checkerboard[x-1][y-1] != null) {
					if(checkerboard[x-1][y-1].charAt(0) != color) {
						int ascii = 'a'+x-1;
						char a = (char)ascii;
						int b = y;
						possiblePosition = possiblePosition+a+b+",";
					}
				}
				
			}
			if(y-1>=0 && x+1<8) {
				if(checkerboard[x+1][y-1] != null) {
					if(checkerboard[x+1][y-1].charAt(0) != color) {
						int ascii = 'a'+x+1;
						char a = (char)ascii;
						int b = y;
						possiblePosition = possiblePosition+a+b+",";
					}
				}
			}
		}
		if(!possiblePosition.equals("")) {
			if(possiblePosition.charAt(possiblePosition.length()-1)==',') {
				possiblePosition = possiblePosition.substring(0,possiblePosition.length()-1);
			}
		} else {
			possiblePosition = "no possible move";
		}
		return possiblePosition;
	}
}