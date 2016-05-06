//**************************************************************************************
//  CrashCymbal.java       Author: Jared Little, Adam Pape, James Oakley
//
//  A class to create create a new crash cymbal instrument for the drum machine.
//  This basic design was based on the Head First Java tutorial on creating a beat box.
//  It was refined to suit our needs.
//**************************************************************************************
package drummachine;

import javax.sound.midi.*;

/**
 * A class containing MIDI information for the Crash Cymbal.  Also contains
 * methods for retrieving this information.
 * @author Jared Little, James Oakley, Adam Pape
 */
public class CrashCymbal implements Instrument {

    private Sequencer tempPlayer;
    private ShortMessage crashCymbalOn = new ShortMessage();
    private ShortMessage crashCymbalOff = new ShortMessage();

    /**
     * This constructor initializes the <code>ShortMessage</code> variables
     */
    public CrashCymbal() {
        try {
            crashCymbalOn.setMessage(144, 9, 49, 100);
            crashCymbalOff.setMessage(128, 9, 49, 100);
        } catch (Exception ex) {
            System.out.println("Crash Cymbal failure");
        }
    }
    
    /**
     * A method to retrieve the ShortMessage on event for the crash cymbal instrument
     * @return the no event for the crash cymbal.
     */
    public ShortMessage getShortMessageOn() {
        return crashCymbalOn;
    }

    /**
     * A method to retrieve the ShortMessage off event for the crash cymbal instrument.
     * @return the off event for the crash cymbal.
     */
    public ShortMessage getShortMessageOff() {
        return crashCymbalOff;
    }
    
    /**
     * A method to play a single instance of a crash cymbal.
     */
    public void playSample() {
        try {

            CrashCymbal tempCrashCymbal = new CrashCymbal();

            tempPlayer = MidiSystem.getSequencer();
            tempPlayer.open();

            Sequence seq1 = new Sequence(Sequence.PPQ, 4);

            Track trackCrashCymbal = seq1.createTrack();

            MidiEvent hiHatOnEvent = new MidiEvent(tempCrashCymbal.getShortMessageOn(), 1);
            MidiEvent hiHatOffEvent = new MidiEvent(tempCrashCymbal.getShortMessageOff(), 4);

            trackCrashCymbal.add(hiHatOnEvent);
            trackCrashCymbal.add(hiHatOffEvent);

            tempPlayer.setSequence(seq1);

            tempPlayer.start();

        } catch (Exception ex) {
            System.out.println("Error: unable to get sequencer");
            ex.printStackTrace();
        }
    }

    /**
     * Provides the <code>ShortMessage</code> data from each variable in int form
     * @return the <code>ShortMessage</code> data in int form
     */
    @Override
    public String toString() {
        return "CrashCymbal crashCymbalOn=" + crashCymbalOn.getCommand() + ", "
                + crashCymbalOn.getChannel() + ", " + crashCymbalOn.getData1()
                + ", " + crashCymbalOn.getData2() + "\n" + "crashCymbalOff="
                + crashCymbalOn.getCommand() + ", "+ crashCymbalOn.getChannel() 
                + ", " + crashCymbalOn.getData1() + ", " + crashCymbalOn.getData2();
    }
}
