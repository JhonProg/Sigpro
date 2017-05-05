<%@ include file="/taglibs.jsp"%>

<script type="text/javascript">

	$(document).ready(function() {
		    
		$("#btnCrearMeta").button();	
		$("#btnCrearCancelarMeta").button();
		$("#btnCrearMeta1").button();	
		$("#btnCrearCancelarMeta1").button();

		$("#fCrearMeta").validate({
			errorClass: "invalid",
			rules: {
				meta: {
					required: true,
					digits: true,
					min:1
				}
				  		
			},
			messages: {
				
				meta:{
					required: "Ingrese un valor para la meta",
					digits: "No puede ingresar letras y/o signos",
					min:"La meta debe ser un valor mayor a $1"	
				}
			},
			submitHandler: function(form) {				
				crearMeta(form);
			}
		});
		
	});

	
	function crearMeta(form) {
		$("[name=btnCrearMeta]").attr('disabled',true);
		$("[name=btnCrearCancelarMeta]").attr('disabled',true);
		$("[name=btnCrearMeta1]").attr('disabled',true);
		$("[name=btnCrearCancelarMeta1]").attr('disabled',true);
		$("#dmMensajeUsuario").dialog("open");
		$("#dmMensajeUsuario").html(getHTMLLoaging16(' Creando'));
		$.ajax({
			cache: false,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
            type: $(form).attr('method'),            
            url: $(form).attr('action'),
            data: $(form).serialize(),
            dataType: "text",
            error: function(jqXHR, textStatus, errorThrown) {
            	$("[name=btnCrearMeta]").attr('disabled',false);
        		$("[name=btnCrearCancelarProducto]").attr('disabled',false);
        		$("[name=btnCrearMeta1]").attr('disabled',false);
        		$("[name=btnCrearCancelarProducto1]").attr('disabled',false);
                $("#dmMensajeUsuario").html(jqXHR.statusText);
	        },
            success: function(data) {  

            	var mensajeOK = '<h3 style="color:blue">Meta registrada con exito.</h3>';
            	var mensajeER = '<h3 style="color:red">Error desconocido. Vuelva a intentarlo.</h3>';
            	
            	if(validarEntero(data)){
            		$("#dmMensajeUsuario").html(mensajeOK);
                	$("#dmNuevaMeta").dialog("close");
                } else {
                	$("[name=btnCrearMeta]").attr('disabled',false);
            		$("[name=btnCrearCancelarProducto]").attr('disabled',false);
            		$("[name=btnCrearMeta1]").attr('disabled',false);
            		$("[name=btnCrearCancelarProducto1]").attr('disabled',false);
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

<form name="fCrearMeta" id="fCrearMeta" action="${ctx}/page/meta" method="post">

	<input type="hidden" name="action" value="insertarMeta"/>
	
	<div>
		<button type="button" id="btnCrearCancelarMeta" name="btnCrearCancelarMeta" onclick="$('#dmNuevaMeta').dialog('close');">Cancelar</button>
		&nbsp;&nbsp;&nbsp;
		<button type="submit" id="btnCrearMeta" name="btnCrearMeta">Crear</button>
	</div>
	<br/>

	<fieldset>
		<legend class="e6">Meta</legend>
		<table border="0" width="100%" class="caja">
		<col style="width: 15%"/>
		<col/>
		<tr>
		<th nowrap="nowrap" style="text-align: right;">Promotor:</th>
			<td>
				<select name="idUsuario" id="idUsuario">
	           		<c:forEach items="${promotores}" var="promotor" varStatus="loop">
						<option  value="${promotor.idUsuario}">
							<c:out value="${promotor.nombre} ${promotor.apellido}"/>
						</option>
					</c:forEach>
				</select>
					
			</td>
		</tr>
		
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Mes/campaña:</th>
			<td> 
	           	<select name="idMes" id="idMes">
	           		<option value="1">Enero</option>
					<option value="2">Febrero</option>
					<option value="3">Marzo</option>
					<option value="4">Abril</option>
					<option value="5">Mayo</option>
					<option value="6">Junio</option>
					<option value="7">Julio</option>
					<option value="8">Agosto</option>
					<option value="9">Septiembre</option>
					<option value="10">Octubre</option>
					<option value="11">Noviembre</option>
					<option value="12">Diciembre</option>			           		
	           	</select> 
			</td>
		</tr>
		
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Meta:</th>
			<td>
				<input type="text" name="meta"  id="meta" value="" style="width: 50%;"/>
			</td>
		</tr>
				
		</table>
	</fieldset>
	<br/>

	<div>
		<button type="button" id="btnCrearCancelarMeta1" name="btnCrearCancelarMeta1" onclick="$('#dmNuevaMeta').dialog('close');">Cancelar</button>
		&nbsp;&nbsp;&nbsp;
		<button type="submit" id="btnCrearMeta1" name="btnCrearMeta1">Crear</button>
	</div>
</form>

</div>
<div style="padding: 30px 0px;"></div>
