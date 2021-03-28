package Logic;

import Vehicles.Tank;

interface ICommand {
    void execute();
}

class TankLauncher {
    private final ICommand turnRightCommand;
    private final ICommand turnLeftCommand;
    private final ICommand goForwardCommand;
    private final ICommand goBackCommand;
    private final ICommand stopTankCommand;
    private final ICommand goLeftCommand;
    private final ICommand goRightCommand;
    private final ICommand backLeftCommand;
    private final ICommand backRightCommand;


    TankLauncher(ICommand turnRight, ICommand goForward, ICommand turnLeft,
                 ICommand goBack, ICommand stopTank, ICommand goLeft,
                 ICommand goRight, ICommand backLeft, ICommand backRight){
        this.turnRightCommand = turnRight;
        this.goForwardCommand = goForward;
        this.turnLeftCommand = turnLeft;
        this.goBackCommand = goBack;
        this.stopTankCommand = stopTank;
        this.goLeftCommand = goLeft;
        this.goRightCommand = goRight;
        this.backLeftCommand = backLeft;
        this.backRightCommand = backRight;
    }

    void stopTank(){
        stopTankCommand.execute();
    }

    void turnRight(){
        turnRightCommand.execute();
    }

    void turnLeft(){
        turnLeftCommand.execute();
    }

    void goForward(){
        goForwardCommand.execute();
    }

    void goBack(){
        goBackCommand.execute();
    }

    void goLeft(){
        goLeftCommand.execute();
    }

    void goRight(){
        goRightCommand.execute();
    }

    void backLeft(){
        backLeftCommand.execute();
    }

    void backRight(){
        backRightCommand.execute();
    }
}

class TurnRight implements ICommand{
    private final Tank tank;

    TurnRight(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(false, false, false ,true);
    }
}

class TurnLeft implements ICommand{
    private final Tank tank;

    TurnLeft(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(false, false, true ,false);
    }
}

class GoBack implements ICommand{
    private final Tank tank;

    GoBack(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(true, true, false ,false);
    }
}

class StopTank implements ICommand{
    private final Tank tank;

    StopTank(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(false, false, false ,false);
    }
}

class GoForward implements ICommand{
    private final Tank tank;

    GoForward(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute(){
        tank.setCanMove(true, false, false, false);
    }
}

class GoLeft implements ICommand{
    private final Tank tank;

    GoLeft(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(true, false, true, false);
    }
}

class GoRight implements ICommand{
    private final Tank tank;

    GoRight(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(true, false, false, true);
    }
}

class BackLeft implements ICommand{
    private final Tank tank;

    BackLeft(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(true, true, true, false);
    }
}

class BackRight implements ICommand{
    private final Tank tank;

    BackRight(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(true, true, false, true);
    }
}