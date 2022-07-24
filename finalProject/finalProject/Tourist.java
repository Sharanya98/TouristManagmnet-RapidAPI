package jp.kcgi.oop.finalProject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.util.Iterator;
import java.util.Scanner;

import static jp.kcgi.oop.finalProject.Admin.allOperation;

class Tourist implements Users {
    private int id;
    private String pwd;

    @Override
    public void login(int id, String pwd) {
        this.id = id;
        this.pwd = pwd;
        System.out.println("login as tourist");
        operation();
    }

    public void operation() {
        System.out.println("choose the operation");
        System.out.println("1.view all of destination details");
        System.out.println("2.compare chart");
        System.out.println("3.exit");

        Scanner sc = new Scanner(System.in);
        byte operation = sc.nextByte();
        switch (operation) {
            case 1:

                Users.readData(FILE_PATH);
                operation();
                break;
            case 2:
                compareChart();

                break;

            case 3:
//
                break;

            case 6:
                break;
            default:
                System.out.println("Wrong Input");
        }
    }

    public void compareChart() {

        getDetailsfromAPI();

    }

    public void getDetailsfromAPI() {

        Scanner sc = new Scanner(System.in);

        System.out.println("choose country");
        String fcountry = sc.nextLine();
        fcountry = fcountry.replace(" ", "%20");
        System.out.println("choose city");
        String fcity = sc.nextLine();
        fcity =fcity.replace(" ", "%20");


        try {

//            getting resonse from rapidAPI URL
            HttpResponse<JsonNode> response = Unirest.get("https://cost-of-living-and-prices.p.rapidapi.com/prices?city_name="+fcity+"&country_name="+fcountry+"")
                    .header("X-RapidAPI-Key", "85d3ffcbc8msh3caab9f2aa2545fp130091jsn6e0ab5b574c0")
                    .queryString("parameter", "value")
                    .asJson();

// converting response in to JSON objct
            JsonNode ret = response.getBody();


            System.out.println(ret.getObject().names());
            JSONObject obj = ret.getObject();
            Iterator<Object> iterator = obj.getJSONArray("prices").iterator();
            org.json.JSONObject data = (JSONObject) iterator.next();
            System.out.println("Country Name :" +obj.getString("country_name"));
            System.out.println("City Name :" + obj.getString("city_name"));
            System.out.println("City Name :" + obj.getString("city_name"));
            System.out.println("Currency Code :" + data.get("currency_code"));
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Price of Daily food Items");
            dailyItems(obj);
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Cost of Transportation");
            transportation(obj);
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Utilities Per Month");
            utilities(obj);
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("price of Restaurants");
            restaurant(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void dailyItems(JSONObject obj){

        Iterator<Object> iterator = obj.getJSONArray("prices").iterator();

        int count = 0;
        while (iterator.hasNext()) {
            org.json.JSONObject data1 = (JSONObject) iterator.next();
            String str = (String) data1.get("category_name");

            if (str.equalsIgnoreCase("Markets")) {
                count++;
                if (count == 5) {
                    break;
                }
                System.out.println("Item:" + data1.get("item_name") + "\tAverage Price:" + data1.get("avg"));
            }
        }
    }

    public static void transportation(JSONObject obj){

        Iterator<Object> iterator = obj.getJSONArray("prices").iterator();

        int count = 0;
        while (iterator.hasNext()) {
            org.json.JSONObject data1 = (JSONObject) iterator.next();
            String str = (String) data1.get("category_name");

            if (str.equalsIgnoreCase("Transportation")) {
                count++;
                if (count == 5) {
                    break;
                }
                System.out.println("Item:" + data1.get("item_name") + "\tAverage Price:" + data1.get("avg"));
            }
        }
    }

    public static void utilities(JSONObject obj){

        Iterator<Object> iterator = obj.getJSONArray("prices").iterator();

        int count = 0;
        while (iterator.hasNext()) {
            org.json.JSONObject data1 = (JSONObject) iterator.next();
            String str = (String) data1.get("category_name");

            if (str.equalsIgnoreCase("Utilities Per Month")) {
                count++;
                if (count == 5) {
                    break;
                }
                System.out.println("Item:" + data1.get("item_name") + "\tAverage Price:" + data1.get("avg"));
            }
        }
    }

    public static void restaurant(JSONObject obj){

        Iterator<Object> iterator = obj.getJSONArray("prices").iterator();

        int count = 0;
        while (iterator.hasNext()) {
            org.json.JSONObject data1 = (JSONObject) iterator.next();
            String str = (String) data1.get("category_name");

            if (str.equalsIgnoreCase("Restaurants")) {
                count++;
                if (count == 5) {
                    break;
                }
                System.out.println("Item:" + data1.get("item_name") + "\tAverage Price:" + data1.get("avg"));
            }
        }
    }
}

