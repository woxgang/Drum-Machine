//*************************************************************************************
//  DrumMachine.java       Author: Jared Little, Adam Pape, James Oakley
//
//  A class to create the MIDI functionality for the drum machine.
//  This basic design was based on the Head First Java tutorial on creating a beat box.
//  It was refined to suit our needs.
//*************************************************************************************
package drummachine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

/**
 * A class that creates a fully functional drum machine utilizing the Java MIDI API. Includes all
 * needed MIDI workings, instrument objects, and methods needed to manipulate this data.
 * @author Jared Little, James Oakley, Adam Pape
 */
public class DrumMachine {

    private final int HORZCHECKS = 16;
    private final int VERTCHECKS = 7;
    private Sequencer player;
    private Sequence seq1;
    private ArrayList<Instrument> instrumentList;
    private ArrayList<Track> trackList;
    private Track kickTrack;
    private Track snareTrack;
    private Track hihatTrack;
    private Track hiTomTrack;
    private Track lowTomTrack;
    private Track rideCymTrack;
    private Track crashCymTrack;
    private Track metaTrack;

    /**
     * The DrumMachine constructor instantiates a new MIDI sequencer, sequence,
     * instruments, and the ArrayList that stores the instrument objects. It also
     * adds a <code>MetaEventListener</code> that will listen for specific MIDI
     * events and perform an action upon coming across one.
     */
    public DrumMachine() {
        KickDrum kickDrum = new KickDrum();
        SnareDrum snareDrum = new SnareDrum();
        Hihat hiHat = new Hihat();
        HiTom hiTom = new HiTom();
        LowTom lowTom = new LowTom();
        RideCymbal rideCymbal = new RideCymbal();
        CrashCymbal crashCymbal = new CrashCymbal();

        instrumentList = new ArrayList<Instrument>();
        instrumentList.add(kickDrum);
        instrumentList.add(snareDrum);
        instrumentList.add(hiHat);
        instrumentList.add(hiTom);
        instrumentList.add(lowTom);
        instrumentList.add(rideCymbal);
        instrumentList.add(crashCymbal);

        try {
            player = MidiSystem.getSequencer();
            player.open();
            player.setTempoInBPM(120);
            seq1 = new Sequence(Sequence.PPQ, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        player.addMetaEventListener(new MetaEventListener() {
            public void meta(MetaMessage msg) {
                MetaMessagePerformed(msg);
            }
        });
    }

    /**
     * Orders the seqeuncer to play the stored sequence.
     */
    public void playSequence() {
        try {
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the playing sequencer and returns it to the zero position. 
     */
    public void stopSequencer() {
        DrumMachineView.getTickIndicatorList().get((int) player.getTickPosition()).setSelected(false);

        player.stop();
        player.setTickPosition(0);
    }

    /**
     * Creates the completed <code>Sequence</code> that the <code>Sequencer</code> will play. 
     * Gets the status of all checkboxes from the <code>DrumMachineView</code> class and uses
     * this data to create an appropriate MIDI sequence by calling methods to initialize the needed tracks and
     * fill them with <code>MidiEvents</code> corresponding to the state of the checkboxes.
     * Lastly this method takes the now completed <code>Sequence</code> and sets it in the
     * <code>Sequencer</code>, ready to play. The core algorithm was taken from an
     * example in the book Head First Java and modified to suit our needs.
     */
    public void createSequence() {
        boolean[] checkBoxStatus = null;
        int currentRow = 0;
        createTracks();

        for (int i = 0; i < VERTCHECKS; i++) {
            checkBoxStatus = new boolean[HORZCHECKS];

            for (int j = 0; j < HORZCHECKS; j++) {
                JCheckBox jc = (JCheckBox) DrumMachineView.getCheckboxList().get(j + (HORZCHECKS * i));

                if (jc.isSelected()) {
                    checkBoxStatus[j] = true;
                } else {
                    checkBoxStatus[j] = false;
                }
            }
            addMidiEventsToTracks(checkBoxStatus, currentRow);
            currentRow += 1;

            trackList.get(i).add(makeMidiEvent(176, 1, 127, 0, HORZCHECKS));
        }
        for (int i = 0; i < VERTCHECKS; i++) {
            trackList.get(i).add(makeMidiEvent(192, 9, 1, 0, 15));
        }

        makeMetaMessages();

        try {
            player.setSequence(seq1);
            player.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 
     * Instantiates needed tracks and adds them to the highly important <code>trackList</code>
     */
    public void createTracks() {
        seq1.deleteTrack(kickTrack);
        seq1.deleteTrack(snareTrack);
        seq1.deleteTrack(hihatTrack);
        seq1.deleteTrack(hiTomTrack);
        seq1.deleteTrack(lowTomTrack);
        seq1.deleteTrack(rideCymTrack);
        seq1.deleteTrack(crashCymTrack);

        kickTrack = seq1.createTrack();
        snareTrack = seq1.createTrack();
        hihatTrack = seq1.createTrack();
        hiTomTrack = seq1.createTrack();
        lowTomTrack = seq1.createTrack();
        rideCymTrack = seq1.createTrack();
        crashCymTrack = seq1.createTrack();

        trackList = new ArrayList<Track>();
        trackList.clear();
        trackList.add(kickTrack);
        trackList.add(snareTrack);
        trackList.add(hihatTrack);
        trackList.add(hiTomTrack);
        trackList.add(lowTomTrack);
        trackList.add(rideCymTrack);
        trackList.add(crashCymTrack);
    }

    /**
     * Adds MIDI events to each track via a loop. Calls the method <code>makeMidiEvent</code>
     * which constructs the <code>MidiEvents</code> which are then added to the track.
     * Works on one track at a time.
     * @param checkBoxStatus the status of the each checkbox in the form of a 
     * boolean array.
     * @param currentRow the curren instrument index/checkbox row.
     */
    public void addMidiEventsToTracks(boolean[] checkBoxStatus, int currentRow) {

        for (int i = 0; i < HORZCHECKS; i++) {
            boolean checkBox = checkBoxStatus[i];

            if (checkBox == true) {
                trackList.get(currentRow).add(makeMidiEvent(instrumentList.get(currentRow).getShortMessageOn(), i));
                trackList.get(currentRow).add(makeMidiEvent(instrumentList.get(currentRow).getShortMessageOff(), i + 1));
            }
        }
    }

    /**
     * A method for creating a MidiEvent using a pre-made <code>ShortMessage.</code> In our
     * program the <code>ShortMessage</code> comes from the specified instrument
     * class.
     * @param shortMessage the ShortMessage to be put into the MidiEvent.
     * @param tick the desired musical beat or "tick" location. Determines where 
     * on the track the MidiEvent will go.
     * MidiEvent will go.
     * @return the completed MidiEvent
     */
    public MidiEvent makeMidiEvent(ShortMessage shortMessage, int tick) {
        MidiEvent event = null;
        event = new MidiEvent(shortMessage, tick);
        return event;
    }

    /**
     * A method for creating a MidiEvent through first creating a <code>ShortMessage</code>
     * using given parameters and then adding it to the <codeMidiEvent</code>.
     * Creates a <code>ShortMessage</code> using parameters
     * which are then put into the final MidiEvent
     * @param comd the command int to be put in the <code>ShortMessage</code>
     * @param chan the channel int to be put in the <code>ShortMessage</code>
     * @param one the first data int to be put in the <code>ShortMessage</code>
     * @param two the second data int to be put in the <code>ShortMessage</code>
     * @param tick the tick to be put in the <code>MidiEvent</code>
     * @return the completed <code>MidiEvent</code>
     * 
     */
    public MidiEvent makeMidiEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;

        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    /**
     * Creates series of <code>MetaMessages</code> which are then added to the 
     * <code>metaTrack</code>. One <code>MetaMessage</code> per tick.
     */
    public void makeMetaMessages() {
        MidiMessage meta = new MetaMessage();
        MidiEvent me;
        seq1.deleteTrack(metaTrack);
        metaTrack = seq1.createTrack();

        for (int i = 0; i < HORZCHECKS; i++) {
            me = new MidiEvent(meta, i);
            metaTrack.add(me);
        }
    }

    /**
     * Dynamically changes the tempo of the sequence using <code>setTempoFactor</code>
     * @param bpm the integer recieved from the <code>tempoSpinner</code> GUI object.
     * Represents the track's tempo in beats per a minute.
     */
    public void changeTempo(int bpm) {
        float factor = (float) bpm / 120;

        player.setTempoFactor(factor);
    }

    /**
     * Mutes a instrument on a given track.
     * @param trackNum the index of the track stored in trackList to be muted
     */
    public void muteInst(int trackNum) {
        player.setTrackMute(trackNum, true);
        boolean muted = player.getTrackMute(trackNum);
        if (!muted) {
            return;
        }
    }

    /**
     * UnMutes an instrument on a given track
     * @param trackNum the sequence track number to be muted
     */
    public void unMuteInst(int trackNum) {
        player.setTrackMute(trackNum, false);
        boolean muted = player.getTrackMute(trackNum);
        if (!muted) {
            return;
        }
    }

    /**
     * Solos an instrument on a given track. 
     * @param trackNum the sequence track number to be muted
     */
    public void soloInst(int trackNum) {
        player.setTrackSolo(trackNum, true);
        player.setTrackSolo(7, true);
        
        boolean soloed = player.getTrackSolo(trackNum);
        if (!soloed) {
            return;
        }
    }

    /**
     * UnSolos an instrument on a given track.
     * @param trackNum the sequence track number to be muted
     */
    public void unSoloInst(int trackNum) {
        player.setTrackSolo(trackNum, false);
        player.setTrackSolo(7, false);
        
        boolean soloed = player.getTrackSolo(trackNum);
        if (!soloed) {
            return;
        }
    }

    /**
     * A method for saving a drum pattern. Specifically the current state of 
     * the checkboxes is saved by serializing a representative boolean array.  
     */
    public void savePattern() {

        boolean[] checkboxState = new boolean[112];


        for (int i = 0; i < 112; i++) {

            System.out.println(DrumMachineView.getCheckboxList().size());

            JCheckBox check = DrumMachineView.getCheckboxList().get(i);

            if (check.isSelected()) {
                checkboxState[i] = true;
            }
        }

        try {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Pattern");

            File f = new File(".ser");
            fileChooser.setSelectedFile(f);
            System.out.println(f.toString());

            int saveSequence = fileChooser.showSaveDialog(null);

            if (saveSequence == JFileChooser.APPROVE_OPTION) {

                File saveFile = fileChooser.getSelectedFile();

                FileOutputStream fileStream = new FileOutputStream(saveFile);
                ObjectOutputStream os = new ObjectOutputStream(fileStream);
                os.writeObject(checkboxState);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    /**
     * A method for loading a saved beat pattern.  Accepts a serialized boolean
     * array and from that modifies the current state of the checkboxes.
     */
    public void loadPattern() {
        boolean[] checkboxState = null;

        try {
            JFileChooser fileChooser2 = new JFileChooser();
            fileChooser2.setDialogTitle("Load Pattern");
            int loadSequence = fileChooser2.showOpenDialog(null);

            File loadFile = fileChooser2.getSelectedFile();

            FileInputStream fileIn = new FileInputStream(loadFile);
            ObjectInputStream is = new ObjectInputStream(fileIn);
            checkboxState = (boolean[]) is.readObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < 112; i++) {

            JCheckBox check = DrumMachineView.getCheckboxList().get(i);

            if (checkboxState[i]) {

                check.setSelected(true);
            } else {

                check.setSelected(false);
            }
        }
        player.stop();
        createSequence();
    }

    /**
     * Saves a beat pattern by writing the current sequence to a MIDI file.
     */
    public void savePatternAsMidi() {
        seq1.deleteTrack(metaTrack);

        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Sequence");
            File f = new File(".mid");
            fileChooser.setSelectedFile(f);
            int saveSequence = fileChooser.showSaveDialog(null);

            if (saveSequence == JFileChooser.APPROVE_OPTION) {
                File saveFile = fileChooser.getSelectedFile();
                MidiSystem.write(seq1, 1, saveFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the current playing status of the sequencer.  True for playing and
     * false for not playing.
     * @return the playing status of the Sequencer
     */
    public boolean getSequencerPlayingStatus() {
        return player.isRunning();
    }

    /**
     * A method called by the <code>MetaEventListener</code> when the sequencer
     * processes a <code>MetaMessage</code>. Modifies the <code>tickIndicator</code>
     * buttons in the <code>DrumMachineView</code> class.
     * @param msg 
     */
    private void MetaMessagePerformed(MetaMessage msg) {
        DrumMachineView.getTickIndicatorList().get((int) player.getTickPosition()).setSelected(true);
        if (player.getTickPosition() != 0) {
            DrumMachineView.getTickIndicatorList().get((int) player.getTickPosition() - 1).setSelected(false);
        }
        if (player.getTickPosition() == 0) {
            DrumMachineView.getTickIndicatorList().get(15).setSelected(false);
        }

    }
}