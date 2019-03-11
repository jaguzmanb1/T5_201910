package model.vo;

public class LocationVO implements Comparable<LocationVO>{

	private String addressId;
	private String location;
	private int numberOfRegisters;
	
	public LocationVO(String pAddressId, String pLocation, int pNumberOfRegisters){
		addressId=pAddressId;
		location=pLocation;
		numberOfRegisters=pNumberOfRegisters;
	}
	 public String getAddressId(){
		 return addressId;
	 }
	 public String getLocation(){
		 return location;
	 }
	 public int getNumberOfRegisters(){
		 return numberOfRegisters;
	 }
	@Override
	public int compareTo(LocationVO o) {
		int retorno=0;
		if (getNumberOfRegisters()>o.getNumberOfRegisters())
			retorno=1;
		else if(getNumberOfRegisters()<o.getNumberOfRegisters())
			retorno=-1;
		else{
			if(getLocation().compareToIgnoreCase(o.getLocation())>0)
				retorno=1;
			else if(getLocation().compareToIgnoreCase(o.getLocation())<0)
				retorno=-1;
		}
		return retorno;
	}
}
