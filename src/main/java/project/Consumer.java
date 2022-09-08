package project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.config.MyConfig;
import project.entity.User;
import java.io.IOException;
import java.util.List;

public class Consumer {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        User user = new User(3L, "James", "Brown", (byte) 32);
        User user1 = new User(3L, "Thomas", "Shelby", (byte) 32);

        List<User> allUsers = communication.showAllUsers();
        System.out.println(allUsers);

        communication.saveUser(user);
        communication.updateUser(user1);
        communication.delete(3L);
    }
}
