package model.vo;

import java.util.ArrayList;

public class VODaylyStatistic {
	
	String fecha;
	
	int NumeroAccidentes;
	
	ArrayList <VOMovingViolations> violaciones;
	
	public VODaylyStatistic(String pFecha) {
		fecha = pFecha;
		violaciones = new ArrayList <VOMovingViolations>();
	}
	
	public String darFecha() {
		// TODO Auto-generated method stub
		return fecha;
	}

	public void agregarReporte(VOMovingViolations violacion) {
		// TODO Auto-generated method stub
		violaciones.add(violacion);
	}
	
	public int darNumeroAccidentes(){
		int accidentes = 0;
		for (int i = 0 ; i < violaciones.size() ; i ++ ){
			if (violaciones.get(i).getAccidentIndicator().equals("YES")){
				accidentes++;
			}
		}
		
		return accidentes;
	}
	
	public int darSumaTotal(){
		int accidentes = 0;
		for (int i = 0 ; i < violaciones.size() ; i ++ ){
			accidentes += violaciones.get(i).getTotalPaid();
		}
		
		return accidentes;
	}
	
	public int darNumeroInfracciones(){
		return violaciones.size();
	}
}
