# 91jizhangba
91记账吧
## 接口说明
    注册接口：/register.json
    入参：{"content":{"password":"123","username":"123"}}
    出参：
      正常返回：{"info":{"code":200,"msg":"操作成功！"},"content":""}
      错误返回：{"info":{"code":500,"msg":"该用户名已存在！"},"content":""}
    登录接口：/login.json
    入参：{"content":{"password":"123","username":"123"}}
    出参：
      正常返回：{"info":{"code":200,"msg":"登录成功！"},"content":{"userid":"1","uuid":"123"}}
      错误返回：{"info":{"code":500,"msg":"用户名或密码错误！"},"content":""}
