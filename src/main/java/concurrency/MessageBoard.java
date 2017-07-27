package concurrency;

import java.util.Observable;

class MessageBoard extends Observable {
    private String message;

    public String getMessage() {
	return message;
    }

    public void changeMessage(String message) {
	this.message = message;
	setChanged();
	notifyObservers(message);
    }

    public static void main(String[] args) {
	MessageBoard board = new MessageBoard();
	Student bob = new Student("bob");
	Student joe = new Student("joe");
	board.addObserver(bob);
	board.addObserver(joe);
	board.changeMessage("More Homework!");
    }
}
