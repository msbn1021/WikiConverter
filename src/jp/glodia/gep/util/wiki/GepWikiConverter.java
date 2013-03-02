package jp.glodia.gep.util.wiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jp.glodia.gep.exception.GepException;
import jp.glodia.gep.util.FileUtil;
import jp.glodia.gep.util.wiki.plugin.GepWikiPlugin;


/**
 * Wikiテキストコンバートクラス.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public class GepWikiConverter {

    /** プラグイン設定ファイル名(指定が無い場合は相対パスルートに配置されることを想定) */
    private String defaultConfPath = "./GepWikiPluginList.conf";

    /** GepWikiプラグインリスト */
    private List<GepWikiPlugin> pluginList = new ArrayList<GepWikiPlugin>();


    /**
     * コンストラクタ.<br>
     *
     * @throws GepException Gep例外
     */
    public GepWikiConverter() throws GepException {
        loadConf(defaultConfPath);
    }


    /**
     * コンストラクタ.<br>
     *
     * @param confPath 設定ファイルパス
     * @throws GepException Gep例外
     */
    public GepWikiConverter(String confPath) throws GepException {
        loadConf(confPath);
    }


    /**
     * 設定ファイル読込み.<br>
     *
     * @param confPath 設定ファイルパス
     * @throws GepException Gep例外
     */
    private void loadConf(String confPath) throws GepException {
        BufferedReader reader = null;
        try {
            // 設定ファイルの読込み準備.
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(new File(confPath))));

            // 読込んだ1行を格納する.
            String line = "";

            // 1行ずつ読み込み.
            while((line = reader.readLine()) != null) {
                line = line.trim();

                // 空行でもコメントでもなければ設定として読込む.
                if(!line.equals("") && !line.startsWith("#")) {
                    try {
                        // プラグインクラスのインスタンスを取得.
                        @SuppressWarnings("unchecked")
                        Class<GepWikiPlugin> clazz = (Class<GepWikiPlugin>)
                                Class.forName(line);

                        // プラグインリストに追加.
                        pluginList.add(clazz.newInstance());

                    } catch (ClassNotFoundException e) {
                        // プラグインクラスを生成中に例外発生.
                        throw new GepException(
                                "GepWikiConverterで指定されたプラグインが見つかりませんでした. " +
                                "( class={0} )",
                                new Object[]{line}, e);

                    } catch (InstantiationException e) {
                        // プラグインクラスを生成中に例外発生.
                        throw new GepException(
                                "GepWikiConverterでプラグイン生成中に" +
                                "InstantiaionExceptionが発生しました. " +
                                "( class={0} )",
                                new Object[]{line}, e);

                    } catch (IllegalAccessException e) {
                        // プラグインクラスを生成中に例外発生.
                        throw new GepException(
                                "GepWikiConverterでプラグイン生成中に" +
                                "IllegalAccessExceptionExceptionが発生しました. " +
                                "( class={0} )",
                                new Object[]{line}, e);

                    }
                }
            }

        } catch (FileNotFoundException e) {
            // プラグイン設定ファイルの読込中に例外発生.
            throw new GepException(
                    "GepWikiConverterで読込もうとした設定ファイルが見つかりませんでした. " +
                    "( confPath={0} )",
                    new Object[]{confPath}, e);

        } catch (IOException e) {
            // プラグイン設定ファイルの読込中に例外発生.
            throw new GepException(
                    "GepWikiConverterで設定ファイル読込中にIOExceptionが発生しました. " +
                    "( confPath={0} )",
                    new Object[]{confPath}, e);

        } finally {
            if(reader!=null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 引数で指定されたWikiテキストをHtmlテキストに置換して返す.<br>
     *
     * @param wikiText Wikiテキスト
     * @return Htmlテキスト
     * @throws GepException Gep例外
     */
    public String wiki2html(String wikiText) throws GepException {
        for(GepWikiPlugin plugin: pluginList) {
            wikiText = plugin.process(wikiText);
        }
        return wikiText;
    }


    /**
     * 動作確認用メソッド.<br>
     *
     * @throws GepException
     */
    public static void main(String...args) throws GepException {
        // Wikiテキストの読み込み.
        String wiki = FileUtil.getTextFromFile("./wiki.txt", "UTF-8");
        // Htmlテキストへの変換.
        String html = (new GepWikiConverter()).wiki2html(wiki);
        // 標準出力.
        System.out.println(html);
        // Htmlファイルの生成.
        FileUtil.writeTextToFile(
                // 出力ファイル名.
                "./html.html",
                // HTMLファイルの中身
                "<html>" +
                "<head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                "</head>" +
                "<body>" +
                "<table border=1 bgColor=#DDDDFF width=100%><tr><td><pre>" + wiki + "</pre></td></tr></table><br>" +
                "<table border=1 bgColor=#DDDDFF width=100%><tr><td>" + html + "</td></tr></table>" +
                "</body>" +
                "</html>",
                // 出力文字コード
                "UTF-8");
    }
}
