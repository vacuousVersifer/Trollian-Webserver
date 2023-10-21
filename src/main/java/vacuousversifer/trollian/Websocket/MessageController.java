package vacuousversifer.trollian.Websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import vacuousversifer.trollian.DataTypes.Message;
import vacuousversifer.trollian.DataTypes.User;

@Controller
public class MessageController {
   @MessageMapping("/message")
   @SendTo("/event/message")
   public Message greeting(Message message) {
      System.out.println("Got Message: " + message.content());
      return new Message(HtmlUtils.htmlEscape(message.content()));
   }
   
   @MessageMapping("/join")
   @SendTo("/event/join")
   public Message join(User user) {
      System.out.println("New member: " + user.name());
      return new Message(HtmlUtils.htmlEscape(user.name()));
   }
}
