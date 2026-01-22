package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private final TalonFX shooterMotor1;
    private final TalonFX shooterMotor2;

    public Shooter() {
        shooterMotor1 = new TalonFX(ShooterConstants.SHOOTER_MOTOR_ID_1);
        shooterMotor2 = new TalonFX(ShooterConstants.SHOOTER_MOTOR_ID_2);
    }

    public Command setShooterSpeed(double speed) {
        return Commands.run(
                () -> {
                    shooterMotor1.set(speed);
                    shooterMotor2.set(speed);
                },
                this);
    }

    public Command stopShooter() {
        return Commands.run(
                () -> {
                    shooterMotor1.stopMotor();
                    shooterMotor2.stopMotor();
                },
                this);
    }
}
