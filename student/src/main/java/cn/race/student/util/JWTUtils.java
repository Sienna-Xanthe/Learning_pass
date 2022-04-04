package cn.race.student.util;

import cn.race.common.response.BusinessException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Map;

@Component
public class JWTUtils {

    /**
     * 生成 token
     */
    public static String getToken(Map<String, String> map) {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,7); //7天时间过期
        Algorithm algorithm = Algorithm.HMAC256("Xinmachong666");
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        // 附带username信息
        return builder
                //到期时间
                .withExpiresAt(calendar.getTime())

                //创建一个新的JWT，并使用给定的算法进行标记
                .sign(algorithm);
    }

    /**
     * 校验 token 是否正确
     */
    public static DecodedJWT verify(String token) {

        return JWT.require(Algorithm.HMAC256("Xinmachong666")).build().verify(token);
    }

    /**
     * 注销token
     */
    public static void deleteToken(HttpServletRequest request){
        if(request==null)return;
       request.setAttribute("token","");
    }


}

