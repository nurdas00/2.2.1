package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.transaction.internal.TransactionImpl;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public void update(User user){
      Session session = sessionFactory.getSessionFactory().openSession();
      Transaction tx1 = session.beginTransaction();
      session.update(user);
      tx1.commit();
      session.close();
   }

   @Override
   public void delete(Long id) {
      Session session = sessionFactory.getSessionFactory().openSession();
      Transaction tx1 = session.beginTransaction();
      User user = session.load(User.class, id);
      session.delete(user);
      tx1.commit();
      session.close();
   }


   @Override
   public void clean() {
      Session session = sessionFactory.getSessionFactory().openSession();
      Transaction tx1 = session.beginTransaction();
      session.createQuery("delete from User").executeUpdate();
      tx1.commit();
      session.close();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Car> userByAuto(String model, int series) {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car where model = :mod and series = :ser");
      query.setParameter("mod", model);
      query.setParameter("ser", series);
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User userByAuto1(String model, int series) {
      Query<User> query=sessionFactory.getCurrentSession()
              .createQuery("from User user inner join fetch user.car where user.car.model = :mod and user.car.series = :ser");
      query.setParameter("mod", model);
      query.setParameter("ser", series);
      return query.uniqueResult();
   }
}
