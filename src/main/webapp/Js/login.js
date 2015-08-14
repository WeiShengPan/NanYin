/**
 * Created by 张一平 on 2015/8/3.
 */
$(document).ready(function(){
    var name=$("#flag").text();
    var level=$("#level").text();
    var text="";
    if(level==false){
        text+="尊敬的普通会员:";
    }else{
        text+="尊敬的高级会员:";
    }
    if(name!=""){
        $("#loginForm #loginTab").remove();
        var add='<table id="addTab" align="center">' +
            '<tr><td>'+text+'</td> </tr>' +
            '<tr><td>'+name+'，您好！</td></tr>'+
            '<tr><td>欢迎来到南音网</td></tr>' +
            '<tr><td><a href="index.do?method=userCenter">我的会员中心</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="Exit()">退出</a></td></tr>' +
            '</table>'
        $("#loginForm #loginField").append(add);
    }

    $.ajax({
        type:'POST',
        url:'index.do?method=announcement',
        error:function(){
            alert("sorry,the web application have some errors!");
        },
        success:function(data){
            var result= $.parseJSON(data);
            for(var i=0;!(i >= result.length);i++){
                $("#announcement"+i).find("a").text(""+(i+1)+""+"."+result[i].content);
                $("#announcement"+i).find("a").attr("href","index.do?method=announcement&id="+result[i].id+"");
            }
        }
    });
});

function checkData(){
    var name=$("#loginForm #user").val();
    var psw=$("#loginForm #psw").val();
    if(!(name.length>0)){
        $("#loginForm #user").focus();
        alert("用户名不能为空！");
        return  false;
    }
    if(!(psw.length>0)){
        $("#loginForm #psw").focus();
        alert("密码不能为空！");
        return  false;
    }
    return true;
}

function Login(){
    if(checkData()){
        $.ajax({
            type:'POST',
            url:'index.do?method=login',
            data:$("#loginForm").serialize(),
            error:function(){
                alert("登录失败！");
            },
            success:function(data){
                if(data!=""){
                    var result= $.parseJSON(data);
                    $("#loginForm #loginTab").remove();
                    var level="";
                    if(result.level==true){
                        level+="尊敬的高级会员:"
                    }else{
                        level+="尊敬的普通会员:";
                    }
                    var add='<table id="addTab" align="center">' +
                        '<tr><td>'+level+'</td> </tr>' +
                        '<tr><td>'+result.userName+'，您好！</td></tr>'+
                        '<tr><td>欢迎来到南音网</td></tr>' +
                        '<tr><td><a href="index.do?method=userCenter">我的会员中心</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="Exit()">退出</a></td></tr>' +
                        '</table>'
                    $("#loginForm #loginField").append(add);

                }else{
                    alert("用户名或密码错误！");
                }
            }
        });
    }
}

function Reset(){
    $("#loginForm #user").val("");
    $("#loginForm #psw").val("");
}


function Exit(){
    $("#loginForm #addTab").remove();
    var loginTab=' <table id="loginTab" class="c2"  style=" width:100%; height:120px; margin-top:15px">'+
        '<tr><td align="right">用户名:</td><td><input id="user" type="text" name="user" width="20px" size="10" maxlength="10"/></td></tr>'+
        '<tr><td align="right">密 码:</td><td><input id="psw" type="password"  name="password" size="10" maxlength="10"/></td></tr>'+
        '<tr><td></td><td><a href="javascript:void(0)" onclick="Login()">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="Reset()">重置</a></td></tr>'+
        '<tr><td></td><td><a href="index.do?method=register&type=0">注册</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">忘记密码</a></td></tr></table>';
    $("#loginForm #loginField").append(loginTab);

    $.ajax({
        type:'POST',
        url:'index.do?method=exit',
    });

}