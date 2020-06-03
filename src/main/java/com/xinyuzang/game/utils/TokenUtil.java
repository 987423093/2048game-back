package com.xinyuzang.game.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Objects;

/**
 * @Description:
 * @Author: zhoutao29203
 * @Date: 2020/6/3 13:35
 * @Copyright: 2020 Hundsun All rights reserved.
 */
public class TokenUtil {

    /**
     * 签名秘钥
     */
    private static final String SECRET = "XinYuZang";
    /**
     * JWT的签发者，是否使用是可选的
     */
    private static final String ISSUUSER = "www.xinyuzang.com";
    /**
     * JWT所面向的用户，是否使用时可选的
     */
    private static final String SUBJECT = "xiaozhou@xinyuzang.com";

    /**
     * 生成token
     *
     * @param id
     * @return
     */
    public static String createJwtToken(int id) {

        //十天过期时间
        long ttlMillis = 10 * 24 * 60 * 60 * 1000;
        return createJwtToken(id, ttlMillis);
    }

    /**
     * 生成token
     *
     * @param id
     * @param ttlMillis
     * @return
     */
    public static String createJwtToken(int id, long ttlMillis) {

        //签名算法,对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //通过密钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //构建jwt
        JwtBuilder builder = Jwts.builder().setId(id + "")
                .setIssuedAt(now)
                .setSubject(SUBJECT)
                .setIssuer(ISSUUSER)
                .signWith(signatureAlgorithm, signingKey);

        //过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }


    /**
     * 校验token
     *
     * @param userId
     * @param token
     * @return
     */
    public static boolean verifyToken(int userId, String token) {

        return Objects.equals(parseJWT(token), userId + "");
    }

    /**
     * 根据jwt解密获得userId
     *
     * @param jwt
     * @return
     */
    public static String parseJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims.getId();
    }
}
