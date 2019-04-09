package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class TankDriveWithJoystick extends Command {
  public TankDriveWithJoystick() {
    requires(Robot.m_Drive);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_Drive.drive(Robot.m_oi.getJoystick2(), Robot.m_oi.getJoystick());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
