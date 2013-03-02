package jp.glodia.gep.util.wiki.plugin.list;


/**
 * 罫線タグ置換プラグインクラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public class NumberListTag extends ListTagPlugin {

    {
        wikiSymbolArray = new String[]{"*","\\*"};
        htmlSymbolArray = new String[]{"ol","li"};
        options = "size=\"1\"";
    }

}
