package model.util;

import java.util.Comparator;

import model.vo.VOMovingViolations;

public class porAddressId implements Comparator<VOMovingViolations>{

	@Override
	public int compare(VOMovingViolations arg0, VOMovingViolations arg1) {
		String uno = arg0.getAddressId();
		String dos = arg1.getAddressId();
		int retorno=0;
		if(uno.compareToIgnoreCase(dos)>0)
			retorno=1;
		else if(uno.compareToIgnoreCase(dos)<0)
			retorno=-1;
		return retorno;
	}

}
