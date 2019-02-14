package model.vo;

import model.data_structures.Queue;

public class VODaylyStatistic {
	
	String fecha;
	
	int NumeroAccidentes;
	
	Queue <VOMovingViolations> violaciones;
	
	public VODaylyStatistic(String pFecha) {
		fecha = pFecha;
		violaciones = new Queue<VOMovingViolations>();
	}
	
	public String darFecha() {
		// TODO Auto-generated method stub
		return fecha;
	}

	public void agregarReporte(VOMovingViolations violacion) {
		// TODO Auto-generated method stub
		violaciones.enqueue(violacion);
	}
	
	public int darNumeroAccidentes(){
		int accidentes = 0;
		for (VOMovingViolations violation : violaciones) {
			if (violation.getAccidentIndicator().equals("Yes")){
				accidentes++;
			}
		}
		
		return accidentes;
	}
	
	public int darSumaTotal(){
		int accidentes = 0;
		for (VOMovingViolations violation : violaciones) {
			accidentes += violation.getTotalPaid();
		}
		
		return accidentes;
	}
	
	public int darNumeroInfracciones(){
		return violaciones.darLongitud();
	}
}
