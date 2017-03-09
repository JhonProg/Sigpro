CREATE OR REPLACE FUNCTION  
SP_consulta_usuario_por_tipo_numero_documento (tipod varchar, numero varchar)
RETURNS SETOF RECORD AS
$BODY$
SELECT 
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
FROM usuario u,tipo_documento t,rol r
WHERE t.tipo=tipod and u.numerodocumento=numero;

$BODY$
LANGUAGE SQL;
