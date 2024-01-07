import java.sql.*;

//interface 
public interface Database{
    void view() throws SQLException;
    void insert() throws SQLException;
    void update() throws SQLException;
    void save() throws SQLException;
    void delete() throws SQLException;
}




