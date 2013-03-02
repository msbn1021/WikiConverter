package jp.glodia.gep.util.wiki.plugin.enclosure;


/**
 * 強調タグ置換プラグインクラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public class TestTag extends EnclosedTagPlugin {

    {
        wikiEnclosureSymbol = "-";
        htmlEnclosureSymbol = "A";
        options = "class=\"default\"";
    }

}
