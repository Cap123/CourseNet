import java.awt.* ;
import java.awt.event.* ;
import javax.swing.*;

public class CourseNet extends JApplet {
	
	// containerPanel
	
	/*public static final int BLANK_PANEL = -1;
	public static final int IDLE = 0;
	public static final int LOGIN_PANEL = 1;
	public static final int COURSES_PANEL = 2;
	public static final int CALENDAR_PANEL = 3;*/
	
	ImagePanel containerPanel;
	ContentPanel stuffPanel;
	Thread panelWatchdog;
	
	
	//Called when this applet is loaded into the browser.
    public void init() {
        //Execute a job on the event-dispatching thread; creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    createGUI();
                    new Thread(
            		new Runnable() {
                        public void run() {
                        	while(true)
                        	{
                        		Panel panelType = stuffPanel.getNewPanelType();
                        		//System.out.println(panelType);
                        		if(!(panelType.equals(Panel.IDLE)))
                        		{
                        			//System.out.println(panelType + " equals Panel.IDLE");
                        			containerPanel.remove(stuffPanel);
                        			repaint();
                        			
                        			switch(panelType)
                        			{
                        			case BLANK:
                        				stuffPanel = new ContentPanel();
                        				break;
                        			case LOGIN:
                        				stuffPanel = new LoginPanel();
                        				break;
                        			case COURSES:
                            			stuffPanel = new CoursesPanel();
                            			break;
                        			case CALENDAR:
                            			stuffPanel = new CalendarPanel();
                            			break;
                        			}
                        			
                        			
                        			containerPanel.add(stuffPanel);
                        			repaint();
                        			//stuffPanel.resetNewPanelType();
                        		}
                        		try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                        	}
                        }
                    }).start();
                }
            });
        } catch (Exception e) { 
            System.err.println("createGUI didn't complete successfully");
        }
    }
    
    private void createGUI()
    {
        //Create and set up the content pane.
		Image image = getToolkit().getImage("../background.jpg");
		containerPanel = new ImagePanel(image);
		containerPanel.setPreferredSize(new Dimension(970, 678));
		
		stuffPanel = new LoginPanel();
		stuffPanel.setOpaque(false);
		stuffPanel.setLayout(null);
		containerPanel.setLayout(null);
		containerPanel.add(stuffPanel);
		
		getContentPane().add(containerPanel);
    }
}