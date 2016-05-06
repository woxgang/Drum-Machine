//*************************************************************************************
//  RideCymbal.java       Author: Jared Little, Adam Pape, James Oakley
//
//  A class to create create a new ride cymbal instrument for the drum machine.
//  This basic design was based on the Head First Java tutorial on creating a beat box.
//  It was refined to suit our needs.
//*************************************************************************************
package drummachine;

import javax.sound.midi.*;

/**
 * A class containing MIDI information for the Ride Cymbal.  Also contains
 * methods for retrieving this information.
 * @author Jared Little, James Oakley, Adam Pape
 */
public class RideCymbal implements Instrument {

    private Sequencer tempPlayer;
    private ShortMessage rideCymbalOn = new ShortMessage();
    private ShortMessage rideCymbalOff = new ShortMessage();

    public RideCymbal() {
        try {
            rideCymbalOn.setMessage(144, 9, 51, 100);
            rideCymbalOff.setMessage(128, 9, 51, 100);
        } catch (Exception ex) {
            System.out.println("Ride Cymbal failure");
        }
    }

        /**
     * A method to retrieve the ShortMessage off event for the ride cymbal instrument.
     * @return the on event for the ride cymbal.
     */
    public ShortMessage getShortMessageOn() {
        return rideCymbalOn;
    }

        /**
     * A method to retrieve the ShortMessage off event for the ride cymbal instrument.
     * @return the off event for the ride cymbal.
     */
    public ShortMessage getShortMessageOff() {
        return rideCymbalOff;
    }
    /**
     * A method to play a single instance of a ride cymbal.
     */

    public void playSample() {
        try {

            RideCymbal tempRideCymbal = new RideCymbal();

            tempPlayer = MidiSystem.getSequencer();
            tempPlayer.open();

            Sequence seq1 = new Sequence(Sequence.PPQ, 4);

            Track trackRideCymbal = seq1.createTrack();

            MidiEvent rideCymbalOnEvent = new MidiEvent(tempRideCymbal.getShortMessageOn(), 1);
            MidiEvent rideCymbalOffEvent = new MidiEvent(tempRideCymbal.getShortMessageOff(), 4);

            trackRideCymbal.add(rideCymbalOnEvent);
            trackRideCymbal.add(rideCymbalOffEvent);

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
        return "RideCymbal rideCymbalOn=" + rideCymbalOn.getCommand() + ", " + 
                rideCymbalOn.getChannel() + ", " + rideCymbalOn.getData1() + ", " + 
                rideCymbalOn.getData2() + "\n" + "rideCymbalOff=" + rideCymbalOff.getCommand() 
                + ", " + rideCymbalOff.getChannel() + ", " + rideCymbalOff.getData1() + ", " + 
                rideCymbalOff.getData2();
    }
}
