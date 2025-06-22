package com.keyin;

public class UndoRedoManager<T> {
    private class Node {
        T data;
        Node prev;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private Node current;

    public void addState(T newState) {
        Node newNode = new Node(newState);
        if (current != null) {

            current.next = null;
            newNode.prev = current;
            current.next = newNode;
        } else {
            head = newNode;
        }
        current = newNode;
        System.out.println("Added state: " + newState);
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo: Current state is now " + current.data);
        } else {
            System.out.println("Undo not available.");
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo: Current state is now " + current.data);
        } else {
            System.out.println("Redo not available.");
        }
    }

    public T getCurrentState() {
        return current != null ? current.data : null;
    }
}