# UserWallet
## 业务背景：
电商业务中，需要给电商app设计一个用户钱包，用户可以往钱包中充值，购买商品时用户可以使用钱包中的钱消费，商品申请退款成功后钱会退回钱包中，用户也可以申请提现把钱提到银行卡中
## 任务需求：
用程序实现如下api接口
1.  查询用户钱包余额
2. 用户消费100元的接口
3. 用户退款20元接口
4. 查询用户钱包金额变动明细的接口
## 准备数据库
### 创建数据库
```SQL
CREATE DATABASE wallet
```
### 创建表
```SQL
DROP TABLE IF EXISTS userwallet;
CREATE TABLE userwallet (
  id INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户钱包id',
  user_id INT(11) NOT NULL COMMENT '用户id',
  balance DECIMAL(10,2) DEFAULT NULL COMMENT '余额',
  update_time TIMESTAMP NULL DEFAULT NULL COMMENT '余额更新时间',
  PRIMARY KEY (id),
  KEY index_user_id (user_id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS bill;
CREATE TABLE bill (
  id INT(11) NOT NULL AUTO_INCREMENT COMMENT '账单id',
  user_id INT(11) NOT NULL COMMENT '用户id',
  wallet_id INT(11) NOT NULL COMMENT '钱包id',
  balance DECIMAL(10,2) DEFAULT NULL COMMENT '当前余额',
  money DECIMAL(10,2) NOT NULL COMMENT '新增或者减少的金额数目',
  money_status INT(11) NOT NULL COMMENT '1表示增加，2表示减少',
  create_time TIMESTAMP NULL DEFAULT NULL COMMENT '账单创建时间',
  PRIMARY KEY (id),
  KEY index_user_id (user_id),
  KEY index_wallet_id (wallet_id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```
### 插入数据
```SQL
INSERT INTO userWallet VALUES (12, 101, 100.00, '2023-06-25 16:56:01');
INSERT INTO userWallet VALUES (13, 102, 50.00, '2023-06-25 15:59:43');

INSERT INTO bill VALUES (2, 101, 12, 100.00, 20.00, 1,'2023-06-25 16:37:49');
INSERT INTO bill VALUES (3, 101, 12, 120.00, 20.00, 2, '2023-06-25 16:41:40');
INSERT INTO bill VALUES (4, 102, 13, 50.00, 20.00, 1, '2023-06-25 16:41:43');
INSERT INTO bill VALUES (5, 102, 13, 70.00, 20.00, 2,'2023-06-25 16:48:30');
```

