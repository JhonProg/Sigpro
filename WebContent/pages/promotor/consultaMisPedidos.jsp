<%@ include file="/taglibs.jsp"%>

<script type="text/javascript">

	$(document).ready(function() {
		$("#btnBuscarPedido").button();
		
		/** Validar formulario */
		$("#fBuscarMisPedidos").validate({
			errorLabelContainer: "#msnBuscarMisPedidos",
			errorClass: "invalid",
			rules: {
				idMesMisPedidos:{
					required: true
				}
			},
			messages: {
				tipoBusquedaProducto: {
					required: "Selecione un mes o  tipo de búsqueda."
				}				
			},
			submitHandler: function(form) {
				consultarMisPedidos(form);
			}
		});
		
	});	
	
	function consultarMisPedidos(form){
		$("#cajaResultadosMisPedidos").html(getHTMLLoaging30()); 
       	$("#btnBuscarPedido").attr('disabled',true);				
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
                $("#cajaResultadosMisPedidos").html(data);
                $("#btnBuscarPedido").attr('disabled',false);	 
            }
        });
	}
	
</script>

<div align="left">
	<form name="fBuscarMisPedidos" id="fBuscarMisPedidos" action="${ctx}/page/pedido" method="post">
	<input type="hidden" name="action" value="consultarPedidosPorMesYUsuario"/>

	<fieldset>
		<legend class="e6">Consulta Mis Pedidos</legend>
		
		<table style="cellspacing:2,  width:100%, border:0">
				<col />
				
				<tr valign="middle">
					<td class="td3">
				
				      <table style="width:100% border:0">          
				        <tr class="td3" valign="middle">
				          <td height="33" align="right" valign="middle" width="300">
				           	Buscar : 
				           	<select name="idMesMisPedidos" id="idMesMisPedidos">
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
				          <td valign="middle" align="left">                       
				           	<button type="submit" id="btnBuscarPedido" name="btnBuscarPedido">Consultar</button>
				          </td>
						</table>
					</tr>
				</table>
		
	</fieldset>
	</form>
</div>
<div  align="center" id="msnBuscarMisPedidos" ></div>
<br><br>
<div id="cajaResultadosMisPedidos"></div>