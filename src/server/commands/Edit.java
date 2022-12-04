package server.commands;

import server.model.Role;
import server.service.ServiceFactory;

public class Edit implements Command{

    @Override
    public String execute(Object caller, String args) {
        String[] arguments = args.split(" ");
        if (arguments.length == 3) {
            return "Unable to execute command EDIT with such params";
        }

        if (ServiceFactory.getInstance().getAuthenticationService().getRole(caller) != Role.ADMIN) {
            return "You should have admin rights to execute this command";
        }

        int id;
        try {
            id = Integer.parseInt(arguments[1]);
        }catch (NumberFormatException e) {
            return "Invalid id";
        }
        if (!ServiceFactory.getInstance().getInfoService().contains(id)) {
            return "Student information not found";
        }
        ServiceFactory.getInstance().getInfoService().edit(id, arguments[2], arguments[3]);
        return "Complete";
    }
}
