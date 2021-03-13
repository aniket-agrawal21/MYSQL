package Model;

import Exceptions.MySQLException;
import javafx.util.Pair;

import java.util.ArrayList;

public class Column implements Constraint {
    private String name;
    private TYPE type;
    private ArrayList<AVAILABLE_CONSTRAINTS> constraint;


    public Column(String name, String type) throws MySQLException {
        this.name=name;
        Pair<Boolean,TYPE> pair=validateType(type);
        if(validateType(type).getKey()) {
            this.type=pair.getValue();
        }
        else {
            throw new MySQLException("Type Not Supported");
        }
        constraint=new ArrayList<>();
    }

    public Column(String name,String type,String Constraint) throws MySQLException {
        this.name=name;
        Pair<Boolean,TYPE> pair=validateType(type);
        if(validateType(type).getKey()) {
            this.type=pair.getValue();
        }
        else {
            throw new MySQLException("Type Not Supported");
        }
        constraint=new ArrayList<>();
        addConstraint(Constraint);
    }

    @Override
    public void addConstraint(String Constraint) throws MySQLException {
        for(AVAILABLE_CONSTRAINTS available_constraints: AVAILABLE_CONSTRAINTS.values()) {
            if(available_constraints.name().equals(Constraint)) {
                this.constraint.add(available_constraints);
                break;
            }
            else {
                throw new MySQLException("Constraint Not Supported");
            }
        }
    }

    public Pair<Boolean,TYPE> validateType(String type) {
        for(TYPE type_value: TYPE.values()) {
            if(type_value.name().equals(type)) {
                return (new Pair<>(true,type_value));
            }
        }
        return (new Pair<>(false,null));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public ArrayList<AVAILABLE_CONSTRAINTS> getConstraint() {
        return this.constraint;
    }

    public boolean validate(Object o) {
        return true;
    }
}

