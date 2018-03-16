import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
    	String [] file = new Read().readFile("C://Users//94088//Desktop//Drexel//Software Reliability//TestInputNull.txt");
    	for (int i = 0; i<3; i++) {
    		System.out.println(file[i]);
    	}

    	Chess test = new Chess();
    	String possiblePosition = test.move(file[2],file);
    	System.out.println("originl move:"+possiblePosition);
    	if(possiblePosition != "illegal input" && possiblePosition != "no possible move" ) {
    		System.out.println("legal   move:"+test.check(file[2],possiblePosition));
    	} else {
    		System.out.println("legal   move:"+possiblePosition);
    	}
    }
}
