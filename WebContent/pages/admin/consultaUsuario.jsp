<%@ include file="/taglibs.jsp"%>

<link type="text/css" href="${ctx}/css/sigpro.css" rel="stylesheet" />			
<link type="text/css" href="${ctx}/css/jquery/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />
<%-- <link type="text/css" href="${ctx}/css/jquery/jquery.multiselect.css" rel="Stylesheet" /> --%>

<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.tablesorter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.fixedtableheader.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.fixedtable.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.hotkeys-0.7.9.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.dateformat.js"></script>	

<%-- <script type="text/javascript" src="${ctx}/js/graft/highcharts-custom.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/js/cvi_text_lib.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/js/jquery.flipv.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/js/ui.dropdownchecklist-1.4-min.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/js/jquery.multiselect.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/js/jquery.multiselect.filter.js"></script> --%>

<%-- <link href='${ctx}/js/qtip/jquery.qtip.css' rel='stylesheet' /> --%>
<%-- <script src='${ctx}/js/qtip/jquery.qtip.js'></script> --%>

<%-- <link href='${ctx}/fullcalendar-2.2.6/fullcalendar.css' rel='stylesheet' /> --%>
<%--
<script src='${ctx}/fullcalendar-2.2.6/lib/moment.min.js'></script>
<script src='${ctx}/fullcalendar-2.2.6/fullcalendar.min.js'></script>
<script src='${ctx}/fullcalendar-2.2.6/lang-all.js'></script>
--%>

<%-- <link type="text/css" href="${ctx}/css/jquery/jquery.multiselect.css" rel="Stylesheet" /> --%>
<%-- <script type="text/javascript" src="${ctx}/js/ui.dropdownchecklist-1.4-min.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/js/jquery.multiselect.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/js/jquery.multiselect.filter.js"></script> --%>


<%-- <script type="text/javascript" src="${ctx}/js/croppic.js"></script> --%>
<%-- <link rel="stylesheet" href="${ctx}/css/croppic.css"> --%>

<%--
<script type="text/javascript" src="${ctx}/restive.js-master/restive.min.js"></script>

<script type="text/javascript" src="${ctx}/Animated-Circle/src/jquery-asPieProgress.js"></script>
<link rel="stylesheet" href="${ctx}/Animated-Circle/css/progress.css">
<script type="text/javascript" src="${ctx}/Animated-Circle/src/jquery-asPieProgress.js"></script>
<link rel="stylesheet" href="${ctx}/Animated-Circle/css/progress.css">
--%>
<script type="text/javascript">

	$(document).ready(function() {
		
		
		/** Botones */
		$("#btnBuscarUsuario").button();
		$("#btnNuevoUsuario").button();
		
		/** Dialogos */
		$("#dmNuevoUsuario").dialog({   				
			width: 800,
			height: 400,   				
			modal: true,
			autoOpen: false,
			resizable: true
		});
		
		$("#dmMensajeUsuario").dialog({   				
			width: 400,
			height: 200,   				
			modal: true,
			autoOpen: false,
			resizable: true
		});
		
		/** Cambios en los tipos de busqueda */
		$("#tipoBusquedaUsuario").change( function() {
			var data =  $(this).val();
			
			$("#porTipoIdentificacion").hide();
			$("#porNombreUsuario").hide();
			
			if(data==1){
				$("#porNombreUsuario").show();
			}else if(data==2){
				$("#porTipoIdentificacion").show();
			}
		});	
		
		/** Validar formulario */
		$("#fBuscarUsuario").validate({
			errorLabelContainer: "#msnBuscarUsuario",
			errorClass: "invalid",
			rules: {
				tipoBusquedaUsuario:{
					required: true
				},
				nombre:{
					required: function(element){ return $("#tipoBusquedaUsuario").val() == 1;} 
				},
				numIdentificacion:{
					required: function(element){ return $("#tipoBusquedaUsuario").val() == 2;}, 
					digits: function(element){ return $("#tipoBusquedaUsuario").val() == 2;}
				}
			},
			messages: {
				tipoBusquedaUsuario: {
					required: "Selecione un tipo de búsqueda."
				},
				nombre:{
					required: "Ingrese un valor." 
				},
				numIdentificacion:{
					required: "Ingrese un valor.",
					digits: "Ingrese solo dígitos"
				}
				
			},
			submitHandler: function(form) {
				consultarUsuario(form);
			}
		});
		
		/** Nuevo Usuario */
		$("#btnNuevoUsuario").click(function(){
			$("#dmNuevoUsuario").dialog("open");
			$("#dmNuevoUsuario").html(getHTMLLoaging30());
			
			$.ajax({
					cache: false,
					contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
		            type: 'POST',	            
		            url: "${ctx}/page/usuario?action=crearCargarNuevoUsuario",
		            dataType: "text",
		            error: function(jqXHR, textStatus, errorThrown) {
		                alert(jqXHR.statusText);
			        },
		            success: function(data) {		                    	                    	
		               	$("#dmNuevoUsuario").html(data);     
		            }
		        });
		});
		
	});
	
	/** Consultar Usuarios */
	function consultarUsuario(form){
		$("#resultado").html(getHTMLLoaging30()); 
       	$("#btnBuscarUsuario").attr('disabled',true);;	 				
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
                $("#resultado").html(data);
                $("#btnBuscarUsuario").attr('disabled',false);	 
            }
        });
	}
	
	
	
</script>
	
<div id="consultaPrincipal">
<%-- 	<form id="fCrearUsuario" name="fCrearUsuario" action="${ctx}/page/usuario" method="post" accept-charset=utf-8> --%>
<!-- 	<input type="hidden" name="action" value="nuevoUsuario"/> -->
<!-- 	<input type="hidden" name="busqueda" value="2"> -->
<form name="fBuscarUsuario" id="fBuscarUsuario" action="${ctx}/page/usuario" method="post">
		<fieldset>
		<legend class="e6">Consulta</legend>
				<table style="cellspacing:2,  width:100%, border:0">
				<col />
				<!-- 
				<col style="width: 5px;"/>
				<col style="width: 200px;"/>	
				-->
				<tr  valign="middle">
					<td class="td3">
<!-- 					<td> -->
				
					  <input type="hidden" name="action" value="consultarUsuarios"/>
					  <input type="hidden" name="busqueda" value="1"/>
				      <table style="width:100% border:0">          
				        <tr class="td3" valign="middle">
				          <td height="33" align="right" valign="middle" width="300">
				           	Buscar por: 
				           	<select name="tipoBusquedaUsuario" id="tipoBusquedaUsuario">
				           		<option value="1">Nombre o apellido</option>
				           		<option value="2">Tip. Identificación</option>
				           	</select>  
				           	&nbsp;&nbsp; &nbsp;&nbsp;          
				         </td>
				         <td valign="middle" align="left" width="360">
				         		
				         		<!-- Por tipo de identificacion -->
				         		<div id="porTipoIdentificacion" style="display: none;">   
					            	<select name="tipoDocumento" id="tipoDocumento">
						           		<c:forEach items="${listaTiposDocumento}" var="tipoDocumento" varStatus="loop">
											<option  value="${tipoDocumento.idTipoDocumento}">
												<c:out value="${tipoDocumento.tipo} - ${tipoDocumento.descripcion}"/>
											</option>
										</c:forEach>
						           	</select> 
						           	&nbsp;
					            	<input type="text" name="numIdentificacion">
					            </div>	  
					                 
					            <!-- Por nombre -->    
					            <div id="porNombreUsuario" style="display: block;">   
					            	<input type="text" name="nombre" size="50">
					            </div>
					           					            	
				           </td>          
				          <td valign="middle" align="left">                       
				           	<button type="submit" id="btnBuscarUsuario" name="btnBuscarUsuario">Consultar</button>
				          </td>
<!-- 				         </tr> -->
<!-- 				       </table> -->
<!-- 			       </td> -->
			       
			       <td></td>
			       
			       	<td>  
				       	<!-- Nuevo usuario -->
						<td valign="middle" align="center" >
						 	<button type="button" id="btnNuevoUsuario" name="btnNuevoUsuario">Nuevo usuario</button>
						</td>
<!-- 					</td> -->
				
						</table>
</tr>
				</table>
		</fieldset>
		 
	</form>

	<div  align="center" id="msnBuscarUsuario" ></div>
	<br><br>
	<div id="resultado"></div>

</div>

<!-- Divs de dialogos -->
<div id="dmNuevoUsuario" title="Nuevo Usuario"></div>
<div id="dmMensajeUsuario" title="Mensaje"></div>

