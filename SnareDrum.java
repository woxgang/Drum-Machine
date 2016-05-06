//*************************************************************************************
//  SnareDrum.java       Author: Jared Little, Adam Pape, James Oakley
//
//  A class to create create a new snare drum instrument for the drum machine.
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
public class SnareDrum implements Instrument {

    private Sequencer tempPlayer;
    private ShortMessage snareDrumOn = new ShortMessage();
    private ShortMessage snareDrumOff = new ShortMessage();

    public SnareDrum() {
        try {
            snareDrumOn.setMessage(144, 9, 38, 70);

            snareDrumOff.setMessage(128, 9, 38, 70);

        } catch (Exception ex) {
            System.out.println("snare drum failure");
        }
    }
    /**
     * A method to retrieve the ShortMessage on event for the snare drum instrument.
     * @return the on event for the snare drum.
     */
    public ShortMessage getShortMessageOn() {
        return snareDrumOn;
    }

    /**
     * A method to retrieve the ShortMessage off event for the snare drum instrument.
     * @return the off event for the snare drum.
     */

    public ShortMessage getShortMessageOff() {
        return snareDrumOff;
    }


    /**
     * A method to play a single instance of a snare drum.
     */
    public void playSample() {
        try {
            SnareDrum tempSnareDrum = new SnareDrum();
            tempPlayer = MidiSystem.getSequencer();
            tempPlayer.open();

            Sequence seq1 = new Sequence(Sequence.PPQ, 4);

            Track trackSnare = seq1.createTrack();

            MidiEvent snareDrumOnEvent = new MidiEvent(tempSnareDrum.getShortMessageOn(), 1);
            MidiEvent snareDrumOffEvent = new MidiEvent(tempSnareDrum.getShortMessageOff(), 4);

            trackSnare.add(snareDrumOnEvent);
            trackSnare.add(snareDrumOffEvent);

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
        return "SnareDrum snareDrumOn=" + snareDrumOn.getCommand() + ", " +
                snareDrumOn.getChannel() + ", " + snareDrumOn.getData1() + ", "
                + snareDrumOn.getData2() + "\n" + "snareDrumOff=" + 
                snareDrumOff.getCommand() + ", " + snareDrumOff.getChannel() + 
                ", " + snareDrumOff.getData1() + ", " + snareDrumOff.getData2();
    }
}
