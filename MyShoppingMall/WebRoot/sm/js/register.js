/**
 * Created by Administrator on 2018/4/23.
 */
    //for表单调用
$("#emailForm").validate({
    //验证规则 ==>对表单的验证
    rules: {
        email: {
            required: true,
            email:true
        },
        password: {
            required: true,
            minlength: 8,
            maxlength: 32
        },
        passwordRepeat: {
            required: true,
            equalTo: "#password"
        },
        click:{
            required:true
        }


    },
    messages: {
        email: {
            required: "请填写用户名",
            email:"邮箱格式不正确"
        },
        password: {
            required: "请填写密码",
            minlength: "密码个数需要大于8位",
            maxlength: "密码最大个数32位"
        },
        passwordRepeat: {
            required: "请填写密码",
            equalTo: "密码需要一致"
        },
        click:{
            required: "不同意不能注册"
        }
    },
    onfocusout:function(e){
        $(e).valid();
    }

});
$("#phoneForm").validate({
    //验证规则 ==>对表单的验证
    rules: {
        phone: {
            required: true,
            checkPhone:true
        },
        password: {
            required: true,
            minlength: 8,
            maxlength: 32
        },
        passwordRepeat: {
            required: true,
            equalTo: "#password"
        },
        click:{
            required:true
        },
        phonecode:{
            required: true
        }


    },
    messages: {
        phone: {
            required: "请填手机号"
        },
        password: {
            required: "请填写密码",
            minlength: "密码个数需要大于8位",
            maxlength: "密码最大个数32位"
        },
        passwordRepeat: {
            required: "请填写密码",
            equalTo: "密码需要一致"
        },
        click:{
            required: "不同意不能注册"
        },
        phonecode:{
            required: "验证码不能为空"
        }
    },
    onfocusout:function(e){
        $(e).valid();
    }

});
//手机号的验证规则
jQuery.validator.addMethod("checkPhone",function(value,element){
    var phone= /^1[3|4|5|7|8][0-9]{9}$/;
    return this.optional(element)||phone.test(value);
},"手机格式不正确")
