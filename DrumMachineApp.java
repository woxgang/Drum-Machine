//*************************************************************************************
//  DrumMachine.java       Author: Jared Little, Adam Pape, James Oakley
//
//  A class to create and build the App.
//  This basic design was based on the Head First Java tutorial on creating a beat box.
//  It was refined to suit our needs.
//*************************************************************************************
package drummachine;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class DrumMachineApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new DrumMachineView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of DrumMachineApp
     */
    public static DrumMachineApp getApplication() {
        return Application.getInstance(DrumMachineApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(DrumMachineApp.class, args);
    }
}
