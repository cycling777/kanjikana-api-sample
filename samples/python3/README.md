# 氏名突合支援 API (Node.js サンプルコード)

## API キーの取得

事前に https://kktg.digital.go.jp/ から、API キーを取得してください

## 使い方

### 事前準備
```
python -m venv venv
. venv/bin/activate
pip install -r requirements.txt
```


### 漢字カナ突合機能
`matching.py` プログラム中のキーを，取得したキーで置き換える。
```python
key="REPLACE_WITH_YOUR_API_KEY"
```

実行
```bash
python matching.py
```