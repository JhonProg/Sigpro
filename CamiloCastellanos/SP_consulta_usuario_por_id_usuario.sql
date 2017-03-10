CREATE OR REPLACE FUNCTION  SP_consulta_usuario_por_id_usuario (idus INTEGER )
RETURNS SETOF usuario AS

$BODY$

SELECT  idusuario, idtipodocumento,idrol, numerodocumento, usuario, 
        clave, nombre, apellido, estado
FROM usuario
WHERE usuario.idusuario= idus

$BODY$
LANGUAGE SQL;