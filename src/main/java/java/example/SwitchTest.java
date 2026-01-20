package java.example;

import java.time.LocalDate;

public class SwitchTest {

    public static void main(String[] args) {
        LocalDate data=LocalDate.now();
        int giorno=switch (data.getDayOfMonth()){
            case 1,2,3 -> 1;
            case 4,5,6 -> 2;
            default -> 3;
        };

        Object obj=data;
        switch (obj){
            case LocalDate l-> System.out.println(l.getDayOfMonth());
            case String s-> System.out.println(s.length());
            default -> System.out.println("Unknown type");
        }
    }
}
