//*************************************************************************************
//  Hihat.java       Author: Jared Little, Adam Pape, James Oakley
//
//  A class to create create a new hi-hat instrument for the drum machine.
//  This basic design was based on the Head First Java tutorial on creating a beat box.
//  It was refined to suit our needs.
//*************************************************************************************
package drummachine;

import javax.sound.midi.*;

/**
 * A class containing MIDI information for the Hi-hat.  Also contains
 * methods for retrieving this information.
 * @author Jared Little, James Oakley, Adam Pape
 */
public class Hihat implements Instrument {

    private Sequencer tempPlayer;
    private ShortMessage hiHatOn = new ShortMessage();
    private ShortMessage hiHatOff = new ShortMessage();

    public Hihat() {
        try {
            hiHatOn.setMessage(144, 9, 44, 100); //(command code, channel, data, data)
            hiHatOff.setMessage(128, 9, 44, 100);
        } catch (Exception ex) {
            System.out.println("Hi-Hat failure");
        }
    }
    
    /**
     * A method to retrieve the ShortMessage on event for the hi-hat instrument.
     * @return the on event for the hi-hat.
     */

    public ShortMessage getShortMessageOn() {
        return hiHatOn;
    }
    
    /**
     * A method to retrieve the ShortMessage off event for the hi-hat instrument.
     * @return the off event for the hi-hat.
     */

    public ShortMessage getShortMessageOff() {
        return hiHatOff;
    }
    
    /**
     * A method to play a single instance of a hi-hat.
     */

    public void playSample() {
        try {

            Hihat tempHiHat = new Hihat();

            tempPlayer = MidiSystem.getSequencer();
            tempPlayer.open();

            Sequence seq1 = new Sequence(Sequence.PPQ, 4);

            Track trackHiHat = seq1.createTrack();

            MidiEvent hiHatOnEvent = new MidiEvent(tempHiHat.getShortMessageOn(), 1);
            MidiEvent hiHatOffEvent = new MidiEvent(tempHiHat.getShortMessageOff(), 4);

            trackHiHat.add(hiHatOnEvent);
            trackHiHat.add(hiHatOffEvent);

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
        return "Hihat hiHatOn=" + hiHatOn.getCommand() + ", " + hiHatOn.getChannel() 
                + ", " + hiHatOn.getData1() + ", " + hiHatOn.getData2() + "\n" +
                "hiHatOff=" + hiHatOff.getCommand() + ", " + hiHatOff.getChannel() 
                + ", " + hiHatOff.getData1() + ", " + hiHatOff.getData2();
    }
}
