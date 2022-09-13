package cn.irisz.irisz_blog_backend.utils;

import cn.irisz.irisz_blog_backend.common.exception.BusinessException;
import cn.irisz.irisz_blog_backend.common.result.ResponseEnum;
import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * <p>
 * JWT工具类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021/8/22 0022
 */

public class JwtUtils {

    private static final long TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;
    private static final String TOKEN_SIGN_KEY = "xjjngcqjm";
    private static final String TOKEN_USER_ID_KEY = "userId";
    private static final String TOKEN_USERNAME_KEY = "userName";

    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES512;
        byte[] bytes = DatatypeConverter.parseBase64Binary(TOKEN_SIGN_KEY);
        return new SecretKeySpec(bytes, signatureAlgorithm.getJcaName());
    }

    public static Long getTokenExpiresTime(){
        return TOKEN_EXPIRATION;
    }

    /**
     * 根据用户名和ID生成token
     */
    public static String createToken(Long userId, String userName) {
        System.out.println(getKeyInstance());
        return Jwts.builder()
                .setSubject("SRB-USER")
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .claim(TOKEN_USER_ID_KEY, userId)
                .claim(TOKEN_USERNAME_KEY, userName)
                .signWith(SignatureAlgorithm.HS512, getKeyInstance())
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }


    /**
     * 判断token是否有效
     */
    public static boolean checkToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据token获取用户ID
     */
    public static Long getUserId(String token) {
        Claims claims = getClaims(token);
        return (Long) claims.get(TOKEN_USER_ID_KEY);
    }

    /**
     * 根据token获取用户名
     */
    public static String getUsername(String token) {
        Claims claims = getClaims(token);
        return (String) claims.get(TOKEN_USERNAME_KEY);
    }

    /**
     * jwt token无需删除，客户端扔掉即可。
     */
    public static void removeToken(String token) { }

    /**
     * 刷新Token
     */
    public String refreshToken(String token){
        Claims claims = getClaims(token);
        String userName = (String) claims.get(TOKEN_USERNAME_KEY);
        Long userId = (Long) claims.get(TOKEN_USER_ID_KEY);
        return createToken(userId, userName);
    }

    /**
     * 校验token并返回Claims
     */
    public static Claims getClaims(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(ResponseEnum.LOGIN_AUTH_ERROR);
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (Exception e) {
            throw new BusinessException(ResponseEnum.LOGIN_TOKEN_CHECK_ERROR);
        }
    }
}
