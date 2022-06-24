package hiber.dao;

import hiber.model.User;
import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> getListUsers();
   User findOwnerByCar(String series, int model);
}
