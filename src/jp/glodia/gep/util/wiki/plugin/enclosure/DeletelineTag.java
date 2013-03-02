package jp.glodia.gep.util.wiki.plugin.enclosure;


/**
 * 取消線タグ置換プラグインクラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public class DeletelineTag extends EnclosedTagPlugin {

    {
        wikiEnclosureSymbol = "%%";
        htmlEnclosureSymbol = "del";
        options = "class=\"default\"";
    }

}
