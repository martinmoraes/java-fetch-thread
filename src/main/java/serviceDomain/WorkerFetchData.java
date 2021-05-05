package serviceDomain;

import java.io.IOException;
import java.util.Random;

import ServiceApplication.Request;
import interfece.IWorkerFetchData;

public class WorkerFetchData implements Runnable {

	private String url;
	private int max;
	private IWorkerFetchData placeHolder;

	public WorkerFetchData(String url, int max, IWorkerFetchData placeHolder) {
		this.url = url;
		this.max = max;
		this.placeHolder = placeHolder;
	}

	public void run() {
		while (this.placeHolder.getContinua()) {
			try {
				String todosJsom = new Request().execute(url + this.getRandom(max));
				this.placeHolder.setListPlaceHolder(Thread.currentThread().getName(), todosJsom);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private int getRandom(int max) {
		Random rd = new Random();
		return rd.nextInt(max) + 1;
	}

}
