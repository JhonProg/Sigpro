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
		
		
		/** Botones */
		$("#btnBuscarProducto").button();
		$("#btnNuevoProducto").button();
		
		/** Dialogos */
		$("#dmNuevoProducto").dialog({   				
			width: 800,
			height: 400,   				
			modal: true,
			autoOpen: false,
			resizable: true
		});
		
		$("#dmMensajeProducto").dialog({   				
			width: 400,
			height: 200,   				
			modal: true,
			autoOpen: false,
			resizable: true
		});
		
		/** Cambios en los tipos de busqueda */
		$("#tipoBusquedaProducto").change( function() {
			var data =  $(this).val();
			
			$("#porNombreProducto").hide();
			$("#porCategoriaProducto").hide();
			
			if(data==2){
				$("#porCategoriaProducto").show();
			}else if(data==1){
				$("#porNombreProducto").show();
			}
		});	
		
		/** Validar formulario */
		$("#fBuscarProductos").validate({
			errorLabelContainer: "#msnBuscarProducto",
			errorClass: "invalid",
			rules: {
				tipoBusquedaProducto:{
					required: true
				},
				nombre:{
					required: function(element){ return $("#tipoBusquedaProducto").val() == 1;} 
				},
				nombreProducto:{
					required: function(element){ return $("#tipoBusquedaProducto").val() == 2;}
				}
			},
			messages: {
				tipoBusquedaProducto: {
					required: "Selecione un tipo de búsqueda."
				},
				nombre:{
					required: "Ingrese un valor." 
				},
				nombreProducto:{
					required: "Ingrese un valor."
				}
				
			},
			submitHandler: function(form) {
				consultarProductos(form);
			}
		});
		
		/** Nuevo producto */
		$("#btnNuevoProducto").click(function(){
			$("#dmNuevoProducto").dialog("open");
			$("#dmNuevoProducto").html(getHTMLLoaging30());
			
			$.ajax({
					cache: false,
					contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
		            type: 'POST',	            
		            url: "${ctx}/page/producto?action=crearCargarNuevoProducto",
		            dataType: "text",
		            error: function(jqXHR, textStatus, errorThrown) {
		                alert(jqXHR.statusText);
			        },
		            success: function(data) {		                    	                    	
		               	$("#dmNuevoProducto").html(data);     
		            }
		        });
		});
		
	});
	
	/** Consultar Productos */
	function consultarProductos(form){
		$("#resultadoProductos").html(getHTMLLoaging30()); 
       	$("#btnBuscarProducto").attr('disabled',true);				
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
                $("#resultadoProductos").html(data);
                $("#btnBuscarProducto").attr('disabled',false);	 
            }
        });
	}
	
	
	
</script>
	
<div id="consultaPrincipalProductos">

<form name="fBuscarProductos" id="fBuscarProductos" action="${ctx}/page/producto" method="post">
	<input type="hidden" name="action" value="consultarProductos"/>
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
				           	<select name="tipoBusquedaProducto" id="tipoBusquedaProducto">
				           		<option value="2">Categoria de producto</option>
				           		<option value="1">Nombre de producto</option>				           		
				           	</select>  
				           	&nbsp;&nbsp; &nbsp;&nbsp;          
				         </td>
				         <td valign="middle" align="left" width="360">
				         		
				         		<!-- Por nombre de producto -->
				         		<div id="porNombreProducto" style="display: none;">   
					            	<input type="text" name="nombreProducto">
					            </div>	  
					                 
					            <!-- Por categoria -->    
					            <div id="porCategoriaProducto" style="display: block;">   
					            	<select name="idCategoriaProducto" id="idCategoriaProducto">
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
				           	<button type="submit" id="btnBuscarProducto" name="btnBuscarProducto">Consultar</button>
				          </td>
			       <td></td>
			       
			       	<td>  
				       	<!-- Nuevo usuario -->
						<td valign="middle" align="center" >
						 	<button type="button" id="btnNuevoProducto" name="btnNuevoProducto">Nuevo producto</button>
						</td>
				
						</table>
					</tr>
				</table>
		</fieldset>
		 
	</form>

	<div  align="center" id="msnBuscarProducto" ></div>
	<br><br>
	
	<!-- Caja de resultados -->
	<div id="resultadoProductos"></div>

</div>

<!-- Divs de dialogos -->
<div id="dmNuevoProducto" title="Nuevo Producto"></div>
<div id="dmMensajeProducto" title="Mensaje"></div>

