package vacuousversifer.trollian;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {
   @MessageMapping("/message")
   @SendTo("/event/message")
   public Message greeting(Message message) throws Exception {
      System.out.println("Got Message: " + message.getContent());
      return new Message(HtmlUtils.htmlEscape(message.getContent()));
   }
   
   @MessageMapping("/join")
   @SendTo("/event/join")
   public Message join(User user) throws Exception {
      System.out.println("New member: " + user.getName());
      return new Message(HtmlUtils.htmlEscape(user.getName()));
   }
}
