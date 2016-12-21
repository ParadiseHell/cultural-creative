var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.json({
  	sate : true,
  	message : "Hello",
  	data:{
  		title : "hello",
  		name : "chengtao"
  	},
  	dataList: null
  });
});

/* GET home page. */
router.post('/user/login', function(req, res, next) {
  var name = req.body.name;
  var password = req.body.password;
  console.log("name:"+name+"---password:"+password);	
  res.json({
  	state : true,
  	message : "登录成功",
  	data:{
		name : name,
		password : password
	},
  	dataList: null
  });
});

module.exports = router;
