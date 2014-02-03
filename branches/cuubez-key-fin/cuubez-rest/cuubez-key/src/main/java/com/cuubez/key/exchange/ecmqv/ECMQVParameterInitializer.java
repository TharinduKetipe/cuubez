package com.cuubez.key.exchange.ecmqv;

import java.math.BigInteger;
import java.security.Security;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.EllipticCurve;

import org.bouncycastle.jce.ECPointUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import com.cuubez.key.exchange.IParameterInitializer;

public class ECMQVParameterInitializer implements IParameterInitializer{

	public Object initialize() {
		
		Security.addProvider(new BouncyCastleProvider());
		
		EllipticCurve curve = new EllipticCurve(
                new ECFieldFp(new BigInteger("883423532389192164791648750360308885314476597252960362792450860609699839")), // q
                new BigInteger("7fffffffffffffffffffffff7fffffffffff8000000000007ffffffffffc", 16), // a
                new BigInteger("6b016c3bdcf18941d0d654921475ca71a9db2fb27d1d37796185c2942c0a", 16)); // b

        ECParameterSpec ecSpec = new ECParameterSpec(
                curve,
                ECPointUtil.decodePoint(curve, Hex.decode("020ffa963cdca8816ccc33b8642bedf905c3d358573d3f27fbbd3b3cb9aaaf")), // G
                new BigInteger("883423532389192164791648750360308884807550341691627752275345424702807307"), // n
                1); // h
        
        
		// TODO Auto-generated method stub
		return ecSpec;
	}

}
