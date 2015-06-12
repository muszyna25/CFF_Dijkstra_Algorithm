import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReadFromFile {

	final static Charset ENCODING = StandardCharsets.UTF_8;

	static ArrayList<Double> array;
	int sizeOfMparameter = 0;

	public ReadFromFile() {

	}

	public static ArrayList<Double> getArray() {
		return array;
	}

	public int getSizeOfMparameter() {
		return sizeOfMparameter;
	}

	public void setSizeOfMparameter(int sizeOfMparameter) {
		this.sizeOfMparameter = sizeOfMparameter;
	}

	public double[][] readDataFromFile(String arg) {
		
		array = new ArrayList<Double>();

		System.out.println("Working Directory = " + System.getProperty("user.dir")+"/" + arg);
		
		final String FILE_NAME = System.getProperty("user.dir") + "/" + arg;

		System.out.println("Reading data from file.\n");

		try {
			readLargerTextFileAlternate(FILE_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Reading successfully completed.\n");

		System.out.println("Converting to double[][].\n");

		double[][] pointCloud = new double[array.size()][getSizeOfMparameter()];

		int embedding = 0;
		boolean check = false;

		for (int i = 0; i < array.size(); i++) {

			if (check) {
				break;
			}

			for (int j = 0; j < getSizeOfMparameter(); j++) {
				if (j + embedding == array.size()) {
					check = true;
					break;
				}
				pointCloud[i][j] = Double.parseDouble(array.get(j + embedding).toString());
				System.out.print(pointCloud[i][j] + ",");
			}
			embedding = embedding + getSizeOfMparameter();
        	System.out.println();
		}

		System.out.println("Converting successfully completed.\n");
		System.out.println("Ready to use!\n");
		return pointCloud;
	}

	void readLargerTextFileAlternate(String aFileName) throws IOException {
		Path path = Paths.get(aFileName);
		try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String delims = ",";
				String[] tokens = line.split(delims);
				for (int i = 0; i < tokens.length; i++) {
					double point = Double.parseDouble(tokens[i]);
					array.add(point);
				}
				sizeOfMparameter = tokens.length;
			}
		}
	}
}
