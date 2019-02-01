package view;

import java.util.Scanner;

import controller.Controller;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;

public class MovingViolationsManagerView 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		Controller controller = new Controller();
		
		while(!fin)
		{
			printMenu();
			
			int option = sc.nextInt();
			
			switch(option)
			{
				case 1:
					controller.loadMovingViolations();
					break;
					
				case 2:
					
					IQueue<VODaylyStatistic> dailyStatistics = controller.getDailyStatistics();
					System.out.println("Se encontraron "+ dailyStatistics.size() + " elementos");
					for (VODaylyStatistic dayStatistic : dailyStatistics) 
					{
						//TODO
						System.out.println("2018-01-01 - accidentes:	100,	infracciones:	200,	multas totales:	$10,000  " );;
					}
					break;
					
				case 3:
					System.out.println("Ingrese el número de infracciones a buscar");
					int n = Integer.parseInt(sc.next());

					
					IStack<VOMovingViolations> violations = controller.nLastAccidents(n);
					System.out.println("Se encontraron "+ violations.size() + " elementos");
					for (VOMovingViolations violation : violations) 
					{
						System.out.println(violation.objectId() + " " 
											+ violation.getTicketIssueDate() + " " 
											+ violation.getLocation()+ " " 
											+ violation.getViolationDescription());
					}
					break;
											
				case 4:	
					fin=true;
					sc.close();
					break;
			}
		}
	}

	private static void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Taller 3----------------------");
		System.out.println("1. Cree una nueva coleccion de infracciones en movimiento");
		System.out.println("2. Dar estadisticas diarias de las infracciones");
		System.out.println("3. Dar ultimos n infracciones que terminaron en accidente");
		System.out.println("4. Salir");
		System.out.println("Digite el n�mero de opci�n para ejecutar la tarea, luego presione enter: (Ej., 1):");
		
	}
}
