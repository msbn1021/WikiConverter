package jp.glodia.gep.util.wiki.plugin.function;

import jp.glodia.gep.exception.GepException;
import jp.glodia.gep.util.wiki.plugin.BaseGepWikiPlugin;


/**
 * 独自機能系の抽象クラス
 *
 * @author Glodia株式会社 システムソリューション部門 H.Takahashi
 */
public abstract class FunctionTagPlugin extends BaseGepWikiPlugin {

    /** Wiki機能記号 */
    protected String wikiFunctionSymbol;

    /** Html機能記号 */
    protected String htmlFunctionSymbol;

    /** タグオプション文字列 */
    protected String options;

    /*
     * (非 Javadoc)
     * @see jp.glodia.gep.util.wiki.plugin.GepWikiPlugin
     * #process(java.lang.String)
     */
    @Override
    public String process(String wikiText) throws GepException {
        checkRequiredField(wikiFunctionSymbol, "wikiFunctionSymbol");
        checkRequiredField(htmlFunctionSymbol, "htmlFunctionSymbol");
        return convert(wikiText);
    }

    /*
     * (非 Javadoc)
     * @see jp.glodia.gep.util.wiki.plugin.GepWikiPlugin
     * #convert(java.lang.String)
     */
    @Override
    public String convert(String wikiText) throws GepException {
        //独自に定義する。
        return wikiText;
    }




}
