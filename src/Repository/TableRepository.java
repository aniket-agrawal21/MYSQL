package Repository;

import Exceptions.MySQLException;
import Model.*;
import javafx.util.Pair;

import java.util.ArrayList;

public class TableRepository {

    public static Table createTable(String Name) {
        return new Table(Name);
    }

}
