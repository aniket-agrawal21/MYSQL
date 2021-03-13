import Exceptions.MySQLException;
import Model.*;
import Repository.ColumnRepository;
import Repository.RowRepository;
import Repository.TableRepository;
import javafx.util.Pair;

import java.util.ArrayList;

public class main {
    public static void main(String args[]) throws MySQLException {

        Table testTable = TableRepository.createTable("User");

        String[] columnName ={"Name", "Place", "AGE", "Thing"};
        String[] Type ={"STRING", "STRING", "INTEGER", "STRING"};
        String[] constraint ={null,null,"NOT_NULL",null};
        ArrayList<Column>columns=new ArrayList<>();
        int flag=0;
        for(int i=0;i<4;i++) {
            Object o;
            Column column;

            if(constraint[i]==null)
                o= ColumnRepository.createColumnWithoutConstraint(columnName[i],Type[i]);
            else
                o=ColumnRepository.createColumnWithConstraint(columnName[i],Type[i],constraint[i]);


            if(o instanceof Column) {
                column= (Column) o;
                columns.add(column);
            }
            else {
                flag=1;
                System.out.println(((MySQLException) o).getMessage());
                break;
            }
        }

        if(flag==0) {
            Schema schema=new Schema(columns);
            testTable.setSchema(schema);
        }
        Object[] row_value={"Aniket", "Delhi", 1022, "Laptop"};
        Object[] row_value1={"Aniket1", "Delhi", 1025, "Laptop"};
        Object[] row_value2={"Aniket", "Delhi", null, "Laptop"};

        try {
            //RowRepository.addRow(testTable,row_value);
            RowRepository.addRow(testTable,row_value2);
            //RowRepository.addRow(testTable,row_value1);

            System.out.println("Successfully Inserted Row");
        }
        catch (MySQLException e) {
            System.out.println("Invalid Entry for Row");
        }
        System.out.println(testTable.getRows().size());
    }

}
