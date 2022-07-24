package jp.kcgi.oop.finalProject;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.List;

public interface Users {

    public static final String FILE_PATH
            = "C:\\Users\\Owner\\IdeaProjects\\OOPs\\src\\jp\\kcgi\\oop\\finalProject\\database.csv";

    public void login(int id,String pwd);


    public  static void readData(String file) {
        try {
            // creating file object for openCSV library to use
            FileReader filereader = new FileReader(file);

            //CSVReader class read/display data
            CSVReader csvReader = new CSVReaderBuilder(filereader).build();
            List<String[]> Data = csvReader.readAll();

            // display Data
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.printf("%10s %10s %10s %10s", "DESTINATION", "DISTANCE", "COST", "RATING");
            System.out.print("\n");

            for (String[] row : Data) {
                for (String cell : row) {
                    System.out.print(cell + "\t\t\t  ");
                }
                System.out.println();
            }

            System.out.println("---------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
