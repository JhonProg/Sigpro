<%@ include file="/taglibs.jsp"%>

<link type="text/css" href="${ctx}/css/sigpro.css" rel="stylesheet" />			
<link type="text/css" href="${ctx}/css/jquery/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />

<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.tablesorter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.fixedtableheader.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.fixedtable.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.hotkeys-0.7.9.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.dateformat.js"></script>	

<script type="text/javascript">

	$(document).ready(function() {
				
		$("#dmMensajeCliente").dialog({   				
			width: 400,
			height: 200,   				
			modal: true,
			autoOpen: false,
			resizable: true
		});
		
		$("#dmMensajeProductoPedido").dialog({   				
			width: 400,
			height: 200,   				
			modal: true,
			autoOpen: false,
			resizable: true
		});
		
		$("#dmCarritoCompras").dialog({   				
			width: 600,
			height: 400,   				
			modal: true,
			autoOpen: false,
			resizable: true
		});
		
		
		$("#btnCrearCliente").button();	
		$("#btnValidarCliente").button();
		$("#btnActualizarCliente").button();
	    $("#btnCrearNuevoCliente").button();
	    $("#btnBuscarProductoPedido").button();
		
		$("#divCrearCliente").hide();
		$("#divMostrarCliente").hide();
		$("#divConsultarProductosParaPedido").hide();
				
		/** Validar formulario VALIDAR CLIENTE*/
		$("#fValidarCliente").validate({
			errorClass: "invalid",
			rules: {
				numIdentificacionCliente: {
					required: true,
					digits: true,
					maxlength: 20
				}				  		
			},
			messages: {
				numIdentificacionCliente: {
					required: "Ingrese un numero de documento.",
					digits: "Ingrese solo digitos.",
					maxlength:"Se permiten maximo 20 digitos."
				}	
			},
			submitHandler: function(form) {				
				validarCliente(form);
			}
		});
		
		
		
		/** Validar formulario ACTUALIZAR CLIENTE */
		$("#fMostrarCliente").validate({
			errorClass: "invalid",
			rules: {
				idTipoIdentificacionClienteEncontrado: {
					required: true
				},
				numIdentificacionClienteEncontrado:{
					required: true,
					digits: true,
					maxlength: 20
				},
				numIdentificacionClienteRepetidoEncontrado:{
					required: true,
					digits: true,
					maxlength: 20,
					equalTo: "#numIdentificacionClienteEncontrado"
				},
				nombresClienteEncontrado:{
					required: true,
					maxlength: 50
				},
				apellidosClienteEncontrado:{
					required: true,
					maxlength: 50
				},
				direccionClienteEncontrado:{
					required: true,
					maxlength: 100
				},
				telefonoClienteEncontrado:{
					required: true,
					digits: true,
					maxlength: 20
				}
			},
			messages: {
				idTipoIdentificacionClienteEncontrado: {
					required: "Seleccione un tipo de documento."
				},
				numIdentificacionClienteEncontrado:{
					required: "Ingrese un numero de identificación.",
					digits: "Ingrese solo digitos.",
					maxlength: "El numero maximo de digitos es 20."
				},
				numIdentificacionClienteRepetidoEncontrado:{
					required: "Ingrese un numero de identificación.",
					digits: "Ingrese solo digitos.",
					maxlength: "El numero maximo de digitos es 20.",
					equalTo: "Los numeros de identificación deben ser iguales."
				},
				nombresClienteEncontrado:{
					required: "Ingrese el nombre del cliente.",
					maxlength: "La cantidad de letras maxima es 50."
				},
				apellidosClienteEncontrado:{
					required: "Ingrese el apellido del cliente.",
					maxlength: "La cantidad de letras maxima es 50."
				},
				direccionClienteEncontrado:{
					required: "Ingrese la dirección del cliente.",
					maxlength: "La cantidad de letras maxima es 100."
				},
				telefonoClienteEncontrado:{
					required: "Ingrese el numero de telefono o celular del cliente.",
					digits: "Ingrese solo digitos.",
					maxlength: "La cantidad maxima de digitsos es 20"
				}
			},
			submitHandler: function(form) {				
				actualizarCliente(form);
			}
		});
		
		
		/** Validar formulario CREAR CLIENTE */
		$("#fCrearCliente").validate({
			errorClass: "invalid",
			rules: {
				idTipoIdentificacionNuevoCliente: {
					required: true
				},
				numIdentificacionNuevoCliente:{
					required: true,
					digits: true,
					maxlength: 20
				},
				numIdentificacionNuevoClienteRepetido:{
					required: true,
					digits: true,
					maxlength: 20,
					equalTo: "#numIdentificacionNuevoCliente"
				},
				nombresNuevoCliente:{
					required: true,
					maxlength: 50
				},
				apellidosNuevoCliente:{
					required: true,
					maxlength: 50
				},
				direccionNuevoCliente:{
					required: true,
					maxlength: 100
				},
				telefonoNuevoCliente:{
					required: true,
					digits: true,
					maxlength: 20
				}
			},
			messages: {
				idTipoIdentificacionNuevoCliente: {
					required: "Seleccione un tipo de documento."
				},
				numIdentificacionNuevoCliente:{
					required: "Ingrese un numero de identificación.",
					digits: "Ingrese solo digitos.",
					maxlength: "El numero maximo de digitos es 20."
				},
				numIdentificacionNuevoClienteRepetido:{
					required: "Ingrese un numero de identificación.",
					digits: "Ingrese solo digitos.",
					maxlength: "El numero maximo de digitos es 20.",
					equalTo: "Los numeros de identificación deben ser iguales."
				},
				nombresNuevoCliente:{
					required: "Ingrese el nombre del cliente.",
					maxlength: "La cantidad de letras maxima es 50."
				},
				apellidosNuevoCliente:{
					required: "Ingrese el apellido del cliente.",
					maxlength: "La cantidad de letras maxima es 50."
				},
				direccionNuevoCliente:{
					required: "Ingrese la dirección del cliente.",
					maxlength: "La cantidad de letras maxima es 100."
				},
				telefonoNuevoCliente:{
					required: "Ingrese el numero de telefono o celular del cliente.",
					digits: "Ingrese solo digitos.",
					maxlength: "La cantidad maxima de digitsos es 20"
				}
			},
			submitHandler: function(form) {				
				crearCliente(form);
			}
		});
		
		//----------------------------------------------------------------
		
		/** Cambios en los tipos de busqueda */
		$("#tipoBusquedaProductoPedido").change( function() {
			var data =  $(this).val();
			
			$("#porNombreProductoPedido").hide();
			$("#porCategoriaProductoPedido").hide();
			
			if(data==2){
				$("#porCategoriaProductoPedido").show();
			}else if(data==1){
				$("#porNombreProductoPedido").show();
			}
		});	
		
		/** Validar formulario */
		$("#fBuscarProductosPedido").validate({
			errorLabelContainer: "#msnBuscarProductoPedido",
			errorClass: "invalid",
			rules: {
				tipoBusquedaProductoPedido:{
					required: true
				},
				nombreProductoPedido:{
					required: function(element){ return $("#tipoBusquedaProductoPedido").val() == 1;}
				}
			},
			messages: {
				tipoBusquedaProductoPedido: {
					required: "Selecione un tipo de búsqueda."
				},
				nombreProductoPedido:{
					required: "Ingrese un valor."
				}
				
			},
			submitHandler: function(form) {
				consultarProductosPedido(form);
			}
		});
		
		
	});
	
	/** Consultar Productos */
	function consultarProductosPedido(form){
		$("#resultadoProductosPedido").html(getHTMLLoaging30()); 
       	$("#btnBuscarProductoPedido").attr('disabled',true);				
		$.ajax({
			cache: false,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
            type: 'POST',
            url: $(form).attr('action'),
            data: $(form).serialize(),
            dataType: "text",
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.statusText);
	        },
            success: function(data) {
                $("#resultadoProductosPedido").html(data);
                $("#btnBuscarProductoPedido").attr('disabled',false);	 
            }
        });
	}
	
	function validarCliente(form){
		
		$("#divCrearCliente").hide();
		$("#divMostrarCliente").hide();
		$("#divConsultarProductosParaPedido").hide();
		
		$("#dmMensajeCliente").dialog("open");
		$("#dmMensajeCliente").html(getHTMLLoaging16(' Validando'));
		$.ajax({
			cache: false,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
            type: $(form).attr('method'),            
            url: $(form).attr('action'),
            data: $(form).serialize(),
            dataType: "text",
            error: function(jqXHR, textStatus, errorThrown) {
            	var mensajeER = '<h3 style="color:red">'+jqXHR.statusText+'</h3>';
             	$("#divMostrarCliente").hide();
             	$("#divCrearCliente").show();
             	$("#dmMensajeCliente").html(mensajeER);
	        },
            success: function(data) {
				var mensajeOK = '<h3 style="color:#58ACFA">El cliente se encuentra registrado.</h3>';
				$("#dmMensajeCliente").html(mensajeOK);
				$("#divCrearCliente").hide();
				$("#divMostrarCliente").show();
				$("#divConsultarProductosParaPedido").show();				
				setearDatosClienteParaEditar(data);
            }
        });
	}
	
	function setearDatosClienteParaEditar(data){
		var datosCliente = data.split("|");
		
		/** Para la consulta */
		$('input[name="idCliente"]').val(datosCliente[0]);
		
		/** Para el formulario */
		$("#fMostrarCliente [name=idTipoIdentificacionClienteEncontrado]").val(datosCliente[1]);
		$('input[name="idClienteEncontrado"]').val(datosCliente[0]);
		$("#numIdentificacionClienteEncontrado").val(datosCliente[2]);
		$("#numIdentificacionClienteRepetidoEncontrado").val(datosCliente[2]);
		$("#nombresClienteEncontrado").val(datosCliente[3]);
		$("#apellidosClienteEncontrado").val(datosCliente[4]);
		$("#direccionClienteEncontrado").val(datosCliente[5]);
		$("#telefonoClienteEncontrado").val(datosCliente[6]);
	}
	
	function actualizarCliente(form){
		$("#dmMensajeCliente").dialog("open");
		$("#dmMensajeCliente").html(getHTMLLoaging16(' Actualizando'));
		$.ajax({
			cache: false,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
            type: $(form).attr('method'),            
            url: $(form).attr('action'),
            data: $(form).serialize(),
            dataType: "text",
            error: function(jqXHR, textStatus, errorThrown) {
             	
            	var mensajeER = '<h3 style="color:red">'+jqXHR.statusText+'</h3>';
             	$("#dmMensajeCliente").html(mensajeER);
             	
             	$("#divConsultarProductosParaPedido").hide();
	        },
            success: function(data) {
				setearDatosClienteParaEditar(data);            	
				$("#divConsultarProductosParaPedido").show();				
				var mensajeOK = '<h3 style="color:#58ACFA">Cliente actualizado con exito. Cnotinue con el pedido.</h3>';
				$("#dmMensajeCliente").html(mensajeOK);
            }
        });
	}
	
	function crearCliente(form){
		
		$("#dmMensajeCliente").dialog("open");
		$("#dmMensajeCliente").html(getHTMLLoaging16(' Creando'));
		$.ajax({
			cache: false,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
            type: $(form).attr('method'),            
            url: $(form).attr('action'),
            data: $(form).serialize(),
            dataType: "text",
            error: function(jqXHR, textStatus, errorThrown) {
             	
            	var mensajeER = '<h3 style="color:red">'+jqXHR.statusText+'</h3>';
             	$("#dmMensajeCliente").html(mensajeER);
             	
             	$("#divMostrarCliente").hide();
             	$("#divConsultarProductosParaPedido").hide();
	        },
            success: function(data) {
            	/** Limpiar formualrio de creacion. */
            	limpiarFormularioCreacionCliente();
            	
            	$("#divCrearCliente").hide();
        		$("#divMostrarCliente").show();
        		$("#divConsultarProductosParaPedido").show();
            	
				setearDatosClienteParaEditar(data);  
								
				var mensajeOK = '<h3 style="color:#58ACFA">Cliente creado con exito. Continue con el pedido.</h3>';
				$("#dmMensajeCliente").html(mensajeOK);
            }
        });
		
	}
	
	function limpiarFormularioCreacionCliente(){
		document.getElementById("fCrearCliente").reset();
	}
	

	function verCarritoCompras(){
		
		var idClienteEncontrado = $('input[name="idClienteEncontrado"]').val();
				
		if(idClienteEncontrado.length == 0){
			alert("No hay productos que mostrar.Debe buscar el cliente y crear/buscar el pedido.");
		}else{
		
			$("#dmCarritoCompras").dialog("open");
			$("#dmCarritoCompras").html(getHTMLLoaging16(' Consultando carrito de compras'));
			
			$.ajax({
				cache: false,
				contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
		        type: 'POST',
		        url: "${ctx}/page/pedido?action=consultarCarritoCompras&idCliente="+idClienteEncontrado,
		        dataType: "text",
		        error: function(jqXHR, textStatus, errorThrown) {
		        	var mensajeER = '<h3 style="color:red">'+jqXHR.statusText+'</h3>';
		         	$("#dmCarritoCompras").html(mensajeER);
		        },
		        success: function(data) {
		        	$("#dmCarritoCompras").html(data);
		        }
		    });
		}
	}
	
</script>

<!-- VALIDAR EXISTENCIA DE CLIENTE -->
<div id="divValidarCliente">
	<form name="fValidarCliente" id="fValidarCliente" action="${ctx}/page/cliente" method="post">
		<input type="hidden" name="action" value="validarSiExisteCliente"/>
		<fieldset>
			<legend class="e6">Validar Cliente</legend>
					
					<h3 style="color:#58ACFA">Para crear un nuevo pedido,Valide si el cliente ya existe.</h3>
					<br>
					
					<!-- Información del cliente -->
					
					<table align ="center" border="0" width="60%" class="caja">
						<col style="width: 15%"/>
						<col/>
						<tr>
							<th nowrap="nowrap" style="text-align: right;">Tipo de identificación:</th>
							<td>
								<select id="idTipoDocumentoCliente" name="idTipoDocumentoCliente">
									<c:forEach items="${listaTiposDocumento}" var="tipoDocumento" varStatus="loop">
										<option value="${tipoDocumento.idTipoDocumento}">
											<c:out value="${tipoDocumento.tipo}"/> - <c:out value="${tipoDocumento.descripcion}"/>
										</option>
									</c:forEach>
								</select>
							</td>
							<th nowrap="nowrap" style="text-align: right;">Num. Identificación:</th>
								<td>
									<input type="text" name="numIdentificacionCliente"  id="numIdentificacionCliente" value="" style="width: 30%;"/>
									&nbsp;
									<button type="submit" id="btnValidarCliente" name="btnValidarCliente">Validar si existe</button>
								</td>
						</tr>
						</table>					
			</fieldset>
	</form>
</div>
<br>

<!-- CREAR NUEVO CLIENTE -->
<div id="divCrearCliente">
	<form name="fCrearCliente" id="fCrearCliente" action="${ctx}/page/cliente" method="post">
		<input type="hidden" name="action" value="crearCliente"/>
			<fieldset>
			<legend class="e6">Nuevo Cliente</legend>
					
					<h3 style="color:#58ACFA">A continuación, registre la información del cliente...</h3>
					<br>
					
					<!-- Información del cliente -->
					
					<table align ="center" border="0" width="60%" class="caja">
						<col style="width: 15%"/>
						<col/>
						<tr>
						<th nowrap="nowrap" style="text-align: right;">Tipo de identificación:</th>
							<td>
								<select id="idTipoDocumentoNuevoCliente" name="idTipoDocumentoNuevoCliente">
									<c:forEach items="${listaTiposDocumento}" var="tipoDocumento" varStatus="loop">
										<option value="${tipoDocumento.idTipoDocumento}">
											<c:out value="${tipoDocumento.tipo}"/> - <c:out value="${tipoDocumento.descripcion}"/>
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						
							<tr>
								<th nowrap="nowrap" style="text-align: right;">Num. Identificación:</th>
								<td>
									<input type="text" name="numIdentificacionNuevoCliente"  id="numIdentificacionNuevoCliente" value="" style="width: 50%;"/>
								</td>
							</tr>
							
							<tr>
								<th nowrap="nowrap" style="text-align: right;">Repetir Num. Identificación:</th>
								<td>
									<input type="text" name="numIdentificacionNuevoClienteRepetido" id="numIdentificacionNuevoClienteRepetido" value="" style="width: 50%;"/>
								</td>
							</tr>
							
							<tr>
								<th nowrap="nowrap" style="text-align: right;">Nombres:</th>
								<td>
									<input type="text" id="nombresNuevoCliente" name="nombresNuevoCliente" value="" style="width: 50%;"/>
								</td>
							</tr>
							<tr>
								<th nowrap="nowrap" style="text-align: right;">Apellidos:</th>
								<td>
									<input type="text" id="apellidosNuevoCliente" name="apellidosNuevoCliente" value="" style="width: 50%;"/>
								</td>
							</tr>
							<tr>
							
							<tr>
								<th nowrap="nowrap" style="text-align: right;">Dirección:</th>
								<td>
									<input type="text" id="direccionNuevoCliente" name="direccionNuevoCliente" value="" style="width: 50%;"/>
								</td>
							</tr>
							
							<tr>
								<th nowrap="nowrap" style="text-align: right;">Teléfono:</th>
								<td>
									<input type="text" id="telefonoNuevoCliente" name="telefonoNuevoCliente" value="" style="width: 50%;"/>
								</td>
							</tr>
					</table>
					<div id="divBotonCrearNuevoCliente">
						<br>
						<button type="submit" id="btnCrearNuevoCliente" name="btnCrearNuevoCliente">Crear Cliente</button>
					</div>
		</fieldset>
	</form>
</div>
<br>

<!-- ACTUALIZAR CLIENTE EXISTENTE -->
<div id="divMostrarCliente">
	<form name="fMostrarCliente" id="fMostrarCliente" action="${ctx}/page/cliente" method="post">
		<input type="hidden" name="action" value="editarCliente"/>
		<input type="hidden" name="idClienteEncontrado" value=""/>
		<fieldset>
			<legend class="e6">Cliente</legend>
								
					<h3 style="color:#58ACFA">Información cliente encontrado...</h3>
					<br>											
					<!-- Información del cliente -->
					
					<table align ="center" border="0" width="60%" class="caja">
						<col style="width: 15%"/>
						<col/>
						<tr>
						<th nowrap="nowrap" style="text-align: right;">Tipo de identificación:</th>
							<td>
								<select id="idTipoIdentificacionClienteEncontrado" name="idTipoIdentificacionClienteEncontrado">
									<c:forEach items="${listaTiposDocumento}" var="tipoDocumento" varStatus="loop">
										<option value="${tipoDocumento.idTipoDocumento}">
											<c:out value="${tipoDocumento.tipo}"/> - <c:out value="${tipoDocumento.descripcion}"/>
										</option>
									</c:forEach>
								</select>								
							</td>
						</tr>
						
							<tr>
								<th nowrap="nowrap" style="text-align: right;">Num. Identificación:</th>
								<td>
									<input type="text" name="numIdentificacionClienteEncontrado"  id="numIdentificacionClienteEncontrado" value="" style="width: 50%;"/>
								</td>
							</tr>
							
							<tr>
								<th nowrap="nowrap" style="text-align: right;">Repetir Num. Identificación:</th>
								<td>
									<input type="text" name="numIdentificacionClienteRepetidoEncontrado" id="numIdentificacionClienteRepetidoEncontrado" value="" style="width: 50%;"/>
								</td>
							</tr>
							
							<tr>
								<th nowrap="nowrap" style="text-align: right;">Nombres:</th>
								<td>
									<input type="text" id="nombresClienteEncontrado" name="nombresClienteEncontrado" value="" style="width: 50%;"/>
								</td>
							</tr>
							<tr>
								<th nowrap="nowrap" style="text-align: right;">Apellidos:</th>
								<td>
									<input type="text" id="apellidosClienteEncontrado" name="apellidosClienteEncontrado" value="" style="width: 50%;"/>
								</td>
							</tr>
							<tr>
							
							<tr>
								<th nowrap="nowrap" style="text-align: right;">Dirección:</th>
								<td>
									<input type="text" id="direccionClienteEncontrado" name="direccionClienteEncontrado" value="" style="width: 50%;"/>
								</td>
							</tr>
							
							<tr>
								<th nowrap="nowrap" style="text-align: right;">Teléfono:</th>
								<td>
									<input type="text" id="telefonoClienteEncontrado" name="telefonoClienteEncontrado" value="" style="width: 50%;"/>
								</td>
							</tr>
			</table>
			<div id="divBotonActualizarCliente">
				<br>
				<button type="submit" id="btnActualizarCliente" name="btnActualizarCliente">Actualizar Cliente</button>
			</div>			
		</fieldset>
	</form>
</div>
<br>

<!-- --------------------- -->
<!-- BUSQUEDA DE PRODUCTOS -->
<!-- --------------------- -->
<div id="divConsultarProductosParaPedido">
	
<form name="fBuscarProductosPedido" id="fBuscarProductosPedido" action="${ctx}/page/pedido" method="post">
		<input type="hidden" name="action" value="consultarProductos"/>
		<input type="hidden" name="idCliente" value=""/>
		<fieldset>
		<legend class="e6">Consulta</legend>
				<table style="cellspacing:2,  width:100%, border:0">
				<col />
				
				<tr  valign="middle">
					<td class="td3">
				
				      <table style="width:100% border:0">          
				        <tr class="td3" valign="middle">
				          <td height="33" align="right" valign="middle" width="300">
				           	Buscar por: 
				           	<select name="tipoBusquedaProductoPedido" id="tipoBusquedaProductoPedido">
				           		<option value="2">Categoria de producto</option>
				           		<option value="1">Nombre de producto</option>				           		
				           	</select>  
				           	&nbsp;&nbsp; &nbsp;&nbsp;          
				         </td>
				         <td valign="middle" align="left" width="360">
				         		
				         		<!-- Por nombre de producto -->
				         		<div id="porNombreProductoPedido" style="display: none;">   
					            	<input type="text" name="nombreProductoPedido">
					            </div>	  
					                 
					            <!-- Por categoria -->    
					            <div id="porCategoriaProductoPedido" style="display: block;">   
					            	<select name="idCategoriaProductoPedido" id="idCategoriaProductoPedido">
						           		<c:forEach items="${listaCategoriasProducto}" var="categoria" varStatus="loop">
											<option  value="${categoria.idProductoCategoria}">
												<c:out value="${categoria.categoria}"/>
											</option>
										</c:forEach>
						           	</select> 
						           	&nbsp;
					            </div>
					           					            	
				           </td>          
				          <td valign="middle" align="left">                       
				           	<button type="submit" id="btnBuscarProductoPedido" name="btnBuscarProductoPedido">Consultar</button>
				          </td>
			       <td></td>
			       
						</table>
					</tr>
				</table>
		</fieldset>
		 
	</form>

	<div  align="center" id="msnBuscarProductoPedido" ></div>
	<br><br>
	
	<!-- Caja de resultados -->
	<div id="resultadoProductosPedido"></div>
		
</div>

<div id="dmMensajeCliente" title="Mensaje"></div>
<div id="dmMensajeProductoPedido" title="Mensaje"></div>
<div id="dmCarritoCompras" title="Carrito"></div>