package simulator.model;

public interface Observable<T> {	//TODO esto se mete dentro de otra clase o aqui esta bien
	void addObserver(T o);

	void removeObserver(T o);
}