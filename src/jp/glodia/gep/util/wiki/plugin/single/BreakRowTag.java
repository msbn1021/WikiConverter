package jp.glodia.gep.util.wiki.plugin.single;


/**
 * 改行タグ自動挿入プラグインクラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public class BreakRowTag extends SingleTagPlugin {

    {
        wikiSymbolArray = new String[]{"\r\n", "\n", "\r", "\\~"};
        htmlSymbol = "br";
        options = "";
    }
}
