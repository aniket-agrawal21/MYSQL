package Repository;

import Exceptions.MySQLException;
import Model.Column;

public class ColumnRepository {

    public static Object createColumnWithoutConstraint(String name, String type) {
        try {
            return new Column(name, type);
        }
        catch (MySQLException e) {
            return e;
        }
    }

    public static Object createColumnWithConstraint(String name, String type, String Constraint) {
        try {
            return new Column(name, type,Constraint);
        }
        catch (MySQLException e) {
            return e;
        }
    }

}
