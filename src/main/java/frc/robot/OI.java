package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AbrirColector;
import frc.robot.commands.ArmPIDControlWithJoystick;
import frc.robot.commands.FexarColector;
import frc.robot.commands.JogarClaw;
import frc.robot.commands.PuxarClaw;

public class OI {
  Joystick stick1 = new Joystick(0);
  Button button = new JoystickButton(stick1, 1);
  Joystick stick2 = new Joystick(0);
  Joystick extreme = new Joystick(1);
  private int lastPov = -1;

  public int getLastPov() {
    return lastPov;
  }

  public void setLastPov(int lastPov) {
    this.lastPov = lastPov;
  }

  public Joystick getExtreme() {
    return extreme;
  }

  public Joystick getJoystick() {
    return stick1;
  }

  public Joystick getJoystick2() {
    return stick2;
  }

  Button buttonClawPuxar = new JoystickButton(stick1, 3);
  Button buttonClawJogar = new JoystickButton(stick1, 4);
  Button buttonColectorAbrir = new JoystickButton(stick1, 5);
  Button buttonColectorFechar = new JoystickButton(stick1, 6);
  Button buttonClawPuxar2 = new JoystickButton(extreme, 4);
  Button buttonClawJogar2 = new JoystickButton(extreme, 6);
  Button buttonColectorAbrir2 = new JoystickButton(extreme, 3);
  Button buttonColectorFechar2 = new JoystickButton(extreme, 5);  /*
  Button buttonSetpoint1 = new JoystickButton(extreme, 7);
  Button buttonSetpoint2 = new JoystickButton(extreme, 8);
  Button buttonSetpoint3 = new JoystickButton(extreme, 9);
  Button buttonSetpoint4 = new JoystickButton(extreme, 10);
  Button buttonSetpoint5 = new JoystickButton(extreme, 11);
  Button buttonSetpoint6 = new JoystickButton(extreme, 12); 

*/
  public OI() {
    stick2.setYChannel(5);
    buttonClawPuxar.whenPressed(new PuxarClaw());
    buttonClawJogar.whenPressed(new JogarClaw());
    buttonColectorAbrir.whenPressed(new AbrirColector());
    buttonColectorFechar.whenPressed(new FexarColector());
    buttonClawPuxar2.whenPressed(new PuxarClaw());
    buttonClawJogar2.whenPressed(new JogarClaw());
    buttonColectorAbrir2.whenPressed(new AbrirColector());
    buttonColectorFechar2.whenPressed(new FexarColector());
    /*
    if (Robot.armToggle) {
      buttonSetpoint6.whenPressed(new ArmGotoPosition(SetPoints.getSetPoints()[0]));
      buttonSetpoint5.whenPressed(new ArmGotoPosition(SetPoints.getSetPoints()[4]));
      buttonSetpoint4.whenPressed(new ArmGotoPosition(SetPoints.getSetPoints()[6]));
      buttonSetpoint3.whenPressed(new ArmGotoPosition(SetPoints.getSetPoints()[9]));
    }
    */
    new Thread(() -> {  // Thread criada para lidar com o debounce do botão
      while (true) { 
        if (Robot.armToggle) {
          if(stick1.getRawButton(2))
            ((ArmPIDControlWithJoystick) Robot.m_PIDarm.getDefaultCommand()).nextSetPoint();
          else if (stick1.getRawButton(1)) 
            ((ArmPIDControlWithJoystick) Robot.m_PIDarm.getDefaultCommand()).previousSetPoint();
          Timer.delay(.1); 
        }        
      }     
    }).start(); 

  }
}
