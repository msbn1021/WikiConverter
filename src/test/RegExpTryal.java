package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * お試し用
 * @author Owner
 *
 */
public class RegExpTryal {

    public static void main(String[] args) {
        String str = "abc''defghi'jklmn''opqrs'''tu'vw\r\nxy''z";

        Pattern pattern = Pattern.compile("(\'\'(.+?)\'\')");
        Matcher matcher = pattern.matcher(str);

        while(matcher.find()) {
            str = str.replaceAll(matcher.group(1), "<b>" + matcher.group(2) + "</b>");
        }
        p(str);
    }

    private static void p(String str) {
        System.out.println(str);
    }
}
