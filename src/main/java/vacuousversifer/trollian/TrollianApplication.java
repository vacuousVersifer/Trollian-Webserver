package vacuousversifer.trollian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class TrollianApplication {
   
   public static void main(String[] args) throws UnknownHostException {
      System.out.println(InetAddress.getLocalHost());
      SpringApplication.run(TrollianApplication.class, args);
   }
   
}
