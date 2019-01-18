package ua.in.boyaryn.invoker;

import ua.in.boyaryn.client.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Processor {

    private final List<Observer> observers = new ArrayList<>();

    public void addSubscriber(Observer subscriber) {
        observers.add(subscriber);
    }

    public void removeSubscriber(Observer subscriber) {
        observers.remove(subscriber);
    }

    public void notifySubscribers(String output, boolean error) {
        observers.forEach(observer -> observer.update(output, error));
    }

    /**
     * Accept a new chunk of input for Processor.
     * If the chunk contains errors, it is discarded,
     * otherwise it is processed and stored in history.
     *
     * @param input - line with new input
     */
    public abstract void feed(String input);

    /**
     * Reverts the last successful processing of input
     */
    public abstract void undo();
}
