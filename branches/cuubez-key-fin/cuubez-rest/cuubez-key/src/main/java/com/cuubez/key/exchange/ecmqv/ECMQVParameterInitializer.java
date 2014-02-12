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

public class ECMQVParameterInitializer implements IParameterInitializer {

	// public static final String p192 = "prime192v1";
	// public static final String p192 = "prime192v2";
	// public static final String p192 = "prime192v3";
	// public static final String p239 = "prime239v1";
	// public static final String p239 = "prime239v2";
	// public static final String p239 = "prime239v3";
	// public static final String p256 = "prime256v1";

	public Object initialize() {

		Security.addProvider(new BouncyCastleProvider());

		// prime239v1
		EllipticCurve curve = new EllipticCurve(
				new ECFieldFp(
						new BigInteger(
								"883423532389192164791648750360308885314476597252960362792450860609699839")), // q
				new BigInteger(
						"7fffffffffffffffffffffff7fffffffffff8000000000007ffffffffffc",
						16), // a
				new BigInteger(
						"6b016c3bdcf18941d0d654921475ca71a9db2fb27d1d37796185c2942c0a",
						16)); // b

		ECParameterSpec ecSpec = new ECParameterSpec(
				curve,
				ECPointUtil.decodePoint(
						curve,
						Hex.decode("020ffa963cdca8816ccc33b8642bedf905c3d358573d3f27fbbd3b3cb9aaaf")), // G
				new BigInteger(
						"883423532389192164791648750360308884807550341691627752275345424702807307"), // n
				1); // h

		/*
		 * //prime256v1 EllipticCurve curve = new EllipticCurve( new
		 * ECFieldFp(new BigInteger(
		 * "115792089210356248762697446949407573530086143415290314195533631308867097853951"
		 * )), // q new BigInteger(
		 * "ffffffff00000001000000000000000000000000fffffffffffffffffffffffc",
		 * 16), // a new BigInteger(
		 * "5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b",
		 * 16)); // b
		 * 
		 * ECParameterSpec ecSpec = new ECParameterSpec( curve,
		 * ECPointUtil.decodePoint(curve, Hex.decode(
		 * "036b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296"
		 * )), // G new BigInteger(
		 * "ffffffff00000000ffffffffffffffffbce6faada7179e84f3b9cac2fc632551",
		 * 16), // n some thing wrong here Need to get real n Value here 1); //
		 * h
		 */

		// TODO Auto-generated method stub
		return ecSpec;

		// Tried to bring Named Curve support to MQV
		// But support for ECMQV with named curve does not seems to be exist
		// with bouncy Castle
		// Problem is to get ECParameterSpec with named curve,It's not working
		// as listed in
		// http://www.bouncycastle.org/wiki/display/JA1/Elliptic+Curve+Key+Pair+Generation+and+Key+Factories

		// How ever You may be able to use JDK for named curve with lower keys
		// ECGenParameterSpec ecGenSpec = new ECGenParameterSpec("prime192v1");
		// Link to Parameters
		// https://android.googlesource.com/platform/external/bouncycastle/+/e1142c149e244797ce73b0e7fad40816e447a817/bcprov/src/main/java/org/bouncycastle/asn1/x9/X962NamedCurves.java
	}

}
