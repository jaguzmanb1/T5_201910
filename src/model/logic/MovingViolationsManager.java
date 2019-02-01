package model.logic;

import api.IMovingViolationsManager;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;
import model.data_structures.IQueue;
import model.data_structures.IStack;

public class MovingViolationsManager implements IMovingViolationsManager {

	
	public void loadMovingViolations(String movingViolationsFile){
		// TODO Auto-generated method stub
		
	}

	@Override
	public IQueue <VODaylyStatistic> getDaylyStatistics () {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStack <VOMovingViolations> nLastAccidents(int n) {
		// TODO
		return null;
	}	


}
