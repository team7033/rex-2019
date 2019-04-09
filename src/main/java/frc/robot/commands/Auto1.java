package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto1 extends CommandGroup {

  public Auto1() {
    addParallel(new FexarColector());
    addSequential(new ArmGotoPosition(200));

    addParallel(new ArmGotoPosition(100));
    addSequential(new GoForward(2, 0.5));

    addSequential(new ArmGotoPosition(30));
    addSequential(new Turn(90));
    addSequential(new GoForward(2, 0.3));
  }
}
