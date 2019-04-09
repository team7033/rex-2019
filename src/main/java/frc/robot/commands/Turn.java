package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Turn extends Command {
  private double m_angle;
  public Turn(double angle) {
    requires(Robot.m_Drive);
    this.m_angle = angle;
    this.setTimeout(1.8);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if(Robot.debug)
    System.out.println("Executing Turn");
    if (m_angle == 90)
      Robot.m_Drive.drive(.7, -.3);
  }

  @Override
  protected boolean isFinished() {
    return this.isTimedOut();
  }

  @Override
  protected void end() {
    Robot.m_Drive.drive(0, 0);
  }

  @Override
  protected void interrupted() {
    Robot.m_Drive.drive(0, 0);
  }
}
