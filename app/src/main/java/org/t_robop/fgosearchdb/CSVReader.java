package org.t_robop.fgosearchdb;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taiga on 2017/01/15.
 */

public class CSVReader {

    private String fileName;
    private Integer num;

    /**
     * コンストラクタ
     * @param fileName ファイル名
     * @param num 読み込み行数(0の場合全量読み込み)
     */
    public CSVReader(String fileName, int num){

        // フィールド設定
        this.fileName = fileName;
        this.num = num;
    }

    /**
     * CSVファイルの読み込みを実行する
     * @return
     */
    public List<List<String>> read(Context context) {

        // 入力チェック
        if(this.fileName == null || this.num == null){
            return null;
        }

        // 返却用リスト箱作成
        List<List<String>> ret = new ArrayList<List<String>>();

        // AssetManagerの呼び出し
        AssetManager assetManager = context.getResources().getAssets();

        BufferedReader br = null;

        try {
            // CSVファイルの読み込み
            InputStream is = assetManager.open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(is,"SJIS");
            //ファイルを読み込んでbr
            br = new BufferedReader(inputStreamReader);

            // num行読み込む（0の場合は全行）
            String line = "";
            int idx = 0;
            while ((line = br.readLine()) != null) {

                // 1行を格納する箱作成
                List<String> tmpList = new ArrayList<String>();

                // 文字列index
                int idxFrom = 0;
                int idxTo = 0;
                // 文字列長
                while (true){

                    // 最終項目を取得後は終了
                    if(idxFrom > line.length()){
                        break;
                    }

                    // 次のセパレータ位置を取得
                    idxTo = line.indexOf(",", idxFrom);

                    // セパレータが発見できない場合は最終項目を取得
                    if( idxTo == -1 ){
                        idxTo = line.length();
                    }

                    // 文字列取得
                    String word = line.substring(idxFrom, idxTo);

                    // 文字列を格納
                    tmpList.add(word);

                    // 検索開始位置を更新
                    idxFrom = idxTo + 1;

//                    if(tmpList.size()>=30){
//                        break;
//                    }
                }

                // 返却用リストに1行データを格納
                ret.add(tmpList);

                // カウンタ
                if(idx % 1000 == 0){
                    System.out.println("入力: " + idx + " 件");
                }
                idx++;

                // numを超えたら読み込み終了。numが0のときは全量読む。

                if( num != 0 && idx > num ){
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

}
