package jp.glodia.gep.util.wiki.plugin;

import jp.glodia.gep.exception.GepException;


/**
 * GepWikiのプラグインを規定するインタフェースクラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public interface GepWikiPlugin {

    /**
     * プラグイン共通プロセス.<br>
     * 変換処理以外の全般的な処理を担う.<br>
     *
     * @param wikiText Wikiテキスト
     * @return Htmlテキスト
     */
    public String process(String wikiText) throws GepException;


    /**
     * WikiテキストをHtmlテキストに変換する処理.<br>
     *
     * @param wikiText Wikiテキスト
     * @return Htmlテキスト
     * @throws GepException
     */
    public String convert(String wikiText) throws GepException;
}
