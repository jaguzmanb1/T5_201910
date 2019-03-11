package controller;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import model.data_structures.HeapPrioridad;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.ListaEncadenada;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.util.porAddressId;
import model.vo.LocationVO;
import model.vo.VODaylyStatistic;

import model.vo.VOMovingViolations;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;
	
	 private HeapPrioridad<LocationVO> heaap; 
	 
	 private ListaEncadenada<VOMovingViolations> linkedList;
	/**
	 * Cola donde se van a cargar los datos de los archivos
	 */
	private Queue<VOMovingViolations> movingViolationsQueue;

	/**
	 * Pila donde se van a cargar los datos de los archivos
	 */
	private Stack<VOMovingViolations> movingViolationsStack;
	/**
	 * Cola con los datos de febrero
	 */
	private Stack<VOMovingViolations> stackFebrero;


	public Controller() {
		view = new MovingViolationsManagerView();
		linkedList = new ListaEncadenada<>();
		//TODO, inicializar la pila y la cola
		movingViolationsQueue = null;
		movingViolationsStack = null;
		stackFebrero=null;
	}
	public Comparable<VOMovingViolations>[] generarMuestra(int tamano){
		Comparable<VOMovingViolations>[] muestra = new Comparable[tamano];
		int bucle = tamano;
		Iterator<VOMovingViolations> actual= linkedList.iterator();
		for (int i=0;i<bucle;i++){
			
			int aleatorio = ThreadLocalRandom.current().nextInt(0,muestra.length-1);
			if(muestra[aleatorio]==null){
				if(actual.hasNext()){
					muestra[aleatorio]=  actual.next();
					
				}
				
			}
			else{
				bucle++;
			}
			
		}
		
		System.out.println("Se genero la muestra correctamente");
		return muestra;
		
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
			case 5:
				this.estadisticasDeTiempo();
			}
		}
	}



	public void loadMovingViolations() {
		
		List<String[]> list = new ArrayList<String[]>();

		CSVReader reader =null;

		try{

			reader=new CSVReaderBuilder(new FileReader("./data/Moving_Violations_Issued_in_January_2018.csv")).withSkipLines(1).build();

			list = reader.readAll();
			
			readFiles(list);
		}
		catch(Exception e){
			e.getMessage();
		}
		List<String[]> list2 = new ArrayList<String[]>();

		CSVReader reader2 =null;

		try{

			reader2=new CSVReaderBuilder(new FileReader("./data/Moving_Violations_Issued_in_February_2018.csv")).withSkipLines(1).build();

			list2 = reader2.readAll();
			
			readFiles(list2);
		}
		catch(Exception e){
			e.getMessage();
		}
		List<String[]> list3 = new ArrayList<String[]>();

		CSVReader reader3 =null;

		try{

			reader3=new CSVReaderBuilder(new FileReader("./data/Moving_Violations_Issued_in_March_2018.csv")).withSkipLines(1).build();

			list3 = reader3.readAll();
			
			readFiles(list3);
		}
		catch(Exception e){
			e.getMessage();
		}
//		List<String[]> list4 = new ArrayList<String[]>();
//
//		CSVReader reader4 =null;
//
//		try{
//
//			reader4=new CSVReaderBuilder(new FileReader("./data/Moving_Violations_Issued_in_April_2018.csv")).withSkipLines(1).build();
//
//			list4 = reader4.readAll();
//			
//			readFiles(list4);
//		}
//		catch(Exception e){
//			e.getMessage();
//		}
//		

	}
		public void readFiles(List<String[]> info){
		
			for(int i=0; i<info.size();i++){
				linkedList.insertarCabeza(new VOMovingViolations(Integer.parseInt(info.get(i)[0]), info.get(i)[2], info.get(i)[13],Integer.parseInt(info.get(i)[9]), info.get(i)[12], info.get(i)[15],info.get(i)[3]));
			}
		}
		
		public void estadisticasDeTiempo(){
			for(int i=1;i<8;i++){
				HeapPrioridad<LocationVO> heapEstadistica = new HeapPrioridad<>(i*50000);
				Comparable<VOMovingViolations>[] muestra=generarMuestra(50000*i);
				ordenarQuickSort(muestra, new porAddressId());
				int x=0;
				long start = System.currentTimeMillis();
				for(int j=0;j<muestra.length;j++){
					if(((VOMovingViolations)muestra[j]).getAddressId()==((VOMovingViolations)muestra[j+1]).getAddressId()){
						x++;
					}
					else{
						String resultado=x+"";
						heapEstadistica.insert(new LocationVO(((VOMovingViolations)muestra[j]).getAddressId(), ((VOMovingViolations)muestra[j]).getLocation(), x));
					x=0;
					}
				}
				long now = System.currentTimeMillis();
				System.out.println("El tiempo transcurrido con "+ (50000*i)+ " datos es de " + ((now-start)/1000)+" segundos");
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
		Stack<VOMovingViolations> polloFrito = new Stack<>();Stack<VOMovingViolations> aiuda = polloFrito;
		for(int i=0;i<n;i++){	
			polloFrito.push(stackFebrero.pop());
		}
		loadMovingViolations();
		
		return polloFrito;
	}
	public static void ordenarQuickSort( Comparable[ ] datos , Comparator<VOMovingViolations> tipo) {

		Collections.shuffle(Arrays.asList(datos));
		 sortParaQuick(datos, 0, datos.length - 1,tipo);
		// TODO implementar el algoritmo QuickSort
	}
	private static void sortParaQuick(Comparable[] a, int lo, int hi, Comparator<VOMovingViolations> tipo)
	 {
	 if (hi <= lo) return;
	 int j = partitionParaQuick(a, lo, hi, tipo); 
	 sortParaQuick(a, lo, j-1,  tipo); 
	 sortParaQuick(a, j+1, hi,  tipo); 
	 } 
	
	
	private static int partitionParaQuick(Comparable[] a, int lo, int hi, Comparator<VOMovingViolations> tipo)
	{ 
	 int i = lo, j = hi+1; 
	 Comparable v = a[lo]; 
	 while (true)
	 { 
	 while (less(a[++i], v, tipo)) if (i == hi) break;
	 while (less(v, a[--j], tipo)) if (j == lo) break;
	 if (i >= j) break;
	 exchange(a, i, j);
	 }
	 exchange(a, lo, j);
	 return j; 
	}
	
	/**
	 * Comparar 2 objetos usando la comparacion "natural" de su clase
	 * @param v primer objeto de comparacion
	 * @param w segundo objeto de comparacion
	 * @return true si v es menor que w usando el metodo compareTo. false en caso contrario.
	 */
	private static boolean less(Comparable v, Comparable w,  Comparator<VOMovingViolations> tipo)
	{
		boolean xd;
		if(tipo.compare((VOMovingViolations)v,(VOMovingViolations) w)>0)
		{
			xd=true;
			// TODO implementar
		}
		else{
			xd=false;
		}
		return xd;
	}
	
	/**
	 * Intercambiar los datos de las posicion i y j
	 * @param datos contenedor de datos
	 * @param i posicion del 1er elemento a intercambiar
	 * @param j posicion del 2o elemento a intercambiar
	 */
	private static void exchange( Comparable[] datos, int i, int j)
	{
		Comparable dato	= datos[i];
		datos[i]=datos[j];
		datos[j]=dato;
		// TODO implementar
	}
}
