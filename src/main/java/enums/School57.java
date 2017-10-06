package enums;

/**
 * Created by X240 on 10/6/2017.
 */
public enum School57 {
    SCHOOL_57(0, 1378, "Школа №57");

    int number;
    int id;
    String name;

    public int getNumber(){
        return number;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    School57(int number, int id, String name){
        this.number = number;
        this.id = id;
        this.name = name;
    }
}
