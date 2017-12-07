package forevtechnologies.alegriauiux.models;

/**
 * Created by jojosexbomb69 on 6/12/17.
 */

public class ZiyadCartModel {
    public String name;
    public int price;


    public ZiyadCartModel(String name, int price) {
        this.name = name;
        this.price = price;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    }
