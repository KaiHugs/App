package fr.stocks.api;

import io.restassured.response.Response;

import java.util.*;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;

import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

public class YahooAPI {

    public static HashMap<String,List<List<Double>>> getStockHistory(String interval, String range, String symbols) {
        baseURI="https://yfapi.net";
        String[] symbolList=symbols.split(",");
        Response res=   given().queryParam("interval",interval).
                        queryParam("range",range).
                        queryParam("symbols",symbols).
                        contentType("application/json").
                        header("accept","application/json").
                        header("X-API-KEY","mXzFbRtl4A7I7OY4ftaY2aWsUuUte3B2aqbWXk4g").  //API KEY sign up https://www.yahoofinanceapi.com/dashboard
                        when().get("/v8/finance/spark")
                        .then().assertThat().statusCode(200).extract().response();

        //System.out.println(res.prettyPrint());
        //

        HashMap<String,List<List<Double>>> responseData=new HashMap<String,List<List<Double>>>();

        JsonPath js=res.jsonPath();

        for(int i=0;i< symbolList.length;i++) {

            //List<Double> timestamp =js.getList("\""+symbolList[i]+"\".timestamp");
        	
        	
        	List<Double> timestamp = js.getList("\'"+symbolList[i]+"\'.timestamp", Double.class);
        	List<Double> firstSymbolCloseList=js.getList("\'"+symbolList[i]+"\'.close", Double.class);
        	List<List<Double>> aaa = new ArrayList<>();
            aaa.add(timestamp);
            aaa.add(firstSymbolCloseList);

            //System.out.println("......................."+timestamp.get(0));
            responseData.put(symbolList[i], aaa);
            //responseData.put(symbolList[i]+".close",firstSymbolCloseList);
        }
        //System.out.println(responseData.get("AAPL").get(1).get(0).getClass().getName());
        return responseData;
    }
    
    
    public static List<String> getSymbolsList(String region) {

        baseURI="https://yfapi.net";

        Response res=   given().

      //  System.out.println("response.Data.get("APPL").get(1).get(0).getClass().getName())")

                contentType("application/json").
                header("accept","application/json").
                header("X-API-KEY","mXzFbRtl4A7I7OY4ftaY2aWsUuUte3B2aqbWXk4g").  //API KEY
                when().get("/v1/finance/trending/"+region)
                .then().assertThat().statusCode(200).extract().response();

        //System.out.println(res.prettyPrint());

        List<String> symbolList=new ArrayList<>();

        JsonPath js=res.jsonPath();

        ArrayList<String> symbols =js.get("finance.result[0].quotes");
        for(int i=0;i<symbols.size();i++) {
            String symbol =js.get("finance.result[0].quotes["+i+"].symbol").toString();
            symbolList.add(symbol);
        }

        return symbolList;

        for(Entry<String, List<List<Double>>> entry : d.entrySet()){
            String key = entry.getKey();
            List<List<Double>> value = entry.getValue();
            System.out.println(key+""+value);
            graphs[counter] = new Graph(key, value);
            counter++
        }



    }

    /* 
    ArrayList<String> symbols = js.get("finance.result[0].quotes");
    for(int i=0;i<symbols.size();i++){
        String symbol = js.get("finance.result[0].quotes["+i+"].symbol").toString();
        symbolList.add(symbol);
    }

    /*
    public static void main(String[] args) {

        //String symbols="AAPL,MSFT";
        String symbols="";
        int count=0;
        List<String> symbol=getSymbolsList("US");

        for(int i=0;i<symbol.size();i++) {

            System.out.println(symbol.get(i));
            symbols+=symbol.get(i);
            if(i!=(symbol.size()-1)) {
                symbols+=",";
            }
            count++;
            if(count==10 || i==(symbol.size()-1)) {
                count=0;
                HashMap<String,List<Double>> responseData = getStockHistory("1wk","3mo",symbols);

                String[] symbolList=symbols.split(",");

                for(int j=0;j<symbolList.length;j++) {

                    System.out.println(symbolList[j]);
                    for(int k = 0; k< responseData.get(symbolList[j]+".timestamp").size(); k++){
                        System.out.println(responseData.get(symbolList[j]+".timestamp").get(k));
                    }

                    for(int k = 0; k< responseData.get(symbolList[j]+".close").size(); k++){
                        System.out.println(responseData.get(symbolList[j]+".close").get(k));
                    }

                    System.out.println("...............................................");
                }

                symbols="";
            }
        }
    }
    */
}
