package vacuousversifer.trollian;

import lombok.Getter;

@Getter
public class User {
   
   private String name;
   
   public User() {
   }
   
   public User(String name) {
      this.name = name;
   }
   
}
