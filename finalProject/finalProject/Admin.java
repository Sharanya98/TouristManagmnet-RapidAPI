package jp.kcgi.oop.finalProject;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

class Admin implements Users {

    private int id;
    private String pwd;

    static boolean flag = true;


    private static final String FILE_PATH
            = "C:\\Users\\Owner\\IdeaProjects\\OOPs\\src\\jp\\kcgi\\oop\\finalProject\\database.csv";

    @Override
    public void login(int id, String pwd) {

        this.id = id;
        this.pwd = pwd;

        if (this.id == 1111 && Objects.equals(this.pwd, "abcd")) {
//            letting admin choose option
            allOperation();

        } else {
            System.out.println("please enter correct credentials");
        }
    }



    public static void addData(String FILE_PATH) {

        Scanner sc1 = new Scanner(System.in);
        List<String[]> data = new ArrayList<String[]>();

        System.out.println("Add Destination");
        String destination = sc1.nextLine();
        System.out.println("Add Distance");
        String distance = sc1.nextLine();
        System.out.println("Add Cost");
        String cost = sc1.nextLine();
        System.out.println("Add Rating");
        String rating = sc1.nextLine();

        try {

            FileWriter outputfile = new FileWriter(FILE_PATH,true);

            CSVWriter writer = new CSVWriter(outputfile);

            data.add(new String[]{destination, distance, cost, rating});
            writer.writeAll(data);
            writer.close();

            System.out.println("destination added succesfully");

        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    public static void deleteData(String filePath,String dest) {
        try {
            // creating file object for openCSV library to use
            FileReader filereader = new FileReader(filePath);

            //CSVReader class read/display data
            CSVReader csvReader = new CSVReaderBuilder(filereader).build();
            List<String[]> Data = csvReader.readAll();



            CSVWriter writer = new CSVWriter(new FileWriter(filePath));
            ;
            for (int i =0;i<Data.size();i++){
                if(Data.get(i)[0].equals(dest)){
                    Data.remove(i);
                    System.out.println("data deleted successfully");
                    flag = false;
                }
            }

            if(flag){

                System.out.println("No data fount");
                allOperation();
            }
            writer.writeAll(Data);
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void allOperation(){
        System.out.println("choose the operation");
        System.out.println("1.add Data into Database");
        System.out.println("2.delete Data from Database");
        System.out.println("3.view Data of Database");
        System.out.println("4.exit");

        Scanner sc = new Scanner(System.in);
        byte operation = sc.nextByte();
        switch (operation) {
            case 1:
                System.out.println("choose your operation)");
                System.out.println("1.start operation)");
                System.out.println("2.stop operation");

                byte opt = sc.nextByte();
                sc.nextLine();
                switch (opt) {
                    case 1:
                        addData(FILE_PATH);
                        String yes = "Y";
                        System.out.println("add more?(Y/N)");
                        String answer = sc.nextLine();
                        if (yes.equalsIgnoreCase(answer)) {
                            addData(FILE_PATH);

                        } else {
                            allOperation();
                        }
                    case 2:
                        allOperation();
                }
                break;
            case 2:

                System.out.println("Enter the destination you want to remove");
                sc.nextLine();
                String answer = sc.nextLine();
                deleteData(FILE_PATH,answer);

                break;

            case 3:
             Users.readData(FILE_PATH);
               allOperation();
                break;

            case 4:
                break;
            default:
                System.out.println("Wrong Input");
        }
    }


}
