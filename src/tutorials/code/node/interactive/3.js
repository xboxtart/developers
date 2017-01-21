function createRobot(res, channelId) {
    return new Interactive.Robot({
        remote: res.body.address,
        channel: channelId,
        key: res.body.key,
    });
}
