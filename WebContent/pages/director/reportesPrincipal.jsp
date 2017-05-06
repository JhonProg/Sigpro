<%@ include file="/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
	<!-- Para graficas -->	
	<script type="text/javascript" src="${ctx}/js/highcharts_propio.js"></script>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			graficarPedidosPorPromotor();
			graficarVentasPorPromotor();
		});
		
		/**
		* Pedidos por promotor.
		*/
		function graficarPedidosPorPromotor(){
						
			var _vectorReportePedidosPorPromotor = $("#datosReportePedidosPorPromotor").val().split('|');
			
			var datos_entrada2 = [];
			
			var arrayLength = _vectorReportePedidosPorPromotor.length;
			try{
				for (var i = 0; i < arrayLength; i++) {
				    var datos    = _vectorReportePedidosPorPromotor[i].split(',');
				    
				    var item = [];
				    item [0] = datos[0];
				    item [1] = parseInt(datos[1]);
						 
				    datos_entrada2.push(item);
				    
				}
			}catch(error){
				console.log("Error "+error);
			}			
			
			var chart = {
		       plotBackgroundColor: null,
		       plotBorderWidth: null,
		       plotShadow: false
		   };
			
		   var title = {
		      text: 'Cantidad De Pedidos Por Promotor'   
		   };     
		   
		   var tooltip = {
		      pointFormat: '{series.name}: <b>{point.y:.1f}</b>'
		   };
		   
		   var plotOptions = {
		      pie: {
		         allowPointSelect: true,
		         cursor: 'pointer',
		         dataLabels: {
		            enabled: true,
		            format: '<b>{point.name}</b>: {point.y:.1f}',
		            style: {
		               color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		            }
		         }
		      }
		   };
		  
		   var series= [{
		      type: 'bar',
		      name: 'Cantidad Pedidos',
		      data: datos_entrada2
		   }];
		   
		   var json = {};   
		   json.chart = chart; 
		   json.title = title;     
		   json.tooltip = tooltip;  
		   json.series = series;
		   json.plotOptions = plotOptions;
		   
		   $('#contenedorPedidosPorPromotor').highcharts(json);
		}
		
		
		function graficarVentasPorPromotor(){
									
			var _vectorReporteVentasPorPromotor = $("#datosReporteVentasPorPromotor").val().split('|');
			
			var itemsParaGrafica = [];
			
			var arrayLength = _vectorReporteVentasPorPromotor.length;
			try{
				for (var i = 0; i < arrayLength; i++) {
				    var datos = _vectorReporteVentasPorPromotor[i].split(',');
				    				    
				    var item = [];
				    item [0] = datos[0];
				    item [1] = parseInt(datos[1]);
						 
				    itemsParaGrafica.push(item);
				    
				}
			}catch(error){
				console.log("Error "+error);
			}
			
			var chart = {
		       plotBackgroundColor: null,
		       plotBorderWidth: null,
		       plotShadow: false
		   };
		
		   var title = {
		      text: 'Ventas Por Promotor'   
		   };     
		   
		   var tooltip = {
		      pointFormat: '{series.name}: <b>{point.percentage:.1f}</b>'
		   };
		   
		   var plotOptions = {
		      pie: {
		         allowPointSelect: true,
		         cursor: 'pointer',
		         dataLabels: {
		            enabled: true,
		            //format: '<b>{point.name}</b>: {point.percentage:.1f}',
		            format: '<b>{point.name}</b>: {point.y:.1f}',
		            style: {
		               color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		            }
		         }
		      }
		   };
		   		  		   
		   var series= [{
		      type: 'pie',
		      name: 'Ventas',
		      data: itemsParaGrafica
		   }];    
		   		   
		   var json = {};   
		   json.chart = chart; 
		   json.title = title;     
		   json.tooltip = tooltip;  
		   json.series = series;
		   json.plotOptions = plotOptions;
		   
		   $('#contenedorVentasPorPromotor').highcharts(json);		   
		}
	</script>
</head>
<body>
		
		<input type="hidden" id="datosReportePedidosPorPromotor" name="datosReportePedidosPorPromotor" value="${datosReportePedidosPorPromotor}"/>
		<input type="hidden" id="datosReporteVentasPorPromotor" name="datosReporteVentasPorPromotor" value="${datosReporteVentasPorPromotor}"/>
		
		<table style="width:100%" border="1"> 
		 <!-- Fila Uno : Reportes graficos -->
		 <tr>
			   <td>
			   		<div id="contenedorPedidosPorPromotor" style="width: 550px; height: 400px; margin: 0 auto"></div>
<!-- 			   		<div id="contenedorPedidosPorPromotorTotal"></div>  -->
			   </td>
			   <td>
			   		<div id="contenedorVentasPorPromotor" style="width: 550px; height: 400px; margin: 0 auto"></div>
			   </td>
		  </tr>
		  <tr>
			  	<td>
				   	<div id="contenedorMetasPromotor" style="width: 550px; height: 400px; margin: 0 auto">
				   		<!-- Tabla uno: promotor, mes, valor -->
				   		<br>
				   		<fieldset>
						<legend class="e6">Ventas por Mes</legend>
						
						<c:choose>
								<c:when test="${fn:length(ventasPromotorMes) eq 0}">
									<div class="msgInfo1" align="left">No se encontraron coincidencias.</div>
								</c:when>		
								<c:otherwise>
									<div>
									<div style="clear: both;"></div>
									<div id="divPedidos" >
										<table width="100%" border="0" id="pedidos" class="tExcel tRowSelect">
										  
										  	<col style="width: 20px;"/>
										  	<col style="width: 20px;"/>
									 	  	<col style="width: 20px;"/>
									 	  	<col style="width: 20px;"/>
									 	  	
									 	  	<thead>
											  <tr class="td3">
											    <th>Promotor Nombre</th>
											    <th>Promotor Apellido</th>			    
												<th>Mes</th>
												<th>Meta</th>
											  </tr>
										  </thead>
									 	  
									 	 <tbody>
										  	<c:forEach items="${promotorMesMeta}" var="venta" varStatus="loop">
											  <tr>
											  	<td><c:out value="${venta.nombrePromotor}"/></td>
											  	<td><c:out value="${venta.apellidoPromotor}"/></td>			  		
											 	<td><c:out value="${venta.mesNombre}"/></td>
											 	<td><c:out value="${venta.meta}"/></td>
											 	</tr>
											 </c:forEach>
											</tbody>
									 	  
									 	  </table>
									 	  </div>
						 </c:otherwise>
									 	  
					</c:choose>		
				   	</div>
				</td>
				<td>
				   	<div id="contenedorMetasPromotor" style="width: 550px; height: 400px; margin: 0 auto">
				   		<!-- Tabla dos: promotor, mes, meta -->
				   		<br>
				   		<fieldset>
						<legend class="e6">Ventas por Mes</legend>
						
						<c:choose>
								<c:when test="${fn:length(ventasPromotorMes) eq 0}">
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
									 	  	
									 	  	<thead>
											  <tr class="td3">
											    <th>Promotor</th>			    
												<th>Venta</th>
												<th>Mes</th>
											  </tr>
										  </thead>
									 	  
									 	 <tbody>
										  	<c:forEach items="${ventasPromotorMes}" var="venta" varStatus="loop">
											  <tr>
											  	<td><c:out value="${venta.nombrePromotor}"/></td>			  		
											  	<td><c:out value="${venta.totalVentas}"/></td>
											 	<td><c:out value="${venta.mesNombre}"/></td>
											 	</tr>
											 </c:forEach>
											</tbody>
									 	  
									 	  </table>
									 	  </div>
						 </c:otherwise>
									 	  
					</c:choose>		 	  
				   		
				   	</div>
				</td>
		  </tr>
		  
		</table>
</body>
</html>