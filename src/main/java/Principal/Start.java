package Principal;

import Entity.User;
import useCase.PlaceHolderUseCase;

public class Start {

	public static void main(String[] args) {
//		new PlaceHolderUseCase().execute("https://jsonplaceholder.typicode.com/todos/", 200, Todos.class);
		new PlaceHolderUseCase().execute("https://jsonplaceholder.typicode.com/users/", 10, User.class);
	}

}
