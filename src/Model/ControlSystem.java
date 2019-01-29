package Model;

import Controller.ServerController;

public class ControlSystem {
    private static ControlSystem controlSystem = new ControlSystem();
    private ServerController serverController;

    private ControlSystem() {

    }

    public static ControlSystem getControlSystem() {
        return controlSystem;
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    public ServerController getServerController() {
        return serverController;
    }
}
