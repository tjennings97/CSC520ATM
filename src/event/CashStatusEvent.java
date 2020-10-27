package event;

import java.util.ArrayList;

import model.MachineStatus;

/**
 * This class implements a "Subject(publisher)" part of the Observer design pattern.
 * Also, known as a publish-subscribe pattern.
 *
 */
public class CashStatusEvent {
	
	/**
	 * The interface that "Observers(subscribers)" have to implement
	 *
	 */
	public interface IObserverCashStatus
	{
		public void updateStatus(MachineStatus machineStatus);
	}
	
	// Holds all the observers(Subscribers)
	private final ArrayList<IObserverCashStatus> observers = new ArrayList<IObserverCashStatus>();
	
	/**
	 * Constructor
	 */
	public CashStatusEvent()
	{
		// All done
		return;
	}
	
	/**
	 * 
	 * @param state - current state of this event
	 */
	private void notifyObservers(MachineStatus machineStatus)
	{
		// Send status update to all registered observers
		for (IObserverCashStatus observer : observers)
		{
			observer.updateStatus(machineStatus);
		}
		// All done
		return;
	}
	
	/**
	 * 
	 * @param observer - Observer
	 */
	public void addObserver(IObserverCashStatus observer)
	{
		observers.add(observer);
		
		// All done
		return;
	}
	
	/**
	 * 
	 * @param observer - Observer
	 */
	public void removeObserver(IObserverCashStatus observer)
	{
		observers.remove(observer);
		
		// All done
		return;
	}
	
	/**
	 * 
	 * @param state - current state of this event
	 */
	public void updateStatus(MachineStatus machineStatus)
	{
		notifyObservers(machineStatus);
		
		// All done
		return;
	}

}
