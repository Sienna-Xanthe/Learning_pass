package cn.race.common.regx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailUtil {
    /**
     * 校验邮箱是否合法
     * @param mail
     * @return
     */
    public static Boolean isMatchesMail(String mail){
        String regex = "\\w+@\\w+\\.(com|cn)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mail);
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMatchesMail("907167912@qq.com"));
    }
}
