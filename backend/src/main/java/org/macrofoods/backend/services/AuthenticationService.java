package org.macrofoods.backend.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.macrofoods.backend.entities.jpa.AppUser;
import org.macrofoods.backend.entities.jpa.AppUser_;

public final class AuthenticationService {

	private static final String USERNAME_PARAM = "username";
	private final TypedQuery<AppUser> hashinfoByUsername;

	public AuthenticationService(EntityManager em) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AppUser> q = builder.createQuery(AppUser.class);
		Root<AppUser> user = q.from(AppUser.class);
		q.where(builder.equal(user.get(AppUser_.username), builder.parameter(String.class, USERNAME_PARAM)));
		hashinfoByUsername = em.createQuery(q);
	}

	public boolean isAdmin(String username, String password) {
		try {
			hashinfoByUsername.setParameter(USERNAME_PARAM, username);
			AppUser userAuth = hashinfoByUsername.getSingleResult();
			byte[] hash = generateSecret(password, userAuth.getSalt(), userAuth.getIteration());
			return Arrays.equals(userAuth.getHash(), hash);
		} catch (Exception e) {
			return false;
		}
	}

	private byte[] generateSecret(String password, byte[] salt, int iterations)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 32 * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		SecretKey key = skf.generateSecret(spec);
		return key.getEncoded();
	}
}
