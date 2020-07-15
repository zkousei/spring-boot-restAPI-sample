# Spring boot 開発おためし用
## RestAPIサンプル

***

###　実装機能

#### ログインAPI (method:POST,path:/login)
 入力されたID,passwordをDBないの値と比較し、OKなら認可用のトークンを返却する

```json
request body
{"id":"userid", "password":"password"}

response body
{"result":true, "token":"randomstring"}

```


#### トークン検証サンプルAPI (method:GET,path:/authsample)
 ログインAPIで払い出されたtokenをリクエストのヘッダに設定する。正しい値であれば正常レスポンスを返却

 ```
 request header
 Authorization: randomstring


 ```

***

### 参考にしたサイト
- プロジェクト構成　
https://qiita.com/YutaKase6/items/7d88fa23f81366905270
- パスワードハッシュ化
https://qiita.com/dumbbell/items/62735f30d8cb33dd2773
