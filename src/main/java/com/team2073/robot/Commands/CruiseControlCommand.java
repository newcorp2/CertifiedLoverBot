package com.team2073.robot.Commands;

import com.revrobotics.CANSparkMax;
import com.team2073.common.command.AbstractLoggingCommand;
import com.team2073.robot.ApplicationContext;
import com.team2073.robot.Subsystems.SimpleSubsystem;

public class CruiseControlCommand extends AbstractLoggingCommand {
    ApplicationContext appCTX = ApplicationContext.getInstance();
    SimpleSubsystem simpleSubsystem = appCTX.getSimpleSubsystem();
    private final CANSparkMax motor = appCTX.getMotor();

    protected void cruiseSet () {
        simpleSubsystem.setCurrentState(SimpleSubsystem.SimpleSubsystemState.CRUISE_SET);
    }

    @Override
    protected void initializeDelegate() {
        cruiseSet();
        simpleSubsystem.setCurrentState(SimpleSubsystem.SimpleSubsystemState.CRUISE);
    }

    @Override
    protected void endDelegate() {
        simpleSubsystem.setCurrentState(SimpleSubsystem.SimpleSubsystemState.AXIS);
    }

    @Override
    protected boolean isFinishedDelegate() {
        return false;
    }
}

