package com.cuubez.key.exchange.ecdh;

import java.security.Security;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.cuubez.key.exchange.IParameterInitializer;

public class ECDHParameterInitializer implements IParameterInitializer {

	public static final String p256 = "prime256v1";
	public static final String p512 = "prime512v1";
	public static final String p1024 = "prime1024v1";

	private String curveName;

	public String getCurveName() {
		return curveName;
	}

	public void setCurveName(String curveName) {
		this.curveName = curveName;
	}

	public Object initialize() {
		// TODO Auto-generated method stub
		Security.addProvider(new BouncyCastleProvider());
		return ECNamedCurveTable.getParameterSpec(this.getCurveName());
	}

}
