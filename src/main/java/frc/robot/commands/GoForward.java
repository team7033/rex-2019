package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class GoForward extends Command {
  private static double kPLeft = .9;
  private static double kPRight = 1;
  private double m_speed;

  public GoForward(double seconds, double speed) {
    requires(Robot.m_Drive);
    this.m_speed = speed;
    this.setTimeout(seconds);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if(Robot.debug)
    System.out.println("Executing GoForward");
    Robot.m_Drive.drive(this.m_speed*kPRight, -this.m_speed*kPLeft);
  }

  @Override
  protected boolean isFinished() {
    return this .isTimedOut();
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
