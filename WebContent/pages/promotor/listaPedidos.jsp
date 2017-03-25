<%@ include file="/taglibs.jsp"%>
<script type="text/javascript">

	$(document).ready(function() {
		
	});
	
</script>
<div align="left" >

<fieldset>
<legend class="e6">Resultados</legend>

<c:choose>
		<c:when test="${fn:length(listaMisPedidos) eq 0}">
			<div class="msgInfo1" align="left">No se encontraron coincidencias.</div>
		</c:when>		
		<c:otherwise>
			<div>
			<div style="clear: both;"></div>
			<div id="divPedidos" >
				<table width="100%" border="0" id="pedidos" class="tExcel tRowSelect">
				  
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
						<th>ID pedido</th>
						<th>Estado pedido</th>
						<th>ID cliente</th>	
					  	<th>Numero Documento</th>
					  	<th>Nombre</th>
					  	<th>Apellido</th>	
					  	<th>Direccion</th>		  				  	
					  	<th>Telefono</th>
					  	<th>Estado</th>
					  	<th>Accion</th>
					  </tr>
				  </thead>
				  <tbody>
				  	
					  <c:forEach items="${listaMisPedidos}" var="miPedido" varStatus="loop">
						  <tr>
						  	<td><c:out value="${loop.index+1}"/></td>			  		
						  	<td><c:out value="${miPedido.pedido.idpedido}"/></td>
						 	<td><c:out value="EN VENTA"/></td>	
						  	<td><c:out value="${miPedido.cliente.idcliente}"/></td>	
						  	<td><c:out value="${miPedido.cliente.numeroDocumento}"/></td>
						    <td><c:out value="${miPedido.cliente.nombre} "/></td>
						    <td><c:out value="${miPedido.cliente.apellido}"/></td>
						    <td><c:out value="${miPedido.cliente.direccion}"/></td>
						    <td><c:out value="${miPedido.cliente.telefono} "/></td>
						    <td><c:out value="ACTIVO"/></td>
						    
						  	<td valign="middle" align="center">
							  		<span class="enlace" title="Agregar al carrito" onclick="alert('Funcionalidad en desarrollo');">
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
