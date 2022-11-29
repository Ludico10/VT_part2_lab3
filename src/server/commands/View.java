package server.commands;

import server.Authentication;
import server.Command;
import server.model.Role;

public class View implements Command {
    @Override
    public String execute(String args) {
        Authentication authentication = new Authentication();
        if (authentication.getRole() != Role.NBD) {

            var result = new StringBuilder();
            result.append("[\n");
            for (var studentCase : studentCases) {
                result.append("\t").append(studentCase.toString()).append("\n");
            }
            result.append("]");
            return result.toString();
        } else {
            return "You are not a user";
        }
    }
}
