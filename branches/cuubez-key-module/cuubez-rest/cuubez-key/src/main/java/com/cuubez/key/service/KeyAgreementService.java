package com.cuubez.key.service;

import com.cuubez.key.exchange.dh.DHOriginator;
import com.cuubez.key.exchange.dh.DHReceiver;
import com.cuubez.key.exchange.ecdh.ECDHOriginator;
import com.cuubez.key.exchange.ecdh.ECDHReceiver;
import com.cuubez.key.exchange.ecmqv.ECMQVOriginator;
import com.cuubez.key.exchange.ecmqv.ECMQVReceiver;

public class KeyAgreementService implements IKeyAgreementService{
	
	public static final int DHALGO = 0;
	public static final int ECDHALGO = 1;
	public static final int ECMQVALGO = 2;
	
	public static final int ORIGINATOR = 0;
	public static final int RECIEVER = 1;

	public Object initiateKeyAgreement(int algoIndex, int intialIndex) {
		Object agent = null;
		switch(intialIndex) {
		case 0 : switch(algoIndex) {
			case 0 : agent = new DHOriginator();
			break;
			case 1 : agent = new ECDHOriginator();
			break;
			case 2 : agent = new ECMQVOriginator();
			break;
		}
		break;
		case 1 : switch(algoIndex) {
			case 0 : agent = new DHReceiver();
			break;
			case 1 : agent = new ECDHReceiver();
			break;
			case 2 : agent = new ECMQVReceiver();
			break;
		}
		}
		
		return agent;
		}
	}
