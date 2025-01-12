package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.Subsystem;

public class IntakeSubsystem implements Subsystem {

    private final double intakePower = 1;
    private final double armPower = 0.5;

    private IntakeChassis chassis;
    private IntakeInputs inputs;

    public IntakeSubsystem(IntakeChassis chassis, IntakeInputs inputs){
        this.chassis = chassis;
        this.inputs = inputs;
    }

    @Override
    public void periodic() {}

    // Stops all two motors in the arm
    public void stopAll() {
        chassis.getArm().set(0);
        chassis.getIntake().set(0);
    }

    public void stopElevator() {
        chassis.getArm().set(0);
    }

    public void stopArm() {
        chassis.getIntake().set(0);
    }

    public void intakeIn() {
        chassis.getIntake().set(intakePower);
    }

    public void intakeOut() {
        chassis.getIntake().set(0.35);

    }

    public void armUp() {
        chassis.getArm().set(armPower);
    }

    public void armDown() {
        chassis.getArm().set(-armPower);
    }

    public void setPowers() {
        // utilize each function based on inputs

        // arm / elevator
        boolean elevatorUp = inputs.elevatorUp.get();
        boolean elevatorDown = inputs.elevatorDown.get();

        // intake
        boolean armUp = inputs.armUp.get();
        boolean armDown = inputs.armDown.get();

        if (elevatorUp) {
            armUp();
        }
        else if (elevatorDown) {
            armDown();
        }
        else {
            stopElevator();
        }

        if (armUp) {
            intakeIn();
        }
        else if (armDown) {
            intakeOut();
        }
        else {
            stopArm();
        }
    }
}