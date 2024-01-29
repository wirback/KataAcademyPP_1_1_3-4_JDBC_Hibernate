package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        /**
         * ? В методе main класса Main должны происходить следующие операции:
         * * 1. Создание таблицы User(ов)
         * * 2. Добавление 4 User(ов) в таблицу с данными на свой выбор.
         * *    После каждого добавления должен быть вывод в консоль (User с именем – name добавлен в базу данных)
         * * 3. Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
         * * 4. Очистка таблицы User(ов)
         * * 5. Удаление таблицы
         */

        List<User> userList = new ArrayList<>();
        userList.add(new User("Ivan", "Ivanov", (byte) 30));
        userList.add(new User("Petr", "Petrov", (byte) 23));
        userList.add(new User("Elena", "Ivanova", (byte) 25));
        userList.add(new User("Mery", "Sabitova", (byte) 19));

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userList.forEach(user -> {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());
        });

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
