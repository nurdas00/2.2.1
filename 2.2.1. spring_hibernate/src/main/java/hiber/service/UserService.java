package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    void update(User user);
    void delete(Long id);
    void clean();
    List<Car> userByAuto(String model, int series);
    User userByAuto1(String model, int series);
}
