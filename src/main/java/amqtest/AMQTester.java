package amqtest;

import javax.jms.DeliveryMode;
import javax.jms.JMSContext;
import javax.jms.Queue;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

/*
 * simple tester for Atremis. Takes server & queue information on the command line, 
 * sends a simple message to the queue and prints back a response. 
 * 
 * args[0] = server
 * args[1] = username
 * args[2] = password
 * args[3] = queue name
 */
public class AMQTester {

	private static final String MESSAGE = "TEST MESSAGE";

	public static void main(String[] args) {

		String brokerurl = args[0];
		String username = args[1];
		String password = args[2];

		ActiveMQConnectionFactory cf=null;
		Queue queue = ActiveMQJMSClient.createQueue(args[3]);

		try {
			cf = new ActiveMQConnectionFactory(brokerurl, username, password);
			JMSContext jmsContext = cf.createContext();

			jmsContext.createProducer().setDeliveryMode(DeliveryMode.PERSISTENT).send(queue, MESSAGE);
			System.out.println("Sent Message: " + MESSAGE);

			String response = jmsContext.createConsumer(queue).receiveBody(String.class);
			System.out.println("Received Message: " + response);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("--------------------------------");
			ex.printStackTrace();
		} finally {
			cf.close();

		}

	}

}
