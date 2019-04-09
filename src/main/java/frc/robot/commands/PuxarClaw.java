package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PuxarClaw extends Command {
  public PuxarClaw() {
    requires(Robot.m_Claw);
    setTimeout(1.0);
  }

  @Override
  protected void initialize() {
    
    }

  @Override
  protected void execute() {
    Robot.m_Claw.puxar();
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.m_Claw.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
