package server.commands;

import server.model.Role;
import server.service.ServiceFactory;

public class Authenticate implements Command{
    @Override
    public String execute(Object caller, String args){
        String[] arguments = args.split(" ");
        if (arguments.length != 2) {
            return "Unable to execute command AUTH with such params";
        }
        Role role;
        try {
            role = Role.valueOf(arguments[1]);
        }catch (IllegalArgumentException e) {
            return "Undefined role";
        }
        ServiceFactory.getInstance().getAuthenticationService().addUser(caller, role);
        return "Complete";
    }
}
