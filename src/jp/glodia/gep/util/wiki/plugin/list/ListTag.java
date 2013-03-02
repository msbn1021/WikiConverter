package jp.glodia.gep.util.wiki.plugin.list;


/**
 * 罫線タグ置換プラグインクラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public class ListTag extends ListTagPlugin {

    {
        wikiSymbolArray = new String[]{"+","\\+"};
        htmlSymbolArray = new String[]{"ul","li"};
        options = "size=\"1\"";
    }

}
