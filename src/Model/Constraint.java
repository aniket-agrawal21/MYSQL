package Model;

import Exceptions.MySQLException;

import java.util.ArrayList;

public interface Constraint {

    final Integer SIZE=20;
    final Integer MAX_POSITIVE_VALUE=1024;
    final Integer MAX_NEGATIVE_VALUE=-1024;

    void addConstraint(String Constraint) throws MySQLException;

    default void addDefaultConstraint(ArrayList<AVAILABLE_CONSTRAINTS> available_constraints) {
        available_constraints.add(AVAILABLE_CONSTRAINTS.MAX_LENGTH);
        available_constraints.add(AVAILABLE_CONSTRAINTS.MAX_VALUE);
    }

    default boolean validateMAX_LENGTH(String value) {
        if(value==null) {
            return true;
        }
        if(value.length()>SIZE) {
            return false;
        }
        return true;
    }

    default boolean validateMAX_VALUE(Integer value) {
        if(value==null) {
            return true;
        }
        if(value>MAX_POSITIVE_VALUE || value<MAX_NEGATIVE_VALUE) {
            return false;
        }
        return true;
    }

    default boolean validateNOT_NULL(Object o) {
        if(o==null) {
            return false;
        }
        return true;
    }

    default boolean validateInteger(Object o) {
        try {
            Integer num=(Integer) o;
        }
        catch (ClassCastException e) {
            return false;
        }
        return true;
    }

    default boolean validateString(Object o) {
        try {
            String word=(String) o;
        }
        catch (ClassCastException e) {
            return false;
        }
        return true;
    }
}
