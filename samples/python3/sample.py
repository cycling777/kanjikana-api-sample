# Copyright (c) 2024 デジタル庁
# 
# This software is released under the MIT License.
# https://opensource.org/licenses/MIT

import requests

version = "v1"
method = "simple" # or detail
kanji="日本［東京］　花子"
kana="トウキョウ　ハナコ"
key="REPLACE_WITH_YOUR_API_KEY"

url = f"https://api.kktg.digital.go.jp/{version}/{method}"

params = {
  'kanji': kanji,
  'kana': kana,
  'key': key,
}

response = requests.get(url, params=params)

# レスポンスのステータスコードを確認
print(response.status_code)  # 200

# レスポンスのコンテンツを表示
print(response.text)
