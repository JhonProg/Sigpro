<%@ include file="/taglibs.jsp"%>

<script type="text/javascript">

	$(document).ready(function() {
		$("#btnNuevaMeta").button();
		
		/** Validar formulario */
		$("#fMetas").validate({
			errorLabelContainer: "",
			errorClass: "invalid",
			rules: {
			},
			messages: {								
			},
			submitHandler: function(form) {
				cargarNuevaMeta(form);
			}
		});
		
	
		
		/** Dialogos */
		$("#dmNuevaMeta").dialog({   				
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
		
		/** Nuevo Usuario */
		$("#btnNuevaMeta").click(function(){
			$("#dmNuevaMeta").dialog("open");
			$("#dmNuevaMeta").html(getHTMLLoaging30());
			
			$.ajax({
					cache: false,
					contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
		            type: 'POST',	            
		            url: "${ctx}/page/meta?action=crearCargarNuevaMeta",
		            dataType: "text",
		            error: function(jqXHR, textStatus, errorThrown) {
		                alert(jqXHR.statusText);
			        },
		            success: function(data) {		                    	                    	
		               	$("#dmNuevaMeta").html(data);     
		            }
		        });
		});
		
	});	
	
	
</script>

<div align="left">
		<fieldset>
		<legend class="e6">Configuración de metas</legend>
		
		<table style="cellspacing:2,  width:100%, border:0">
			<col />
				         
			<tr class="td3" valign="middle">
	          <td valign="middle" align="left">                       
	           	<button type="button" id="btnNuevaMeta" name="btnNuevaMeta">Nueva Meta</button>
	          </td>
			</tr>
		</table>
		
	</fieldset>
</div>
<br><br>
<!-- <div id="cajaResultadosPedidosDirctor"></div>-->
<div id="dmNuevaMeta" title="Nueva Meta"></div>
<div id="dmMensajeUsuario" title="Mensaje"></div>