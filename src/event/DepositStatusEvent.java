package event;

import java.util.ArrayList;

import model.MachineStatus;

/**
 * This class implements to "Subject(publisher)" part of the Observer design pattern.
 * Also, known as a publish-subscribe pattern.
 *
 */
public class DepositStatusEvent {
	
	/**
	 * The interface that "Observers(subscribers)" have to implement
	 *
	 */
	public interface IObserverDepositStatus
	{
		public void updateStatus(MachineStatus machineStatus);
	}
	
	// Holds all the observers(Subscribers)
	private final ArrayList<IObserverDepositStatus> observers = new ArrayList<IObserverDepositStatus>();
	
	public DepositStatusEvent()
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
		for (IObserverDepositStatus observer : observers)
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
	public void addObserver(IObserverDepositStatus observer)
	{
		observers.add(observer);
		
		// All done
		return;
	}
	
	/**
	 * 
	 * @param observer - Observer
	 */
	public void removeObserver(IObserverDepositStatus observer)
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
