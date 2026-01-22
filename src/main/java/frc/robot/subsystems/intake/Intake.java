package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.intake.IntakeConstants.RollerConstants;

public class Intake extends SubsystemBase {
    private static IntakeIO intake;
    private static IntakeIO.IntakeIOInputs inputs;

    public Intake(IntakeIO intakeIO) {
        intake = intakeIO;
        inputs = new IntakeIO.IntakeIOInputs();
    }

    public static void intakeCommand() {
        intake.setRollerSpeed(RollerConstants.INTAKE_SPEED);
    }

    public static void outtakeCommand() {
        intake.setRollerSpeed(RollerConstants.OUTAKE_SPEED);
    }

    @Override
    public void periodic() {
        intake.updateInputs(inputs);
    }
}
