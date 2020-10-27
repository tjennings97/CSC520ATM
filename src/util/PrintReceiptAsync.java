package util;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.SwingWorker;

import main.Main;
import ui.JFrameATM;
import ui.JPanelCustomer;
import ui.JPanelTechnician;

/**
 * This class prints a component asynchronously.
 * 
 * It uses the SwingWorker abstract class to do the work.
 * 
 * For: SwingWorker<T, V>
 * Where: 
 * 		T - The result type returned by this SwingWorker's doInBackground and get methods
 *		V - The type used for carrying out intermediate results by this SwingWorker's 
 *          publish and process methods
 *      Note: These types must be Objects.
 *            Therefore: Use Void for void
 *                       Use Integer for int
 *                       etc. 
 */
public class PrintReceiptAsync extends SwingWorker<String, String> 
{
	JFrameATM atmFrame = null;
	private Component component = null;
	
	/*
	 * @param atmFrame - The systems ATM frame
	 * @param component - The component to print
	 */
	public PrintReceiptAsync(JFrameATM atmFrame
			               , Component component)
	{
		this.atmFrame = atmFrame;
		this.component = component;
		
		// All done
		return;
	}

    /**
     * Called when the SwingWorker is executed ( (PrintReceiptAsync) printReceiptAsync.execute(); )
     * 
     * @return - The result.
     */
    @Override
    public String doInBackground() 
    { 
		// define what thread will do here 
    	printReceipt((e -> {publish((String) e); return null;}), component );
    	
    	// All done
        return "Print successful";
    } 
    
    /**
     * Receives data chunks from the publish method asynchronously on the Event Dispatch Thread.
     * 
     * @param chunks - A list of intermediate results produced by calls to "publish(T)"
     */
    @Override
    protected void process(List<String> chunks)
    {
    	// This method is called from the event dispatch thread
    	// It receives a list of intermediate results.
    	// The intermediate results get added to the list via 
    	// calls to the "publish(T)" method
        
    	// Get the last result in the list
        String chunk = chunks.get(chunks.size()-1); 
        
        // Debug print it 
        if (Main.DEBUG)
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": chunkSize: " + chunks.size()
			         + ": chunk: " + chunk
			          );
        
        // If we really wanted to be geeky we could update the panel
        // with these messages to show progress :)
        // But not doing it at this moment :)

        // All done
        return;
    } 
    
    /**
     * Called at when the background thread finishes
     */
    @Override
    protected void done()  
    { 
        try 
        { 
        	// This gets the results returned from doInBackground()
            String results = get(); 
            if (Main.DEBUG)
		    	System.err.println(this.getClass().getTypeName() + "."
				         + (new Throwable().getStackTrace()[0].getMethodName())
				         + ": results: " + results
				          );               
    		
            // Set the next panel based on the transaction type
            if (atmFrame.getTransactionType().equals("TECHNICIAN"))
            {
				// Create the technician panel and place it on the frame
				JPanelTechnician panelTechnician = new JPanelTechnician(atmFrame);
				panelTechnician.showPanel();
            }
            else
            {
	    		// Create the customer pane and place it on the frame
	    		JPanelCustomer panelCustomer = new JPanelCustomer(atmFrame);
	    		panelCustomer.showPanel();
            }
        }  
        catch (InterruptedException intrpdEx)  
        { 
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": InterruptedException: \n" 
			         + intrpdEx.getMessage()
			          );
        }  
        catch (ExecutionException execEx)  
        { 
	    	System.err.println(this.getClass().getTypeName() + "."
			         + (new Throwable().getStackTrace()[0].getMethodName())
			         + ": ExecutionException: \n" 
			         + execEx.getMessage()
			          );
        }
        
        // All done
        return;
    } 
	
    /**
     * Controls the printing of the receipt.
     * 
     * @param publish - The SwingWorker "<R> publish(<T>)" method
     * @param receipt - The Swing component to be printed
     */
	private static void printReceipt(Function<String, Void> publish
			                       , Component receipt)
	{
		// Print this pane as the receipt
		try
		{
			if(!Main.NOPRINT) printComponent(publish
		    		                       , receipt
		    		                       //, true); // Fill the page
                                           , false); // Don't fill the page
		}
		catch(Exception ex)
		{
			
		}
				
		// All done
		return;
	}
	
	/**
	 * 
	 * @param component - The Swing component to print
	 * @param fill -  Fill the page
	 */
    public static void printComponent(Function<String, Void> publish
    		                        , Component originalComponent
    		                        , boolean fill)  
    {
        // Make a deep copy to work from as we will change the size of the object
    	// Note: All objects must be serializable, not just the outer object
    	Component component = (Component) Misc.deepCopy(originalComponent);
    	
    	PrinterJob printerJob = PrinterJob.getPrinterJob();
    	
        PageFormat pageFormat = printerJob.defaultPage();
        pageFormat.setOrientation(PageFormat.PORTRAIT);

        // Don't use the dialog
        //PageFormat postPageFormat = printerJob.pageDialog(pageFormat);
        PageFormat postPageFormat = printerJob.defaultPage();
        postPageFormat.setOrientation(PageFormat.PORTRAIT);
        
        if (pageFormat != postPageFormat) 
        {
        	// Publish the status
        	publish.apply("Setting the print component");
        	
            //Set print component
        	printerJob.setPrintable(new ComponentPrinter(component, fill), postPageFormat);
        	/*
            if (printerJob.printDialog()) {
            	printerJob.print();
            }
            */
    		try 
    		{
            	// Publish the status
            	publish.apply("Finding the default print service");
            	
    	        //find the default printService
    	        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();//findPrintService(printerName);                                    
    			if (printService != null) 
    			{
                	// Publish the status
                	publish.apply("Printing with default print service");
                	
    				// There is a default print service so use it w/o displaying the print dialog
    				printerJob.setPrintService(printService); 
    				printerJob.print();
    			} 
    			else 
    			{
                	// Publish the status
                	publish.apply("Printing with print service dialog");
                	
    				// Use the print dialog to print
    				if (printerJob.printDialog() == false) return;				
    			}
    		} 
    		catch (PrinterException prntrEx) 
    		{
		    	System.err.println(PrintReceiptAsync.class.getTypeName() + "."
				         + (new Throwable().getStackTrace()[0].getMethodName())
				         + ": SQLException: \n" 
				         + prntrEx.getMessage()
				          );
    		}
    		catch (Exception ex) 
    		{
		    	System.err.println(PrintReceiptAsync.class.getTypeName() + "."
				         + (new Throwable().getStackTrace()[0].getMethodName())
				         + ": PrinterException: \n" 
				         + ex.getMessage()
				          );
    		}
        }    
    }

    /**
     * Class that formats the component for printing.
     *
     */
    private static class ComponentPrinter implements Printable 
    {

        private Component comp;
        private boolean fill;
        
		/**
		 * 
		 * @param comp - The component to print
		 * @param fill - The indicates whether to fill the page (true) or not (false)
		 */
        public ComponentPrinter(Component comp
        		              , boolean fill) 
        {
            this.comp = comp;
            this.fill = fill;
        }

        /**
         * @param graphics - Graphics object containing the print image.
         * @param pageFormat - Specified page format.
         * @param pageIndex - Page index number.
         * @return 
         */
        @Override
        public int print(Graphics graphics
        		       , PageFormat pageFormat
        		       , int pageIndex) throws PrinterException 
        {

            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            double width = (int) Math.floor(pageFormat.getImageableWidth());
            double height = (int) Math.floor(pageFormat.getImageableHeight());

            if (!fill) 
            {
                width = Math.min(width, comp.getPreferredSize().width);
                height = Math.min(height, comp.getPreferredSize().height);
            }

            comp.setBounds(0, 0, (int) Math.floor(width), (int) Math.floor(height));
            if (comp.getParent() == null) 
            {
                comp.addNotify();
            }
            comp.validate();
            comp.doLayout();
            comp.printAll(graphics2D);
            if (comp.getParent() != null) 
            {
                comp.removeNotify();
            }

            return Printable.PAGE_EXISTS;
        }

    }

}
