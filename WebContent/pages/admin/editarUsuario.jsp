<%@ include file="/taglibs.jsp"%>

<script type="text/javascript">

	$(document).ready(function() {
		    
		$("#btnEditarUsuario").button();	
		$("#btnEditarCancelarUsuario").button();
		$("#btnEditarUsuario1").button();	
		$("#btnEditarCancelarUsuario1").button();
		
		$("#fEditarUsuario").validate({
			errorClass: "invalid",
			rules: {
				idTipoDocumento: {
					required: true,
					digits: true,
					min:1
				},
				numIdentificacion:{
					required: true,
					digits: true
				},
				numIdentificacionRepetido: {
					required: true,
					 equalTo: "#numIdentificacion"
				},					 
				idRol:{
					required: true,
					digits: true,
					min:1					
				},
				nombres:{
					required: true
				},
				apellidos:{
					required: true
				},
				
				usuario:{
					required: true
				},
				clave:{
					required: true
				}
								
			},
			messages: {
				idTipoDocumento: {
					required: "Seleccione una opción para tipo de documento",
					digits: "Seleccione una opción para tipo de documento",
				},
				numIdentificacion: {
					required: "Ingrese un valor.",
					digits: "Ingrese solo dígitos"
				},
				numIdentificacionRepetido: {
					required: "Ingrese un valor igual al número de identificación.",
					equalTo: "Ingrese un valor igual al número de identificación.",
					digits: "Ingrese solo dígitos"
				},
				idRol:{
					required: "Seleccione una opción para el rol.",
					digits: "Seleccione una opción para el rol.",
					min:1	
				},
				nombres:{
					required: "Ingrese un valor."
				},
				apellidos:{
					required: "Ingrese un valor."
				},
				usuario:{
					required: "Ingrese un valor."
				},
				clave:{
					required: "Ingrese un valor."
				}				
			},
			submitHandler: function(form) {				
				editarUsuario(form);
			}
		});
		
	});
	
	
	function editarUsuario(form) {
		$("[name=btnEditarUsuario]").attr('disabled',true);
		$("[name=btnEditarUsuario1]").attr('disabled',true);
		$("[name=btnEditarCancelarUsuario]").attr('disabled',true);
		$("[name=btnEditarCancelarUsuario1]").attr('disabled',true);
		$("#dmMensajeUsuario").dialog("open");
		$("#dmMensajeUsuario").html(getHTMLLoaging16(' Guardando'));
		$.ajax({
			cache: false,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
            type: $(form).attr('method'),            
            url: $(form).attr('action'),
            data: $(form).serialize(),
            dataType: "text",
            error: function(jqXHR, textStatus, errorThrown) {
            	$("[name=btnEditarUsuario]").attr('disabled',false);
            	$("[name=btnEditarUsuario1]").attr('disabled',false);
        		$("[name=btnEditarCancelarUsuario]").attr('disabled',false);
        		$("[name=btnEditarCancelarUsuario1]").attr('disabled',false);
                $("#dmMensajeUsuario").html(jqXHR.statusText);
	        },
            success: function(data) {  

            	var mensajeOK = '<h3 style="color:blue">Cambios guardados con exito.</h3>';
            	var mensajeER = '<h3 style="color:red">Error desconocido. Vuelva a intentarlo.</h3>';
            	
            	if(validarEntero(data)){
            		$("#dmMensajeUsuario").html(mensajeOK);
                	$("#dmEditarUsuario").dialog("close");
                } else {
                	$("[name=btnEditarUsuario]").attr('disabled',false);
                	$("[name=btnEditarUsuario1]").attr('disabled',false);
                	$("[name=btnEditarCancelarUsuario]").attr('disabled',false);
            		$("[name=btnEditarCancelarUsuario1]").attr('disabled',false);
                    $("#dmMensajeUsuario").html(mensajeER);					
                }    
            	
            }
        });
	}

	function validarEntero(valor){ 
    	valor = parseInt(valor);
     	if (isNaN(valor)) { 
           	 return false;
     	}else{ 
           	 return true;
     	} 
	}
	
	
</script>

<div  align="left">

<div>
</div>

<form name="fEditarUsuario" id="fEditarUsuario" action="${ctx}/page/usuario" method="post">

	<input type="hidden" name="action" value="editarUsuario"/>
	<input type="hidden" name="idUsuarioEditando" value="${usuario.idUsuario}"/>
	
	<div>
		<button type="button" id="btnEditarCancelarUsuario" name="btnEditarCancelarUsuario" onclick="$('#dmEditarUsuario').dialog('close');">Cancelar</button>
		&nbsp;&nbsp;&nbsp;
		<button type="submit" id="btnEditarUsuario" name="btnEditarUsuario">Guardar</button>
	</div>
	<br/>

	<fieldset>
		<legend class="e6">Usuario</legend>
		<table border="0" width="100%" class="caja">
		<col style="width: 15%"/>
		<col/>
		<tr>
		<th nowrap="nowrap" style="text-align: right;">Tipo de identificación:</th>
			<td>
				<select name="idTipoDocumento">
					<c:forEach items="${listaTiposDocumento}" var="tipoDocumento" varStatus="loop">
						<option value="${tipoDocumento.idTipoDocumento}">
							<c:out value="${tipoDocumento.tipo}"/> - <c:out value="${tipoDocumento.descripcion}"/>
						</option>
					</c:forEach>
				</select>
				<script type="text/javascript">
					$("#fEditarUsuario [name=idTipoDocumento]").val("${usuario.idTipoDocumento}");
				</script>
			</td>
		</tr>
		
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Num. Identificación:</th>
			<td>
				<input type="text" name="numIdentificacion"  id="numIdentificacion" value="${usuario.numeroDocumento}" style="width: 50%;"/>
			</td>
		</tr>
		
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Repetir Num. Identificación:</th>
			<td>
				<input type="text" name="numIdentificacionRepetido" id="numIdentificacionRepetido" value="${usuario.numeroDocumento}" style="width: 50%;"/>
			</td>
		</tr>
			
		<tr>
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Rol:</th>
			<td>
				<select name="idRol">
					<c:forEach items="${listaRoles}" var="rol" varStatus="loop">
						<option value="${rol.idRol}">
							<c:out value="${rol.rol}"/>
						</option>
					</c:forEach>
				</select>
				<script type="text/javascript">
					$("#fEditarUsuario [name=idRol]").val("${usuario.idRol}");
				</script>
			</td>
		</tr>
		<tr>
		<th nowrap="nowrap" style="text-align: right;">Nombres:</th>
			<td>
				<input type="text" name="nombres" value="${usuario.nombre}" style="width: 50%;"/>
			</td>
		</tr>
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Apellidos:</th>
			<td>
				<input type="text" name="apellidos" value="${usuario.apellido}" style="width: 50%;"/>
			</td>
		</tr>
		<tr>
		
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Nombre de usuario:</th>
			<td>
				<input type="text" name="usuario" value="${usuario.usuario}" style="width: 50%;"/>
			</td>
		</tr>
		
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Clave:</th>
			<td>
				<input type="password" name="clave" value="${usuario.clave}" style="width: 50%;"/>
			</td>
		</tr>
		
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Estado:</th>
			<td>
				<select name="idEstado">
					<option value="2">Activo - Activar</option>
				    <option value="1">Inactivo - Inactivar</option>
				</select>
				<script type="text/javascript">
					$("#fEditarUsuario [name=idEstado]").val("${usuario.estado}");
				</script>
			</td>
		</tr>
		
		</table>
	</fieldset>
	<br/>

	<div>
		<button type="button" id="btnEditarCancelarUsuario1" name="btnEditarCancelarUsuario1" onclick="$('#dmEditarUsuario').dialog('close');">Cancelar</button>
		&nbsp;&nbsp;&nbsp;
		<button type="submit" id="btnEditarUsuario1" name="btnEditarUsuario1">Guardar</button>
	</div>
</form>

</div>
<div style="padding: 30px 0px;"></div>