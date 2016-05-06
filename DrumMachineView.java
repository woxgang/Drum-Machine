//********************************************************************
//  DrumMachine.java       Author: Jared Little, Adam Pape, James Oakley
//
//  A class to create GUI functionality for the drum machine .
//  This basic design was based on the Head First Java tutorial on creating a beat box.
//  It was refined to suit our needs.
//********************************************************************
package drummachine;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.util.ArrayList;
import javax.sound.midi.MidiSystem;
import javax.swing.JFileChooser;
import javax.swing.JToggleButton;

/**
 * A class that handles all the GUI workings and components. In part auto-generated
 * using the Swing Application Framework.
 * @author Adam Pape, Jared Little, James Oakley
 */
public class DrumMachineView extends FrameView {

    private static ArrayList<JCheckBox> checkboxList;
    private static ArrayList<JToggleButton> tickIndicatorList;
    private DrumMachine drumMachine;
    private JFileChooser save = new JFileChooser();

    /**
     * Instantiates GUI components, a <code>DrumMachine</code> object, an 
     * ArrayList containing the <code>JCheckBoxes</code> and an ArrayList containing the 
     * <code>tickIndicator</code> buttons.
     * @param app the SingleFrameApplication object from the Swing Application
     * Framework
     */
    public DrumMachineView(SingleFrameApplication app) {
        super(app);
        getFrame().setResizable(true);
        getFrame().setTitle("Java Drum Machine");
        drumMachine = new DrumMachine();
        checkboxList = new ArrayList<JCheckBox>();
        tickIndicatorList = new ArrayList<JToggleButton>();

        
        initComponents();

        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;

            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");

        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {

                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }

                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();

                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());

                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());

                }
            }
        });
    }

    /**
     * Shows a <code>jDialog</code> box.
     */
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = DrumMachineApp.getApplication().getMainFrame();

            aboutBox.setLocationRelativeTo(mainFrame);
        }
        DrumMachineApp.getApplication().show(aboutBox);
    }

    /**
     * Adds all of the <code>JCheckBoxes</code> into the <code>ArrayList checkboxList</code>.
     * The status of the <code>JCheckBoxes</code> is core to the functioning of this program,
     * thus this method and the <code>ArrayList</code> it's creating are vital.
     */
    public void generateCheckboxList() {
        checkboxList.clear();
        checkboxList.add(kick1);
        checkboxList.add(kick2);
        checkboxList.add(kick3);
        checkboxList.add(kick4);
        checkboxList.add(kick5);
        checkboxList.add(kick6);
        checkboxList.add(kick7);
        checkboxList.add(kick8);
        checkboxList.add(kick9);
        checkboxList.add(kick10);
        checkboxList.add(kick11);
        checkboxList.add(kick12);
        checkboxList.add(kick13);
        checkboxList.add(kick14);
        checkboxList.add(kick15);
        checkboxList.add(kick16);

        checkboxList.add(snare1);
        checkboxList.add(snare2);
        checkboxList.add(snare3);
        checkboxList.add(snare4);
        checkboxList.add(snare5);
        checkboxList.add(snare6);
        checkboxList.add(snare7);
        checkboxList.add(snare8);
        checkboxList.add(snare9);
        checkboxList.add(snare10);
        checkboxList.add(snare11);
        checkboxList.add(snare12);
        checkboxList.add(snare13);
        checkboxList.add(snare14);
        checkboxList.add(snare15);
        checkboxList.add(snare16);

        checkboxList.add(hihat1);
        checkboxList.add(hihat2);
        checkboxList.add(hihat3);
        checkboxList.add(hihat4);
        checkboxList.add(hihat5);
        checkboxList.add(hihat6);
        checkboxList.add(hihat7);
        checkboxList.add(hihat8);
        checkboxList.add(hihat9);
        checkboxList.add(hihat10);
        checkboxList.add(hihat11);
        checkboxList.add(hihat12);
        checkboxList.add(hihat13);
        checkboxList.add(hihat14);
        checkboxList.add(hihat15);
        checkboxList.add(hihat16);

        checkboxList.add(highTom1);
        checkboxList.add(highTom2);
        checkboxList.add(highTom3);
        checkboxList.add(highTom4);
        checkboxList.add(highTom5);
        checkboxList.add(highTom6);
        checkboxList.add(highTom7);
        checkboxList.add(highTom8);
        checkboxList.add(highTom9);
        checkboxList.add(highTom10);
        checkboxList.add(highTom11);
        checkboxList.add(highTom12);
        checkboxList.add(highTom13);
        checkboxList.add(highTom14);
        checkboxList.add(highTom15);
        checkboxList.add(highTom16);

        checkboxList.add(lowTom1);
        checkboxList.add(lowTom2);
        checkboxList.add(lowTom3);
        checkboxList.add(lowTom4);
        checkboxList.add(lowTom5);
        checkboxList.add(lowTom6);
        checkboxList.add(lowTom7);
        checkboxList.add(lowTom8);
        checkboxList.add(lowTom9);
        checkboxList.add(lowTom10);
        checkboxList.add(lowTom11);
        checkboxList.add(lowTom12);
        checkboxList.add(lowTom13);
        checkboxList.add(lowTom14);
        checkboxList.add(lowTom15);
        checkboxList.add(lowTom16);

        checkboxList.add(rideCymb1);
        checkboxList.add(rideCymb2);
        checkboxList.add(rideCymb3);
        checkboxList.add(rideCymb4);
        checkboxList.add(rideCymb5);
        checkboxList.add(rideCymb6);
        checkboxList.add(rideCymb7);
        checkboxList.add(rideCymb8);
        checkboxList.add(rideCymb9);
        checkboxList.add(rideCymb10);
        checkboxList.add(rideCymb11);
        checkboxList.add(rideCymb12);
        checkboxList.add(rideCymb13);
        checkboxList.add(rideCymb14);
        checkboxList.add(rideCymb15);
        checkboxList.add(rideCymb16);

        checkboxList.add(crashCymb1);
        checkboxList.add(crashCymb2);
        checkboxList.add(crashCymb3);
        checkboxList.add(crashCymb4);
        checkboxList.add(crashCymb5);
        checkboxList.add(crashCymb6);
        checkboxList.add(crashCymb7);
        checkboxList.add(crashCymb8);
        checkboxList.add(crashCymb9);
        checkboxList.add(crashCymb10);
        checkboxList.add(crashCymb11);
        checkboxList.add(crashCymb12);
        checkboxList.add(crashCymb13);
        checkboxList.add(crashCymb14);
        checkboxList.add(crashCymb15);
        checkboxList.add(crashCymb16);

    }
    
        /**
     * Adds all of the <code>tickIndicator JToggleButtons</code> into the 
     * <code>ArrayList checkboxList</code>. These buttons represent the current
     * MIDI tick.
     */
       public void generateTickIndicatorList() {
        tickIndicatorList.clear();
        tickIndicatorList.add(tickIndicator1);
        tickIndicatorList.add(tickIndicator2);
        tickIndicatorList.add(tickIndicator3);
        tickIndicatorList.add(tickIndicator4);
        tickIndicatorList.add(tickIndicator5);
        tickIndicatorList.add(tickIndicator6);
        tickIndicatorList.add(tickIndicator7);
        tickIndicatorList.add(tickIndicator8);
        tickIndicatorList.add(tickIndicator9);
        tickIndicatorList.add(tickIndicator10);
        tickIndicatorList.add(tickIndicator11);
        tickIndicatorList.add(tickIndicator12);
        tickIndicatorList.add(tickIndicator13);
        tickIndicatorList.add(tickIndicator14);
        tickIndicatorList.add(tickIndicator15);
        tickIndicatorList.add(tickIndicator16);
    }

    /**
     * Returns the current state of the checkboxes via the <code>checkboxList</code>.
     * @return the <code>ArrayList</code> containing the <code>JCheckBoxes</code>.
     */
    public static ArrayList<JCheckBox> getCheckboxList() {
        return checkboxList;
    }
    
    /**
     * Returns the <code>tickIndicatorList</code> containing the <code>
     * tickIndicator</code> buttons.
     * @return the <code>ArrayList</code> containing the <code>tickIndicator</code>
     * buttons.
     */
    public static ArrayList<JToggleButton> getTickIndicatorList() {
        return tickIndicatorList;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        muteKick = new javax.swing.JToggleButton();
        kick1 = new javax.swing.JCheckBox();
        kick2 = new javax.swing.JCheckBox();
        kick3 = new javax.swing.JCheckBox();
        kick4 = new javax.swing.JCheckBox();
        kick5 = new javax.swing.JCheckBox();
        kick6 = new javax.swing.JCheckBox();
        kick7 = new javax.swing.JCheckBox();
        kick8 = new javax.swing.JCheckBox();
        kick9 = new javax.swing.JCheckBox();
        kick10 = new javax.swing.JCheckBox();
        kick11 = new javax.swing.JCheckBox();
        kick12 = new javax.swing.JCheckBox();
        kick13 = new javax.swing.JCheckBox();
        kick14 = new javax.swing.JCheckBox();
        kick15 = new javax.swing.JCheckBox();
        kick16 = new javax.swing.JCheckBox();
        muteSnare = new javax.swing.JToggleButton();
        snare1 = new javax.swing.JCheckBox();
        snare2 = new javax.swing.JCheckBox();
        snare3 = new javax.swing.JCheckBox();
        snare4 = new javax.swing.JCheckBox();
        snare5 = new javax.swing.JCheckBox();
        snare6 = new javax.swing.JCheckBox();
        snare7 = new javax.swing.JCheckBox();
        snare8 = new javax.swing.JCheckBox();
        snare9 = new javax.swing.JCheckBox();
        snare10 = new javax.swing.JCheckBox();
        snare11 = new javax.swing.JCheckBox();
        snare12 = new javax.swing.JCheckBox();
        snare13 = new javax.swing.JCheckBox();
        snare14 = new javax.swing.JCheckBox();
        snare15 = new javax.swing.JCheckBox();
        snare16 = new javax.swing.JCheckBox();
        muteHihat = new javax.swing.JToggleButton();
        hihat1 = new javax.swing.JCheckBox();
        hihat2 = new javax.swing.JCheckBox();
        hihat3 = new javax.swing.JCheckBox();
        hihat4 = new javax.swing.JCheckBox();
        hihat5 = new javax.swing.JCheckBox();
        hihat6 = new javax.swing.JCheckBox();
        hihat7 = new javax.swing.JCheckBox();
        hihat8 = new javax.swing.JCheckBox();
        hihat9 = new javax.swing.JCheckBox();
        hihat10 = new javax.swing.JCheckBox();
        hihat11 = new javax.swing.JCheckBox();
        hihat12 = new javax.swing.JCheckBox();
        hihat13 = new javax.swing.JCheckBox();
        hihat14 = new javax.swing.JCheckBox();
        hihat15 = new javax.swing.JCheckBox();
        hihat16 = new javax.swing.JCheckBox();
        muteCrashCymb = new javax.swing.JToggleButton();
        crashCymb1 = new javax.swing.JCheckBox();
        crashCymb2 = new javax.swing.JCheckBox();
        crashCymb3 = new javax.swing.JCheckBox();
        crashCymb4 = new javax.swing.JCheckBox();
        crashCymb5 = new javax.swing.JCheckBox();
        crashCymb6 = new javax.swing.JCheckBox();
        crashCymb7 = new javax.swing.JCheckBox();
        crashCymb8 = new javax.swing.JCheckBox();
        crashCymb9 = new javax.swing.JCheckBox();
        crashCymb10 = new javax.swing.JCheckBox();
        crashCymb11 = new javax.swing.JCheckBox();
        crashCymb12 = new javax.swing.JCheckBox();
        crashCymb13 = new javax.swing.JCheckBox();
        crashCymb14 = new javax.swing.JCheckBox();
        crashCymb15 = new javax.swing.JCheckBox();
        crashCymb16 = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        tempoSpinner = new javax.swing.JSpinner();
        tempoLabel = new javax.swing.JLabel();
        muteLowTom = new javax.swing.JToggleButton();
        muteRideCymb = new javax.swing.JToggleButton();
        rideCymb9 = new javax.swing.JCheckBox();
        rideCymb8 = new javax.swing.JCheckBox();
        rideCymb11 = new javax.swing.JCheckBox();
        rideCymb10 = new javax.swing.JCheckBox();
        rideCymb12 = new javax.swing.JCheckBox();
        rideCymb13 = new javax.swing.JCheckBox();
        rideCymb14 = new javax.swing.JCheckBox();
        rideCymb15 = new javax.swing.JCheckBox();
        rideCymb16 = new javax.swing.JCheckBox();
        rideCymb1 = new javax.swing.JCheckBox();
        rideCymb6 = new javax.swing.JCheckBox();
        rideCymb7 = new javax.swing.JCheckBox();
        rideCymb4 = new javax.swing.JCheckBox();
        rideCymb5 = new javax.swing.JCheckBox();
        rideCymb2 = new javax.swing.JCheckBox();
        rideCymb3 = new javax.swing.JCheckBox();
        lowTom13 = new javax.swing.JCheckBox();
        lowTom12 = new javax.swing.JCheckBox();
        lowTom10 = new javax.swing.JCheckBox();
        lowTom11 = new javax.swing.JCheckBox();
        lowTom8 = new javax.swing.JCheckBox();
        lowTom9 = new javax.swing.JCheckBox();
        lowTom4 = new javax.swing.JCheckBox();
        lowTom5 = new javax.swing.JCheckBox();
        lowTom6 = new javax.swing.JCheckBox();
        lowTom7 = new javax.swing.JCheckBox();
        lowTom16 = new javax.swing.JCheckBox();
        lowTom1 = new javax.swing.JCheckBox();
        lowTom14 = new javax.swing.JCheckBox();
        lowTom15 = new javax.swing.JCheckBox();
        lowTom2 = new javax.swing.JCheckBox();
        lowTom3 = new javax.swing.JCheckBox();
        muteHighTom = new javax.swing.JToggleButton();
        highTom9 = new javax.swing.JCheckBox();
        highTom4 = new javax.swing.JCheckBox();
        highTom5 = new javax.swing.JCheckBox();
        highTom6 = new javax.swing.JCheckBox();
        highTom12 = new javax.swing.JCheckBox();
        highTom10 = new javax.swing.JCheckBox();
        highTom11 = new javax.swing.JCheckBox();
        highTom8 = new javax.swing.JCheckBox();
        highTom7 = new javax.swing.JCheckBox();
        highTom13 = new javax.swing.JCheckBox();
        highTom2 = new javax.swing.JCheckBox();
        highTom3 = new javax.swing.JCheckBox();
        highTom14 = new javax.swing.JCheckBox();
        highTom15 = new javax.swing.JCheckBox();
        highTom1 = new javax.swing.JCheckBox();
        highTom16 = new javax.swing.JCheckBox();
        saveButton = new javax.swing.JButton();
        kickPlay = new javax.swing.JButton();
        snarePlay = new javax.swing.JButton();
        hihatPlay = new javax.swing.JButton();
        hitomPlay = new javax.swing.JButton();
        lowtomPlay = new javax.swing.JButton();
        ridePlay = new javax.swing.JButton();
        crashPlay = new javax.swing.JButton();
        kickSolo = new javax.swing.JToggleButton();
        snareSolo = new javax.swing.JToggleButton();
        hihatSolo = new javax.swing.JToggleButton();
        hiTomSolo = new javax.swing.JToggleButton();
        lowTomSolo = new javax.swing.JToggleButton();
        rideSolo = new javax.swing.JToggleButton();
        crashSolo = new javax.swing.JToggleButton();
        clear = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        exportMidi = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 2), new java.awt.Dimension(0, 2), new java.awt.Dimension(32767, 2));
        tickIndicator1 = new javax.swing.JToggleButton();
        tickIndicator2 = new javax.swing.JToggleButton();
        tickIndicator3 = new javax.swing.JToggleButton();
        tickIndicator4 = new javax.swing.JToggleButton();
        tickIndicator6 = new javax.swing.JToggleButton();
        tickIndicator5 = new javax.swing.JToggleButton();
        tickIndicator7 = new javax.swing.JToggleButton();
        tickIndicator8 = new javax.swing.JToggleButton();
        tickIndicator9 = new javax.swing.JToggleButton();
        tickIndicator10 = new javax.swing.JToggleButton();
        tickIndicator11 = new javax.swing.JToggleButton();
        tickIndicator12 = new javax.swing.JToggleButton();
        tickIndicator13 = new javax.swing.JToggleButton();
        tickIndicator14 = new javax.swing.JToggleButton();
        tickIndicator15 = new javax.swing.JToggleButton();
        tickIndicator16 = new javax.swing.JToggleButton();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(800, 500));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(drummachine.DrumMachineApp.class).getContext().getResourceMap(DrumMachineView.class);
        muteKick.setIcon(resourceMap.getIcon("muteKick.icon")); // NOI18N
        muteKick.setText(resourceMap.getString("muteKick.text")); // NOI18N
        muteKick.setName("muteKick"); // NOI18N
        muteKick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteKickActionPerformed(evt);
            }
        });

        kick1.setText(resourceMap.getString("kick1.text")); // NOI18N
        kick1.setName("kick1"); // NOI18N
        kick1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick2.setName("kick2"); // NOI18N
        kick2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick3.setName("kick3"); // NOI18N
        kick3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick4.setName("kick4"); // NOI18N
        kick4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick5.setName("kick5"); // NOI18N
        kick5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick6.setName("kick6"); // NOI18N
        kick6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick7.setName("kick7"); // NOI18N
        kick7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick8.setName("kick8"); // NOI18N
        kick8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick9.setName("kick9"); // NOI18N
        kick9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick10.setName("kick10"); // NOI18N
        kick10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick11.setName("kick11"); // NOI18N
        kick11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick12.setName("kick12"); // NOI18N
        kick12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick13.setName("kick13"); // NOI18N
        kick13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick14.setName("kick14"); // NOI18N
        kick14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick15.setName("kick15"); // NOI18N
        kick15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        kick16.setName("kick16"); // NOI18N
        kick16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        muteSnare.setIcon(resourceMap.getIcon("muteSnare.icon")); // NOI18N
        muteSnare.setName("muteSnare"); // NOI18N
        muteSnare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteSnareActionPerformed(evt);
            }
        });

        snare1.setName("snare1"); // NOI18N
        snare1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare2.setName("snare2"); // NOI18N
        snare2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare3.setName("snare3"); // NOI18N
        snare3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare4.setName("snare4"); // NOI18N
        snare4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare5.setName("snare5"); // NOI18N
        snare5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare6.setName("snare6"); // NOI18N
        snare6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare7.setName("snare7"); // NOI18N
        snare7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare8.setName("snare8"); // NOI18N
        snare8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare9.setName("snare9"); // NOI18N
        snare9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare10.setName("snare10"); // NOI18N
        snare10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare11.setName("snare11"); // NOI18N
        snare11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare12.setName("snare12"); // NOI18N
        snare12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare13.setName("snare13"); // NOI18N
        snare13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare14.setName("snare14"); // NOI18N
        snare14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare15.setName("snare15"); // NOI18N
        snare15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        snare16.setName("snare16"); // NOI18N
        snare16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        muteHihat.setIcon(resourceMap.getIcon("muteHihat.icon")); // NOI18N
        muteHihat.setName("muteHihat"); // NOI18N
        muteHihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteHihatActionPerformed(evt);
            }
        });

        hihat1.setName("hihat1"); // NOI18N
        hihat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat2.setName("hihat2"); // NOI18N
        hihat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat3.setName("hihat3"); // NOI18N
        hihat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat4.setName("hihat4"); // NOI18N
        hihat4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat5.setName("hihat5"); // NOI18N
        hihat5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat6.setName("hihat6"); // NOI18N
        hihat6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat7.setName("hihat7"); // NOI18N
        hihat7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat8.setName("hihat8"); // NOI18N
        hihat8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat9.setName("hihat9"); // NOI18N
        hihat9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat10.setName("hihat10"); // NOI18N
        hihat10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat11.setName("hihat11"); // NOI18N
        hihat11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat12.setName("hihat12"); // NOI18N
        hihat12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat13.setName("hihat13"); // NOI18N
        hihat13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat14.setName("hihat14"); // NOI18N
        hihat14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat15.setName("hihat15"); // NOI18N
        hihat15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        hihat16.setName("hihat16"); // NOI18N
        hihat16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        muteCrashCymb.setIcon(resourceMap.getIcon("muteCrashCymb.icon")); // NOI18N
        muteCrashCymb.setName("muteCrashCymb"); // NOI18N
        muteCrashCymb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteCrashCymbActionPerformed(evt);
            }
        });

        crashCymb1.setName("crashCymb1"); // NOI18N
        crashCymb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb2.setName("crashCymb2"); // NOI18N
        crashCymb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb3.setName("crashCymb3"); // NOI18N
        crashCymb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb4.setName("crashCymb4"); // NOI18N
        crashCymb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb5.setName("crashCymb5"); // NOI18N
        crashCymb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb6.setName("crashCymb6"); // NOI18N
        crashCymb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb7.setName("crashCymb7"); // NOI18N
        crashCymb7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb8.setName("crashCymb8"); // NOI18N
        crashCymb8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb9.setName("crashCymb9"); // NOI18N
        crashCymb9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb10.setName("crashCymb10"); // NOI18N
        crashCymb10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb11.setName("crashCymb11"); // NOI18N
        crashCymb11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb12.setName("crashCymb12"); // NOI18N
        crashCymb12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb13.setName("crashCymb13"); // NOI18N
        crashCymb13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb14.setName("crashCymb14"); // NOI18N
        crashCymb14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb15.setName("crashCymb15"); // NOI18N
        crashCymb15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        crashCymb16.setName("crashCymb16"); // NOI18N
        crashCymb16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        jSeparator1.setName("jSeparator1"); // NOI18N

        tempoSpinner.setModel(new javax.swing.SpinnerNumberModel(120, 20, 208, 1));
        tempoSpinner.setName("tempoSpinner"); // NOI18N
        tempoSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tempoSpinnerStateChanged(evt);
            }
        });

        tempoLabel.setText(resourceMap.getString("tempoLabel.text")); // NOI18N
        tempoLabel.setName("tempoLabel"); // NOI18N

        muteLowTom.setIcon(resourceMap.getIcon("muteLowTom.icon")); // NOI18N
        muteLowTom.setName("muteLowTom"); // NOI18N
        muteLowTom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteLowTomActionPerformed(evt);
            }
        });

        muteRideCymb.setIcon(resourceMap.getIcon("muteRideCymb.icon")); // NOI18N
        muteRideCymb.setName("muteRideCymb"); // NOI18N
        muteRideCymb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteRideCymbActionPerformed(evt);
            }
        });

        rideCymb9.setName("rideCymb9"); // NOI18N
        rideCymb9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb8.setName("rideCymb8"); // NOI18N
        rideCymb8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb11.setName("rideCymb11"); // NOI18N
        rideCymb11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb10.setName("rideCymb10"); // NOI18N
        rideCymb10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb12.setName("rideCymb12"); // NOI18N
        rideCymb12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb13.setName("rideCymb13"); // NOI18N
        rideCymb13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb14.setName("rideCymb14"); // NOI18N
        rideCymb14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb15.setName("rideCymb15"); // NOI18N
        rideCymb15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb16.setName("rideCymb16"); // NOI18N
        rideCymb16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb1.setName("rideCymb1"); // NOI18N
        rideCymb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb6.setName("rideCymb6"); // NOI18N
        rideCymb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb7.setName("rideCymb7"); // NOI18N
        rideCymb7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb4.setName("rideCymb4"); // NOI18N
        rideCymb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb5.setName("rideCymb5"); // NOI18N
        rideCymb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb2.setName("rideCymb2"); // NOI18N
        rideCymb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        rideCymb3.setName("rideCymb3"); // NOI18N
        rideCymb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom13.setName("lowTom13"); // NOI18N
        lowTom13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom12.setName("lowTom12"); // NOI18N
        lowTom12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom10.setName("lowTom10"); // NOI18N
        lowTom10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom11.setName("lowTom11"); // NOI18N
        lowTom11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom8.setName("lowTom8"); // NOI18N
        lowTom8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom9.setName("lowTom9"); // NOI18N
        lowTom9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom4.setName("lowTom4"); // NOI18N
        lowTom4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom5.setName("lowTom5"); // NOI18N
        lowTom5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom6.setName("lowTom6"); // NOI18N
        lowTom6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom7.setName("lowTom7"); // NOI18N
        lowTom7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom16.setName("lowTom16"); // NOI18N
        lowTom16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom1.setName("lowTom1"); // NOI18N
        lowTom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom14.setName("lowTom14"); // NOI18N
        lowTom14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom15.setName("lowTom15"); // NOI18N
        lowTom15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom2.setName("lowTom2"); // NOI18N
        lowTom2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        lowTom3.setName("lowTom3"); // NOI18N
        lowTom3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        muteHighTom.setIcon(resourceMap.getIcon("muteHighTom.icon")); // NOI18N
        muteHighTom.setName("muteHighTom"); // NOI18N
        muteHighTom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteHighTomActionPerformed(evt);
            }
        });

        highTom9.setName("highTom9"); // NOI18N
        highTom9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom4.setName("highTom4"); // NOI18N
        highTom4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom5.setName("highTom5"); // NOI18N
        highTom5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom6.setName("highTom6"); // NOI18N
        highTom6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom12.setName("highTom12"); // NOI18N
        highTom12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom10.setName("highTom10"); // NOI18N
        highTom10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom11.setName("highTom11"); // NOI18N
        highTom11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom8.setName("highTom8"); // NOI18N
        highTom8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom7.setName("highTom7"); // NOI18N
        highTom7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom13.setName("highTom13"); // NOI18N
        highTom13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom2.setName("highTom2"); // NOI18N
        highTom2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom3.setName("highTom3"); // NOI18N
        highTom3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom14.setName("highTom14"); // NOI18N
        highTom14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom15.setName("highTom15"); // NOI18N
        highTom15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom1.setName("highTom1"); // NOI18N
        highTom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        highTom16.setName("highTom16"); // NOI18N
        highTom16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });

        saveButton.setText(resourceMap.getString("saveButton.text")); // NOI18N
        saveButton.setName("saveButton"); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        kickPlay.setText(resourceMap.getString("kickPlay.text")); // NOI18N
        kickPlay.setName("kickPlay"); // NOI18N
        kickPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kickPlayActionPerformed(evt);
            }
        });

        snarePlay.setText(resourceMap.getString("snarePlay.text")); // NOI18N
        snarePlay.setName("snarePlay"); // NOI18N
        snarePlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snarePlayActionPerformed(evt);
            }
        });

        hihatPlay.setText(resourceMap.getString("hihatPlay.text")); // NOI18N
        hihatPlay.setName("hihatPlay"); // NOI18N
        hihatPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hihatPlayActionPerformed(evt);
            }
        });

        hitomPlay.setText(resourceMap.getString("hitomPlay.text")); // NOI18N
        hitomPlay.setName("hitomPlay"); // NOI18N
        hitomPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitomPlayActionPerformed(evt);
            }
        });

        lowtomPlay.setText(resourceMap.getString("lowtomPlay.text")); // NOI18N
        lowtomPlay.setName("lowtomPlay"); // NOI18N
        lowtomPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lowtomPlayActionPerformed(evt);
            }
        });

        ridePlay.setText(resourceMap.getString("ridePlay.text")); // NOI18N
        ridePlay.setName("ridePlay"); // NOI18N
        ridePlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ridePlayActionPerformed(evt);
            }
        });

        crashPlay.setText(resourceMap.getString("crashPlay.text")); // NOI18N
        crashPlay.setName("crashPlay"); // NOI18N
        crashPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crashPlayActionPerformed(evt);
            }
        });

        kickSolo.setText(resourceMap.getString("kickSolo.text")); // NOI18N
        kickSolo.setName("kickSolo"); // NOI18N
        kickSolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kickSoloActionPerformed(evt);
            }
        });

        snareSolo.setText(resourceMap.getString("snareSolo.text")); // NOI18N
        snareSolo.setName("snareSolo"); // NOI18N
        snareSolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snareSoloActionPerformed(evt);
            }
        });

        hihatSolo.setText(resourceMap.getString("hihatSolo.text")); // NOI18N
        hihatSolo.setName("hihatSolo"); // NOI18N
        hihatSolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hihatSoloActionPerformed(evt);
            }
        });

        hiTomSolo.setText(resourceMap.getString("hiTomSolo.text")); // NOI18N
        hiTomSolo.setName("hiTomSolo"); // NOI18N
        hiTomSolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hiTomSoloActionPerformed(evt);
            }
        });

        lowTomSolo.setText(resourceMap.getString("lowTomSolo.text")); // NOI18N
        lowTomSolo.setName("lowTomSolo"); // NOI18N
        lowTomSolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lowTomSoloActionPerformed(evt);
            }
        });

        rideSolo.setText(resourceMap.getString("rideSolo.text")); // NOI18N
        rideSolo.setName("rideSolo"); // NOI18N
        rideSolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rideSoloActionPerformed(evt);
            }
        });

        crashSolo.setText(resourceMap.getString("crashSolo.text")); // NOI18N
        crashSolo.setName("crashSolo"); // NOI18N
        crashSolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crashSoloActionPerformed(evt);
            }
        });

        clear.setText(resourceMap.getString("clear.text")); // NOI18N
        clear.setName("clear"); // NOI18N
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        stopButton.setText(resourceMap.getString("stopButton.text")); // NOI18N
        stopButton.setName("stopButton"); // NOI18N
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        playButton.setText(resourceMap.getString("playButton.text")); // NOI18N
        playButton.setName("playButton"); // NOI18N
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        loadButton.setText(resourceMap.getString("loadButton.text")); // NOI18N
        loadButton.setName("loadButton"); // NOI18N
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        exportMidi.setText(resourceMap.getString("exportMidi.text")); // NOI18N
        exportMidi.setName("exportMidi"); // NOI18N
        exportMidi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportMidiActionPerformed(evt);
            }
        });

        filler1.setName("filler1"); // NOI18N

        tickIndicator1.setText(resourceMap.getString("tickIndicator1.text")); // NOI18N
        tickIndicator1.setName("tickIndicator1"); // NOI18N
        tickIndicator1.setRolloverEnabled(false);
        tickIndicator1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator2.setName("tickIndicator2"); // NOI18N
        tickIndicator2.setRolloverEnabled(false);
        tickIndicator2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator3.setName("tickIndicator3"); // NOI18N
        tickIndicator3.setRolloverEnabled(false);
        tickIndicator3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator4.setName("tickIndicator4"); // NOI18N
        tickIndicator4.setRolloverEnabled(false);
        tickIndicator4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator6.setName("tickIndicator6"); // NOI18N
        tickIndicator6.setRolloverEnabled(false);
        tickIndicator6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator5.setName("tickIndicator5"); // NOI18N
        tickIndicator5.setRolloverEnabled(false);
        tickIndicator5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator7.setName("tickIndicator7"); // NOI18N
        tickIndicator7.setRolloverEnabled(false);
        tickIndicator7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator8.setName("tickIndicator8"); // NOI18N
        tickIndicator8.setRolloverEnabled(false);
        tickIndicator8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator9.setName("tickIndicator9"); // NOI18N
        tickIndicator9.setRolloverEnabled(false);
        tickIndicator9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator10.setName("tickIndicator10"); // NOI18N
        tickIndicator10.setRolloverEnabled(false);
        tickIndicator10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator11.setName("tickIndicator11"); // NOI18N
        tickIndicator11.setRolloverEnabled(false);
        tickIndicator11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator12.setName("tickIndicator12"); // NOI18N
        tickIndicator12.setRolloverEnabled(false);
        tickIndicator12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator13.setName("tickIndicator13"); // NOI18N
        tickIndicator13.setRolloverEnabled(false);
        tickIndicator13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator14.setName("tickIndicator14"); // NOI18N
        tickIndicator14.setRolloverEnabled(false);
        tickIndicator14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator15.setName("tickIndicator15"); // NOI18N
        tickIndicator15.setRolloverEnabled(false);
        tickIndicator15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        tickIndicator16.setName("tickIndicator16"); // NOI18N
        tickIndicator16.setRolloverEnabled(false);
        tickIndicator16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickIndicatorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(clear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportMidi))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(muteKick, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                            .addComponent(muteCrashCymb, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                            .addComponent(muteHihat, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                            .addComponent(muteSnare, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                            .addComponent(muteRideCymb, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                            .addComponent(muteLowTom, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(snareSolo)
                                                    .addComponent(hihatSolo)
                                                    .addComponent(kickSolo))
                                                .addGap(10, 10, 10))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lowTomSolo)
                                                    .addComponent(rideSolo)
                                                    .addComponent(crashSolo))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(snarePlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lowtomPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(ridePlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(crashPlay, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                            .addComponent(kickPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(hihatPlay, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                                        .addGap(3, 3, 3))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(muteHighTom, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(hiTomSolo)
                                        .addGap(10, 10, 10)
                                        .addComponent(hitomPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(hihat1)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat2)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat3)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat4)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat5)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat6)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat7)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat8)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat9)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat10)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat11)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat12)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat13)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat14)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat15)
                                        .addGap(18, 18, 18)
                                        .addComponent(hihat16))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(snare1)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare2)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare3)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare4)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare5)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare6)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare7)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare8)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare9)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare10)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare11)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare12)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare13)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare14)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare15)
                                        .addGap(18, 18, 18)
                                        .addComponent(snare16))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(lowTom1)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom2)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom3)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom4)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom5)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom6)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom7)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom8)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom9)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom10)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom11)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom12)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom13)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom14)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom15)
                                        .addGap(18, 18, 18)
                                        .addComponent(lowTom16))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(highTom1)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom2)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom3)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom4)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom5)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom6)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom7)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom8)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom9)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom10)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom11)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom12)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom13)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom14)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom15)
                                        .addGap(18, 18, 18)
                                        .addComponent(highTom16))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(rideCymb1)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb2)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb3)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb4)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb5)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb6)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb7)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb8)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb9)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb10)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb11)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb12)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb13)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb14)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb15)
                                        .addGap(18, 18, 18)
                                        .addComponent(rideCymb16))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(crashCymb1)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb2)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb3)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb4)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb5)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb6)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb7)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb8)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb9)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb10)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb11)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb12)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb13)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb14)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb15)
                                        .addGap(18, 18, 18)
                                        .addComponent(crashCymb16))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tickIndicator1, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tickIndicator2, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tickIndicator3, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tickIndicator4, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tickIndicator5, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tickIndicator6, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tickIndicator7, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tickIndicator8, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tickIndicator9, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tickIndicator10, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tickIndicator11, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tickIndicator12, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tickIndicator13, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tickIndicator14, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tickIndicator15, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tickIndicator16, 0, 0, Short.MAX_VALUE)
                                            .addComponent(kick16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
                .addGap(32, 32, 32))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tempoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tempoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(922, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(685, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(316, 316, 316))
        );

        mainPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {muteCrashCymb, muteHighTom, muteHihat, muteKick, muteLowTom, muteRideCymb, muteSnare});

        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(muteKick)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kickSolo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kickPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tickIndicator2)
                            .addComponent(tickIndicator3)
                            .addComponent(tickIndicator4)
                            .addComponent(tickIndicator5)
                            .addComponent(tickIndicator6)
                            .addComponent(tickIndicator7)
                            .addComponent(tickIndicator8)
                            .addComponent(tickIndicator9)
                            .addComponent(tickIndicator10)
                            .addComponent(tickIndicator11)
                            .addComponent(tickIndicator12)
                            .addComponent(tickIndicator13)
                            .addComponent(tickIndicator14)
                            .addComponent(tickIndicator15)
                            .addComponent(tickIndicator16)
                            .addComponent(tickIndicator1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kick16)
                            .addComponent(kick15)
                            .addComponent(kick14)
                            .addComponent(kick13)
                            .addComponent(kick12)
                            .addComponent(kick11)
                            .addComponent(kick10)
                            .addComponent(kick9)
                            .addComponent(kick8)
                            .addComponent(kick7)
                            .addComponent(kick6)
                            .addComponent(kick4)
                            .addComponent(kick3)
                            .addComponent(kick2)
                            .addComponent(kick1)
                            .addComponent(kick5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(snareSolo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(snarePlay, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(muteSnare))
                        .addGap(11, 11, 11))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(snare1)
                            .addComponent(snare2)
                            .addComponent(snare3)
                            .addComponent(snare4)
                            .addComponent(snare5)
                            .addComponent(snare6)
                            .addComponent(snare7)
                            .addComponent(snare8)
                            .addComponent(snare9)
                            .addComponent(snare10)
                            .addComponent(snare11)
                            .addComponent(snare12)
                            .addComponent(snare13)
                            .addComponent(snare14)
                            .addComponent(snare15)
                            .addComponent(snare16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hihat1)
                    .addComponent(hihat2)
                    .addComponent(hihat3)
                    .addComponent(hihat4)
                    .addComponent(hihat5)
                    .addComponent(hihat6)
                    .addComponent(hihat7)
                    .addComponent(hihat8)
                    .addComponent(hihat9)
                    .addComponent(hihat10)
                    .addComponent(hihat11)
                    .addComponent(hihat12)
                    .addComponent(hihat13)
                    .addComponent(hihat14)
                    .addComponent(hihat15)
                    .addComponent(hihat16)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(hihatSolo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hihatPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(muteHihat))
                .addGap(10, 10, 10)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(highTom16)
                            .addComponent(highTom15)
                            .addComponent(highTom14)
                            .addComponent(highTom13)
                            .addComponent(highTom12)
                            .addComponent(highTom11)
                            .addComponent(highTom10)
                            .addComponent(highTom9)
                            .addComponent(highTom8)
                            .addComponent(highTom7)
                            .addComponent(highTom6)
                            .addComponent(highTom5)
                            .addComponent(highTom4)
                            .addComponent(highTom3)
                            .addComponent(highTom2)
                            .addComponent(highTom1))
                        .addGap(2, 2, 2)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(muteHighTom)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(hiTomSolo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hitomPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lowTomSolo)
                        .addComponent(lowtomPlay))
                    .addComponent(muteLowTom)
                    .addComponent(lowTom16)
                    .addComponent(lowTom15)
                    .addComponent(lowTom14)
                    .addComponent(lowTom13)
                    .addComponent(lowTom12)
                    .addComponent(lowTom11)
                    .addComponent(lowTom10)
                    .addComponent(lowTom9)
                    .addComponent(lowTom8)
                    .addComponent(lowTom7)
                    .addComponent(lowTom6)
                    .addComponent(lowTom5)
                    .addComponent(lowTom4)
                    .addComponent(lowTom3)
                    .addComponent(lowTom2)
                    .addComponent(lowTom1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rideCymb1)
                    .addComponent(rideCymb16)
                    .addComponent(rideCymb15)
                    .addComponent(rideCymb14)
                    .addComponent(rideCymb13)
                    .addComponent(rideCymb12)
                    .addComponent(rideCymb11)
                    .addComponent(rideCymb10)
                    .addComponent(rideCymb9)
                    .addComponent(rideCymb8)
                    .addComponent(rideCymb7)
                    .addComponent(rideCymb6)
                    .addComponent(rideCymb5)
                    .addComponent(rideCymb4)
                    .addComponent(rideCymb3)
                    .addComponent(rideCymb2)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rideSolo)
                        .addComponent(ridePlay))
                    .addComponent(muteRideCymb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crashCymb16)
                    .addComponent(crashCymb15)
                    .addComponent(crashCymb14)
                    .addComponent(crashCymb13)
                    .addComponent(crashCymb12)
                    .addComponent(crashCymb11)
                    .addComponent(crashCymb10)
                    .addComponent(crashCymb9)
                    .addComponent(crashCymb8)
                    .addComponent(crashCymb7)
                    .addComponent(crashCymb6)
                    .addComponent(crashCymb5)
                    .addComponent(crashCymb4)
                    .addComponent(crashCymb3)
                    .addComponent(crashCymb2)
                    .addComponent(crashCymb1)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(crashSolo)
                        .addComponent(crashPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(muteCrashCymb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempoLabel)
                    .addComponent(tempoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playButton)
                    .addComponent(clear)
                    .addComponent(stopButton)
                    .addComponent(saveButton)
                    .addComponent(loadButton)
                    .addComponent(exportMidi))
                .addGap(239, 239, 239))
        );

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Changes the tempo by calling the <code>changeTempo(int)</code> method through the <code>
     * drumMachine</code> object at the prompt of the tempoSpinner.
     * @param evt the <code>Event</code> generated by the <code>tempoSpinner</code> listener.
     */
    private void tempoSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tempoSpinnerStateChanged
        drumMachine.changeTempo((Integer) tempoSpinner.getValue());
    }//GEN-LAST:event_tempoSpinnerStateChanged

    /**
     * Saves the current pattern by calling the <code>savePattern()</code> method through the <code>
     * drumMachine</code> object at the prompt of the <code>saveButton</code>.
     * @param evt the <code>Event</code> generated by the <code>saveButton</code> listener.
     */
private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
    generateCheckboxList();
    drumMachine.savePattern();
}//GEN-LAST:event_saveButtonActionPerformed

    /**
     * Mutes or unmutes the crash cymbol track by calling the 
     * <code>muteInst(int)</code> or <code>unMuteInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>muteCrashCymb</code> button.
     * @param evt the <code>Event</code> generated by the <code>muteCrashCymb</code> listener.
     */
private void muteCrashCymbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteCrashCymbActionPerformed
    if (muteCrashCymb.isSelected()) {
        drumMachine.muteInst(6);
    } else {
        drumMachine.unMuteInst(6);
    }
}//GEN-LAST:event_muteCrashCymbActionPerformed

    /**
     * Mutes or unmutes the ride cymbol track by calling the <code>muteInst(int)</code> or <code>unMuteInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>muteRideCymb</code> button.
     * @param evt the <code>Event</code> generated by the <code>muteRideCymb</code> listener.
     */
private void muteRideCymbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteRideCymbActionPerformed
    if (muteRideCymb.isSelected()) {
        drumMachine.muteInst(5);
    } else {
        drumMachine.unMuteInst(5);
    }
}//GEN-LAST:event_muteRideCymbActionPerformed

    /**
     * Mutes or unmutes the low tom track by calling the <code>muteInst(int)</code> or <code>unMuteInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>mutLowTom</code> button.
     * @param evt the <code>Event</code> generated by the <code>muteLowTom</code> listener.
     */
private void muteLowTomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteLowTomActionPerformed
    if (muteLowTom.isSelected()) {
        drumMachine.muteInst(4);
    } else {
        drumMachine.unMuteInst(4);
    }
}//GEN-LAST:event_muteLowTomActionPerformed

    /**
     * Mutes or unmutes the high tom track by calling the <code>muteInst(int)</code> or <code>unMuteInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>mutHighTom</code> button.
     * @param evt the <code>Event</code> generated by the <code>muteHighTom</code> listener.
     */
private void muteHighTomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteHighTomActionPerformed
    if (muteHighTom.isSelected()) {
        drumMachine.muteInst(3);
    } else {
        drumMachine.unMuteInst(3);
    }
}//GEN-LAST:event_muteHighTomActionPerformed

    /**
     * Mutes or unmutes the hi hat track by calling the <code>muteInst(int)</code> or <code>unMuteInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>mutHihat</code> button.
     * @param evt the <code>Event</code> generated by the <code>muteHihat</code> listener.
     */
private void muteHihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteHihatActionPerformed
    if (muteHihat.isSelected()) {
        drumMachine.muteInst(2);
    } else {
        drumMachine.unMuteInst(2);
    }
}//GEN-LAST:event_muteHihatActionPerformed

    /**
     * Mutes or unmutes the snare track by calling the <code>muteInst(int)</code> or <code>unMuteInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>muteSnare</code> button.
     * @param evt the <code>Event</code> generated by the <code>muteSnare</code> listener.
     */
private void muteSnareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteSnareActionPerformed
    if (muteSnare.isSelected()) {
        drumMachine.muteInst(1);
    } else {
        drumMachine.unMuteInst(1);
    }
}//GEN-LAST:event_muteSnareActionPerformed

    /**
     * Mutes or unmutes the kick track by calling the <code>muteInst(int)</code> or <code>unMuteInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>muteKick</code> button.
     * @param evt the <code>Event</code> generated by the <code>muteKick</code> listener.
     */
private void muteKickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteKickActionPerformed
    if (muteKick.isSelected()) {
        drumMachine.muteInst(0);
    } else {
        drumMachine.unMuteInst(0);
    }
}//GEN-LAST:event_muteKickActionPerformed

    /**
     * Solos or unsolos the kick track by calling the <code>soloInst(int)</code> or <code>unSoloInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>kickSolo</code> button.
     * @param evt the <code>Event</code> generated by the <code>kickSolo</code> listener.
     */
private void kickSoloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kickSoloActionPerformed
    if (kickSolo.isSelected()) {
        drumMachine.soloInst(0);
    } else {
        drumMachine.unSoloInst(0);
    }
}//GEN-LAST:event_kickSoloActionPerformed

    /**
     * Solos or unsolos the snare track by calling the <code>soloInst(int)</code> or <code>unSoloInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>snareSolo</code> button.
     * @param evt the <code>Event</code> generated by the <code>snareSolo</code> listener.
     */
private void snareSoloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snareSoloActionPerformed
    if (snareSolo.isSelected()) {
        drumMachine.soloInst(1);
    } else {
        drumMachine.unSoloInst(1);
    }
}//GEN-LAST:event_snareSoloActionPerformed

    /**
     * Solos or unsolos the hi-hat track by calling the <code>soloInst(int)</code> or <code>unSoloInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>hihatSolo</code> button.
     * @param evt the <code>Event</code> generated by the <code>hihatSolo</code> listener.
     */
private void hihatSoloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hihatSoloActionPerformed
    if (hihatSolo.isSelected()) {
        drumMachine.soloInst(2);
    } else {
        drumMachine.unSoloInst(2);
    }
}//GEN-LAST:event_hihatSoloActionPerformed

    /**
     * Solos or unsolos the hi tom track by calling the <code>soloInst(int)</code> or <code>unSoloInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>hiTomSolo</code> button.
     * @param evt the <code>Event</code> generated by the <code>hiTomSolo</code> listener.
     */
private void hiTomSoloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hiTomSoloActionPerformed
    if (hiTomSolo.isSelected()) {
        drumMachine.soloInst(3);
    } else {
        drumMachine.unSoloInst(3);
    }
}//GEN-LAST:event_hiTomSoloActionPerformed

    /**
     * Solos or unsolos the low tom track by calling the <code>soloInst(int)</code> or <code>unSoloInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>lowTomSolo</code> button.
     * @param evt the <code>Event</code> generated by the <code>lowTomSolo</code> listener.
     */
private void lowTomSoloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lowTomSoloActionPerformed
    if (lowTomSolo.isSelected()) {
        drumMachine.soloInst(4);
    } else {
        drumMachine.unSoloInst(4);
    }
}//GEN-LAST:event_lowTomSoloActionPerformed

    /**
     * Solos or unsolos the ride cymbal track by calling the <code>soloInst(int)</code> or <code>unSoloInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>rideSolo</code> button.
     * @param evt the <code>Event</code> generated by the <code>rideSolo</code> listener.
     */
private void rideSoloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rideSoloActionPerformed
    if (rideSolo.isSelected()) {
        drumMachine.soloInst(5);
    } else {
        drumMachine.unSoloInst(5);
    }
}//GEN-LAST:event_rideSoloActionPerformed

    /**
     * Solos or unsolos the crash cymbal track by calling the <code>soloInst(int)</code> or <code>unSoloInst(int)</code>
     * method through the <code>drumMachine</code> object at the prompt of 
     * the <code>crashSolo</code> button.
     * @param evt the <code>Event</code> generated by the <code>crashSolo</code> listener.
     */
private void crashSoloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crashSoloActionPerformed
    if (crashSolo.isSelected()) {
        drumMachine.soloInst(6);
    } else {
        drumMachine.unSoloInst(6);
    }
}//GEN-LAST:event_crashSoloActionPerformed

    /**
     * Plays a single kick drum note by calling the <code>playSample()</code> method through the 
     * <code>snareDrum</code> object at the prompt of the <code>snarePlay</code> button.
     * @param evt the <code>Event</code> generated by the <code>snarePlay</code> listener.
     */
private void kickPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kickPlayActionPerformed
    KickDrum kickDrum = new KickDrum();
    kickDrum.playSample();

}//GEN-LAST:event_kickPlayActionPerformed

    /**
     * Plays a single snare drum note by calling the <code>playSample()</code> method through the 
     * <code>snareDrum</code> object at the prompt of the <code>snarePlay</code> button.
     * @param evt the <code>Event</code> generated by the <code>snarePlay</code> listener.
     */
private void snarePlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snarePlayActionPerformed
    SnareDrum snareDrum = new SnareDrum();
    snareDrum.playSample();
}//GEN-LAST:event_snarePlayActionPerformed

    /**
     * Plays a single hi-hat note by calling the <code>playSample()</code> method through the 
     * <code>hihat</code> object at the prompt of the <code>hihatPlay</code> button.
     * @param evt the <code>Event</code> generated by the <code>hihatPlay</code> listener.
     */
private void hihatPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hihatPlayActionPerformed
    Hihat hihat = new Hihat();
    hihat.playSample();
}//GEN-LAST:event_hihatPlayActionPerformed

    /**
     * Plays a single high tom note by calling the <code>playSample()</code> method through the 
     * <code>hiTom</code> object at the prompt of the <code>hitomPlay</code> button.
     * @param evt the <code>Event</code> generated by the <code>hitom</code> listener.
     */
private void hitomPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitomPlayActionPerformed
    HiTom hiTom = new HiTom();
    hiTom.playSample();
}//GEN-LAST:event_hitomPlayActionPerformed
    
    /**
     * Plays a single low tom note by calling the <code>playSample()</code> method through the 
     * <code>lowTom</code> object at the prompt of the <code>lowtom</code> button.
     * @param evt the <code>Event</code> generated by the <code>lowtom</code> listener.
     */
private void lowtomPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lowtomPlayActionPerformed
    LowTom lowTom = new LowTom();
    lowTom.playSample();
}//GEN-LAST:event_lowtomPlayActionPerformed

    /**
     * Plays a single ride cymbal note by calling the <code>playSample()</code> method through the 
     * <code>ride</code> object at the prompt of the <code>ridePlay</code> button.
     * @param evt the <code>Event</code> generated by the <code>ridePlay</code> listener.
     */
private void ridePlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ridePlayActionPerformed
    RideCymbal ride = new RideCymbal();
    ride.playSample();
}//GEN-LAST:event_ridePlayActionPerformed

    /**
     * Plays a single crash cymbal note by calling the <code>playSample()</code> method through the 
     * <code>crashCymbal</code> object at the prompt of the <code>crashPlay</code> button.
     * @param evt the <code>Event</code> generated by the <code>crashPlay</code> listener.
     */
private void crashPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crashPlayActionPerformed
    CrashCymbal crashCymbal = new CrashCymbal();
    crashCymbal.playSample();
}//GEN-LAST:event_crashPlayActionPerformed


    /**
     * Sets all the checkboxes to their "off" state at the prompt of
     * the <code>clear</code> button.
     * @param evt the <code>Event</code> generated by the <code>clearAction</code> listener.
     */
private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
    drumMachine.stopSequencer();
    for (int i = 0; i < checkboxList.size(); i++) {

        if (checkboxList.get(i).isSelected()) {
            checkboxList.get(i).setSelected(false);
        }

    }
}//GEN-LAST:event_clearActionPerformed

    /**
     * Stops the sequencer by calling the <code>stopSequencer()</code> method through the 
     * <code>drumMachine</code> object at the prompt of the <code>stopButton</code>
     * @param evt the <code>Event</code> generated by the <code>stopButton</code> listener.
     */
private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed

    drumMachine.stopSequencer();

}//GEN-LAST:event_stopButtonActionPerformed

    /**
     * Plays the sequence by calling the <code>createSequence()</code>
     * method and the <code>playSequence() method through the <code>drumMachine</code> 
     * object at the prompt of the <code>playButton</code>.
     * @param evt the <code>Event</code> generated by the <code>playButton</code> listener.
     */
private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
    generateTickIndicatorList();
    generateCheckboxList();

    drumMachine.createSequence();
    drumMachine.playSequence();
}//GEN-LAST:event_playButtonActionPerformed

    /**
     * Loads a saved pattern by calling the <code>loadPattern()</code> 
     * method through the <code>drumMachine</code> object at the prompt of the 
     * <code>loadButton</code>.
     * @param evt the <code>Event</code> generated by the <code>loadButton</code> listener.
     */
private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed

    drumMachine.loadPattern();
}//GEN-LAST:event_loadButtonActionPerformed

    /**
     * Saves a pattern as a MIDI file by calling the <code>savePatternAsMidi()</code> 
     * method through the <code>drumMachine</code> object at the prompt of the 
     * <code>exportMidi</code> button.
     * @param evt the <code>Event</code> generated by the <code>exportMidi</code> listener.
     */
private void exportMidiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportMidiActionPerformed
    drumMachine.savePatternAsMidi();
}//GEN-LAST:event_exportMidiActionPerformed

    /**
     * If the sequencer in <code>DrumMachine</code> is playing this method
     * creates a new sequence at the prompt of a checkbox being clicked.
     * @param evt the <code>Event</code> generated by the <code>checkBox</code> listener.
     */
    private void checkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxActionPerformed
        if (drumMachine.getSequencerPlayingStatus() == true) {
            drumMachine.createSequence();
        }
    }//GEN-LAST:event_checkBoxActionPerformed

    /**
     * As the <code>tickIndicator</code> buttons are not intentended to be 
     * directly user-interactable this code simply resets the <code>JToggleButton</code>
     * if it is pressed.
     * @param evt the <code>Event</code> generated by the <code>tickIndicator</code> listener.
     */
    private void tickIndicatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tickIndicatorActionPerformed
        generateTickIndicatorList();
        for(int i = 0; i < tickIndicatorList.size(); i++) {
            if (tickIndicatorList.get(i).isSelected()) {
                tickIndicatorList.get(i).setSelected(false);
            }
        }
        
    }//GEN-LAST:event_tickIndicatorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clear;
    private javax.swing.JCheckBox crashCymb1;
    private javax.swing.JCheckBox crashCymb10;
    private javax.swing.JCheckBox crashCymb11;
    private javax.swing.JCheckBox crashCymb12;
    private javax.swing.JCheckBox crashCymb13;
    private javax.swing.JCheckBox crashCymb14;
    private javax.swing.JCheckBox crashCymb15;
    private javax.swing.JCheckBox crashCymb16;
    private javax.swing.JCheckBox crashCymb2;
    private javax.swing.JCheckBox crashCymb3;
    private javax.swing.JCheckBox crashCymb4;
    private javax.swing.JCheckBox crashCymb5;
    private javax.swing.JCheckBox crashCymb6;
    private javax.swing.JCheckBox crashCymb7;
    private javax.swing.JCheckBox crashCymb8;
    private javax.swing.JCheckBox crashCymb9;
    private javax.swing.JButton crashPlay;
    private javax.swing.JToggleButton crashSolo;
    private javax.swing.JButton exportMidi;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JToggleButton hiTomSolo;
    private javax.swing.JCheckBox highTom1;
    private javax.swing.JCheckBox highTom10;
    private javax.swing.JCheckBox highTom11;
    private javax.swing.JCheckBox highTom12;
    private javax.swing.JCheckBox highTom13;
    private javax.swing.JCheckBox highTom14;
    private javax.swing.JCheckBox highTom15;
    private javax.swing.JCheckBox highTom16;
    private javax.swing.JCheckBox highTom2;
    private javax.swing.JCheckBox highTom3;
    private javax.swing.JCheckBox highTom4;
    private javax.swing.JCheckBox highTom5;
    private javax.swing.JCheckBox highTom6;
    private javax.swing.JCheckBox highTom7;
    private javax.swing.JCheckBox highTom8;
    private javax.swing.JCheckBox highTom9;
    private javax.swing.JCheckBox hihat1;
    private javax.swing.JCheckBox hihat10;
    private javax.swing.JCheckBox hihat11;
    private javax.swing.JCheckBox hihat12;
    private javax.swing.JCheckBox hihat13;
    private javax.swing.JCheckBox hihat14;
    private javax.swing.JCheckBox hihat15;
    private javax.swing.JCheckBox hihat16;
    private javax.swing.JCheckBox hihat2;
    private javax.swing.JCheckBox hihat3;
    private javax.swing.JCheckBox hihat4;
    private javax.swing.JCheckBox hihat5;
    private javax.swing.JCheckBox hihat6;
    private javax.swing.JCheckBox hihat7;
    private javax.swing.JCheckBox hihat8;
    private javax.swing.JCheckBox hihat9;
    private javax.swing.JButton hihatPlay;
    private javax.swing.JToggleButton hihatSolo;
    private javax.swing.JButton hitomPlay;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox kick1;
    private javax.swing.JCheckBox kick10;
    private javax.swing.JCheckBox kick11;
    private javax.swing.JCheckBox kick12;
    private javax.swing.JCheckBox kick13;
    private javax.swing.JCheckBox kick14;
    private javax.swing.JCheckBox kick15;
    private javax.swing.JCheckBox kick16;
    private javax.swing.JCheckBox kick2;
    private javax.swing.JCheckBox kick3;
    private javax.swing.JCheckBox kick4;
    private javax.swing.JCheckBox kick5;
    private javax.swing.JCheckBox kick6;
    private javax.swing.JCheckBox kick7;
    private javax.swing.JCheckBox kick8;
    private javax.swing.JCheckBox kick9;
    private javax.swing.JButton kickPlay;
    private javax.swing.JToggleButton kickSolo;
    private javax.swing.JButton loadButton;
    private javax.swing.JCheckBox lowTom1;
    private javax.swing.JCheckBox lowTom10;
    private javax.swing.JCheckBox lowTom11;
    private javax.swing.JCheckBox lowTom12;
    private javax.swing.JCheckBox lowTom13;
    private javax.swing.JCheckBox lowTom14;
    private javax.swing.JCheckBox lowTom15;
    private javax.swing.JCheckBox lowTom16;
    private javax.swing.JCheckBox lowTom2;
    private javax.swing.JCheckBox lowTom3;
    private javax.swing.JCheckBox lowTom4;
    private javax.swing.JCheckBox lowTom5;
    private javax.swing.JCheckBox lowTom6;
    private javax.swing.JCheckBox lowTom7;
    private javax.swing.JCheckBox lowTom8;
    private javax.swing.JCheckBox lowTom9;
    private javax.swing.JToggleButton lowTomSolo;
    private javax.swing.JButton lowtomPlay;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JToggleButton muteCrashCymb;
    private javax.swing.JToggleButton muteHighTom;
    private javax.swing.JToggleButton muteHihat;
    private javax.swing.JToggleButton muteKick;
    private javax.swing.JToggleButton muteLowTom;
    private javax.swing.JToggleButton muteRideCymb;
    private javax.swing.JToggleButton muteSnare;
    private javax.swing.JButton playButton;
    private javax.swing.JCheckBox rideCymb1;
    private javax.swing.JCheckBox rideCymb10;
    private javax.swing.JCheckBox rideCymb11;
    private javax.swing.JCheckBox rideCymb12;
    private javax.swing.JCheckBox rideCymb13;
    private javax.swing.JCheckBox rideCymb14;
    private javax.swing.JCheckBox rideCymb15;
    private javax.swing.JCheckBox rideCymb16;
    private javax.swing.JCheckBox rideCymb2;
    private javax.swing.JCheckBox rideCymb3;
    private javax.swing.JCheckBox rideCymb4;
    private javax.swing.JCheckBox rideCymb5;
    private javax.swing.JCheckBox rideCymb6;
    private javax.swing.JCheckBox rideCymb7;
    private javax.swing.JCheckBox rideCymb8;
    private javax.swing.JCheckBox rideCymb9;
    private javax.swing.JButton ridePlay;
    private javax.swing.JToggleButton rideSolo;
    private javax.swing.JButton saveButton;
    private javax.swing.JCheckBox snare1;
    private javax.swing.JCheckBox snare10;
    private javax.swing.JCheckBox snare11;
    private javax.swing.JCheckBox snare12;
    private javax.swing.JCheckBox snare13;
    private javax.swing.JCheckBox snare14;
    private javax.swing.JCheckBox snare15;
    private javax.swing.JCheckBox snare16;
    private javax.swing.JCheckBox snare2;
    private javax.swing.JCheckBox snare3;
    private javax.swing.JCheckBox snare4;
    private javax.swing.JCheckBox snare5;
    private javax.swing.JCheckBox snare6;
    private javax.swing.JCheckBox snare7;
    private javax.swing.JCheckBox snare8;
    private javax.swing.JCheckBox snare9;
    private javax.swing.JButton snarePlay;
    private javax.swing.JToggleButton snareSolo;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel tempoLabel;
    private javax.swing.JSpinner tempoSpinner;
    private javax.swing.JToggleButton tickIndicator1;
    private javax.swing.JToggleButton tickIndicator10;
    private javax.swing.JToggleButton tickIndicator11;
    private javax.swing.JToggleButton tickIndicator12;
    private javax.swing.JToggleButton tickIndicator13;
    private javax.swing.JToggleButton tickIndicator14;
    private javax.swing.JToggleButton tickIndicator15;
    private javax.swing.JToggleButton tickIndicator16;
    private javax.swing.JToggleButton tickIndicator2;
    private javax.swing.JToggleButton tickIndicator3;
    private javax.swing.JToggleButton tickIndicator4;
    private javax.swing.JToggleButton tickIndicator5;
    private javax.swing.JToggleButton tickIndicator6;
    private javax.swing.JToggleButton tickIndicator7;
    private javax.swing.JToggleButton tickIndicator8;
    private javax.swing.JToggleButton tickIndicator9;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
}
