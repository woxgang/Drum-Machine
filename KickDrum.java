//*************************************************************************************
//  KickDrum.java       Author: Jared Little, Adam Pape, James Oakley
//
//  A class to create create a new kick drum instrument for the drum machine.
//  This basic design was based on the Head First Java tutorial on creating a beat box.
//  It was refined to suit our needs.
//*************************************************************************************
package drummachine;

import javax.sound.midi.*;

/**
 * A class containing MIDI information for the Kick Drum.  Also contains
 * methods for retrieving this information.
 * @author Jared Little, James Oakley, Adam Pape
 */
public class KickDrum implements Instrument {

    private Sequencer tempPlayer;
    private ShortMessage kickDrumOn = new ShortMessage();
    private ShortMessage kickDrumOff = new ShortMessage();

    public KickDrum() {
        try {
            kickDrumOn.setMessage(144, 9, 35, 100);
            kickDrumOff.setMessage(128, 9, 35, 100);
        } catch (Exception ex) {
            System.out.println("kick drum failure");
        }
    }

    /**
     * A Method to retrieve the ShortMessage on event for the kick drum instrument.
     * @return the on event for the kick drum.
     */
    public ShortMessage getShortMessageOn() {
        return kickDrumOn;
    }
    
    /**
     * A method to retrieve the ShortMessage off event for the kick drum instrument.
     * @return the off event for the kick drum.
     */

    public ShortMessage getShortMessageOff() {
        return kickDrumOff;
    }

    /**
     * A method play a single instance of a kick drum sound.
     */
    public void playSample() {
        try {

            KickDrum tempKickDrum = new KickDrum();
            tempPlayer = MidiSystem.getSequencer();
            tempPlayer.open();

            Sequence seq = new Sequence(Sequence.PPQ, 4);

            Track trackKick = seq.createTrack();

            MidiEvent kickDrumOnEvent = new MidiEvent(tempKickDrum.getShortMessageOn(), 1);
            MidiEvent kickDrumOffEvent = new MidiEvent(tempKickDrum.getShortMessageOff(), 4);

            trackKick.add(kickDrumOnEvent);
            trackKick.add(kickDrumOffEvent);

            tempPlayer.setSequence(seq);

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
        return "KickDrum kickDrumOn=" + kickDrumOn.getCommand() + ", " + kickDrumOn.getChannel()
                + ", " + kickDrumOn.getData1() + ", " + kickDrumOn.getData2() + "/n" +
                "kickDrumOff=" + kickDrumOff.getCommand() + ", " + kickDrumOff.getChannel()
                + ", " + kickDrumOff.getData1() + ", " + kickDrumOff.getData2();
    }
}
