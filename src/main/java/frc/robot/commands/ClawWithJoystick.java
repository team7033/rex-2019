package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClawWithJoystick extends Command {
  public ClawWithJoystick() {
    requires(Robot.m_Claw);
  }

  protected void initialize() {
  }

  protected void execute() {
    Robot.m_Claw.run(Robot.m_oi.getJoystick());
  }

  protected boolean isFinished() {
      return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
