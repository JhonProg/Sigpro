<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<title>SIGPRO-FUKL</title>
	
	<link type="text/css" href="${ctx}/css/sigpro.css" rel="stylesheet" />			
	<link type="text/css" href="${ctx}/css/jquery/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />
	
	<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-ui-1.8.21.custom.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.tablesorter.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.fixedtableheader.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.fixedtable.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.dateformat.js"></script>	
	
<script type="text/javascript">



	function campoTrim(obj){
		$(obj).val($.trim($(obj).val()));
	}
	
	function getHTMLLoaging14(txt){
		if(txt=='' || txt=='undefined'){
			return "<span class='img1'><img width='14' height='14' alt='Cargando...' src='${ctx}/imagen/loading_14x14.gif'/></span>";
		}else{
			return "<span class='img1'><img width='14' height='14' alt='Cargando...' src='${ctx}/imagen/loading_14x14.gif'/></span> "+txt;
		}		
	}
		
	function getHTMLLoaging16(txt){
		if(txt=='' || txt=='undefined'){
			return "<span class='img1'><img width='16' height='16' alt='Cargando...' src='${ctx}/imagen/loading_16x16.gif'/></span>";
		} else {
			return "<span class='img1'><img width='16' height='16' alt='Cargando...' src='${ctx}/imagen/loading_16x16.gif'/></span> "+txt;
		}
	}
	
	function getHTMLLoaging30(){
		return "<img width='30' height='30'  alt='Cargando...' src='${ctx}/imagen/loading_30x30.gif'/>";
	}
	
	function getMsn(msn){
		return "<div class='msgInfo2'>"+msn+"</div>";
	}
	
	$(document).ready(function() {
				
		$("#tabsOpciones").tabs({
			cache: true,
			spinner: ' '+getHTMLLoaging14(''),			
			ajaxOptions: {
				cache: false,
				error: function( xhr, status, index, anchor ) {
					$( anchor.hash ).html(
						"No se pudo cargar esta pestana. Informe al administrador del sistema." );
				}
			}
		}).addClass('ui-tabs-vertical ui-helper-clearfix');		

	});

	function recargarTabOpcion(index){
		$("#tabsOpciones").tabs("load", index );
	}
	


	
</script>
</head>

<body>
	
	<div id="testinicio" >
		<table border="0"  width="100%">
			<tr height="30">
				<td valign="middle" align="center" width="400"><img alt="" src="${ctx}/imagen/logo_konrad.png"></td>
				<td align="right" valign="top">
					<div  style="float: right; padding: 5px;">
						[<b><c:out value="${nombreCompleto}" /></b>]
						(<b><c:out value="${nombreUsuario}"/>)</b>, 
						<a href="${ctx}/page/login?action=cerrarSesion" id="salir_sistema">Salir</a>
						<br>
						<c:if test="${rol==4}">
							<br>
							<span class="enlace" onclick="verCarritoCompras();" title="Carrito">
								<img alt="Detalle" src="${ctx}/imagen/carrito.png">
							</span>
							<br/> 
							<b>Carrito de compras</b>
						</c:if>
					</div>
				</td>
			</tr>
		</table>
				
		<div style="padding: 10px;">
			<div id="tabsOpciones" class="tab tab-vert">
				<ul>
					<!-- Administrador -->
					<c:if test="${rol==1}">
						<li>
							<a href="${ctx}/page/usuario?action=cargarConsultarUsuario">&nbsp;Usuarios<span>&nbsp;</span></a>
						</li>
						
						<li>
							<a href="${ctx}/page/producto?action=cargarConsultarProducto">&nbsp;Productos<span>&nbsp;</span></a>
						</li>	
					</c:if>
					
					<!-- Gerente -->
					<c:if test="${rol==2}">
						<li>
							<a href="#">&nbsp;Reportes<span>&nbsp;</span></a>
						</li>
					</c:if>	
					
					<!-- Director -->
					<c:if test="${rol==3}">
						<li>
							<a href="${ctx}/page/pedido?action=cargarConsultarPedido">&nbsp;Pedidos<span>&nbsp;</span></a>
						</li>
						<li>
							<a href="${ctx}/page/producto?action=cargarConsultarProducto">&nbsp;Productos<span>&nbsp;</span></a>
						</li>
						<li>
							<a href="${ctx}/page/reporte?action=cargarVerReportesGraficos">&nbsp;Reportes<span>&nbsp;</span></a>
						</li>
						
						<li>
							<a href="${ctx}/page/meta?action=cargarInicioMeta">&nbsp;Metas<span>&nbsp;</span></a>
						</li>
						
						
					</c:if>	
						
					<!-- Promotor -->
					<c:if test="${rol==4}">
						<li>
							<a href="${ctx}/page/producto?action=cargarConsultarProducto">&nbsp;Productos<span>&nbsp;</span></a>
						</li>	
						
						<li>
							<a href="${ctx}/page/pedido?action=cargarCrearPedido">&nbsp;Pedidos<span>&nbsp;</span></a>
						</li>
						
						<li>
							<a href="${ctx}/page/pedido?action=misPedidos">&nbsp;Mis Pedidos<span>&nbsp;</span></a>
						</li>	
					</c:if>	
							
				</ul>
			</div>
		</div>
		
		<div style="padding: 50px 0px;" ></div>
	
	</div>
	
</body>
</html>
