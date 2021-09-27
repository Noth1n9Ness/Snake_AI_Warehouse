package sample.misc;

import java.util.LinkedList;

public class Utils {
    public static LinkedList clone(LinkedList origin) {
        LinkedList clone = (LinkedList) origin.clone();
        return clone;
    }
}