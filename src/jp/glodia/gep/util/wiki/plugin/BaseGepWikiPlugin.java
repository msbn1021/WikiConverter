package jp.glodia.gep.util.wiki.plugin;

import jp.glodia.gep.exception.GepException;

/**
 * 基底プラグインクラス.<br>
 * プラグインクラスで共通の機能をここで定義する.<br>
 *
 * @author Glodia株式会社 システムソリューション部門 K.Nakaoka
 */
public abstract class BaseGepWikiPlugin implements GepWikiPlugin {

    /**
     * 設定必須の文字列変数がnullならば例外を投げる.<br>
     *
     * @param requiredString 必須文字列変数値
     * @param fieldName 必須文字列変数名
     * @throws GepException Gep例外
     */
    protected void checkRequiredField(String requiredString, String fieldName) throws GepException {
        // 下位クラスで必ず値を設定する必要がある変数がnullならばエラーとする.
        if(requiredString == null) {
            throw new GepException(
                    "プラグインの {0} が null です. " +
                    "( plugin={1} )",
                    new Object[]{
                            fieldName,
                            this.getClass().getName()
                    }
            );
        }
    }
}
