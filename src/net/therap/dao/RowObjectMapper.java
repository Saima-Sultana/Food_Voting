package net.therap.dao;
import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 4/10/12
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
interface RowObjectMapper<E> {

     E mapRowToObject(ResultSet resultSet);
}
