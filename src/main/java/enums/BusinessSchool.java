package enums;

/**
 * Created by X240 on 10/6/2017.
 */
public enum BusinessSchool {
    BUSINESS_SCHOOL(2, 22, "Высшая школа бизнеса");

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

    BusinessSchool(int number, int id, String name){
        this.number = number;
        this.id = id;
        this.name = name;
    }
}
