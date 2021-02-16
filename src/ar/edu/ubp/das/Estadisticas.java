package ar.edu.ubp.das;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ar.edu.ubp.das.manager.EstadisticasManager;

public class Estadisticas {

	public static void main(String[] args) {
		
		
		Runnable runnable = new Runnable() {			
			@Override
			public void run() {
				//Esta funcion es la que se llama cada x cantidad de tiempo
				if(Thread.currentThread().isInterrupted()) return;			
				EstadisticasManager manager = new EstadisticasManager();				
				int result = 0;
				
				result = manager.GenerarReportes();
				if(result != 0) System.err.println("Error");
			}
		};
		
		//Se crea el Thread que dispara la funcion cada x tiempo
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		//Se configura el schedule, el primer numero es el delay hasta la primera ejecucion
		//el segundo es cada cuanto se repite y el ultimo valor es la unidad de tiempo
		executor.scheduleAtFixedRate(runnable, 5, 12, TimeUnit.HOURS);	
		
		InputStreamReader inputStream = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStream);
		
		System.out.println("Para frenar el proceso presione 1");			
		try {
			String input = bufferedReader.readLine();
			
			switch (input) {
				case "1":
					System.out.println("Terminando el proceso");
					executor.shutdown();
					boolean resultado = executor.awaitTermination(60, TimeUnit.SECONDS);					
					System.out.println("Terminado: " + (resultado?"Bien":"Mal"));
				break;

				default:
					System.out.println("Continuar ");
					break;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
