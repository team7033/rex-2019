package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class JogarClaw extends Command {
  public JogarClaw() {
    requires(Robot.m_Colector);
    setTimeout(1.0);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_Claw.jogar();
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
