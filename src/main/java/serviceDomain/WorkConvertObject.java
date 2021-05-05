package serviceDomain;

import java.lang.reflect.Type;

import com.google.gson.Gson;

import interfece.IWorkerConvertObject;

public class WorkConvertObject implements Runnable {

	private IWorkerConvertObject placeHolder;
	private Gson gson;
	private Type classe;
	
	public WorkConvertObject(IWorkerConvertObject placeHolder, Type classe) {
		this.placeHolder = placeHolder;
		this.gson = new Gson();
		this.classe = classe;
	}
	
	
	public void run() {
		
		while(this.placeHolder.getContinua()) {
			String todosJson = this.placeHolder.getElementListPlaceHolder();
			System.out.println(todosJson);
			if(todosJson != null) {
				Object todos = this.gson.fromJson(todosJson, classe);
			}
		}

	}

}
