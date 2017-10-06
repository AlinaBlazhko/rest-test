package enums;

/**
 * Created by X240 on 10/6/2017.
 */
public enum FacultiesOfBioengineering {
    FACULTY_OF_BIOENGINEERING(0, 20, "Факультет биоинженерии и биоинформатики");

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

    FacultiesOfBioengineering(Integer number, Integer id, String name){
        this.number = number;
        this.id = id;
        this.name = name;
    }
}
