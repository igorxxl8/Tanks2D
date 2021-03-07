package Logic;

import Vehicles.Tank;

interface ICommand {
    void execute();
}

class TankLauncher {
    private ICommand turnRightCommand;
    private ICommand turnLeftCommand;
    private ICommand goForwardCommand;
    private ICommand goBackCommand;
    private ICommand stopTankCommand;
    private ICommand goLeftCommand;
    private ICommand goRightCommand;
    private ICommand backLeftommand;
    private ICommand backRightCommand;


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
        this.backLeftommand = backLeft;
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
        backLeftommand.execute();
    }

    void backRight(){
        backRightCommand.execute();
    }
}

class TurnRight implements ICommand{
    private Tank tank;

    TurnRight(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(false, false, false ,true);
    }
}

class TurnLeft implements ICommand{
    private Tank tank;

    TurnLeft(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(false, false, true ,false);
    }
}

class GoBack implements ICommand{
    private Tank tank;

    GoBack(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(true, true, false ,false);
    }
}

class StopTank implements ICommand{
    private Tank tank;

    StopTank(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(false, false, false ,false);
    }
}

class GoForward implements ICommand{
    private Tank tank;

    GoForward(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute(){
        tank.setCanMove(true, false, false, false);
    }
}

class GoLeft implements ICommand{
    private Tank tank;

    GoLeft(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(true, false, true, false);
    }
}

class GoRight implements ICommand{
    private Tank tank;

    GoRight(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(true, false, false, true);
    }
}

class BackLeft implements ICommand{
    private Tank tank;

    BackLeft(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(true, true, true, false);
    }
}

class BackRight implements ICommand{
    private Tank tank;

    BackRight(Tank tank){
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.setCanMove(true, true, false, true);
    }
}