package duke.util;

import java.util.ArrayList;

/*
 * NoteList
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * NoteList class defines the behavior of notes
 * management.
 * @author Mario Lorenzo
 */

public class NoteList implements IList<Note> {
    ArrayList<Note> notes;

    /**
     * Constructs a NoteList instance.
     * @param notes The ArrayList of tasks.
     */

    public NoteList(ArrayList<Note> notes) {
        this.notes = notes;
    }

    /**
     * Prints all the notes stored in the ArrayList
     * of the Duke instance.
     */

    public String listNotes() {
        if (notes.size() == 0) {
            return "The list is currently empty. Fill me please!";
        }
        String message = "Here's your notes:\n";
        for (int i = 1; i <= notes.size(); i++) {
            message += String.format("%d. %s", i, notes.get(i - 1));

            if (i != notes.size()) {
                message += "\n";
            }
        }

        return message;
    }

    /**
     * Gets the note of a given index
     * from the list of tasks. The index value is normalized
     * by subtracting the value with 1 since the value starts
     * from 1.
     * @param index The index of the note in the list.
     * @return The Note instance of an index provided by the client.
     */

    private Note getNote(int index) {
        return notes.get(index - 1);
    }

    /**
     * Adds the note to the list.
     * @param note The task that wants to be added to the list.
     */

    public String addNote(Note note, NoteStorage noteStorage) {
        notes.add(note);
        boolean isAppendMode = notes.size() != 1;
        noteStorage.write(note, isAppendMode);
        return "Got it. I've added this task: \n"
                + String.format("    %s\n", note)
                + String.format("Now you have %d note(s) in the list.", notes.size());
    }

    /**
     * Deletes the note of a particular index
     * from the list, then remove it from the file.
     * @param index The index of the task in the list to be deleted.
     */

    public String deleteNote(int index, NoteStorage noteStorage) {
        Note task = getNote(index);
        notes.remove(index - 1);
        noteStorage.rewriteToFile(notes);
        return "Noted. I've removed this note: \n "
                + String.format("    %s\n", task)
                + String.format("Now you have %d note(s) in the list.", notes.size());
    }

    /**
     * Returns the size of the list.
     * @return The size of the list.
     */

    public int size() {
        return this.notes.size();
    }
}
