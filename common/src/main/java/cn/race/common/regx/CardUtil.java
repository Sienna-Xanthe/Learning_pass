package cn.race.common.regx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardUtil {
    /**
     * 校验身份证是否合法
     *
     * @param card
     * @return
     */
    public static Boolean isMatchesCard(String card) {
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regex = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(card);
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMatchesCard(""));
    }
}
