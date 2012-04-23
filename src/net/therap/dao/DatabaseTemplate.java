package net.therap.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 4/9/12
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
class DatabaseTemplate {
    private String databaseUrl;
    private String databaseUserName;
    private String databaseUserPassword;
    //private DatabaseUser databaseUser;

    public DatabaseTemplate(String databaseUrl, String databaseUserName,String databaseUserPassword) {
        this.databaseUrl =databaseUrl;
        this.databaseUserName = databaseUserName;
        this.databaseUserPassword = databaseUserPassword;

    }

    /*public DatabaseTemplate(String databaseUrl, String databaseUserName,String databaseUserPassword, DatabaseUser databaseUser) {
        this.databaseUrl = databaseUrl;
        this.databaseUserName = databaseUserName;
        this.databaseUserPassword = databaseUserPassword;
        //this.databaseUser = databaseUser;
    }*/

    void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    void setDatabaseUserName(String databaseUserName) {
        this.databaseUserName = databaseUserName;
    }

    void setDatabaseUserPassword(String databaseUserPassword) {
        this.databaseUserPassword = databaseUserPassword;
    }

    Connection openConnection() {
        Connection connection;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(databaseUrl, databaseUserName, databaseUserPassword);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return connection;
    }


    /*void execute(DatabaseUser databaseUser) {
        Connection conToUse = openConnection();


        try {
            databaseUser.doWithDatabase(conToUse);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            closeConnection(conToUse);
        }


    }*/

    void execute(String query) {
        Connection conToUse = openConnection();
        Statement stmt = null;
        try {
            stmt = conToUse.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                stmt.close();
            }
            catch (NullPointerException e) {
                closeConnection(conToUse);
                throw new RuntimeException(e);
            }catch (SQLException e) {
                closeConnection(conToUse);
                throw new RuntimeException(e);
            }


        }
    }

    <E> List<E> queryForObject(String query, RowObjectMapper<E> rowObjectMapper) {
        Connection conToUse = openConnection();
        Statement stmt = null;
        ResultSet resultSet = null;
        List<E> listOfE = new ArrayList<E>();
        try {
            stmt = conToUse.createStatement();
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                listOfE.add(rowObjectMapper.mapRowToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                resultSet.close();
                stmt.close();
            }
            catch (NullPointerException e) {
                closeConnection(conToUse);
                throw new RuntimeException(e);
            }catch (SQLException e) {
                closeConnection(conToUse);
                throw new RuntimeException(e);
            }



        }
        return listOfE;
    }

    void executeQuery(String query, Object... parameters) {
        Connection conToUse = openConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conToUse.prepareStatement(query);
            int i = 1;
            for (Object parameter : parameters) {
                if (parameter instanceof String) {
                    preparedStatement.setString(i, (String) parameter);
                } else if (parameter instanceof Integer) {
                    preparedStatement.setInt(i, (Integer) parameter);
                } else if (parameter instanceof Long) {
                    preparedStatement.setLong(i, (Long) parameter);
                }
                i++;
            }

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {

                preparedStatement.close();
            } catch (NullPointerException e) {
                closeConnection(conToUse);
                throw new RuntimeException(e);
            } catch (SQLException e) {
                closeConnection(conToUse);
                throw new RuntimeException(e);
            }
        }
    }

    void closeConnection(Connection conToClose) {
        try {
            conToClose.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
