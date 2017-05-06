package co.com.sigpro.util;

public class Calendar {

	public static String getNombreMesPorNumero(Integer mes){
		String mesStr = "";
		switch (mes) {
			case 1:
				mesStr= "Enero";
				break;
			case 2:
				mesStr= "Febrero";
				break;
			case 3:
				mesStr= "Marzo";
				break;
			case 4:
				mesStr= "Abril";
				break;
			case 5:
				mesStr= "Mayo";
				break;
			case 6:
				mesStr= "Junio";
				break;
			case 7:
				mesStr= "Julio";
				break;
			case 8:
				mesStr= "Agosto";
				break;
			case 9:
				mesStr= "Septiembre";
				break;
			case 10:
				mesStr= "Octubre";
				break;
			case 11:
				mesStr= "Noviembre";
				break;
			case 12:
				mesStr= "Diciembre";
				break;
		default:
			mesStr="No Definido";
			break;
		}
		return mesStr;
	}
	
}
