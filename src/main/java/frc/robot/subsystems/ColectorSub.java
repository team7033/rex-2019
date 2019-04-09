package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ColectorSub extends Subsystem {
  private Victor Colector = new Victor(RobotMap.colector);

  @Override
  public void initDefaultCommand() {
  }

  public void abrir(){
    Colector.set(0.5);
  }
  
  public void fechar(){
    Colector.set(-0.5);
  }

  public void stop(){
    Colector.set(0.0);
  }

}
