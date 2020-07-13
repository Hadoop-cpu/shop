<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<c:url value='/admin/css/style.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/admin/css/select.css'/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/admin/js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/admin/js/select-ui.min.js'/>"></script>
	<script type="text/javascript">
	//上传前判断文件是否选择
		function validateName(){	
			var name = $('input[name="imgpath"]');
			var result = $("#result");
			result.html("");
			if(name.val()==null||($.trim(name.val()))==""){
				result.html("上传文件名称不能为空！");
				return false;
			}
			var start = name.val().lastIndexOf(".")+1;
			var type = name.val().substr(start);
			var ext = ["jpg","gif","bmp","png","JPG","GIF","BMP","PNG"];
			var flag = false;
			for(var i=0;i<ext.length;i++){
				if(type == ext[i]){
					flag = true;
					break;
				}
			}
			if(flag){
				return true;
			}else{
				result.html("请选择一个有效的图片文件！");
				return false;	
			}	
			return true;
		}
		//向父窗口传递数据
		$(function(){
			$("#sure").click(function(){
				var path = $("#path").val();
				$("#filepath",window.opener.document).val(path);
				$("#img1",window.opener.document).attr("src",<c:url value='/'/>+path+"?t="+Math.random());
				window.close();
			});
		});
	
	</script>
</head>

<body>

	
	    <form action="${pageContext.request.contextPath}/imageUploadServlet" method="post" enctype="multipart/form-data">
	     <input type="hidden" id="path" value="${path}"/>
		<div class="formbody">
			<div class="formtitle">
				<span>上传</span>
			</div>
			<ul class="forminfo">
					
					<li><label>文件</label>
					<div class="vocation">
						<input name="imgpath" type="file" class="dfinput" style="width:200px;" />
						<input  type="submit" class="btn" value="上传" onclick="javascript:return validateName();"/>
					</div></li>
					<li><label style="color:red;width:278px;" id="result">${msg}</label></li>
				<li><label>&nbsp;</label>
				&nbsp;&nbsp;
				<input type="button" id="sure" class="btn" value="确定"/>
				</li>
			</ul>

		</div>
	</form>
</body>
</html>

