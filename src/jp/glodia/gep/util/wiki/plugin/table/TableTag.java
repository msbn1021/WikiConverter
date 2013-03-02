package jp.glodia.gep.util.wiki.plugin.table;


/**
 * 罫線タグ置換プラグインクラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public class TableTag extends TableTagPlugin {

    {
        wikiSymbolArray = new String[]{"\\-\\-\\-\\-"};
        htmlSymbol = "hr";
        options = "size=\"1\"";
    }

}
