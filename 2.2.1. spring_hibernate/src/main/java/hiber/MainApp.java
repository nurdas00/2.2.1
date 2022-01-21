package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.management.Query;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      userService.add(user1);
      Car bmw = new Car(1234, "bmw");
      bmw.setUser(user1);
      user1.setCar(bmw);
      userService.update(user1);

      userService.add(user2);
      Car audi = new Car(3333, "audi");
      audi.setUser(user2);
      user2.setCar(audi);
      userService.update(user2);

      userService.add(user3);
      Car jaguar = new Car(1111, "jaguar");
      jaguar.setUser(user3);
      user3.setCar(jaguar);
      userService.update(user3);

      userService.add(user4);
      Car bentley = new Car(2222, "bentley");
      bentley.setUser(user4);
      user4.setCar(bentley);
      userService.update(user4);

      List<User> users = userService.listUsers();

      for (User user : users) {
         System.out.println(user);
      }

      System.out.println("_______________________________________________________________");

      System.out.println(userService.userByAuto1("jaguar", 1111));
      context.close();
   }
}
