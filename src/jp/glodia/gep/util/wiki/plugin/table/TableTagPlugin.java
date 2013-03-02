package jp.glodia.gep.util.wiki.plugin.table;

import jp.glodia.gep.exception.GepException;
import jp.glodia.gep.util.wiki.plugin.BaseGepWikiPlugin;


/**
 * 単一タグ置換の抽象クラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public abstract class TableTagPlugin extends BaseGepWikiPlugin {

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

        return wikiText;
    }
}
