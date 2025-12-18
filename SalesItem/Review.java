public class Review {
    private String reviewer;
    private String content;
    private int stars;
    private int helpfulVotes;

    public Review(String reviewer, String content, int stars) {
        this.reviewer = reviewer;
        this.content = content;
        this.stars = stars;
        this.helpfulVotes = 0;
    }

    public String getReviewer() {
        return reviewer;
    }

    public int getHelpfulVotes() {
        return helpfulVotes;
    }

    public void upvote() {
        helpfulVotes++;
    }

    public void downvote() {
        helpfulVotes--;
    }

    public String getDetailString() {
        StringBuilder starsLine = new StringBuilder();
        for (int i = 0; i < stars; i++) {
            starsLine.append("*");
        }

        return String.format("Rating: %s (%d)\nAuthor: %s\nKomentar: \"%s\"\nVotes: %d",
                starsLine.toString(), stars, reviewer, content, helpfulVotes);
    }
}