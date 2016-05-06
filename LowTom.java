//*************************************************************************************
//  LowTom.java       Author: Jared Little, Adam Pape, James Oakley
//
//  A class to create create a new low tom instrument for the drum machine.
//  This basic design was based on the Head First Java tutorial on creating a beat box.
//  It was refined to suit our needs.
//*************************************************************************************
package drummachine;

import javax.sound.midi.*;

/**
 * A class containing MIDI information for the Low Tom.  Also contains
 * methods for retrieving this information.
 * @author Jared Little, James Oakley, Adam Pape
 */
public class LowTom implements Instrument {

    private Sequencer tempPlayer;
    private ShortMessage lowTomOn = new ShortMessage();
    private ShortMessage lowTomOff = new ShortMessage();

    public LowTom() {
        try {
            lowTomOn.setMessage(144, 9, 43, 100);
            lowTomOff.setMessage(128, 9, 43, 100);
        } catch (Exception ex) {
            System.out.println("Low Tom failure");
        }
    }
    
    /**
     * A method to retrieve the ShortMessage on event for the low tom instrument.
     * @return the on event for the low tom.
     */

    public ShortMessage getShortMessageOn() {
        return lowTomOn;
    }

    /**
     * A method to retrieve the ShortMessage off event for the low tom instrument.
     * @return the off event for the low tom.
     */
    public ShortMessage getShortMessageOff() {
        return lowTomOff;
    }
    
    /**
     * A method to play a single instance of a low tom.
     */

    public void playSample() {
        try {

            LowTom tempLowTom = new LowTom();

            tempPlayer = MidiSystem.getSequencer();
            tempPlayer.open();

            Sequence seq1 = new Sequence(Sequence.PPQ, 4);

            Track trackLowTom = seq1.createTrack();

            MidiEvent lowTomOnEvent = new MidiEvent(tempLowTom.getShortMessageOn(), 1);
            MidiEvent lowTomOffEvent = new MidiEvent(tempLowTom.getShortMessageOff(), 4);

            trackLowTom.add(lowTomOnEvent);
            trackLowTom.add(lowTomOffEvent);

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
        return "LowTom lowTomOn=" + lowTomOn.getCommand() + ", " + lowTomOn.getChannel()
                + ", " + lowTomOn.getData1() + ", " + lowTomOn.getData2() + "\n" +
                "lowTomOff=" + lowTomOff.getCommand() + ", " + lowTomOff.getChannel()
                + ", " + lowTomOff.getData1() + ", " + lowTomOff.getData2();
    }
}
