<%@ include file="/taglibs.jsp"%>
<script type="text/javascript">

	$(document).ready(function() {
		
		$("#productosPedido").fixedtableheader();
		
		$("#dmConfirmarCantidad").dialog({
			width: 200,
			height: 150,   				
			modal: true,
			autoOpen: false,
			resizable: true
		});
		
	});

	function agregarAlCarrito(idCliente,idProducto,cantidad){
		
		$("#dmConfirmarCantidad").dialog("open");
		$("#dmConfirmarCantidad").html(getHTMLLoaging30());
		$.ajax({
			cache: false,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
            type: 'POST',
            url: "${ctx}/page/pedido?action=confirmarCantidad&idCliente="+idCliente+"&idProducto="+idProducto+"&cantidad="+cantidad,
            dataType: "text",
            error: function(jqXHR, textStatus, errorThrown) {
            	var mensajeER = '<h3 style="color:red">'+jqXHR.statusText+'</h3>';
             	$("#dmConfirmarCantidad").html(mensajeER);
	        },
            success: function(data) {		                    	                    	
               	$("#dmConfirmarCantidad").html(data);     
            }
        });
		
	}
	
</script>
<div align="left" >

<fieldset>
<legend class="e6">Resultados</legend>

<c:choose>
		<c:when test="${fn:length(productos) eq 0}">
			<div class="msgInfo1" align="left">No se encontraron coincidencias.</div>
		</c:when>		
		<c:otherwise>
			<div>
			<div style="clear: both;"></div>
			<div id="divproductos" >
				<table width="100%" border="0" id="productosPedido" class="tExcel tRowSelect">
				  
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
				  <col style="width: 30px;"/>
				  <col style="width: 30px;"/>
				 	
				  <thead>
					  <tr class="td3">
					    <th>#</th>			    
						<th>ID producto</th>
						<th>SKU</th>	
					  	<th>Codigo Barras</th>
					  	<th>Estado</th>
					  	<th>Categoria</th>	
					  	<th>Nombre producto</th>		  				  	
					  	<th>Descripcion</th>
					  	<th>Precio Unitario</th>
					  	<th>Precio Descuento</th>
					  	<th>Cantidad</th>
					  	<th>Acciones</th>
					  </tr>
				  </thead>
				  <tbody>
				  	
				  <c:forEach items="${productos}" var="producto" varStatus="loop">
					  <tr style="color: ${producto.cantidad==0?'red':''};">
					  	<td><c:out value="${loop.index+1}"/></td>			  		
					  	<td><c:out value="${producto.idProducto}"/></td>
					 	<td><c:out value="${producto.sku}"/></td>	
					  	<td><c:out value="${producto.codigoBarras}"/></td>	
					  	<td><c:out value="${producto.productoEstado.estado}"/></td>
					    <td><c:out value="${producto.productoCategoria.categoria} "/></td>
					    <td><c:out value="${producto.nombre}"/></td>
					    <td><c:out value="${producto.descripcion}"/></td>
					    <td><c:out value="${producto.precioUnitario} "/></td>
					    <td><c:out value="${producto.precioDescuento}"/></td>
					    <td><c:out value="${producto.cantidad}"/></td>
					    
					  	<td valign="middle" align="center">
					  		<c:if test="${producto.cantidad>0}">
						  		<span class="enlace" title="Agregar al carrito" onclick="agregarAlCarrito(${idCliente},${producto.idProducto},${producto.cantidad});">
						  			<img alt="Editar" src="${ctx}/imagen/carrito.png">
						  		</span>
					  		</c:if>
					  	</td>
					  						  	
					  </tr>			 
				  </c:forEach>			  
				  </tbody>
				</table>
			</div>
			</div>
	</c:otherwise>
</c:choose>
</fieldset>
</div>
<div id="dmConfirmarCantidad" title="Confirme cantidad"></div>