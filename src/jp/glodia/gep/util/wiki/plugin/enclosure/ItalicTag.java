package jp.glodia.gep.util.wiki.plugin.enclosure;


/**
 * 斜体タグ置換プラグインクラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public class ItalicTag extends EnclosedTagPlugin {

    {
        wikiEnclosureSymbol = "'''";
        htmlEnclosureSymbol = "i";
        options = "class=\"default\"";
    }
}
