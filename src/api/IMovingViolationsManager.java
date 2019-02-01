package api;

import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;

/**
 * Basic API for testing the functionality of the STS manager
 */
public interface IMovingViolationsManager {

	/**
	 * Method to load the Moving Violations of the STS
	 * @param movingViolationsFile - path to the file 
	 */
	void loadMovingViolations(String[] movingViolationsFiles);
	
	public IQueue <VODaylyStatistic> getDaylyStatistics ();	
	
	public IStack <VOMovingViolations> nLastAccidents(int n);
	
}