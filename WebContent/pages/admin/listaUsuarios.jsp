<%@ include file="/taglibs.jsp"%>
<script type="text/javascript">

	$(document).ready(function() {
		
		$("#usuarios").fixedtableheader();
	
		$("#dmEditarUsuario").dialog({   				
			width: 800,
			height: 400,   				
			modal: true,
			autoOpen: false,
			resizable: true
		});
		
	});

	function cargarEditarUsuario(idUsuario){
		$("#dmEditarUsuario").dialog("open");
		$("#dmEditarUsuario").html(getHTMLLoaging30());
		$.ajax({
			cache: false,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
            type: 'POST',
            url: "${ctx}/page/usuario?action=cargarEditarUsuario&idUsuario="+idUsuario,
            dataType: "text",
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.statusText);
                $("#dmEditarUsuario").dialog("close");
	        },
            success: function(data) {		                    	                    	
               	$("#dmEditarUsuario").html(data);     
            }
        });
	}
	
	
	
</script>
<div align="left" >

<fieldset>
<legend class="e6">Resultados</legend>

<c:choose>
		<c:when test="${fn:length(usuarios) eq 0}">
			<div class="msgInfo1" align="left">No se encontraron coincidencias.</div>
		</c:when>		
		<c:otherwise>
			<div>
			<div style="clear: both;"></div>
			<div id="divUsuarios" >
				<table width="100%" border="0" id="usuarios" class="tExcel tRowSelect">
				  
				  <col style="width: 20px;"/>
				  <col style="width: 40px;"/>
			 	  <col style="width: 30px;"/>
				  <col style="width: 30px;"/>
				  <col style="width: 30px;"/>
				  <col style="width: 30px;"/>
				  <col style="width: 30px;"/>
				  <col style="width: 30px;"/>
				  <col style="width: 30px;"/>
				 	
				  <thead>
					  <tr class="td3">
					    <th>#</th>			    
						<th><span title="Código de usuario">Código de usuario</span></th>
						<th><span title="Tipo de Documento">Tipo de documento</span></th>	
					  	<th><span title="Número de identificación">Identificación</span></th>
					  		
					  	<th>Usuario</th>		  				  	
					  	<th>Nombres</th>
					  	<th>Apellidos</th>
					  	<th>Estado</th>
					  	<th>Acciones</th>
					  </tr>
				  </thead>
				  <tbody>
				  	
				  <c:forEach items="${usuarios}" var="usuario" varStatus="loop">
					  <tr style="color: ${usuario.estado==2?'':'red'};">
					  	<td><c:out value="${loop.index+1}"/></td>			  		
					  	<td><c:out value="${usuario.idUsuario}"/></td>
					 	<td><c:out value="${usuario.tipoDocumento.tipo}"/> - <c:out value="${usuario.tipoDocumento.descripcion}"/></td>	
					  	<td><c:out value="${usuario.numeroDocumento}"/></td>	
					  	<td><c:out value="${usuario.usuario}"/> </td>
					    <td><c:out value="${usuario.nombre} "/></td>
					    <td><c:out value="${usuario.apellido}"/></td>
					    <td><c:out value="${usuario.estado==2?'Activo':'Inactivo'}"/></td>
					  	<td valign="middle" align="center">
					  		<span class="enlace" onclick="cargarEditarUsuario(${usuario.idUsuario});" title="Editar">
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

<div id="dmEditarUsuario" title="Editar Usuario"></div>
