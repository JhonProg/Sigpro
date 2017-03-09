CREATE OR REPLACE FUNCTION  SP_consulta_usuarios( )
RETURNS SETOF record AS

$BODY$

select
u.idusuario 
,u.idtipodocumento
,u.idrol
,u.numerodocumento 
,u.usuario
,u.clave
,u.nombre
,u.apellido 
,u.estado
,t.idtipodocumento
,t.tipo
,t.descripcion
,t.estado
,r.idrol
,r.rol
,r.descripcion
,r.estado

from usuario u,tipo_documento t,rol r 
   where
    u.idtipodocumento=t.idtipodocumento and
    u.idrol=r.idrol;
	 
$BODY$
LANGUAGE SQL;
