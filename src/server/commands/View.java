package server.commands;

import server.Authentication;
import server.Command;
import server.Role;

public class View implements Command {
    @Override
    public String execute(String args) {
        Authentication authentication = new Authentication();
        if (authentication.getRole() != Role.NBD) {

        } else {
            return "You are not a user";
        }
    }
}
