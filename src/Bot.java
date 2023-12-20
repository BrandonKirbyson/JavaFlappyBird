public final class Bot {
    /**
     * @return if the bot should jump
     */
    public static boolean getJump(Bird bird, Obstacles obs) {
        return bird.getY() > obs.getCurrentPipe().getGapY();
    }
}
