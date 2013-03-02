package jp.glodia.gep.util.wiki.plugin.enclosure;


/**
 * 強調タグ置換プラグインクラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public class BoldTag extends EnclosedTagPlugin {

    {
        wikiEnclosureSymbol = "''";
        htmlEnclosureSymbol = "b";
        options = "class=\"default\"";
    }

}
