package controller;

import api.IMovingViolationsManager;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.logic.MovingViolationsManager;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;

public class Controller {

	/**
	 * Reference to the services manager
	 */
	private IMovingViolationsManager  manager;
 
	public Controller() {
		manager = new MovingViolationsManager();
	}
	
	public void loadMovingViolations() {
		
	}
	
	public IQueue <VODaylyStatistic> getDailyStatistics () {
		return null;
	}
	
	public IStack <VOMovingViolations> nLastAccidents(int n) {
		return null;
	}
}
