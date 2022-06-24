package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
//      sessionFactory.getCurrentSession().save(user.getCar());
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getListUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User findOwnerByCar(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("FROM User us WHERE us.car.series = :series AND us.car.model = :model")
              .setParameter("series", series)
              .setParameter("model", model);
      //Тут можливе виникнення винятку оскільки результат може бути неуникальным, тобто наявність двох однакових записів.
      return query.getSingleResult();
   }
}
