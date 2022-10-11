package com.qing.www.util;


import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * Token相关工具类
 */
public class TokenUtils {
    /**
     * 生成token
     * @return
     */
    public static String generateToken(String cardId, String userSign) {
        return JWT.create().withAudience(cardId) //将cardId保存在token里作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),2)) //2h后token过期
                .sign(Algorithm.HMAC256(userSign));//以password作为token密钥

    }
}
