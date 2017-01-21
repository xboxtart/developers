package pro.beam.interactive.example;

import pro.beam.api.BeamAPI;
import pro.beam.api.resource.BeamUser;
import pro.beam.api.services.impl.UsersService;
import pro.beam.interactive.net.packet.Protocol;
import pro.beam.interactive.robot.RobotBuilder;

import java.awt.*;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws AWTException {
        BeamAPI beam = new BeamAPI();
        Robot controller = new Robot();
        try {
            BeamUser user = beam.use(UsersService.class).login("username", "password").get();
            pro.beam.interactive.robot.Robot robot = new RobotBuilder()
                    .channel(user.channel.id)
                    .build(beam, false)
                    .get();

            robot.on(Protocol.Report.class, report -> {
                // If we have any joysticks in the report
                if (report.getJoystickCount() > 0) {
                    // Get the coordMean from the joystick
                    Protocol.Coordinate coordMean = report.getJoystick(0).getCoordMean();
                    Point mousePosition = MouseInfo.getPointerInfo().getLocation();

                    // Apply it to the current mouse position, if its values are not NaN
                    if (!Double.isNaN(coordMean.getX()) && !Double.isNaN(coordMean.getY())) {
                        controller.mouseMove(
                            ((int) (mousePosition.getX() + 300 * coordMean.getX())),
                            ((int) (mousePosition.getY() + 300 * coordMean.getY()))
                        );
                    }
                }
            });

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
