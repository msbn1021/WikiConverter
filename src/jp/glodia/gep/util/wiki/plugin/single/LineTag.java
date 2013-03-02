package jp.glodia.gep.util.wiki.plugin.single;


/**
 * 罫線タグ置換プラグインクラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public class LineTag extends SingleTagPlugin {

    {
        wikiSymbolArray = new String[]{"\\-\\-\\-\\-"};
        htmlSymbol = "hr";
        options = "size=\"1\"";
    }

}
