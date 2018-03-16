import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedReader;

public class Read {
	public String[] readFile(String file) throws IOException {
		File filename = new File(file);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
		BufferedReader br = new BufferedReader(reader);
		String [] s1 = new String [3];
		int pos = 0;
		while(pos<3) {
			s1[pos] = br.readLine();
			if(s1[pos].charAt(s1[pos].length()-1) != ':') {
				s1[pos] = s1[pos].split(":")[1];
			} else {
				s1[pos] = "";
			}
			pos++;
		}
		br.close();
        return s1;
    }
}
