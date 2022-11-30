package server.commands;

public interface Command {
    String execute(Object caller, String args);
}
