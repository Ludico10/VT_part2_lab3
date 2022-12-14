package server.commands;

import server.model.Role;
import server.model.StudentInfo;
import server.service.ServiceFactory;

import java.util.List;

public class View implements Command {
    @Override
    public String execute(Object caller, String args) {

        Role role = ServiceFactory.getInstance().getAuthenticationService().getRole(caller);
        if (role != Role.NBD) {
            List<StudentInfo> studentInfos = ServiceFactory.getInstance().getInfoService().getAll();
            StringBuilder result = new StringBuilder();
            result.append("[\n");
            for (var studentInfo : studentInfos) {
                result.append("\t").append(studentInfo.toString()).append("\n");
            }
            result.append("]");
            return result.toString();
        } else {
            return "You are not a user";
        }
    }
}
