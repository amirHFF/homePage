package com.homepage.home.config;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public abstract class JwtUtil {
	private static final int expireMinToken = 15;
	private static final int expireMinRefreshToken = 72;

	private static final String SECRET = "FBA898697394CDBC534E7ED86A97AA59F627FE6B309E0A21EEC6C9B130E0369C";

	public static String generateAccessToken(String username , String issuer, List<String> roles){
		String accessToken = null;
		JWTClaimsSet claimsSet=new JWTClaimsSet.Builder().subject(username).issuer(issuer).claim("roles",roles)
				.expirationTime(Date.from(Instant.now().plusSeconds(15*expireMinToken))).issueTime(new Timestamp(System.currentTimeMillis())).build();

		Payload payload=new Payload(claimsSet.toJSONObject());

		JWSObject jwsObject=new JWSObject(new JWSHeader(JWSAlgorithm.HS256),payload);

		try {
			jwsObject.sign(new MACSigner(SECRET));
			accessToken = jwsObject.serialize();
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	public static String generateRefreshToken(String username){

		return null;
	}

	public static UsernamePasswordAuthenticationToken parseToken(String token) throws ParseException, JOSEException, BadJOSEException {
		byte[] tokenAsByte=token.getBytes();
		SignedJWT signedJWT=SignedJWT.parse(token);
		signedJWT.verify(new MACVerifier(tokenAsByte));

		ConfigurableJWTProcessor<SecurityContext> jwtProcessor=new DefaultJWTProcessor<>();

		JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(JWSAlgorithm.HS256,
				new ImmutableSecret<>(tokenAsByte));
		jwtProcessor.setJWSKeySelector(keySelector);
		jwtProcessor.process(signedJWT, null);
		JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
		String username = claims.getSubject();
		var roles = (List<String>) claims.getClaim("roles");
		var authorities = roles == null ? null : roles.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		return new UsernamePasswordAuthenticationToken(username, null, authorities);

	}
}
