package controller;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;

	/**
	 * Cola donde se van a cargar los datos de los archivos
	 */
	private Queue<VOMovingViolations> movingViolationsQueue;

	/**
	 * Pila donde se van a cargar los datos de los archivos
	 */
	private Stack<VOMovingViolations> movingViolationsStack;


	public Controller() {
		view = new MovingViolationsManagerView();

		//TODO, inicializar la pila y la cola
		movingViolationsQueue = null;
		movingViolationsStack = null;
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin = false;

		while(!fin)
		{
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 1:
				this.loadMovingViolations();
				break;

			case 2:
				IQueue<VODaylyStatistic> dailyStatistics = this.getDailyStatistics();
				view.printDailyStatistics(dailyStatistics);
				break;

			case 3:
				view.printMensage("Ingrese el numero de infracciones a buscar");
				int n = sc.nextInt();

				IStack<VOMovingViolations> violations = this.nLastAccidents(n);
				view.printMovingViolations(violations);
				break;

			case 4:	
				fin=true;
				sc.close();
				break;
			}
		}
	}



	public void loadMovingViolations() {
		try{
			FileReader n1 = new FileReader("./data/Moving_Violations_Issued_In_February_2018_ordered.csv");
			CSVReader n2 = new CSVReaderBuilder(n1).withSkipLines(1).build();

			List <String[]> info = n2.readAll();

			movingViolationsStack = new Stack<VOMovingViolations>();

			for( int i = 1 ; i < info.size() ; i++ ){
				movingViolationsStack.push(new VOMovingViolations(Integer.parseInt(info.get(i)[0]), info.get(i)[2], info.get(i)[13],Integer.parseInt(info.get(i)[9]), info.get(i)[12], info.get(i)[15]));
			}

			FileReader n3= new FileReader("./data/Moving_Violations_Issued_In_January_2018_ordered.csv");
			CSVReader n4 = new CSVReaderBuilder(n3).build();
			List <String[]> info2 = n4.readAll();
			movingViolationsQueue = new Queue<VOMovingViolations>();

			for ( int i = 1 ; i<info.size() ; i++ ){
				movingViolationsQueue.enqueue(new VOMovingViolations(Integer.parseInt(info2.get(i)[0]), info2.get(i)[2], info2.get(i)[13],Integer.parseInt(info2.get(i)[9]), info2.get(i)[12], info2.get(i)[15]));
			}


			n1.close();
			n2.close();
			n3.close();
			n4.close();
		}
		catch(Exception e){
			view.printMensage(e.getMessage());
		}
	}

	public IQueue <VODaylyStatistic> getDailyStatistics () {
		ArrayList<VODaylyStatistic> arreglosEstadisticas = new ArrayList<>();
		IQueue<VODaylyStatistic> dailyStatistics = new Queue<VODaylyStatistic>();
		
		String fecha;
		String fechaRecortada;

		for (VOMovingViolations violation : movingViolationsQueue){
			fecha = violation.getTicketIssueDate();
			fechaRecortada = fecha.substring(0, Math.min(fecha.length(), 10));
			if (arreglosEstadisticas.size() == 0){
				arreglosEstadisticas.add(new VODaylyStatistic(fechaRecortada));
			}
			else{
				Boolean esta = false;
				for ( int i = 0 ; i < arreglosEstadisticas.size(); i ++ ){
					if (arreglosEstadisticas.get(i).darFecha().contains(fechaRecortada)){
						esta = true;
					}
				}
				if (esta == false){
					arreglosEstadisticas.add(new VODaylyStatistic(fechaRecortada));
				}
			}
		}

		for (VOMovingViolations violation : movingViolationsQueue){
			fecha = violation.getTicketIssueDate();
			fechaRecortada = fecha.substring(0, Math.min(fecha.length(), 10));

			for (int i = 0 ; i < arreglosEstadisticas.size() ; i ++ ){
				if (arreglosEstadisticas.get(i).darFecha().contains(fechaRecortada)){
					arreglosEstadisticas.get(i).agregarReporte( violation );
				}
			}
		}

		for (int i = 0 ; i < arreglosEstadisticas.size() ; i ++ ){
			dailyStatistics.enqueue(arreglosEstadisticas.get(i));
		}

		return dailyStatistics;
	}

	public IStack <VOMovingViolations> nLastAccidents(int n) {
		// TODO
		return null;
	}
}
