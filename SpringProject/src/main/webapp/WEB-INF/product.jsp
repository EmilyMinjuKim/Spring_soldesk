<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RESTFUL</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		//ajaxSetup :  실행될  AJAX 요청에 대한 기본 속성을 정의해 재사용 
		//jqXHR : jQuery XMLHttpRequest의 약자. XML뿐만 아니라 모든 유형의 데이터를 검색하는 데 사용
		//Jquery Ajax로 호출하기 전에 serialize를 해주면 form안에 값들을 한번에 전송 가능한 data로 만들 수 있다
		
		$.ajaxSetup({
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',	//한글깨짐 방지
			success: function(result) {
				alert(result);
			},
			error: function(jqXHR) {
				//Exception 처리
				alert("jqXHR status code:"+jqXHR.status+" message:"+jqXHR.responseText);	
			}
		});	//.ajaxSetup
		
		//GET
		$("#testGetListBtn").click(function(){
			$.ajax({
				type: "get",
				url: "products",
				contentType: 'application/x-www-form-urlencoded; charset=utf-8',
				success: function(productList) {
					alert(productList);
					$("#listView").empty();
					$.each(productList, function(i, product) {
						$("#listView").append(
								product.id+" "
								+product.name+" "
								+product.brand+" "
								+product.price
								+"<br>").css("background", "pink");
					});
				}
			});	//.ajax
		});	//.click
		
		//GET 상세정보
		$("#testGetBtn").click(function(){	
			$.ajax({
				type:"get",
				url:"products/"+$("#pid").val(),		
				contentType: 'application/x-www-form-urlencoded; charset=utf-8',
				success:function(product){					
					alert(product.id+" "+product.name+" "+product.brand+" "+product.price);
				}
			});//ajax
		});//click	
		
		//POST
		$("#testCreateBtn").click(function(){
			$.ajax({
				
				type: "post",
				url: "products",
				data: $("#createProductForm").serialize()	//서버에서 역직렬화는 자동으로 처리해줌.
				
			}).done(function() {
				$("#createProductForm")[0].reset();
				
			});	//.ajax
			
		});	//.click
		
		//PUT
		$("#testPutBtn").click(function(){
			$.ajax({
				
				type: "put",
				url: "products?"+$("updateProductForm").serialize()
						
			}).always(function() {
				$("#updateProductForm")[0].reset();
				
			});	//.ajax
			
		});	//.click
		
		//DELETE
		$("#testDeleteBtn").click(function(){
			$.ajax({
				
				type: "delete",
				url: "products/"+$("#deleteID").val()
						
			});	//.ajax
			
		});	//.click
		
	});	//.ready
</script>
<style type="text/css">
	.restImg{
		width: 500px;
		height: 400px;
	}
</style>
</head>
<body>
<h3>REST 적용 웹애플리케이션 구현</h3>
<ul>
	<li>
	HTTP Request Method<br><br>
	
	GET : 리스트 조회 
	<button value="testGetListBtn" id="testGetListBtn">조회</button><br>
	<div id="listView"></div><br><br>
	
	GET : 조회 <input type="text" id="pid" />
				<button value="testGetBtn" id="testGetBtn">조회</button><br><br>
	
	POST : 생성 <button value="testCreateBtn" id="testCreateBtn">생성</button><br>
	<form id="createProductForm">
		상품번호 <input type="text" name="id" size="5" />
		상품명 <input type="text" name="name" size="5" />
		제조사 <input type="text" name="brand" size="5" />
		가격 <input type="number" name="price" />
	</form>
	<br><br>
	
	PUT : 수정 <button value="testPutBtn" id="testPutBtn">수정</button><br>
	<form id="updateProductForm">
		상품번호 <input type="text" name="id" size="5" id="updateID" />
		상품명 <input type="text" name="name" size="5" />
		제조사 <input type="text" name="brand" size="5" />
		가격 <input type="number" name="price" />
	</form>
	<br><br>
	
	DELETE : 삭제 <input type="text" id="deleteID" />
					<button value="testDeleteBtn" id="testDeleteBtn">삭제</button><br><br>
	</li>
</ul>
<img src="image/img.png" class="restImg" />

<!-- <h1><a href="test1">test1</a></h1> -->

</body>
</html>