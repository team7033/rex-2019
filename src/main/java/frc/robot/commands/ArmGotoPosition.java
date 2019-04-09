package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmGotoPosition extends Command {

  private double kDefaultPosition = 95;
  private double kInitialPosition = 0;
  private double m_angle;

  public ArmGotoPosition(double angle) {
    this.m_angle = angle;
    setTimeout(5.0);
  }

  @Override
  protected void execute() {
    if(Robot.debug)
    System.out.println("Executing ArmGotoPosition");
    ((ArmPIDControlWithJoystick) Robot.m_PIDarm.getDefaultCommand()).setM_angle(this.m_angle);
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut() || Robot.m_PIDarm.onTarget();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
