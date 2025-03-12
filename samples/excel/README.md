# 氏名突合支援 API (Excel サンプルコード)

## 概要

Excel VBA を用いた氏名突合支援 API を利用するための独自関数（MatchName、PredictKanji、PredictKana）を提供し、氏名突合支援 API を用いた氏名突合・氏名推定を Excel シート上で行うことができるサンプルファイル（モジュール）です。

## 事前準備
### サンプルファイルのダウンロード

1. [kanjikana.xlsm](./kanjikana.xlsm) をダウンロードしてください。
2. VBA マクロが埋め込まれたファイルであるため、Windows のセキュリティ機能でマクロ実行がブロックされる場合があります。その場合には、ダウンロードしたファイルを右クリック、`プロパティ` を開き、プロパティウィンドウ内の `全般` タブ、`セキュリティ` 項目の `許可する` のチェックを入れて、`OK` をクリックしてください。

### API キーの取得

1. [氏名突合支援サービスサポートサイト](https://kktg.digital.go.jp/) より API キー（32文字の英数字列）を取得してください。
2. 取得した API キーを `設定` シートの `APIキー` 内にB2セルの `REPLACE_WITH_YOUR_API_KEY` を置き換える形で入力してください。

### （オプション）独自 Excel ファイルへの組み込み

1. [VBA-JSON](https://github.com/VBA-tools/VBA-JSON)（対象ファイル: `JsonConverter.bas`）及び [VBA-Dictionary](https://github.com/VBA-tools/VBA-Dictionary)（対象ファイル: `Dictionary.cls`）をダウンロードしてください。
2. ダウンロードした [kanjikana.xlsm](./kanjikana.xlsm) から標準モジュール `KanjiKanaNameMatch` をエクスポート（既定ファイル名: `KanjiKanaNameMatch.bas`）してください。
3. 独自 Excel ファイル（マクロ有効ブック形式 `*.xlsm`）に対し、標準モジュールに `JsonConverter.bas` と `KanjiKanaNameMatch.bas` を、クラスモジュールに `Dictionary.cls` をインポートしてください。
4. 標準モジュール `KanjiKanaNameMatch` 内の `strApiKey = "REPLACE_WITH_YOUR_API_KEY"` の `REPLACE_WITH_YOUR_API_KEY` を取得した API キーで置き換えてください。

## 使い方

### 氏名突合機能

#### 仕様
##### 入力
```
MatchName(漢字氏名, カナ氏名, [詳細モデル利用フラグ])
```

| 引数 | 内容 |
| --- | --- |
| 漢字氏名 | 突合したい氏名の漢字・アルファベット |
| カナ氏名 | 突合したい氏名のカナ |
| 詳細モデル利用フラグ | TRUE = 詳細モデル / FALSE = 簡易モデル (既定値: FALSE)  |

##### 出力
`0` ～ `99` の間の値が返却されます。`50` 以上の場合には正しいと考えられるものとして、値を返却しています。
```
90
```

#### 関数の入力例
セルA2に漢字氏名、セルB2にカナ氏名が入力されている状況で、簡易モデルで突合する
```
=MatchName(A2, B2)
```

セルA2に漢字氏名、セルB2にカナ氏名が入力されている状況で、詳細モデルで突合する
```
=MatchName(A2, B2, TRUE)
```

### 氏名推定機能

#### 仕様
##### 入力
```
PredictKana(漢字氏名, [返却最大数])     // 漢字 → カナ
PredictKanji(カナ氏名, [返却最大数])    // カナ → 漢字
```
| 引数 | 内容 |
| --- | --- |
| 漢字氏名 | 突合したい氏名の漢字・アルファベット |
| カナ氏名 | 突合したい氏名のカナ |
| 返却最大数 | 1～10の値 (既定値: 1)  |

##### 出力
候補を上位から順番にセミコロン `;` 区切りで列挙します。カッコ内の値は全候補を 100% として、そのうちのどの程度を占めるかの割合を示します。 
```
ニホンイチロウ (62%); ニッポンイチロウ (2%); ニモトイチロウ (0%)
日本一郎 (84%); 日本一朗 (0%); ニッポン一郎 (0%); にっぽん一郎 (0%); 日本市郎 (0%)
```

#### 関数の入力例
セルA2に漢字氏名が入力されている状況で、カナ氏名を推定する
```
=PredictKana(A2)
```

セルB2にカナ氏名が入力されている状況で、漢字氏名を上位2候補推定する
```
=PredictKanji(B2, 5)
```

## 制限事項
- インターネット接続（api.kktg.digital.go.jp への HTTPS 通信）が必要です。
- Windows 11 + Microsoft Office (Excel for Microsoft 365, Version 2408) 環境で動作確認しています。
- 現時点で Mac (macOS) での動作はしません。
