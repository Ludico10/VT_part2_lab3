package server.commands;

import server.model.Role;
import server.service.ServiceFactory;

public class Create implements Command{
    @Override
    public String execute(Object caller, String args) {
        String[] arguments = args.split(" ");
        if (arguments.length != 3) {
            return "Unable to execute command CREATE with such params";
        }

        if (ServiceFactory.getInstance().getAuthenticationService().getRole(caller) != Role.ADMIN) {
            return "You should have manager rights to execute this command";
        }

        ServiceFactory.getInstance().getInfoService().add(arguments[1], arguments[2]);
        return "Complete";
    }
}
