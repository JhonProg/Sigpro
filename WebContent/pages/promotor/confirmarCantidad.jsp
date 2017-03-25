<%@ include file="/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
	
	$("#productosPedido").fixedtableheader();
	
	$("#dmConfirmandoConCarrito").dialog({
		width: 200,
		height: 150,   				
		modal: true,
		autoOpen: false,
		resizable: true
	});
	
	$("#botonConfirmarProducto").button();
	
});


	
	function agregarPoductoAlCarro(){
		var cantidadDeseada     = $("#cantidadSeleccionada").val();
		var idClienteConfirmar  = $("#idClienteConfirmar").val();
		var idProductoConfirmar = $("#idProductoConfirmar").val();
		
		$("#dmConfirmarCantidad").dialog("close");
		$("#dmConfirmandoConCarrito").dialog("open");
		$("#dmConfirmandoConCarrito").html(getHTMLLoaging30());
		$.ajax({
			cache: false,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
            type: 'POST',
            url: "${ctx}/page/pedido?action=agregarProductoAlCarrito&idCliente="+idClienteConfirmar+"&idProducto="+idProductoConfirmar+"&cantidad="+cantidadDeseada,
            dataType: "text",
            error: function(jqXHR, textStatus, errorThrown) {
            	var mensajeER = '<h3 style="color:red">'+jqXHR.statusText+'</h3>';
             	$("#dmConfirmandoConCarrito").html(mensajeER);
	        },
            success: function(data) {
               	/** Validar Entero */
               	if(validarEntero(data)){
               		var mensajeExito = '<h3 style="color:#58ACFA">Producto agregado exitosamente.</h3>';
               		var vacio = '<h3 align="left" style="color:#58ACFA">Sin resultados. Realice una nueva consulta.</h3>';
                 	$("#dmConfirmandoConCarrito").html(mensajeExito);
                 	$("#resultadoProductosPedido").html(vacio);
               	}else{
               		var mensajeError = '<h3 style="color:red">No fue posible agregar el producto al carrito.Intente de nuevo.</h3>';
                 	$("#dmConfirmandoConCarrito").html(mensajeError);
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
		<input type="hidden" id="idClienteConfirmar"  name="idClienteConfirmar"  value="${idCliente}"/>
		<input type="hidden" id="idProductoConfirmar" name="idProductoConfirmar" value="${idProducto}"/>
				
		<b>Cantidad : </b> 
		<select id="cantidadSeleccionada" name="cantidadSeleccionada">
			<c:forEach var="numero" begin="1" end="${cantidad}">
			   <option value="<c:out value="${numero}"></c:out>">${numero}</option>
			</c:forEach>
		</select> <br/> <br/>
		<button id="botonConfirmarProducto" name="botonConfirmarProducto" onclick="agregarPoductoAlCarro();">Aceptar</button>
</div>

<div id="dmConfirmandoConCarrito" title="Confirmacion"></div>					