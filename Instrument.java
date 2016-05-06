//*************************************************************************************
//  Instrument.java       Author: Jared Little, Adam Pape, James Oakley
//
//  An interface to implement MIDI functionality for the drum machine.
//  This basic design was based on the Head First Java tutorial on creating a beat box.
//  It was refined to suit our needs.
//*************************************************************************************
package drummachine;

import javax.sound.midi.*;

/**
 * An interface that sets implementation standards for implementing instrument
 * classes and allows for polymorphism.
 * @author Jared Little, James Oakley, Adam Pape
 */
public interface Instrument {

    /**
     * Plays one note of the instrument.
     */
    void playSample();

    /**
     * Gets the instrument's unique <code>ShortMessage</code> on data.
     * @return the instrument's <code>ShortMessage</code> on data.
     */
    ShortMessage getShortMessageOn();

    /**
     * Gets the instrument's unique <code>ShortMessage</code> off data.
     * @return the instrument's <code>ShortMessage</code> off data.
     */
    ShortMessage getShortMessageOff();
}
