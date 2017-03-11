<%@ include file="/taglibs.jsp"%>

<script type="text/javascript">

	$(document).ready(function() {
		    
		$("#btnCrearProducto").button();	
		$("#btnCrearCancelarProducto").button();
		$("#btnCrearProducto1").button();	
		$("#btnCrearCancelarProducto1").button();

		$("#fCrearProducto").validate({
			errorClass: "invalid",
			rules: {
				idProductoCategoria: {
					required: true,
					digits: true,
					min:1
				},
				idProductoEstado: {
					required: true,
					digits: true,
					min:1
				},
				sku:{
					required: true,
					digits: true
				},
				codigoBarras: {
					required: true,
					digits: true
				},					 
				nombreProducto:{
					required: true
				},
				descripcion:{
					required: true
				},
				
				precioUnitario:{
					required: true,
					digits:true
				},
				precioDescuento:{
					required: true,
					digits:true
				},
				cantidadDisponible:{
					required: true,
					digits:true
				}
				  		
			},
			messages: {
				idTipoDocumento: {
					required: "Seleccione una opción para tipo de documento",
					digits: "Seleccione una opción para tipo de documento",
				},
				sku: {
					required: "Ingrese un valor.",
					digits: "Ingrese solo dígitos"
				},
				codigoBarras: {
					required: "Ingrese un valor igual al número de identificación.",
					equalTo: "Ingrese un valor igual al número de identificación.",
					digits: "Ingrese solo dígitos"
				},
				idRol:{
					required: "Seleccione una opción para el rol.",
					digits: "Seleccione una opción para el rol.",
					min:1	
				},
				nombreProducto:{
					required: "Ingrese un valor."
				},
				descripcion:{
					required: "Ingrese un valor."
				},
				precioUnitario:{
					required: "Ingrese un valor.",
					digits: "Ingrese solo dígitos"
				},
				precioDescuento:{
					required: "Ingrese un valor.",
					digits:"Ingrese solo dígitos"
				},
				cantidadDisponible:{
					required: "Ingrese un valor.",
					digits:"Ingrese solo dígitos"
				}		
			},
			submitHandler: function(form) {				
				crearProducto(form);
			}
		});
		
	});

	
	function crearProducto(form) {
		$("[name=btnCrearProducto]").attr('disabled',true);
		$("[name=btnCrearCancelarProducto]").attr('disabled',true);;
		$("#dmMensajeProducto").dialog("open");
		$("#dmMensajeProducto").html(getHTMLLoaging16(' Creando'));
		$.ajax({
			cache: false,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
            type: $(form).attr('method'),            
            url: $(form).attr('action'),
            data: $(form).serialize(),
            dataType: "text",
            error: function(jqXHR, textStatus, errorThrown) {
            	$("[name=btnCrearProducto]").attr('disabled',false);
        		$("[name=btnCrearCancelarProducto]").attr('disabled',false);
                $("#dmMensajeProducto").html(jqXHR.statusText);
	        },
            success: function(data) {  

            	var mensajeOK = '<h3 style="color:blue">Producto registrado con exito.</h3>';
            	var mensajeER = '<h3 style="color:red">Error desconocido. Vuelva a intentarlo.</h3>';
            	
            	if(validarEntero(data)){
            		$("#dmMensajeProducto").html(mensajeOK);
                	$("#dmNuevoProducto").dialog("close");
                } else {
                	$("[name=btnCrearProducto]").attr('disabled',false);
            		$("[name=btnCrearCancelarProducto]").attr('disabled',false);
                    $("#dmMensajeProducto").html(mensajeER);					
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

<form name="fCrearProducto" id="fCrearProducto" action="${ctx}/page/producto" method="post">

	<input type="hidden" name="action" value="insertarProducto"/>
	
	<div>
		<button type="button" id="btnCrearCancelarProducto" name="btnCrearCancelarProducto" onclick="$('#dmNuevoProducto').dialog('close');">Cancelar</button>
		&nbsp;&nbsp;&nbsp;
		<button type="submit" id="btnCrearProducto" name="btnCrearProducto">Crear</button>
	</div>
	<br/>

	<fieldset>
		<legend class="e6">Producto</legend>
		<table border="0" width="100%" class="caja">
		<col style="width: 15%"/>
		<col/>
		<tr>
		<th nowrap="nowrap" style="text-align: right;">Categoria:</th>
			<td>
				<select name="idProductoCategoria">
					<c:forEach items="${listaProductoCategorias}" var="categoria" varStatus="loop">
						<option value="${categoria.idProductoCategoria}">
							<c:out value="${categoria.categoria}"/> - <c:out value="${categoria.descripcion}"/> 
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Estado:</th>
			<td>
				<select name="idProductoEstado">
					<c:forEach items="${listaProductoEstados}" var="productoEstado" varStatus="loop">
						<option value="${productoEstado.idpedidoestado}">
							<c:out value="${productoEstado.estado}"/> - <c:out value="${productoEstado.descripcion}"/>
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<th nowrap="nowrap" style="text-align: right;">SKU:</th>
			<td>
				<input type="text" name="sku"  id="sku" value="" style="width: 50%;"/>
			</td>
		</tr>
		
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Codigo de Barras:</th>
			<td>
				<input type="text" name="codigoBarras" id="codigoBarras" value="" style="width: 50%;"/>
			</td>
		</tr>
			
		<tr>
		
		<tr>
		<th nowrap="nowrap" style="text-align: right;">Nombre Producto:</th>
			<td>
				<input type="text" name="nombreProducto" value="" style="width: 50%;"/>
			</td>
		</tr>
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Descripcion:</th>
			<td>
				<input type="text" name="descripcion" value="" style="width: 50%;"/>
			</td>
		</tr>
		<tr>
		
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Precio Unitario:</th>
			<td>
				<input type="text" name="precioUnitario" value="" style="width: 50%;"/>
			</td>
		</tr>
		
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Precio Con Descuento:</th>
			<td>
				<input type="text" name="precioDescuento" value="" style="width: 50%;"/>
			</td>
		</tr>
		
		<tr>
			<th nowrap="nowrap" style="text-align: right;">Cantidad disponible:</th>
			<td>
				<input type="text" name="cantidadDisponible" value="" style="width: 50%;"/>
			</td>
		</tr>
		
		</table>
	</fieldset>
	<br/>

	<div>
		<button type="button" id="btnCrearCancelarProducto1" name="btnCrearCancelarProducto1" onclick="$('#dmNuevoProducto').dialog('close');">Cancelar</button>
		&nbsp;&nbsp;&nbsp;
		<button type="submit" id="btnCrearProducto1" name="btnCrearProducto1">Crear</button>
	</div>
</form>

</div>
<div style="padding: 30px 0px;"></div>
