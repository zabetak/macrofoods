package org.macrofoods.backend.services;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;

public final class TokenService {
	public static final TokenService INSTANCE = new TokenService();

	private final Algorithm rsaAlgorithm;
	private final JWTVerifier verifier;

	private TokenService() {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			KeyPair kp = kpg.generateKeyPair();
			rsaAlgorithm = Algorithm.RSA512((RSAPublicKey) kp.getPublic(), (RSAPrivateKey) kp.getPrivate());
			verifier = JWT.require(rsaAlgorithm).withIssuer("macrofoods").acceptLeeway(10).build();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public String create() {
		long afterOneMinute = System.currentTimeMillis() + expiresIn();
		return JWT.create().withIssuer("macrofoods").withExpiresAt(new Date(afterOneMinute)).sign(rsaAlgorithm);
	}

	public void verify(String token) {
		verifier.verify(token);
	}

	public int expiresIn() {
		return 1000 * 60; // 1 minute in ms
	}

}
