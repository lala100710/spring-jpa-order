# spring-jpa-order
 利用 H2 Database + Spring Data JPA結合postman完成簡單訂餐練習。

## 功能

### 資料庫連結

#### jdbc:h2:mem:meal

#### 查詢餐點 :查詢所有餐點及根據編號查詢
            :black_small_square:http://localhost:8080/Meal/
            :black_small_square:http://localhost:8080/Meal/:id
 
#### 新增餐點
:black_small_square:http://localhost:8080/Meal/
傳入json格式中需包含name(餐點名稱) 、price（餐點價格）、description(餐點介紹)

#### 修改餐點資訊
:black_small_square:http://localhost:8080/Meal/:id
id為(欲修改的餐點代號)，可修改一至多個欄位。

#### 刪除餐點
:black_small_square:http://localhost:8080/Meal/:id

id 為欲刪除的餐點代號

### 訂單

#### 可以查看所有訂單或者根據訂單編號查看訂單
:black_small_square:http://localhost:8080/Order/
:black_small_square:http://localhost:8080/Order/:id

#### 新增訂單
:black_small_square:http://localhost:8080/Order/

傳入json檔包含waiter、orderItemList(包含餐點名稱及數量)
    
#### 修改訂單資訊
:black_small_square:http://localhost:8080/Order/:id

id為(欲修改的訂單編號)

#### 刪除訂單
:black_small_square:http://localhost:8080/Order/:id
id 為欲刪除的訂單編號
