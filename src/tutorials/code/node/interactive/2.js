const beam = new Beam();

beam.use('password', {
    username,
    password,
}).attempt().then(res => {
    const channelId = res.body.channel.id;

    return beam.game.join(channelId)
        .then(res => createRobot(res, channelId))
        .then(robot => performRobotHandShake(robot))
        .then(robot => setupRobotEvents(robot));
}).catch(err => {
    if (err.res) {
        throw new Error('Error connecting to Interactive: ' + err.res.body.mesage);
    }
    throw err;
});
