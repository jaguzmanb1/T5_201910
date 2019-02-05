package controller;

import java.util.Scanner;

import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;
import view.MovingViolationsManagerView;

public class Controller {
 
	private MovingViolationsManagerView view;
	
	public Controller() {
		view = new MovingViolationsManagerView();
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
					view.showDailyStatistics(dailyStatistics);
					break;
					
				case 3:
					view.showMensage("Ingrese el número de infracciones a buscar");
					int n = Integer.parseInt(sc.next());

					IStack<VOMovingViolations> violations = this.nLastAccidents(n);
					view.showMovingViolations(violations);
					break;
											
				case 4:	
					fin=true;
					sc.close();
					break;
			}
		}
	}

	

	
	
	public void loadMovingViolations() {
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
