//*************************************************************************************
//  HiTom.java       Author: Jared Little, Adam Pape, James Oakley
//
//  A class to create create a new hi tom instrument for the drum machine.
//  This basic design was based on the Head First Java tutorial on creating a beat box.
//  It was refined to suit our needs.
//*************************************************************************************
package drummachine;

import javax.sound.midi.*;

/**
 * A class containing MIDI information for the HiTom.  Also contains
 * methods for retrieving this information.
 * @author Jared Little, James Oakley, Adam Pape
 */
public class HiTom implements Instrument {

    private Sequencer tempPlayer;
    private ShortMessage hiTomOn = new ShortMessage();
    private ShortMessage hiTomOff = new ShortMessage();

    public HiTom() {
        try {
            hiTomOn.setMessage(144, 9, 50, 100);
            hiTomOff.setMessage(128, 9, 50, 100);
        } catch (Exception ex) {
            System.out.println("Hi Tom failure");
        }
    }
    
    /**
     * A method to retrieve the ShortMessage on event for the hi tom instrument.
     * @return the on event for the hi tom.
     */
    public ShortMessage getShortMessageOn() {
        return hiTomOn;
    }

    /**
     * A method to retrieve the ShortMessage off event for the hi tom instrument.
     * @return the off event for the hi tom.
     */
    public ShortMessage getShortMessageOff() {
        return hiTomOff;
    }

    /**
     * A method to play a single instance of a hi tom.
     */
    public void playSample() {
        try {

            HiTom tempHiTom = new HiTom();

            tempPlayer = MidiSystem.getSequencer();
            tempPlayer.open();

            Sequence seq1 = new Sequence(Sequence.PPQ, 4);

            Track trackHiTom = seq1.createTrack();

            MidiEvent hiTomOnEvent = new MidiEvent(tempHiTom.getShortMessageOn(), 1);
            MidiEvent hiTomOffEvent = new MidiEvent(tempHiTom.getShortMessageOff(), 4);

            trackHiTom.add(hiTomOnEvent);
            trackHiTom.add(hiTomOffEvent);

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
        return "HiTom hiTomOn=" + hiTomOn.getCommand() + ", " + hiTomOn.getChannel()
                + ", " + hiTomOn.getData1() + ", " + hiTomOn.getData2() + "\n" + "hiTomOff="
                + hiTomOff.getCommand() + ", "+ hiTomOff.getChannel() 
                + ", " + hiTomOff.getData1() + ", " + hiTomOff.getData2();
        
    }
}
