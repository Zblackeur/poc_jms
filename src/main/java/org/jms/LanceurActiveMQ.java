package org.jms;

import org.apache.activemq.broker.BrokerService;

public class LanceurActiveMQ {
public static void main(String[] args) {
try {
	BrokerService brokerService =new BrokerService();
	//désactiver la persistance
	brokerService.setPersistent(false);
	//Accepter la connexion de n'importe quelle adresse IP (0.0.0.0)
	// fixer le port à 61616
	brokerService.addConnector("tcp://0.0.0.0:61616");
	brokerService.start();
	System.out.println("Démarrage du broker...");
	}
catch (Exception e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}