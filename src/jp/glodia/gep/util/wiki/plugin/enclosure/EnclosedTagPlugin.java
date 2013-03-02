package jp.glodia.gep.util.wiki.plugin.enclosure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.glodia.gep.exception.GepException;
import jp.glodia.gep.util.wiki.plugin.BaseGepWikiPlugin;


/**
 * 単純囲みタグ置換の抽象クラス.
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public abstract class EnclosedTagPlugin extends BaseGepWikiPlugin {

    /** Wiki囲い記号 */
    protected String wikiEnclosureSymbol;

    /** Html囲い記号 */
    protected String htmlEnclosureSymbol;

    /** タグオプション文字列 */
    protected String options;


    /*
     * (非 Javadoc)
     * @see jp.glodia.gep.util.wiki.plugin.GepWikiPlugin
     * #masterProcess(java.lang.String)
     */
    @Override
    public String process(String wikiText) throws GepException {
        // 下位クラスで必ず値を設定する変数のエラーチェック.
        checkRequiredField(wikiEnclosureSymbol, "wikiEnclosureSymbol");
        checkRequiredField(htmlEnclosureSymbol, "htmlEnclosureSymbol");
        return convert(wikiText);
    }


    /*
     * (非 Javadoc)
     * @see jp.glodia.gep.util.wiki.plugin.GepWikiPlugin
     * #convert(java.lang.String)
     */
    @Override
    public String convert(String wikiText) {
        // 正規表現定義.
        Pattern pattern = Pattern.compile(
                "(" + wikiEnclosureSymbol + "(.+?)" + wikiEnclosureSymbol + ")");

        // 検索モジュール.
        Matcher matcher = pattern.matcher(wikiText);

        // 検索+置換の実行
        while(matcher.find()) {
            // 検索で一致した外郭部分をタグで囲った内郭部分に置換する.
            wikiText = wikiText.replaceAll(
                    // マッチした外郭部を置換する
                    matcher.group(1),
                    // 開始タグ(オプション)
                    "<" + htmlEnclosureSymbol + " " + options + ">" +
                    // マッチした内郭部
                    matcher.group(2) +
                    // 終了タグ
                    "</" + htmlEnclosureSymbol + ">");
        }
        return wikiText;
    }
}
