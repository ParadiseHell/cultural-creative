$(document).ready(function() {
	$('#culture-tab a').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
	});
	$("#user-form").validate({
		rules:{
			name : {
				required : true,
				minlength: 2,
				maxlength : 15,
			},
			password:{
				required : true,
				minlength: 6,
				maxlength : 16
			},
			passwordAgain:{
				required : true,
				minlength: 6,
				maxlength : 16,
				equalTo : "#password"
			}
		},
		messages:{
			name : {
				required : "请输入用户名",
				minlength: "用户名长度最少为2",
				maxlength : "用户名长度最大为20位",
			},
			password:{
				required : "请输入密码",
				minlength: "密码长度最少为6位",
				maxlength : "密码长度最大为16位"
			},
			passwordAgain:{
				required : "请输入确认密码",
				minlength: "密码长度最少为6位",
				maxlength : "密码长度最大为16位",
				equalTo : "两次密码不一致"
			}
		}
	});
});

