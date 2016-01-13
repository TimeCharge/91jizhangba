# 91jizhangba
91记账吧
## 接口说明
    注册接口：/register
    入参：passWord=111&userName=23232&equipmentId=23123123
    出参：
      正常返回：{"code":200,"msg":"操作成功！","data":""}
      错误返回：{"code":500,"msg":"该用户名已存在！","data":""}
    登录接口：/login
    入参：passWord=111&userName=23232
    出参：
      正常返回：{"code":200,"msg":"登录成功！","data":{"id":2,"userId":7,"token":"1d425f8a1a2f4958bbe6866d8da1289a"}}
      错误返回：{"code":500,"msg":"用户名或密码错误！","data":""}
    登出接口：/logout
    入参：token=1d425f8a1a2f4958bbe6866d8da1289a&userId=7
    出参：
      正常返回：{"code":200,"msg":"登出成功！","data":""}
      错误返回：{"code":500,"msg":"登出失败，请联系客服！","data":""}
    无帐号登录接口：/preRegister
    入参：equipmentId=23123123
    出参：
      正常返回：{"code":200,"msg":"登录成功！","data":{"id":2,"userId":7,"token":"1d425f8a1a2f4958bbe6866d8da1289a"}}
      错误返回：{"code":500,"msg":"用户名或密码错误！","data":""}
    帐号验证接口：/userValidata
    入参：userName=23123123&token=1d425f8a1a2f4958bbe6866d8da1289a&userId=7
    出参：
      正常返回：{"code":200,"msg":"操作成功！","data":""}
      错误返回：{"code":500,"msg":"该用户名已存在！","data":""}
