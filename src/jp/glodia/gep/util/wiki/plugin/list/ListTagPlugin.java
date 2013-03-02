package jp.glodia.gep.util.wiki.plugin.list;

import jp.glodia.gep.exception.GepException;
import jp.glodia.gep.util.wiki.plugin.BaseGepWikiPlugin;

/**
 * リスト系機能の抽象クラス
 *
 * @author Glodia株式会社 システムソリューション部門 H.Takahashi
 */
public abstract class ListTagPlugin extends BaseGepWikiPlugin {

    /** Wiki記号 */
    protected String[] wikiSymbolArray;

    /** Html記号のタイプ */
    protected String[] htmlSymbolArray;

    /** タグオプション文字列 */
    protected String options;

	int n=0;
    /*
     * (非 Javadoc)
     * @see jp.glodia.gep.util.wiki.plugin.GepWikiPlugin
     * #process(java.lang.String)
     */
    @Override
    public String process(String wikiText) throws GepException {
        for(String symbol: wikiSymbolArray) {
            checkRequiredField(symbol, "wikiSymbol[]");
        }
        for(String symbol: htmlSymbolArray) {
            checkRequiredField(symbol, "htmlSymbol[]");
        }
        return convert(wikiText);
    }

    /*
     * (非 Javadoc)
     * @see jp.glodia.gep.util.wiki.plugin.GepWikiPlugin
     * #convert(java.lang.String)
     */
    @Override
    public String convert(String wikiText) throws GepException {
    	//受け取ったWikiTextに属性付けをする。
    	
    	//リストに突入した場合にtrueになる。
    	boolean listflag = false;
    	
    	//WikiTextを配列に変更。
    	String[] textArray = wikiText.split("\n");
    	
    	//WikiText配列を同じ長さの属性判定配列を作成
    	String[] type = new String[textArray.length];
    	
    	for(int i=0;i<textArray.length;i++){
			type[i] = "";
			
			textArray[i] = textArray[i].trim();
			
	    		if(listflag == false){
	    			//リストへの初突入時
	        		if(textArray[i].length()>0){
	        			//文字列がある場合
		    			
			    		if(textArray[i].substring(0,1).equals(wikiSymbolArray[0])){
			    			listflag = true ;
			    			type[i] = "liststart";
			    		}else{
			    			listflag = false ;
			    		}
	        		}
	    		}else{
	    			if(textArray[i]==null || textArray[i].equals("")){
	    				//リスト突入のままファイルの終点に突入した場合
	    				if(type[i-1].equals("liststart")){
	    					type[i-1] = "listonce";
	    				}else{
		    				type[i-1] = "listend";
	    				}
		    			listflag = false ;
	    			}else{
	    	    		if(textArray[i].length()>0){
	    	    		//文字列が存在する場合
			    			listflag = false ;
			    			//リストに既に突入している場合
				    		if(textArray[i].substring(0,1).equals(wikiSymbolArray[0])){
				    			type[i] = "list";
				    			listflag = true ;
				    		}else{
				    		//リスト条件が終了した場合
				    			if(i==1){
				    				//ファイルの0行目である場合
				    				type[0] = "listonce";
					    			listflag = false ;
				    			}else{
				    				if(type[i-1].equals("liststart")){
				    					type[i-1] = "listonce";
				    				}else{
					    				type[i-1] = "listend";
				    				}
						    			listflag = false ;
				    			}
				    		}
	    	    		}else{
				    	//文字列が存在しない場合
			    			listflag = false ;
			    			if(i==1){
			    				//ファイルの0行目である場合
			    				type[0] = "listonce";
			    			}else{
			    				type[i-1] = "listend";
			    			}
			    		}
	    			}
	    		}
    	}
    	//最後のループ終了後。ファイルの末尾チェック
		if(type[type.length-1].equals("list")||type[type.length-1].equals("liststart")){
			type[type.length-1]="listend";
			if(type[type.length-2].equals("")){
				type[type.length-1]="listonce";
			}
		}
	    		
    	
		for(int i=0;i<textArray.length;i++){
			if(type[i]!=null){
				if(type[i].equals("listonce")){
					textArray[i]=textArray[i].replaceAll("^"+wikiSymbolArray[1]+"(.*)","<"+htmlSymbolArray[0]+" class="+options+ "><"+htmlSymbolArray[1]+">$1</"+htmlSymbolArray[1]+"></"+htmlSymbolArray[0]+">");
				}
				if(type[i].equals("liststart")){
					textArray[i]=textArray[i].replaceAll("^"+wikiSymbolArray[1]+"(.*)","<"+htmlSymbolArray[0]+" class="+options+ "><"+htmlSymbolArray[1]+">$1</"+htmlSymbolArray[1]+">");
				}
				if(type[i].equals("list")){
					textArray[i]=textArray[i].replaceAll("^"+wikiSymbolArray[1]+"(.*)","<"+htmlSymbolArray[1]+" class="+options+ ">$1</"+htmlSymbolArray[1]+">");
				}
				if(type[i].equals("listend")){
					textArray[i]=textArray[i].replaceAll("^"+wikiSymbolArray[1]+"(.*)","<"+htmlSymbolArray[1]+" class="+options+ ">$1</"+htmlSymbolArray[1]+"></"+htmlSymbolArray[0]+">");
				}
			}
		}

    	StringBuilder builder = new StringBuilder();
    	for(String str : textArray) {
    	  builder.append(str).append("\n");
    	}
    	wikiText = builder.substring(0, builder.length() - 1);
    	
		//出力
        return wikiText;
    }
}
