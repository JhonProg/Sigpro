<%@ include file="/taglibs.jsp"%>
<script type="text/javascript">

	$(document).ready(function() {
		
		$("#productos").fixedtableheader();
		
		$("#dmEditarProducto").dialog({   				
			width: 800,
			height: 400,   				
			modal: true,
			autoOpen: false,
			resizable: true
		});
		
		
	});

	function cargarEditarProducto(idProducto){
		$("#dmEditarProducto").dialog("open");
		$("#dmEditarProducto").html(getHTMLLoaging30());
		$.ajax({
			cache: false,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
            type: 'POST',
            url: "${ctx}/page/producto?action=cargarEditarProducto&idProducto="+idProducto,
            dataType: "text",
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.statusText);
                $("#dmEditarProducto").dialog("close");
	        },
            success: function(data) {		                    	                    	
               	$("#dmEditarProducto").html(data);     
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
				<table width="100%" border="0" id="productos" class="tExcel tRowSelect">
				  
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
						<th>ID producto</span></th>
						<th>SKU</span></th>	
					  	<th>Codigo Barras</span></th>
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
					  		<span class="enlace" onclick="cargarEditarProducto(${producto.idProducto});" title="Editar">
					  		<img alt="Editar" src="${ctx}/imagen/ico-editar.gif">
					  		</span>
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

<div id="dmEditarProducto" title="Editar Producto"></div>
