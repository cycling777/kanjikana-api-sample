# 氏名突合支援 API (Java サンプルコード)

## API キーの取得

事前に https://kktg.digital.go.jp/ から、API キーを取得してください

## 使い方

```
mvn clean compile assembly:single
java -cp target/sample-1.0-SNAPSHOT-jar-with-dependencies.jar jp.go.digital.kanjikana.samples.App --apikey REPLACE_WITH_YOUR_API_KEY --kanji "日本［東京］　花子" --kana "トウキョウ　ハナコ"
```

レスポンス
```json
{
    "response":"OK",
    "result":{
        "status":90
    },
   "version":"1.6o" 
}
```