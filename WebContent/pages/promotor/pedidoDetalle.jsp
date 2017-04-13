<%@ include file="/taglibs.jsp"%>
<script type="text/javascript">

	$(document).ready(function() {
		$("#btnActualizarEstadoPedido").button();
		
		$("#dmMensajeDetallePedido").dialog({   				
			width: 400,
			height: 200,   				
			modal: true,
			autoOpen: false,
			resizable: true
		});
		
	});
	
	function actualizarEstadoPedido(idPedido){
		var idNuevoEstadoPedido = $("#idNuevoEstadoPedido").val();
		var nombreEstadoNuevo   = "";
		
		if(idNuevoEstadoPedido=="4"){
			nombreEstadoNuevo = "[FINALIZADO]";
		}else if(idNuevoEstadoPedido=="5"){
			nombreEstadoNuevo = "[CANCELADO]";
		}
		
		if(confirm('¿ Está seguro de desea cambiar el estado del pedido a '+nombreEstadoNuevo+' ?')){
			$("#dmMensajeDetallePedido").dialog("open");
			$("#dmMensajeDetallePedido").html(getHTMLLoaging16(' Actualizando estado...'));
			
			$.ajax({
				cache: false,
				contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
		        type: 'POST',
		        url: "${ctx}/page/pedido?action=cambiarEstadoPedido&idPedido="+idPedido+"&idNuevoEstadoPedido="+idNuevoEstadoPedido,
		        dataType: "text",
		        error: function(jqXHR, textStatus, errorThrown) {
		        	var mensajeER = '<h3 style="color:red">'+jqXHR.statusText+'</h3>';
		         	$("#dmMensajeDetallePedido").html(mensajeER);
		         	$("#dmDetallePedido").dialog("close");
		        },
		        success: function(data) {
		        	
		        	if(validarEntero(data)){
		        		var mensajeOK = '<h3 style="color:blue">Pedido actualizado con exito...</h3>';
		        		$("#dmMensajeDetallePedido").html(mensajeOK);
			        	$("#dmDetallePedido").dialog("close");
		        	}else{
		        		var mensajeER = '<h3 style="color:red">El pedido no se actualizó con exito. Intente más tarde.</h3>';
		        		$("#dmMensajeDetallePedido").html(mensajeER);
			        	$("#dmDetallePedido").dialog("close");
		        	}
		        }
		    });
			
		}
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

<div align="left" >
	<fieldset>
		<legend class="e6">Detalle pedido</legend>
		
		<c:choose>
			<c:when test="${fn:length(listaPedidoDetalle) eq 0}">
				<div class="msgInfo1" align="left">No se encontraron productos.</div>
			</c:when>		
			<c:otherwise>
				<br>
					<h3>Total Pedido:$ ${total}</h3>
				<br>
				
				<div style="clear: both;"></div>
				<div id="divproductosPedidos" >
					<table style="width:100%; border:0" id="productosCarrito" class="tExcel tRowSelect">
					  
					  <col style="width: 20px;"/>
					  <col style="width: 20px;"/>
				 	  <col style="width: 30px;"/>
					  <col style="width: 30px;"/>
					  <col style="width: 30px;"/>
					  <col style="width: 30px;"/>
					  <col style="width: 60px;"/>
					  <col style="width: 60px;"/>
					  <col style="width: 30px;"/>
					  <col style="width: 30px;"/>
					 	
					  <thead>
						  <tr class="td3">
						    <th>#</th>			    
							<th>ID producto</th>
							<th>SKU</th>	
						  	<th>Codigo Barras</th>
						  	<th>Nombre producto</th>		  				  	
						  	<th>Precio Unitario</th>
						  	<th>Precio Descuento</th>
						  	<th>Cantidad</th>
						  	<th>Subtotal</th>
						  </tr>
					  </thead>
					  <tbody>
					  		<c:forEach items="${listaPedidoDetalle}" var="carrito" varStatus="loop">
							  <tr>
							  	<td><c:out value="${loop.index+1}"/></td>			  		
							  	<td><c:out value="${carrito.producto.idProducto}"/></td>
							 	<td><c:out value="${carrito.producto.sku}"/></td>	
							  	<td><c:out value="${carrito.producto.codigoBarras}"/></td>	
							    <td><c:out value="${carrito.producto.nombre}"/></td>
							    <td><c:out value="${carrito.producto.precioUnitario} "/></td>
							    <td><c:out value="${carrito.producto.precioDescuento}"/></td>
							    <td><c:out value="${carrito.producto.cantidad}"/></td>
							    <td><c:out value="${carrito.producto.subTotal}"/></td>
							  </tr>			 
						  	</c:forEach>	
					  </tbody>
					  </table>
					  <br>
					  
					  <!-- Cambiar estado del pedido -->
					  <div id="divEstadoPedido" align="center">
					  	<b>Cambiar Estado Pedido : </b> 
						<select id="idNuevoEstadoPedido" name="idNuevoEstadoPedido">
							<option value="4">Finalizado</option>
							<option value="5">Cancelado</option>
						</select>
					  </div>
					  
					  <div id="divBotonActualizarEstadoPedido" align="center">
							<br>
							<button type="submit" id="btnActualizarEstadoPedido" name="btnActualizarEstadoPedido" onclick="actualizarEstadoPedido(${idPedido});">Cambiar Estado</button>
					  </div>
				</div>
					  					
			</c:otherwise>
		</c:choose>
		
	</fieldset>
</div>
<div id="dmMensajeDetallePedido" title="Mensaje"></div>