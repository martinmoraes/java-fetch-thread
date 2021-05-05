package useCase;

import java.lang.reflect.Type;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import interfece.IWorkerConvertObject;
import interfece.IWorkerFetchData;
import serviceDomain.WorkConvertObject;
import serviceDomain.WorkerFetchData;

public class PlaceHolderUseCase implements IWorkerFetchData, IWorkerConvertObject {
	private ArrayList<String> listPlaceHolder = new ArrayList<String>();
	private boolean continua = true;
	private int totalRequisicoes = 0;

	public void execute(String url, int max, Type classe) {
		// fazer o fatch
		Instant inicioA = Instant.now();
		Thread threadA1 = this.initializesFetchData(url, max);
		Thread threadA2 = this.initializesFetchData(url, max);
		Thread threadA3 = this.initializesFetchData(url, max);
		Thread threadA4 = this.initializesFetchData(url, max);
		
		
		Thread ThreadC = this.initializesConvertObject(classe);

		try {
			threadA1.join();
			threadA2.join();
			threadA3.join();
			threadA4.join();
			
			ThreadC.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Instant fimA = Instant.now();
		Duration duracaoA = Duration.between(inicioA, fimA);
		System.out.println("Duração:" + duracaoA.toMillis() + " milisegundos. ");
		// colocar em uma lista
		// consumir lista e converter em objeto
		// contabilizar

	}

	private Thread initializesConvertObject(Type classe) {
		Thread thread = new Thread(new WorkConvertObject(this, classe));
		thread.start();
		return thread;
	}

	private Thread initializesFetchData(String url, int max) {
		Thread thread = new Thread(new WorkerFetchData(url, max, this));
		thread.start();
		return thread;
	}

	public synchronized void setListPlaceHolder(String threadName, String todosJsom) {
		this.listPlaceHolder.add(todosJsom);
		System.out.println(threadName + " Qtde: " + this.listPlaceHolder.size());
		this.totalRequisicoes ++; 
		if (this.totalRequisicoes >= 80) {
			this.continua = false;
		}
	}

	public synchronized String getElementListPlaceHolder() {
		if (this.listPlaceHolder.size() > 0) {
			return listPlaceHolder.remove(0);
		}
		return null;
	}

	public synchronized boolean getContinua() {
		return continua;
	}

}
