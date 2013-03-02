package jp.glodia.gep.util.wiki.plugin.function;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.glodia.gep.exception.GepException;


/**
 * 下線タグ置換プラグインクラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 H.Takahashi
 */
public class DateTag extends FunctionTagPlugin {

    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");  
    	
    	
    	wikiFunctionSymbol = "&date&";
    	htmlFunctionSymbol = sdf.format(date);
        options = "class=\"default\"";
    }
    
    /*
     * (非 Javadoc)
     * @see jp.glodia.gep.util.wiki.plugin.GepWikiPlugin
     * #convert(java.lang.String)
     */
    @Override
    public String convert(String wikiText) throws GepException {
        // 正規表現定義.
        Pattern pattern = Pattern.compile(
                "(" + wikiFunctionSymbol+ ")");

        // 検索モジュール.
        Matcher matcher = pattern.matcher(wikiText);

        // 検索+置換の実行
        while(matcher.find()) {
            // 検索で一致した外郭部分をタグで囲った内郭部分に置換する.
            wikiText = wikiText.replaceAll(
                    // マッチした外郭部を置換する
                    matcher.group(1),
                    htmlFunctionSymbol);
        }
    	
        return wikiText;
    }
    
}
