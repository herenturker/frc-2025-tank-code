package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveCmd;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Joystick;
import java.util.function.DoubleSupplier;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final Drivetrain m_drivetrain = new Drivetrain();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public final Joystick joystick1 = new Joystick(OperatorConstants.kDriverControllerPort);

  public final Trigger leftTrigger = new Trigger(() -> joystick1.getRawAxis(2) > 0.5); // Backward
  public final Trigger rightTrigger = new Trigger(() -> joystick1.getRawAxis(3) > 0.5); // Forward

  public final POVButton robotGoLeftPOV = new POVButton(joystick1, 270);
  public final JoystickButton robotGoRightButton = new JoystickButton(joystick1, OperatorConstants.goRightButton);

  // DoubleSuppliers for forward and rotation inputs
  private final DoubleSupplier forwardSupplier = new DoubleSupplier() {
    @Override
    public double getAsDouble() {
      return joystick1.getRawAxis(Constants.OperatorConstants.kJoystickSpeedAxis); // Forward/Backward axis
      // -() yapabilirsin ?
    }
  };

  private final DoubleSupplier rotationSupplier = new DoubleSupplier() {
    @Override
    public double getAsDouble() {
      return joystick1.getRawAxis(Constants.OperatorConstants.kJoystickRotationAxis); // Rotation axis
    }
  };

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Set the default command for the drivetrain
    m_drivetrain.setDefaultCommand(new DriveCmd(m_drivetrain, forwardSupplier, rotationSupplier));

    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    // Bind buttons or triggers to commands if necessary
    leftTrigger.whileTrue(new DriveCmd(m_drivetrain, 2));
    rightTrigger.whileTrue(new DriveCmd(m_drivetrain, 1));
    robotGoRightButton.whileTrue(new DriveCmd(m_drivetrain, 4));
    robotGoLeftPOV.whileTrue(new DriveCmd(m_drivetrain, 3));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }

  public Drivetrain getDrivetrain() {
    return m_drivetrain;
  }
}
