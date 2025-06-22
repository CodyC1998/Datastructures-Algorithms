package com.keyin;

public class Main {
    public static void main(String[] args) {
        UndoRedoManager<String> manager = new UndoRedoManager<>();

        manager.addState("State 1");
        manager.addState("State 2");
        manager.addState("State 3");

        manager.undo(); // back to State 2
        manager.undo(); // back to State 1
        manager.redo(); // forward to State 2
        System.out.println("Final state: " + manager.getCurrentState());
    }
}
