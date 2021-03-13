package Model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Rows {
    private ArrayList<Pair<Column,Object>> value;

    public Rows(ArrayList<Pair<Column,Object>> value) {
        this.value=value;
    }

    public ArrayList<Pair<Column,Object>> getValue() {
        return value;
    }

}
