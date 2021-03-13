package Model;

import Exceptions.MySQLException;

import java.util.ArrayList;

public class Schema implements DefaultColumns{
    private ArrayList<Column> Columns;
    private Integer id;
    private Column PKColumn;
    private Column CreatedColumn;
    private Integer CustomColumns;
    public Schema(ArrayList<Column> Columns) {
        this.Columns =Columns;
        this.id=0;
        this.CustomColumns=Columns.size();
        addTimeStampColumn();
        addPrimaryKeyColumn();
    }

    public ArrayList<Column> getColumns() {
        return Columns;
    }


    public void addColumn(Column column) {
        this.Columns.add(column);
    }

    @Override
    public void addTimeStampColumn() {
        try {
            CreatedColumn = new Column("Created", "STRING");
            addColumn(CreatedColumn);
        }
        catch (MySQLException e) {
            System.out.println("Default Error");
        }
    }

    @Override
    public void addPrimaryKeyColumn() {
        try {
            PKColumn=new Column("PrimaryKey", "INTEGER");
            addColumn(PKColumn);
        }
        catch (MySQLException e) {
            System.out.println("Default Error");
        }
    }

    public void IncrementID() {
        this.id++;
    }
     public void DecrementID() {
        this.id--;
     }

    public Integer getId() {
        return id;
    }

    public Column getPKColumn() {
        return this.PKColumn;
    }

    public Column getCreatedColumn() {
        return this.CreatedColumn;
    }

    public Integer getCustomColumns() {
        return CustomColumns;
    }
}
