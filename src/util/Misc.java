package util;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class Misc {
	
	/**
	 * Makes a deep copy of any Java object that is passed.
	 * 
	 * See: https://www.journaldev.com/17129/java-deep-copy-object
	 */
	public static Component deepCopy(Component component) 
	{
		Component deepCopy = null;
		 
	    try 
	    {
	     // Output the component to an in memory stream
		    int origHash = component.hashCode();
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
	        outputStrm.writeObject(component);
	        
	        // Input the in memory stream to new deep copy
	        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
	        ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
	        deepCopy = (Component)objInputStream.readObject();
	        int copyHash = deepCopy.hashCode();
	         
	        // Validate the copies are the same
	        if (origHash != copyHash)
			{
		    	System.err.println(Misc.class.getTypeName() + "."
				         + (new Throwable().getStackTrace()[0].getMethodName())
				         + ": original hash (" + origHash + ") does not mathc deep copy hash (" + copyHash + ")"
				          );
			}	       
	    }
	    catch (Exception ex) 
	    {
	        ex.printStackTrace();
	    }
	     
	    // All done
        return deepCopy;

	}
	
	/**
	 * 
	 * @param money - BigDecimal to round to two decimals as a money amount
	 * @return - Rounded money amount
	 */
    public static BigDecimal roundToMoney(BigDecimal money)
    {
    	// See: http://www.javapractices.com/topic/TopicAction.do?Id=13
    	
    	final int DECIMALS = 2;
    	final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
    	
	    return money.setScale(DECIMALS, ROUNDING_MODE);
	}
	
	/**
	 * 
	 * @param component - The component to print
	 * @param fill -  Fill the page
	 */
    public static void printComponent(Component compOriginal, boolean fill)  
    {
        // Make a deep copy to work from as we will change the size of the object
    	// Note: All objects must be serializable, not just the outer object
    	Component comp = (Component) deepCopy(compOriginal);
    	
    	PrinterJob printerJob = PrinterJob.getPrinterJob();
    	
        PageFormat pageFormat = printerJob.defaultPage();
        pageFormat.setOrientation(PageFormat.PORTRAIT);

        // Don't use the dialog
        //PageFormat postPageFormat = printerJob.pageDialog(pageFormat);
        PageFormat postPageFormat = printerJob.defaultPage();
        postPageFormat.setOrientation(PageFormat.PORTRAIT);
        
        if (pageFormat != postPageFormat) {
            //Set print component
        	printerJob.setPrintable(new ComponentPrinter(comp, fill), postPageFormat);
        	/*
            if (printerJob.printDialog()) {
            	printerJob.print();
            }
            */
    		try {
    	        //find the default printService
    	        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();//findPrintService(printerName);                                    
    			if (printService != null) {
    				// There is a default print service so use it w/o displaying the print dialog
    				printerJob.setPrintService(printService); 
    				printerJob.print();
    			} 
    			else {
    				// Use the print dialog to print
    				if (printerJob.printDialog() == false) return;				
    			}
    		} 
    		catch (PrinterException prntrEx) {
		    	System.err.println(Misc.class.getTypeName() + "."
				         + (new Throwable().getStackTrace()[0].getMethodName())
				         + "ERROR: PrinterException: " + prntrEx
				          );
    		}
    		catch (Exception ex) {
		    	System.err.println(Misc.class.getTypeName() + "."
				         + (new Throwable().getStackTrace()[0].getMethodName())
				         + "ERROR: Exception: " + ex
				          );
    		}
        }    
    }

    /*
    private static void printComponentToFile(Component comp, boolean fill) throws PrinterException {    
        Paper paper = new Paper();
        paper.setSize(8.3 * 72, 11.7 * 72);
        paper.setImageableArea(18, 18, 559, 783);

        PageFormat pf = new PageFormat();
        pf.setPaper(paper);
        pf.setOrientation(PageFormat.LANDSCAPE);

        BufferedImage img = new BufferedImage(
                        (int) Math.round(pf.getWidth()),
                        (int) Math.round(pf.getHeight()),
                        BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle(0, 0, img.getWidth(), img.getHeight()));
        ComponentPrinter cp = new ComponentPrinter(comp, fill);
        try {
            cp.print(g2d, pf, 0);
        } finally {
            g2d.dispose();
        }

        try 
        {
            ImageIO.write(img, "png", new File("Page-" + (fill ? "Filled" : "") + ".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
    }
    */

    private static class ComponentPrinter implements Printable {

        private Component comp;
        private boolean fill;

        public ComponentPrinter(Component comp, boolean fill) {
            this.comp = comp;
            this.fill = fill;
        }

        @Override
        public int print(Graphics g, PageFormat format, int page_index) throws PrinterException {

            if (page_index > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2 = (Graphics2D) g;
            g2.translate(format.getImageableX(), format.getImageableY());

            double width = (int) Math.floor(format.getImageableWidth());
            double height = (int) Math.floor(format.getImageableHeight());

            if (!fill) {

                width = Math.min(width, comp.getPreferredSize().width);
                height = Math.min(height, comp.getPreferredSize().height);

            }

            comp.setBounds(0, 0, (int) Math.floor(width), (int) Math.floor(height));
            if (comp.getParent() == null) {
                comp.addNotify();
            }
            comp.validate();
            comp.doLayout();
            comp.printAll(g2);
            if (comp.getParent() != null) {
                comp.removeNotify();
            }

            return Printable.PAGE_EXISTS;
        }

    }

}
