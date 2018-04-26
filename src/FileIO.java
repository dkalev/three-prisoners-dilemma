
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileIO {

	/**
	 * Stores the results from the policy evaluation in a .csv file
	 * @param policies
	 * @param fileName
	 */
	public static void writeToFile(List<String> games, String fileName) {
		StringBuilder builder = new StringBuilder();
		
		for(String game : games) {
				builder.append(game);
				builder.append("\n");
		}
				
		
		try {
			File f = new File(fileName);
			if(f.exists()) {
				FileWriter fw = new FileWriter(f, true);
				fw.write(builder.toString());
				fw.close();
			}else {
				FileWriter fw = new FileWriter(f, false);
				fw.write(builder.toString());
				fw.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
