# 氏名突合支援サービス

## 説明

入力された名前の漢字文字列と読み仮名（フリガナ）であるカタカナ文字列を、デジタル庁が用意した名前突合システムの辞書データと突合し、読み仮名（フリガナ）が正しいかどうかを統計的に判断します。

モデルの詳細については[氏名漢字カナ突合システムサポートサイト](https://kktg.digital.go.jp/support/index.html)を参照のこと。


## 出力
APIからの戻り値はJSON形式で返却されます。`status`の項目が判定結果をあらわし，0から99の値をとります。50以上が漢字文字列とカタカナ文字列が一致していると判定されます。

`status`の値は90,80,70,30,0の値をとりそれぞれの意味は次のとおりです

- 90    
 簡易モデルのみで漢字とカタカナが一致していると判定
- 80    
 簡易モデルではNGだが，詳細モデルの三つのモデル全てで一致していると判定
- 70    
 簡易モデルではNGだが，詳細モデルの二つのモデル全てで一致していると判定
- 30    
  簡易モデルではNGだが，詳細モデルの一つのモデル全てで一致していると判定
- 0    
  簡易モデル，詳細モデルの全てで一致していないと判定
  
## 具体例 1

漢字氏名「日本　太郎」とカナ氏名「ニホン　タロウ」の氏名突合を行う場合

```
// リクエスト (REPLACE_WITH_YOUR_API_KEY は取得したAPIキーで置き換えてください)
curl --get --data-urlencode "kanji=日本　太郎" --data-urlencode "kana=ニホン　タロウ" -d "key=REPLACE_WITH_YOUR_API_KEY" https://api.kktg.digital.go.jp/v1/simple
```

```
// レスポンス
{
    "response": "OK",
    "result": {
        "status": 90
    },
    "version": "1.6o"
}
```

`"status": 90` は、`ほぼ正しい読み方だろう` と意味します。

## 具体例 2

漢字氏名「日本　太郎」とカナ氏名「ジャポン　タロウ」の氏名突合を行う場合

```
// リクエスト (REPLACE_WITH_YOUR_API_KEY は取得したAPIキーで置き換えてください)
curl --get --data-urlencode "kanji=日本　太郎" --data-urlencode "kana=ジャポン　タロウ" -d "key=REPLACE_WITH_YOUR_API_KEY" https://api.kktg.digital.go.jp/v1/simple
```

```
// レスポンス
{
    "response": "OK",
    "result": {
        "status": 0
    },
    "version": "1.6o"
}
```

`"status": 0` は、`ほぼ，正しくない読み方だろう ` と意味します。

## API キーの取得

[氏名突合支援サービスサポートサイト](https://kktg.digital.go.jp/)にアクセスし、取得してください。

初めて利用する場合には、[利用者新規登録] ボタンを押して、新規登録を行い、API キーを取得してください。

すでに登録済みの場合には、https://kktg.digital.go.jp/ から、[ログイン] ボタンを押して、API キーを取得してください。

![login page](./images/registration/kktg-login.png)

## サンプルコード

- [Java](./samples/java/)
- [Node.js](./samples/nodejs/)
- [Python](./samples/python3/)
- [Vue.js](./samples/vue3/)
- [excel](./samples/excel/)