CREATE OR REPLACE FUNCTION SP_consulta_tipos_documento()
RETURNS SETOF tipo_documento AS
$BODY$
 SELECT  idtipodocumento,tipo,descripcion, estado
 FROM    tipo_documento
 WHERE   estado =2;

$BODY$
LANGUAGE SQL;