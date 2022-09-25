package poc_jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consommateur {
	
	public static void main(String[] args) {
		
		try {
			//1-Cr�er un objet ConnectionFactory
			ConnectionFactory connectionFactory =new ActiveMQConnectionFactory("tcp://localhost:61616");
			//2-Etablir une connexion
			Connection connection = connectionFactory.createConnection();
			//3-D�marrer la connexion
			connection.start();
			//4-Cr�er une session
			Session session =connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//5-Cr�er une destination (file d'attente : queue)
			Destination destination = session.createQueue("poc_queue"); 
			//6-Cr�er un Consommateur pour la destination cr��e
			MessageConsumer consumer = session.createConsumer(destination);
			//7-Ajouter un listener pour attendre les messages
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					// TODO Auto-generated method stub
					if (message instanceof TextMessage)
					{
						TextMessage textMessage =(TextMessage)message;
						try {
							System.out.println("R�ception[consommateur]: "+textMessage.getText());
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			
			System.out.println("[Consommateur] :en attente de message...");	

			
		} catch (JMSException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}