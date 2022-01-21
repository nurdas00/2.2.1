package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   public void update(User user) {
      userDao.update(user);
   }

   @Override
   public void delete(Long id) {
      userDao.delete(id);
   }

   @Override
   public void clean() {
      userDao.clean();
   }

   @Override
   public List<Car> userByAuto(String model, int series) {
      return userDao.userByAuto(model, series);
   }

   @Override
   public User userByAuto1(String model, int series) {
      return userDao.userByAuto1(model, series);
   }
}
