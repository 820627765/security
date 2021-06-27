package com.example.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {
    private static final String SECRET = "123!@aab@@!(&&"; //盐值或密钥，不可透露 HMAC256 和 JWT

    public static void main(String[] args) {
        String jwt = createJWT();
        jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6InpzIiwiZXhwIjoxNjI0Nzk1MzQ2LCJ1c2VySWQiOjEsImp0aSI6ImEifQ.r9BymirC-ByvcLR1VIWmV9B4tjJjFRxbAfxUbupb60E";
        System.out.println(jwt);
        parseJWT(jwt);
    }

    public static void parseJWT(String token) {
        //获取一个jwt的验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        //如果验证通过，会返回一个JWT 的解码对象 DecodedJWT，通过该对象可以获取到 header 信息，payload信息，过期时间等。
        //如果验证失败，会抛出异常，如过期异常TokenExpiredException，或其他
        DecodedJWT verify = jwtVerifier.verify(token);
        String header = verify.getHeader();
        String payload = verify.getPayload();
        String signature = verify.getSignature();
        String token1 = verify.getToken();
        System.out.println("header=" + header);
        System.out.println("payload=" + payload);
        System.out.println("signature=" + signature);
        System.out.println("token1=" + token1);

        //获取payload 解码的结果
        System.out.println(verify.getClaims());
        System.out.println(verify.getClaim("userName"));

    }

    public static String createJWT() {
        Map<String, Object> header = new HashMap<>();
        Map<String, Object> payLoad = new HashMap<>();
        payLoad.put("userName", "zs");
        Date expireDate = new Date();
        expireDate.setTime(System.currentTimeMillis() + 1000 * 60);
        String token = JWT.create()
                .withHeader(header)  //header信息,这个可以不写，默认就是
                .withPayload(payLoad) //payload 信息
                .withClaim("userId", 1) //单个payload信息的设置方式
                .withExpiresAt(expireDate) //指定令牌过期的时间
                .withJWTId("a")
                .sign(Algorithm.HMAC256(SECRET));//指定签名算法和签名盐值

        return token;
    }
}
