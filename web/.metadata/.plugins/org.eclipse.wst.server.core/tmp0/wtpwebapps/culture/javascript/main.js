$(document).ready(function(){
	$("#signup").validate({
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
			ensurePassword:{
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
			ensurePassword:{
				required : "请输入确认密码",
				minlength: "密码长度最少为6位",
				maxlength : "密码长度最大为16位",
				equalTo : "两次密码不一致"
			}
		}
	});
	$("#login").validate({
		rules:{
			name : {
				required : true,
				minlength: 2,
				maxlength : 15,
			},
			password:{
				required : true,
				minlength: 2,
				maxlength : 16
			}
		},
		messages:{
			name : {
				required : "请输入用户名",
				minlength: "用户名长度最少为2位",
				maxlength : "用户名长度最大为15位",
			},
			password:{
				required : "请输入密码",
				minlength: "密码长度最少为2位",
				maxlength : "密码长度最大为16位"
			}
		}
	});
	$("#attendAdd").validate({
		rules:{
			bill : {
				required : true,
				minlength: 2,
				maxlength : 30,
			},
			billContent:{
				required : true,
				minlength: 2,
				maxlength : 500
			}
		},
		messages:{
			bill : {
				required : "请填写发票抬头",
				minlength: "发票抬头长度最少为2位",
				maxlength : "发票抬头长度最大为30位",
			},
			billContent:{
				required : "请填写发票内容",
				minlength: "发票内容长度最少为2位",
				maxlength : "发票内容长度最大为500位"
			}
		}
	});
	$("#userModify").validate({
		rules:{
			name : {
				minlength: 2,
				maxlength : 15,
			},
			password:{
				minlength: 6,
				maxlength : 16
			},
			ensurePassword:{
				minlength: 6,
				maxlength : 16,
				equalTo : "#password"
			}
		},
		messages:{
			name : {
				minlength: "用户名长度最少为2",
				maxlength : "用户名长度最大为15位",
			},
			password:{
				minlength: "密码长度最少为6位",
				maxlength : "密码长度最大为16位"
			},
			ensurePassword:{
				minlength: "密码长度最少为6位",
				maxlength : "密码长度最大为16位",
				equalTo : "两次密码不一致"
			}
		}
	});
	$("#meetingModify").validate({
		rules:{
			meetingName : {
				required : true,
				minlength: 2,
				maxlength : 20,
			},
			meetingAddr:{
				required : true,
				minlength: 8,
				maxlength : 30
			},
			meetingHost:{
				required : true,
				minlength: 2,
				maxlength : 15,
			},
			meetingLimit :{
				required : true,
			},
			meetingStartTime:{
				required : true,
			},
			meetingEntryStart:{
				required : true,
			},
			meetingEntryEnd : {
				required : true,
			},
			meetingContent:{
				required : true,
				minlength: 20,
				maxlength : 500
			},
			meetingContact:{
				required : true,
				maxlength : 13
			}
		},
		messages:{
			meetingName : {
				required : "请填写会议名称",
				minlength: "会议名称长度最小为2位",
				maxlength : "会议名称长度最大为20位"
			},
			meetingAddr:{
				required : "请填写会议地址",
				minlength: "会议地址长度最小为8位",
				maxlength : "会议地址长度最大为30位"
			},
			meetingHost:{
				required : "请填写主持人姓名",
				minlength: "主持人姓名长度最少为2位",
				maxlength : "主持人姓名长度最大为15位",
			},
			meetingLimit :{
				required : "请选择人数限制",
			},
			meetingStartTime:{
				required : "请选择会议开始时间",
			},
			meetingEntryStart:{
				required : "请选择会议报名开始时间",
			},
			meetingEntryEnd : {
				required : "请选择会议报名截止时间",
			},
			meetingContent:{
				required : "请填写会议内容",
				minlength: "会议内容长度最少为20位",
				maxlength : "会议内容长度最少为500位"
			},
			meetingContact:{
				required : "请填写会议联系方式",
				maxlength : "联系方式长度至少为13位"
			}
		}
	});
	$("#meetingAdd").validate({
		rules:{
			meetingName : {
				required : true,
				minlength: 2,
				maxlength : 20,
			},
			meetingAddr:{
				required : true,
				minlength: 8,
				maxlength : 30
			},
			meetingHost:{
				required : true,
				minlength: 2,
				maxlength : 15,
			},
			meetingLimit :{
				required : true,
			},
			meetingStartTime:{
				required : true,
			},
			meetingEntryStart:{
				required : true,
			},
			meetingEntryEnd : {
				required : true,
			},
			meetingContent:{
				required : true,
				minlength: 20,
				maxlength : 500
			},
			meetingContact:{
				required : true,
				maxlength : 13
			}
		},
		messages:{
			meetingName : {
				required : "请填写会议名称",
				minlength: "会议名称长度最小为2位",
				maxlength : "会议名称长度最大为20位"
			},
			meetingAddr:{
				required : "请填写会议地址",
				minlength: "会议地址长度最小为8位",
				maxlength : "会议地址长度最大为30位"
			},
			meetingHost:{
				required : "请填写主持人姓名",
				minlength: "主持人姓名长度最少为2位",
				maxlength : "主持人姓名长度最大为15位",
			},
			meetingLimit :{
				required : "请选择人数限制",
			},
			meetingStartTime:{
				required : "请选择会议开始时间",
			},
			meetingEntryStart:{
				required : "请选择会议报名开始时间",
			},
			meetingEntryEnd : {
				required : "请选择会议报名截止时间",
			},
			meetingContent:{
				required : "请填写会议内容",
				minlength: "会议内容长度最少为20位",
				maxlength : "会议内容长度最少为500位"
			},
			meetingContact:{
				required : "请填写会议联系方式",
				maxlength : "联系方式长度至少为13位"
			}
		}
	});
});	

function startLoginModal(){
	$("#loginModal").find('h4').eq(0).html("登录失败");
	$('#loginModal').modal({
	  keyboard: false
	});
}
function startDeleteModal(){
	$('#deleteModal').modal({
	  keyboard: false
	});
}

function startSignUpModal(message){
	$("#signUpModal").find('h4').eq(0).html("注册失败");
	$("#signUpModal").find('p').eq(0).html(message);
	$('#signUpModal').modal({
	  keyboard: false
	});
}

function tip(title,message){
	$("#tipModal").find('h4').eq(0).html(title);
	$("#tipModal").find('p').eq(0).html(message);
	$('#tipModal').modal({
	  keyboard: false
	});
}

