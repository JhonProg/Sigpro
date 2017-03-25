<%@ include file="/taglibs.jsp"%>
<script type="text/javascript">

	$(document).ready(function() {
	
		$("#btnTerminarPedido").button();
		
		$("#dmMensajeCreacionPedido").dialog({   				
			width: 400,
			height: 200,   				
			modal: true,
			autoOpen: false,
			resizable: true
		});
		
	});
	
	function finalizarCreacionPedido(idPedido){
		
		$('input[name="idClienteEncontrado"]').val("");
				
		if(confirm("¿Esta seguro de que desea completar y finalizar este pedido?")){
		
			$("#dmMensajeCreacionPedido").dialog("open");
			$("#dmMensajeCreacionPedido").html(getHTMLLoaging16(' Finalizando pedido'));
			
			$.ajax({
				cache: false,
				contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
		        type: 'POST',
		        url: "${ctx}/page/pedido?action=pasarPedidoAVenta&idPedido="+idPedido,
		        dataType: "text",
		        error: function(jqXHR, textStatus, errorThrown) {
		        	var mensajeER = '<h3 style="color:red">'+jqXHR.statusText+'</h3>';
		         	$("#dmMensajeCreacionPedido").html(mensajeER);
		        },
		        success: function(data) {
		        	
		        	if(validarEntero(data)){
		        		
		        		$("#dmCarritoCompras").dialog("close");
		    			$("#divMostrarCliente").hide();
		    			$("#divCrearCliente").hide();
		    			$("#resultadoProductosPedido").html("");
		    			$("#divConsultarProductosParaPedido").hide();
		    			
		        		var mensajeOK = '<h3 style="color:blue">Pedido creado con exito.</h3>';
		        		$("#dmMensajeCreacionPedido").html(mensajeOK);
		        	}else{
		        		var mensajeER = '<h3 style="color:red">No se ha podido finalizar el pedido.Vuelva a intentarlo.</h3>';
			         	$("#dmMensajeCreacionPedido").html(mensajeER);
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
		<legend class="e6">Productos</legend>
		<c:choose>
			<c:when test="${fn:length(listaCarritoDetalle) eq 0}">
				<div class="msgInfo1" align="left">No se encontraron productos.</div>
			</c:when>		
			<c:otherwise>
				<div>
				<br>
					<h3>Total Pedido:$ ${total}</h3>
				<br>
				
				<div style="clear: both;"></div>
				<div id="divproductosPedidos" >
					<table width="100%" border="0" id="productosCarrito" class="tExcel tRowSelect">
					  
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
						  	<th>Acciones</th>
						  </tr>
					  </thead>
					  <tbody>
					  		<c:forEach items="${listaCarritoDetalle}" var="carrito" varStatus="loop">
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
							    
							  	<td valign="middle" align="center">
							  		<span class="enlace" title="Quitar al carrito" onclick="alert('Funcionalidad en desarrollo.');">
							  			<img alt="Quitar" src="${ctx}/imagen/ico-editar.gif">
							  		</span>
							  	</td>
							  	</tr>			 
						  	</c:forEach>	
					  </tbody>
					  </table>
					  </div>
					  <br>
					  
					  <div id="divBotonTerminarPedido" align="center">
							<br>
							<button type="submit" id="btnTerminarPedido" name="btnTerminarPedido" onclick="finalizarCreacionPedido(${idPedido});">Finalizar Pedido</button>
						</div>
					  
					  </div>
				  </c:otherwise>
		</c:choose>
				  
	</fieldset>

</div>
<div id="dmMensajeCreacionPedido" title="Pedido"></div>