package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmControlWithJoystick extends Command {

  public ArmControlWithJoystick() {
    requires(Robot.m_armSub);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (!Robot.armToggle && Robot.m_oi.getExtreme().getRawButton(2)) {
      if (Robot.debug) 
        System.out.println("Robot arm: manual control=" + 
        -Robot.m_oi.getExtreme()
        .getY()/2.0 + (Robot.m_oi.getExtreme().getThrottle() / 10));

      Robot.m_armSub.set(-Robot.m_oi.getExtreme()
      .getY()/2.0 + (Robot.m_oi.getExtreme().getThrottle() / 10));
    }
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
