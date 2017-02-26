<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<title>SIGPRO-FUKL</title>
	
	<link type="text/css" href="${ctx}/css/sc.css" rel="stylesheet" />			
	<link type="text/css" href="${ctx}/css/jquery/jquery-ui-1.8.21.custom.css" rel="Stylesheet" />
	<link type="text/css" href="${ctx}/css/jquery/jquery.multiselect.css" rel="Stylesheet" />
	
	<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-ui-1.8.21.custom.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.tablesorter.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.fixedtableheader.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.fixedtable.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.hotkeys-0.7.9.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.dateformat.js"></script>	

	<script type="text/javascript" src="${ctx}/js/graft/highcharts-custom.js"></script>
	<script type="text/javascript" src="${ctx}/js/cvi_text_lib.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.flipv.js"></script>
	<script type="text/javascript" src="${ctx}/js/ui.dropdownchecklist-1.4-min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.multiselect.filter.js"></script>
	
	<link href='${ctx}/js/qtip/jquery.qtip.css' rel='stylesheet' />
	<script src='${ctx}/js/qtip/jquery.qtip.js'></script>
	
	<link href='${ctx}/fullcalendar-2.2.6/fullcalendar.css' rel='stylesheet' />
	<script src='${ctx}/fullcalendar-2.2.6/lib/moment.min.js'></script>
	<script src='${ctx}/fullcalendar-2.2.6/fullcalendar.min.js'></script>
	<script src='${ctx}/fullcalendar-2.2.6/lang-all.js'></script>
	
	<link type="text/css" href="${ctx}/css/jquery/jquery.multiselect.css" rel="Stylesheet" />
	<script type="text/javascript" src="${ctx}/js/ui.dropdownchecklist-1.4-min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.multiselect.filter.js"></script>
	
	
	<script type="text/javascript" src="${ctx}/js/croppic.js"></script>
	<link rel="stylesheet" href="${ctx}/css/croppic.css">
	
	
	<script type="text/javascript" src="${ctx}/restive.js-master/restive.min.js"></script>
	
	<script type="text/javascript" src="${ctx}/Animated-Circle/src/jquery-asPieProgress.js"></script>
	<link rel="stylesheet" href="${ctx}/Animated-Circle/css/progress.css">
	<script type="text/javascript" src="${ctx}/Animated-Circle/src/jquery-asPieProgress.js"></script>
	<link rel="stylesheet" href="${ctx}/Animated-Circle/css/progress.css">

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
						"No se pudo cargar esta pestaña. Informe al administrador del sistema." );
				}
			}
		}).addClass('ui-tabs-vertical ui-helper-clearfix');		
		
	    $('body').restive({
	    	breakpoints: ['240', '320', '480', '640-p', '640-l', '960'],
	    	classes: ['css-240', 'css-320', 'css-480', 'css-640-p', 'css-640-l', 'css-960']
	    });
	    
	    $('#testinicio').restive({
		    breakpoints: ['240', '320', '480', '640-p', '640-l', '960'],
		    classes: ['css-240', 'css-320', 'css-480', 'css-640-p', 'css-640-l', 'css-960']
		    });

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
						<br>
					</div>
				</td>
			</tr>
				
			<tr>
				<td>	
				</td>
			</tr>
		</table>
		
		
		<div style="padding: 10px;">
			<div id="tabsOpciones" class="tab tab-vert">
				<ul>
					<!-- Administrador -->
					<c:if test="${rol==1}">
						<li>
							<a href="#">&nbsp;Usuarios<span>&nbsp;</span></a>
						</li>
						
						<li>
							<a href="#">&nbsp;Productos<span>&nbsp;</span></a>
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
							<a href="#">&nbsp;Productos<span>&nbsp;</span></a>
						</li>
					</c:if>	
						
					<!-- Promotor -->
					<c:if test="${rol==4}">
						<li>
							<a href="#">&nbsp;Productos<span>&nbsp;</span></a>
						</li>
						
						<li>
							<a href="#">&nbsp;Pedidos<span>&nbsp;</span></a>
						</li>	
					</c:if>	
							
				</ul>
			</div>
		</div>
		
		<div style="padding: 50px 0px;" ></div>
	
	</div>
</body>
</html>
