package com.zhihao.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {


    public static String createToken(String claimName, Map<String, Object> claim, Integer expire, String secret) {
        DateTime time = DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, expire); // 获取日期偏移量，1天后的时间
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withClaim(claimName, claim).withExpiresAt(time).sign(algorithm);
    }

    public static String createToken(String claimName, String claim, Integer expire, String secret) {
        DateTime time = DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, expire); // 获取日期偏移量，1天后的时间
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withClaim(claimName, claim).withExpiresAt(time).sign(algorithm);
    }

    /*
     * 获取 token Map
     * */
    public static Map<String, Object> getMap(String token, String claimName) {
        return JWT.decode(token).getClaim(claimName).asMap();
    }

    /*
     * 获取 token String
     * */
    public static String getString(String token, String claimName) {
        return JWT.decode(token).getClaim(claimName).asString();
    }

    /*
     * 获取 token 过期时间
     * */
    public static Long getExpire(String token) {
        return JWT.decode(token).getClaim("exp").asLong();
    }

    /*
     * 验证 token 有效性
     * */
    public static void verifierToken(String token, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWT.require(algorithm).build().verify(token);
    }

}
