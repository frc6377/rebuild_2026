package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.intake.IntakeConstants.RollerConstants;

public class IntakeRollerSubsystem extends SubsystemBase {
    private static IntakeIO intakeMotor;

    public IntakeRollerSubsystem(IntakeIO intakeIO) {
        intakeMotor = intakeIO;
    }

    public Command intakeCommand() {
        return startEnd(() -> intakeMotor.setSpeed(RollerConstants.INTAKE_SPEED), () -> intakeMotor.setSpeed(0));
    }

    public Command outtakeCommand() {
        return startEnd(() -> intakeMotor.setSpeed(RollerConstants.OUTAKE_SPEED), () -> intakeMotor.setSpeed(0));
    }

    @Override
    public void periodic() {
        intakeMotor.periodic();
    }
}
