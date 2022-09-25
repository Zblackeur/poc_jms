package org.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producteur2 {

	public static void main(String[] args) {
		// saisir la valeur du code
		Scanner scanner = new Scanner(System.in);
		System.out.println("Vers le code :");
		String code =scanner.nextLine();
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
			//Destination destination = session.createQueue("poc_queue"); 
			//5-Cr�er une destination (Topic)
			Destination destination = session.createTopic("poc_topic"); 
			//6-Cr�er un producteur pour la destination cr��e
			MessageProducer producer = session.createProducer(destination);
			//7- Sp�cifier le mode non persistant
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			//8-Pr�parer un message � envoyer
			TextMessage textMessage =session.createTextMessage();
			textMessage.setText("Hello..avec JMS...");
			// ajouter cette propri�t� de filtre
			textMessage.setStringProperty("code", code);			
			//9-Envoyer le message
			producer.send(textMessage);
			//10-Fermer la session
			session.close();
			//11- Fermer la connexion
			connection.close();
			
			System.out.println("Producteur avec code ("+code+"): envoi du message...");	
		} catch (JMSException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
