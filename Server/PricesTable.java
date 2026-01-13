package Server;

import org.w3c.dom.ls.LSOutput;

public class PricesTable {

    public String city;
    public String time;
    public double price;

    public static String toMsgPriceTable = "";


    public PricesTable(String city, String time, double price) {
        this.city = city;
        this.time = time;
        this.price = price;
        PricesTable.setToMsg(city, time, price);
    }



    private static void setToMsg(String city, String time, double price) {
        PricesTable.toMsgPriceTable += city+" -- "+time+" -- $"+price+"\n";
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
