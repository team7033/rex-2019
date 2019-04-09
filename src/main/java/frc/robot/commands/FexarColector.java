package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FexarColector extends Command {
  public FexarColector() {
    // requires(Robot.m_switch);
    requires(Robot.m_Colector);
    setTimeout(2.0);
  }

  @Override
  protected void initialize() {
   Robot.m_Colector.fechar();
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.m_Colector.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
