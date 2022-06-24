package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
public class MainApp {
   //Користувачи
   private static final String[] names = {"Sotona", "Siska", "Volodya", "Valera"};
   private static final String[] lastNames = {"Atttsky", "Piva", "Putler", "Deagle"};
   private static final String[] mails = {"sotona@hell.com", "pivas@kb.ru", "ozero-coop@spb.ru", "valera@magnum-research.us"};
   private static final String[] carModels = {"HellMobile", "Toyota", "Volga", "Belka"};
   private static final int[] carSeries = {666, 123, 777, 0};
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      carService.add(new Car(carModels[0], carSeries[0]));
      carService.add(new Car(carModels[1], carSeries[1]));
      carService.add(new Car(carModels[2], carSeries[2]));
      carService.add(new Car(carModels[3], carSeries[3]));

      userService.add(new User(names[0], lastNames[0], mails[0], carService.getListCar().get(0)));
      userService.add(new User(names[1], lastNames[1], mails[1], carService.getListCar().get(1)));
      userService.add(new User(names[2], lastNames[2], mails[2], carService.getListCar().get(2)));
      userService.add(new User(names[3], lastNames[3], mails[3], carService.getListCar().get(3)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+ user.getId());
         System.out.println("First Name = "+ user.getFirstName());
         System.out.println("Last Name = "+ user.getLastName());
         System.out.println("Email = "+ user.getEmail());
         System.out.println();
      }

      System.out.println(userService.findOwnerByCar("Toyota", 123).toString());

      context.close();
   }
}
