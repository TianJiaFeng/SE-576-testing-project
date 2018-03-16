import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class AppTest {
    public static class Random extends Generator<Integer> {
        public Random() {
            super(Integer.class);
        }
        @Override public Integer generate(SourceOfRandomness r, GenerationStatus s) {
        	int number = 0;
        	if(r.nextInt() <0 ) {
        		number = Math.abs(r.nextInt());
        	}
            return number % 8;
        }
    }
    
    @Property(trials = 250, shrink = true)
    public void checRandomRook(@From(Random.class) int x, @From(Random.class) int y) throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//RandomQueen.txt");
    	int ascii = 'a'+x;
		char a = (char)ascii;
		int b = y+1;
		String position = "";
		position = position+'R'+a+b;
		file[0]=file[0]+","+position;
		file[2]=position;
		String possiblePosition = classUnderTest.move(file[2],file);
		assertNotNull("all input should have a return value",possiblePosition);
    }
    
    @Property(trials = 250, shrink = true)
    public void checRandomKing(@From(Random.class) int x, @From(Random.class) int y) throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//RandomKing.txt");
    	int ascii = 'a'+x;
		char a = (char)ascii;
		int b = y+1;
		String position = "";
		position = position+'K'+a+b;
		file[0]=file[0]+","+position;
		file[2]=position;
		String possiblePosition = classUnderTest.move(file[2],file);
		assertNotNull("all input should have a return value",possiblePosition);
    }
    
    @Property(trials = 250, shrink = true)
    public void checRandomQueen(@From(Random.class) int x, @From(Random.class) int y) throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//RandomQueen.txt");
    	int ascii = 'a'+x;
		char a = (char)ascii;
		int b = y+1;
		String position = "";
		position = position+'Q'+a+b;
		file[0]=file[0]+","+position;
		file[2]=position;
		String possiblePosition = classUnderTest.move(file[2],file);
		assertNotNull("all input should have a return value",possiblePosition);
    }
    
    @Property(trials = 250, shrink = true)
    public void checRandomKnight(@From(Random.class) int x, @From(Random.class) int y) throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//RandomKnight.txt");
    	int ascii = 'a'+x;
		char a = (char)ascii;
		int b = y+1;
		String position = "";
		position = position+'N'+a+b;
		file[0]=file[0]+","+position;
		file[2]=position;
		String possiblePosition = classUnderTest.move(file[2],file);
		assertNotNull("all input should have a return value",possiblePosition);
    }
    
    @Property(trials = 250, shrink = true)
    public void checRandomBishop(@From(Random.class) int x, @From(Random.class) int y) throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//RandomQueen.txt");
    	int ascii = 'a'+x;
		char a = (char)ascii;
		int b = y+1;
		String position = "";
		position = position+'B'+a+b;
		file[0]=file[0]+","+position;
		file[2]=position;
		String possiblePosition = classUnderTest.move(file[2],file);
		assertNotNull("all input should have a return value",possiblePosition);
    }

    @Property(trials = 250, shrink = true)
    public void checRandomWhitePawn(@From(Random.class) int x, @From(Random.class) int y) throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//Random.txt");
    	int ascii = 'a'+x;
		char a = (char)ascii;
		int b = y+1;
		String position = "";
		position = position+'P'+a+b;
		file[0]=file[0]+","+position;
		file[2]=position;
		String possiblePosition = classUnderTest.move(file[2],file);
		assertNotNull("all input should have a return value",possiblePosition);
    }
    
    @Property(trials = 250, shrink = true)
    public void checRandomBlackPawn(@From(Random.class) int x, @From(Random.class) int y) throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//Random.txt");
    	int ascii = 'a'+x;
		char a = (char)ascii;
		int b = y+1;
		String position = "";
		position = position+'P'+a+b;
		file[1]=file[1]+","+position;
		file[2]=position;
		String possiblePosition = classUnderTest.move(file[2],file);
		assertNotNull("all input should have a return value",possiblePosition);
    }
    
    @Property public void checkRead() throws IOException {
    	Read classUnderTest = new Read();
    	String [] file = classUnderTest.readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestCheck.txt");
    	assertNotNull(file);
    }
    
    @Property public void checkCheck() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestCheck.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	String legalMove = classUnderTest.check(file[2],possiblePosition);
    	assertEquals(legalMove,"no legal move");
    }
    
    @Property public void checkCastling() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestCastling.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	String legalMove = classUnderTest.check(file[2],possiblePosition);
    	assertEquals(legalMove,"d1,d2,e2,f1,f2,g1");
    }
    
    @Property public void checkKing() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestKing.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	assertEquals(possiblePosition,"no possible move");
    }
    
    @Property public void checkRook() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestRook.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	String legalMove = classUnderTest.check(file[2],possiblePosition);
    	assertEquals(legalMove,"f5,d5,c5,b5,a5,e6,e7,e4,e3,e2,e1");
    }
    
    @Property public void checkKnight() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestKnight.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	String legalMove = classUnderTest.check(file[2],possiblePosition);
    	assertEquals(legalMove,"b5,c6,c2,e2,f5,f3");
    }
    
    
    @Property public void checkQueen() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestQueen.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	String legalMove = classUnderTest.check(file[2],possiblePosition);
    	assertEquals(legalMove,"e5,e3,c5,c3,e4,c4,d5,d3");
    }
    
    @Property public void checkQueenRook() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestQueenRook.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	String legalMove = classUnderTest.check(file[2],possiblePosition);
    	assertEquals(legalMove,"e4");
    }
    
    @Property public void checkQueenBishop() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestQueenBishop.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	String legalMove = classUnderTest.check(file[2],possiblePosition);
    	assertEquals(legalMove,"e5");
    }
    
    @Property public void checkPawn() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestPawn.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	String legalMove = classUnderTest.check(file[2],possiblePosition);
    	assertEquals(legalMove,"d6,d5,c6");
    }
    
    @Property public void checkBishop() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestBishop.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	String legalMove = classUnderTest.check(file[2],possiblePosition);
    	assertEquals(legalMove,"g6,g4,h7,h3,e6,e4,d7,d3,c8,c2,b1");
    }
    
    @Property public void checkGeneralPawn() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestGeneralPawn.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	String legalMove = classUnderTest.check(file[2],possiblePosition);
    	assertEquals(legalMove,"e5");
    }
    
    @Property public void checkIllegal() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestIllegal.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	assertEquals(possiblePosition,"illegal input");
    }
    
    @Property public void checkInputNull() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestInputNull.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	assertEquals(possiblePosition,"illegal input");
    }
    
    @Property public void checkMoreKing() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestMoreKing.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	assertEquals(possiblePosition,"illegal input");
    }
    
    @Property public void checkMorePawn() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestMorePawn.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	assertEquals(possiblePosition,"illegal input");
    }
    
    @Property public void checkMoreRook() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestMoreRook.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	assertEquals(possiblePosition,"illegal input");
    }
    
    @Property public void checkMoreBishop() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestMoreBishop.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	assertEquals(possiblePosition,"illegal input");
    }
    @Property public void checkMoreQueen() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestMoreQueen.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	assertEquals(possiblePosition,"illegal input");
    }
    @Property public void checkMoreKnight() throws IOException {
    	Chess classUnderTest = new Chess();
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestMoreKnight.txt");
    	String possiblePosition = classUnderTest.move(file[2],file);
    	assertEquals(possiblePosition,"illegal input");
    }
}
