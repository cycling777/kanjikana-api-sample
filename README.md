# 氏名突合支援サービス

## 説明

入力された名前文字列と読み仮名（フリガナ）を、デジタル庁が用意した名前突合システムの辞書データと突合し、読み仮名（フリガナ）が正しいかどうかを統計的に判断します。

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

`"status": 90` は、`90%の確率で正しい読み方だろう` と意味します。

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

`"status": 0` は、`0%の確率で正しい読み方だろう (つまり、正しい読み方ではなさそう)` と意味します。

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
