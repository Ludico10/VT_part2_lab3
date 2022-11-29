package server.commands;

import server.service.impl.AuthenticationImpl;
import server.model.Role;

public class View implements Command {
    @Override
    public String execute(String args) {
        AuthenticationImpl authenticationImpl = new AuthenticationImpl();
        if (authenticationImpl.getRole() != Role.NBD) {

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
