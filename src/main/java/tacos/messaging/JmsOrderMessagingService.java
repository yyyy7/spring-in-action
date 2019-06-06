package tacos.messaging;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import tacos.Order;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

  private JmsTemplate jms;
  private Destination orderQueue;

  @Bean
  public Destination orderQueue() {
    return new ActiveMQQueue("tacocloud.order.queue");
  }

  @Autowired
  public JmsOrderMessagingService(JmsTemplate jms, Destination orderQueue{
    this.jms = jms;
    this.orderQueue = orderQueue;
  }

  @Override
  public void sendOrder(Order order) {
    jms.convertAndSend("tacocloud.order.queue", order);
  }

  
}