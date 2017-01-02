var express = require('express');
var router = express.Router();

/* GET home page. */
router.post('/', function(req, res, next) {
  var date = req.body.date;
  var type = req.body.type;
  console.log("date:"+date+"----"+"type:"+type);
  res.json({
  	state : true,
  	message : "Hello",
  	data:null,
  	dataList: [
  		{
  			id : 1,
  			companyName　: "北京林业大学",
  			newsTitle : "北京林业大学校庆64周年",
  			newsTime : 1483269852,
  			newsDetail : "北京林业大学校庆64周年",
  			images : null,
  			notes : null,
  			visitNum : 10,
  			operateType : 1
  		},
  		{
  			id : 2,
  			companyName　: "北京林业大学",
  			newsTitle : "北京林业大学校庆64周年",
  			newsTime : 1483269852,
  			newsDetail : "北京林业大学校庆64周年",
  			images : null,
  			notes : null,
  			visitNum : 10,
  			operateType : 2
  		},
  		{
  			id : 3,
  			companyName　: "北京林业大学",
  			newsTitle : "北京林业大学校庆64周年",
  			newsTime : 1483269852,
  			newsDetail : "北京林业大学校庆64周年",
  			images : null,
  			notes : null,
  			visitNum : 10,
  			operateType : 1
  		},
  		{
  			id : 4,
  			companyName　: "北京林业大学",
  			newsTitle : "北京林业大学校庆64周年",
  			newsTime : 1483269852,
  			newsDetail : "北京林业大学校庆64周年",
  			images : null,
  			notes : null,
  			visitNum : 10,
  			operateType : 2
  		},
  		{
  			id : 5,
  			companyName　: "北京林业大学",
  			newsTitle : "北京林业大学校庆64周年",
  			newsTime : 1483269852,
  			newsDetail : "北京林业大学校庆64周年",
  			images : null,
  			notes : null,
  			visitNum : 10,
  			operateType : 0
  		}
  	]
  });
});

module.exports = router;
