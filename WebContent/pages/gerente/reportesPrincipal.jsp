<%@ include file="/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
	<!-- Para graficas -->	
	<script type="text/javascript" src="${ctx}/js/highcharts_propio.js"></script>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#btnComparar").button();
			
			graficarPedidosPorPromotor();
			graficarVentasPorPromotor();
			
			
			$("#btnComparar").click(function(){
				
				$("#contenedorResultadosComparativa").html(getHTMLLoaging30());
				
				var idMesUno = $('select[name="idMesUno"]').val();
				var idMesDos = $('select[name="idMesDos"]').val();
				
				if(idMesUno-idMesDos==0){
					alert("Ooops, no vale la pena comparar dos meses iguales.Cambialos.");
				}else{
					$.ajax({
							cache: false,
							contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
				            type: 'POST',	            
				            url: "${ctx}/page/reporte?action=compararVentasEntreDosMeses&idMesUno="+idMesUno+"&idMesDos="+idMesDos,
				            dataType: "text",
				            error: function(jqXHR, textStatus, errorThrown) {
				                alert("Ha ocurrido un error. Intenta de nuevo.");
					        },
				            success: function(data) {		                    	                    	
				               	graficarComparativaVentasEntreMeses(data); 
			            }
			        });
				}
			});
			
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
		
		
		function graficarComparativaVentasEntreMeses(datos){
			var _vectorReporteVentasPorMeses = datos.split('|');
			
			var itemsParaGrafica2 = [];
			
			var arrayLength = _vectorReporteVentasPorMeses.length;
			try{
				for (var i = 0; i < arrayLength; i++) {
				    var datos = _vectorReporteVentasPorMeses[i].split(',');
				    				    
				    var item = [];
				    item [0] = datos[0];
				    item [1] = parseInt(datos[1]);
						 
				    itemsParaGrafica2.push(item);
				    
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
		      text: 'Ventas Entre Meses'   
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
		      name: 'Ventas_Mes',
		      data: itemsParaGrafica2
		   }];    
		   		   
		   var json = {};   
		   json.chart = chart; 
		   json.title = title;     
		   json.tooltip = tooltip;  
		   json.series = series;
		   json.plotOptions = plotOptions;
		   
		   $('#contenedorResultadosComparativa').highcharts(json);
		}
		
	</script>
</head>
<body>
		
		<input type="hidden" id="datosReportePedidosPorPromotor" name="datosReportePedidosPorPromotor" value="${datosReportePedidosPorPromotor}"/>
		<input type="hidden" id="datosReporteVentasPorPromotor" name="datosReporteVentasPorPromotor" value="${datosReporteVentasPorPromotor}"/>
		
					   			<fieldset>
						<legend class="e6">Meta</legend>
							<table border="0" width="100%" class="caja">
								<col style="width: 15%"/>
								<col/>
								<tr>
									<th nowrap="nowrap" style="text-align: right;">Mes/campaña:</th>
									<td> 
							           	<select name="idMesUno" id="idMesUno">
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
									</td>
									
									<th nowrap="nowrap" style="text-align: right;">Mes/campaña:</th>
									<td> 
							           	<select name="idMesDos" id="idMesDos">
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
									</td>
									
									<th nowrap="nowrap" style="text-align: right;">Mes/campaña:</th>
									<td>
										<button type="button" id="btnComparar" name="btnComparar">Comparar</button>
									</td>
									
								</tr>
							</table>
						</fieldset>
		
		<table style="width:100%" border="1">
		
		<tr>
		  	<td>
<!-- 			   	<div id="contenedorComparativaVentasEntreMeses" style="width: 550px; height: 400px; margin: 0 auto"> -->
<!-- 			   		<div id="contenedorFiltroMeses"> -->
			   			
<!-- 			   		</div> -->
			   		<div id="contenedorResultadosComparativa" style="width: 550px; height: 400px; margin: 0 auto">
			   		</div>
<!-- 			   	</div> -->
			 </td>
			 <td>
			   	<div id="" style="width: 550px; height: 400px; margin: 0 auto"></div>
			 </td>
		 </tr>
		
		 <!-- Fila Uno : Reportes graficos -->
		 <tr>
			   <td>
			   		<div id="contenedorPedidosPorPromotor" style="width: 550px; height: 400px; margin: 0 auto"></div>
			   </td>
			   <td>
			   		<div id="contenedorVentasPorPromotor" style="width: 550px; height: 400px; margin: 0 auto"></div>
			   </td>
		  </tr>
		  <tr>
		  	<!-- Fila reservada para otros reportes -->
		  </tr>
		  
		</table>
</body>
</html>