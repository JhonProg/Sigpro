<%@ include file="/taglibs.jsp"%>

<script type="text/javascript">

	$(document).ready(function() {
		$("#btnBuscarPedidosDirector").button();
		
		/** Validar formulario */
		$("#fBuscarPedidosDirector").validate({
			errorLabelContainer: "",
			errorClass: "invalid",
			rules: {
			},
			messages: {								
			},
			submitHandler: function(form) {
				consultarPedidosDirector(form);
			}
		});
		
	});	
	
	function consultarPedidosDirector(form){
		$("#cajaResultadosPedidosDirctor").html(getHTMLLoaging30()); 
       	$("#btnBuscarPedidosDirector").attr('disabled',true);				
		$.ajax({
			cache: false,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
            type: 'POST',
            url: $(form).attr('action'),
            data: $(form).serialize(),
            dataType: "text",
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.statusText);
                $("#btnBuscarPedidosDirector").attr('disabled',false);	
	        },
            success: function(data) {
                $("#cajaResultadosPedidosDirctor").html(data);
                $("#btnBuscarPedidosDirector").attr('disabled',false);	 
            }
        });
	}
	
</script>

<div align="left">
	<form name="fBuscarPedidosDirector" id="fBuscarPedidosDirector" action="${ctx}/page/pedido" method="post">
	<input type="hidden" name="action" value="consultarPedidosPorMesYPromotores"/>

	<fieldset>
		<legend class="e6">Consulta Pedidos</legend>
		
		<table style="cellspacing:2,  width:100%, border:0">
				<col />
				
				<tr valign="middle">
					<td class="td3">
				
				      <table style="width:100% border:0">          
				        <tr class="td3" valign="middle">
				          <td height="33" align="right" valign="middle" width="320">
				           	Mes/Campaña : 
				           	<select name="idMes" id="idMes">
				           		<option value="0">Todos</option>
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
				           	&nbsp;&nbsp; &nbsp;&nbsp;
				           	
				         </td>
				         <td>
				         	Promotor:
				           	<select name="idUsuario" id="idUsuario">
				           		<option  value="0">
									<c:out value="Todos"/>
								</option>
				           		<c:forEach items="${listaPromotores}" var="promotor" varStatus="loop">
									<option  value="${promotor.idUsuario}">
										<c:out value="${promotor.nombre} ${promotor.apellido}"/>
									</option>
								</c:forEach>
				           	</select>
				           	&nbsp;&nbsp; &nbsp;&nbsp;
				         </td>   
				          <td valign="middle" align="left">                       
				           	<button type="submit" id="btnBuscarPedidosDirector" name="btnBuscarPedidosDirector">Consultar</button>
				          </td>
						</table>
					</tr>
				</table>
		
	</fieldset>
	</form>
</div>
<br><br>
<div id="cajaResultadosPedidosDirctor"></div>