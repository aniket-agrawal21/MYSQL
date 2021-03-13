package Model;

import Model.Rows;

import java.util.ArrayList;

public class Table {

    private Schema schema;
    private String name;
    private ArrayList<Rows> rows;

    public Table(String name) {
        this.name=name;
        rows=new ArrayList<>();
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Rows> getRows() {
        return rows;
    }


    public void addSingleRow(Rows row) {
        this.rows.add(row);
    }

    public String getRowsAsOutput() {
        return rows.toString();
    }
}
