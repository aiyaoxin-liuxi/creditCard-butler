<%@ page pageEncoding="UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="work/toReleaseJobInfo">dd</a>

<form id="form1" method="post" action="userInfo/upLoadHeadImg" enctype="multipart/form-data">                  
    <tr>  
        <td width="25%" align="right">上传文件：</td>  
        <td><input id="file" type="file" NAME="file" style="width:300px;"></td>  
    </tr>  
    <input type="text" name="userId" id="userId" value="test123"/>
    <tr align="center" valign="middle">  
        <td height="60" colspan="2"><input type="submit" ID="BtnOK" value="确认上传"></td>  
    </tr>  
</form>   

</body>
</html>