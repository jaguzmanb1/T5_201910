package controller;

import java.io.FileReader;
import java.util.List;
import java.util.Scanner;


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
					view.printMensage("Ingrese el número de infracciones a buscar");
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
			FileReader n1= new FileReader("./data/Moving_Violations_Issued_In_February_2018_ordered.csv");
			CSVReader n2 = new CSVReaderBuilder(n1).withSkipLines(1).build();
			List <String[]> info = n2.readAll();
			movingViolationsStack= new Stack<VOMovingViolations>();
			for(int i=0;i<info.size();i++){
				movingViolationsStack.push(new VOMovingViolations(Integer.parseInt(info.get(i)[0]), info.get(i)[2], info.get(i)[13],Integer.parseInt(info.get(i)[9]), info.get(i)[12], info.get(i)[15]));
			}
			FileReader n3= new FileReader("./data/Moving_Violations_Issued_In_January_2018_ordered.csv");
			CSVReader n4 = new CSVReaderBuilder(n3).build();
			List <String[]> info2 = n4.readAll();
			Queue<VOMovingViolations> Que= new Queue<VOMovingViolations>();
		}
		catch(Exception e){
			
		}
		// TODO
	}
	
	public IQueue <VODaylyStatistic> getDailyStatistics () {
		// TODO
		
		return null;
	}
	
	public IStack <VOMovingViolations> nLastAccidents(int n) {
		// TODO
		return null;
	}
}
