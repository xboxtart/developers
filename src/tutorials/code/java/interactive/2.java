BeamUser user = beam.use(UsersService.class).login("username", "password").get();
pro.beam.interactive.robot.Robot robot = new RobotBuilder()
        .channel(user.channel.id)
        .build(beam, false)
        .get();
