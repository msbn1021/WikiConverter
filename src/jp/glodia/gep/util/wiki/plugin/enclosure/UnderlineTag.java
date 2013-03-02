package jp.glodia.gep.util.wiki.plugin.enclosure;


/**
 * 下線タグ置換プラグインクラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public class UnderlineTag extends EnclosedTagPlugin {

    {
        wikiEnclosureSymbol = "%%%";
        htmlEnclosureSymbol = "u";
        options = "class=\"default\"";
    }
}
