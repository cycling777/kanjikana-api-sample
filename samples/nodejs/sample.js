// Copyright (c) 2025 デジタル庁
// 
// This software is released under the MIT License.
// https://opensource.org/licenses/MIT

import https from 'https';

(async () => {

  const method = 'simple'; // or detail
  const version = 'v1';

  const url = new URL(`https://api.trueno-kktg.digital.go.jp/${version}/${method}`);
  url.search = new URLSearchParams({
    'kanji': '日本　太郎',
    'kana': 'にほん たろう',
    'key': '(api key)',
  });

  const response = await (new Promise((resolve) => {
    https.get(url, (response) => {
      const buffer = [];
      response.on('data', chunk => buffer.push(chunk));
      response.once('end', () => {
        resolve({
          statusCode: response.statusCode,
          body: buffer.join(''),
        })
      })
    });
  }));

  // レスポンスの表示
  console.log(response);

})();