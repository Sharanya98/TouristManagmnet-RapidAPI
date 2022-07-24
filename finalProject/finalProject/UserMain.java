package jp.kcgi.oop.finalProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserMain {
    private static final String FILE_PATH
            = "C:\\Users\\Owner\\IdeaProjects\\OOPs\\src\\jp\\kcgi\\oop\\finalProject\\database.csv";
    public static void main(String[] args) {

        //Id=1111 and password='abcd'for admin
        profileOperation();

    }

    public static void profileOperation(){
        System.out.println("choose your profile?");
        System.out.println("1.Admin");   //Id=1111 and password='abcd'for admin
        System.out.println("2.Tourist");

        Scanner sc = new Scanner(System.in);
        byte userType = sc.nextByte();

        switch (userType){
            case 1:
                System.out.println("Enter Id");//Id=1111 and password='abcd'for admin
                int a_id = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Password");
                String a_pwd = sc.nextLine();
                Admin a = new Admin();
                a.login(a_id,a_pwd);
                break;
            case 2:
                System.out.println("1.login as Tourist");
                byte type = sc.nextByte();
                Tourist c = new Tourist();
                switch(type){
                    case 1:

//                        Logging process
                        System.out.println("Choose id(4 digit between[0-9])");
                        int new_id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Choose Password(4 character between[a-z])");
                        String new_pwd = sc.nextLine();
                        c.login(new_id,new_pwd);
                        break;
                    case 2:
                        profileOperation();
                }
                break;
            default:
                System.out.println("Wrong Input");


        }
    }
}
