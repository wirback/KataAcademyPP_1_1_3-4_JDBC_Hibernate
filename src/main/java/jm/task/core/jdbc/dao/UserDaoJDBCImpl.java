package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {

    }


    private void processingSQL(String sql) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        processingSQL("""
                CREATE TABLE IF NOT EXISTS users_table(
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(30),
                last_name VARCHAR(30),
                age TINYINT
                )""");
    }

    public void dropUsersTable() {
        processingSQL("DROP TABLE IF EXISTS users_table");
    }

    public void saveUser(String name, String lastName, byte age) {
        processingSQL(String.format("""
                INSERT INTO users_table(name, last_name, age)
                VALUES('%s', '%s', '%s')
                """, name, lastName, age)
        );
    }

    public void removeUserById(long id) {
        processingSQL(String.format("DELETE FROM users_table WHERE %s", id));
    }

    public List<User> getAllUsers() {
        List<User> list = null;

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM users_table");
             ResultSet resultSet = ps.executeQuery())
        {
            list = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));

                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void cleanUsersTable() {
        processingSQL("DELETE FROM users_table");
    }
}
