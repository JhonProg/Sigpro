<%@ include file="/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
	<title>Login</title>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/css/sigpro.css">
	<link type="text/css" href="${ctx}/css/jquery/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-ui-1.8.21.custom.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#btnIngresar").button();
			
				
				$("#dmCarritoCompras").dialog({   				
					width: 400,
					height: 200,   				
					modal: true,
					autoOpen: false,
					resizable: true
				});
				
			
			
			$("#fLogin").validate({
				errorLabelContainer: "#msnLogin",
				errorClass: "invalid",
				rules: {
					usuario:{
						required: true
					},
					clave:{
						required: true
					}
				},
				messages: {
					usuario: {
						required: "Ingrese un usuario."
					},
					clave:{
						required: "Ingrese una clave."
					}
				}
			});

			$("#j_usuario").focus();
			
		});
		
		
	</script>
	
</head>
	
<body>
	<div style="padding: 20px;"></div>
	<div align="center">
		<img alt="Konrad Lorenz" src="${ctx}/imagen/logo_konrad.png">
		<div style="padding: 10px;"></div>
		<form name="fLogin" id="fLogin"
			action="${ctx}/page/login?action=iniciarSesion" method="post" style="autocomplete=off">
			<table>
					<tr>
						<th colspan="2">
							<div style="padding: 3px 2px;">
								<c:out value="Ingeniera de Software II" />
							</div>
						</th>
					</tr>
				
				<tr>
					<td colspan="2"><div style="padding: 2px;"></div></td>
				</tr>
				<tr>
				<tr>
					<td align="right"><b>Usuario: </b></td>
					<td><input type="text" name="usuario" tabindex="1"
						id="j_usuario" style="autocorrect=off" /></td>
				</tr>
				<tr>
					<td align="right"><b>Clave: </b></td>
					<td><input type="password" name="clave" tabindex="2"
						id="j_clave" style="autocomplete=off" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center" valign="middle">
						<div style="padding: 2px;"></div>
						<button type="submit" id="btnIngresar" name="btnIngresar">Ingresar</button>
					</td>
				</tr>
			</table>
		</form>
		<div style="padding: 5px;"></div>
		<div align="center" id="msnLogin">
			<c:if test="${not empty errorMsg}">
				<label for="clave" style="generated=true" class="invalid">${errorMsg}</</label>
			</c:if>
		</div>
	</div>
	
</body>
	
</html>
