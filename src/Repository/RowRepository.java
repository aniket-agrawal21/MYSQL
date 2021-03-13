package Repository;

import Exceptions.MySQLException;
import Model.*;
import javafx.util.Pair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RowRepository {

    public static void addRow(Table table, Object[] row_value) throws MySQLException {
        ArrayList<Pair<Column,Object>> row_metadata=new ArrayList<>();
        ArrayList<Column> columns =table.getSchema().getColumns();

        for(int i=0;i<table.getSchema().getCustomColumns();i++) {
            row_metadata.add(new Pair<>(columns.get(i),row_value[i]));
        }

        if(validateRow(row_metadata)) {
            addDataToDefaultColumn(table,row_metadata);
            Rows row = new Rows(row_metadata);
            table.addSingleRow(row);
        }
        else {
            throw new MySQLException("Invalid Entry for Row, Check schema");
        }
    }

    private static void addDataToDefaultColumn(Table table, ArrayList<Pair<Column, Object>> row_metadata) {
        Column PKColumn=table.getSchema().getPKColumn();
        Column CreatedColumn=table.getSchema().getCreatedColumn();
        table.getSchema().IncrementID();
        row_metadata.add(new Pair<>(PKColumn,table.getSchema().getId()));
        row_metadata.add(new Pair<>(CreatedColumn,new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())));
    }

    static boolean validateRow(ArrayList<Pair<Column, Object>>  row) {
        ArrayList<Pair<Column, Object>> value=row;
        Integer i=0;
        Integer flag=0;
        while(i<value.size()) {
            Column column=value.get(i).getKey();
            Object o=value.get(i).getValue();
            TYPE type=column.getType();
            if(column.getConstraint().size()!=0) {
                for(int j=0;j<column.getConstraint().size();j++) {
                    if(column.getConstraint().get(j)== AVAILABLE_CONSTRAINTS.NOT_NULL) {
                        if(!column.validateNOT_NULL(o));{
                            flag=1;
                            break;
                        }
                    }
                }
            }
            if(type.equals(TYPE.INTEGER)) {
                if(!column.validateInteger(o) || !column.validateMAX_VALUE((Integer) o)) {
                    flag=1;
                    break;
                }
            }
            if(type.equals(TYPE.STRING)) {
                if(!column.validateString(o) || !column.validateMAX_LENGTH((String) o)) {
                    flag=1;
                    break;
                }
            }
            i++;
        }
        if(flag==0) {
            return true;
        }
        return false;
    }
}
