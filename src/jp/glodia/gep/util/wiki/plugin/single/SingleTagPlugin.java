package jp.glodia.gep.util.wiki.plugin.single;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.glodia.gep.exception.GepException;
import jp.glodia.gep.util.wiki.plugin.BaseGepWikiPlugin;


/**
 * 単一タグ置換の抽象クラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public abstract class SingleTagPlugin extends BaseGepWikiPlugin {

    /** Wiki記号 */
    protected String[] wikiSymbolArray;

    /** Html記号 */
    protected String htmlSymbol;

    /** タグオプション文字列 */
    protected String options;

    /*
     * (非 Javadoc)
     * @see jp.glodia.gep.util.wiki.plugin.GepWikiPlugin
     * #process(java.lang.String)
     */
    @Override
    public String process(String wikiText) throws GepException {
        for(String symbol: wikiSymbolArray) {
            checkRequiredField(symbol, "wikiSymbol[]");
        }
        checkRequiredField(htmlSymbol, "htmlSymbol");
        return convert(wikiText);
    }

    /*
     * (非 Javadoc)
     * @see jp.glodia.gep.util.wiki.plugin.GepWikiPlugin
     * #convert(java.lang.String)
     */
    @Override
    public String convert(String wikiText) throws GepException {
        // wikiSymbolArrayに設定された置換対象毎に置換していく.
        for(String wikiSymbol: wikiSymbolArray) {
            // 正規表現定義.
            Pattern pattern = Pattern.compile(wikiSymbol);

            // 検索モジュール.
            Matcher matcher = pattern.matcher(wikiText);

            // 検索+置換の実行
            while(matcher.find()) {
                wikiText = wikiText.replaceAll(
                        matcher.group(), "<" + htmlSymbol + " " + options + ">");
            }
        }
        return wikiText;
    }
}
