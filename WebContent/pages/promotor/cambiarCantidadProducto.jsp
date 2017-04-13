<%@ include file="/taglibs.jsp"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		$("#botonConfirmarNuevaCantidadProducto").button();
		
		$("#dmCambioCantidad").dialog({
			width: 200,
			height: 150,   				
			modal: true,
			autoOpen: false,
			resizable: true
		});
		
	});

	
	function actualizarCantidadDeProducto(){
		
		var idPedido        = $("#idPedidoCambioCantidad").val();
		var idProducto      = $("#idProductoCambioCantidad").val();
		var cantidadActual  = $("#cantidadActual").val();
		var cantidadDeseada = $("#nuevaCantidadSeleccionada").val();
		
		$("#dmModificarCantidadProducto").dialog("close");
		
		$("#dmCambioCantidad").dialog("open");
		$("#dmCambioCantidad").html(getHTMLLoaging30());
		
		$.ajax({
			cache: false,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
            type: 'POST',
            url: "${ctx}/page/pedido?action=actualizarCantidadDeProductoEnPedido&idPedido="+idPedido+"&idProducto="+idProducto+"&cantidadActual="+cantidadActual+"&cantidadDeseada="+cantidadDeseada,
            dataType: "text",
            error: function(jqXHR, textStatus, errorThrown) {
            	var mensajeER = '<h3 style="color:red">'+jqXHR.statusText+'</h3>';
             	$("#dmCambioCantidad").html(mensajeER);
	        },
            success: function(data) {
               	if(validarEntero(data)){
               		var mensajeExito = '<h3 style="color:#58ACFA">Se ha modificado la cantidad exitosamente.</h3>';
                 	$("#dmCambioCantidad").html(mensajeExito);
                 	verCarritoCompras();
               	}else{
               		var mensajeError = '<h3 style="color:red">No fue posible cambiar la cantidad.Intente de nuevo.</h3>';
                 	$("#dmCambioCantidad").html(mensajeError);
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

<div>
		<input type="hidden" id="idPedidoCambioCantidad" name="idPedidoCambioCantidad" value="${idPedido}"/>
		<input type="hidden" id="idProductoCambioCantidad" name="idProductoCambioCantidad" value="${idProducto}"/>
		<input type="hidden" id="cantidadActual" name="cantidadActual" value="${cantidadActual}"/>
		
		<b>Cantidad Actual: </b> ${cantidadActual}<br/>		
		<b>Nueva Cantidad : </b><select id="nuevaCantidadSeleccionada" name="nuevaCantidadSeleccionada">
									<c:forEach var="numero" begin="1" end="${cantidadDisponible}">
									   <option value="<c:out value="${numero}"></c:out>">${numero}</option>
									</c:forEach>
								</select>
		<br/> 
		<br/>
		<button id="botonConfirmarNuevaCantidadProducto" name="botonConfirmarNuevaCantidadProducto" onclick="actualizarCantidadDeProducto();">Aceptar</button>
</div>


<div id="dmCambioCantidad" title="Cambio Cantidad"></div>	

	
