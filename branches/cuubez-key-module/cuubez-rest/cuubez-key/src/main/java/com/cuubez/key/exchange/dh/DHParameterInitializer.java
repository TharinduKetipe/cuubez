package com.cuubez.key.exchange.dh;

import java.math.BigInteger;
import java.security.Security;

import javax.crypto.spec.DHParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.cuubez.key.exchange.IParameterInitializer;

public class DHParameterInitializer implements IParameterInitializer {
	// TODO Auto-generated method stub
	private BigInteger g512;
	private BigInteger p512; 

	private BigInteger g768; 
	private BigInteger p768;

	private BigInteger  g1024;
	private BigInteger  p1024;

	public BigInteger getG512() {
		return g512;
	}



	public void setG512(BigInteger g512) {
		this.g512 = g512;
	}



	public BigInteger getP512() {
		return p512;
	}



	public void setP512(BigInteger p512) {
		this.p512 = p512;
	}



	public BigInteger getG768() {
		return g768;
	}



	public void setG768(BigInteger g768) {
		this.g768 = g768;
	}



	public BigInteger getP768() {
		return p768;
	}



	public void setP768(BigInteger p768) {
		this.p768 = p768;
	}



	public BigInteger getG1024() {
		return g1024;
	}



	public void setG1024(BigInteger g1024) {
		this.g1024 = g1024;
	}



	public BigInteger getP1024() {
		return p1024;
	}



	public void setP1024(BigInteger p1024) {
		this.p1024 = p1024;
	}


	

	public DHParameterInitializer() {
		super();
		g512 = new BigInteger("153d5d6172adb43045b68ae8e1de1070b6137005686d29d3d73a7749199681ee5b212c9b96bfdcfa5b20cd5e3fd2044895d609cf9b410b7a0f12ca1cb9a428cc", 16);
		p512 = new BigInteger("9494fec095f3b85ee286542b3836fc81a5dd0a0349b4c239dd38744d488cf8e31db8bcb7d33b41abb9e5a33cca9144b1cef332c94bf0573bf047a3aca98cdf3b", 16);

		g768 = new BigInteger("7c240073c1316c621df461b71ebb0cdcc90a6e5527e5e126633d131f87461c4dc4afc60c2cb0f053b6758871489a69613e2a8b4c8acde23954c08c81cbd36132cfd64d69e4ed9f8e51ed6e516297206672d5c0a69135df0a5dcf010d289a9ca1", 16);
		p768 = new BigInteger("8c9dd223debed1b80103b8b309715be009d48860ed5ae9b9d5d8159508efd802e3ad4501a7f7e1cfec78844489148cd72da24b21eddd01aa624291c48393e277cfc529e37075eccef957f3616f962d15b44aeab4039d01b817fde9eaa12fd73f", 16);

		g1024 = new BigInteger("1db17639cdf96bc4eabba19454f0b7e5bd4e14862889a725c96eb61048dcd676ceb303d586e30f060dbafd8a571a39c4d823982117da5cc4e0f89c77388b7a08896362429b94a18a327604eb7ff227bffbc83459ade299e57b5f77b50fb045250934938efa145511166e3197373e1b5b1e52de713eb49792bedde722c6717abf", 16);
		p1024 = new BigInteger("a00e283b3c624e5b2b4d9fbc2653b5185d99499b00fd1bf244c6f0bb817b4d1c451b2958d62a0f8a38caef059fb5ecd25d75ed9af403f5b5bdab97a642902f824e3c13789fed95fa106ddfe0ff4a707c85e2eb77d49e68f2808bcea18ce128b178cd287c6bc00efa9a1ad2a673fe0dceace53166f75b81d6709d5f8af7c66bb7", 16);
	}



	public DHParameterSpec initialize() {
		Security.addProvider(new BouncyCastleProvider());
		return  new DHParameterSpec(p1024, g1024);
	}
	
}
