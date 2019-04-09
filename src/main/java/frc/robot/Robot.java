package frc.robot;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.arm.ArmPIDSystem;
import frc.robot.subsystems.arm.ArmSub;
import frc.robot.subsystems.arm.ArmSwitch;
import frc.robot.commands.Auto1;
import frc.robot.subsystems.ClawSub;
import frc.robot.subsystems.ColectorSub;
import frc.robot.subsystems.TankDrive;

public class Robot extends TimedRobot {

  Robot() {
    super(0.02);  // Tempo periódico, padrao = 0.02
  }

  public static boolean camera = true;
  public static boolean debug = true;
  public static boolean armToggle = true;  // Habilita ou desabilita os subsistemas do braço com PID 

  Accelerometer accel;
  public static OI m_oi;
  public static TankDrive m_Drive;
  public static ClawSub m_Claw;
  public static ColectorSub m_Colector;
  public static ArmPIDSystem m_PIDarm;
  public static ArmSwitch m_switch;
  public static ArmSub m_armSub;
  public Potentiometer pot = new AnalogPotentiometer(0, 330);

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  NetworkTableEntry armToggleEntry, debugToggleEntry;

  @Override
  public void robotInit() {
    
    m_switch = new ArmSwitch();
    SpeedControllerGroup armMotors = new SpeedControllerGroup(
      new Victor(RobotMap.kArm1), 
      new Victor(RobotMap.kArm2));
    
    //if (armToggle)
      m_PIDarm = new ArmPIDSystem(armMotors, pot);
    //else 
      m_armSub = new ArmSub(armMotors, pot);

    m_Drive = new TankDrive();
    m_Claw = new ClawSub();
    m_Colector = new ColectorSub();
    m_oi = new OI();

    if (camera) CameraServer.getInstance().startAutomaticCapture();

    accel = new BuiltInAccelerometer(); 
    accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);
    m_chooser.addOption("Auto 1", new Auto1()); 

    SmartDashboard.putData("Auto mode", m_chooser);

    armToggleEntry = Shuffleboard.getTab("Tab").add("Arm Toggle",true)
    .withWidget(BuiltInWidgets.kToggleButton).getEntry();

    debugToggleEntry = Shuffleboard.getTab("Tab").add("Debug",true)
    .withWidget(BuiltInWidgets.kToggleButton).getEntry();
  }

  @Override
  public void robotPeriodic() {
    Robot.armToggle = armToggleEntry.getBoolean(true);
    Robot.debug = debugToggleEntry.getBoolean(false);

    double xVal = accel.getX();
    double yVal = accel.getY();
    double zVal = accel.getZ();
    SmartDashboard.putNumber("x", xVal);
    SmartDashboard.putNumber("y", yVal);
    SmartDashboard.putNumber(" ", zVal);
    if (armToggle) {
      SmartDashboard.putNumber("SetPoint", Robot.m_PIDarm.getSetpoint());
      SmartDashboard.putNumber("AnguloArm", Robot.m_PIDarm.getPosition());

    }
    
  }

  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    Scheduler.getInstance().run();
    m_autonomousCommand = m_chooser.getSelected();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    debug = true;
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}
